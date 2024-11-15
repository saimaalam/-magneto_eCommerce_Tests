package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import magneto_eCommercePages.HomePage;
import magneto_eCommercePages.LoginPage;
import magneto_eCommercePages.MyAccountPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;

public class BaseTests {
    protected static ExtentReports extent;
    protected static ExtentSparkReporter spark;
    protected static String dataProviderName;
    protected HomePage homePage;
    protected ExtentTest test;
    private WebDriver driver;
    private TakesScreenshot capture;
    private String browser;
    private boolean isHeadless;
    protected String dataProviderSource;


    public void loadConfig() {
        Properties properties = new Properties();
        String projectPath = System.getProperty("user.dir");
        try {
            InputStream input = new FileInputStream(projectPath + "/src/main/java/config/config.propertise");
            properties.load(input);
            browser = properties.getProperty("browser").toLowerCase();
            isHeadless = Boolean.parseBoolean(properties.getProperty("headless", "false"));
            System.out.println("Browser: " + browser + ", Headless: " + isHeadless);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return options;
    }

    @BeforeSuite
    public void setupReport() {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy_HH_mm_ss"));
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("report/ExtentReport_" + timeStamp + ".html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Magneto eCommerce Test Results");
        spark.config().setReportName("TestReports");
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setUrl() {
        loadConfig();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
        }

        driver.get("https://magento.softwaretestingboard.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void setTestData(Method method) {
        test = extent.createTest(method.getAnnotation(Test.class).description()).assignDevice(browser);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy_HH_mm_ss"));
        if (ITestResult.FAILURE == result.getStatus()) {
            capture = (TakesScreenshot) driver;
            File screenshot = capture.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/screenshots/" + result.getName() + "_" + timeStamp + ".png");
            try {
                Files.move(screenshot, destination);
                test.fail("Test step failed. See screenshot below:",
                        MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/screenshots/" + result.getName() + "_" + timeStamp + ".png").build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void quitBrowser() {
        driver.quit();
    }

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    protected MyAccountPage loginUser(String email, String password) {
        LoginPage loginPage = homePage.clickSigninLink();
        loginPage.setEmailField(email);
        loginPage.setPasswordField(password);
        HomePage home = loginPage.clickLoginButton();
        home.clickCustomerMenu();
        home.clickMyAccountLink();
        return new MyAccountPage(driver);
    }

    public void takeFailedStepScreenshot(String stepName) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy_HH_mm_ss"));
        capture = (TakesScreenshot) driver;
        File screenshot = capture.getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + stepName + "_" + timeStamp + ".png";
        File destination = new File(screenshotPath);
        try {
            Files.move(screenshot, destination);
            test.fail("Screenshot attached for failed step:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

