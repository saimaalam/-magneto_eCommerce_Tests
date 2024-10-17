package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.LoginTest_DataProvider;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTests {
    protected HomePage homePage;
    private WebDriver driver;
    private TakesScreenshot capture;
    protected static ExtentReports extent;
    protected static ExtentSparkReporter spark;


   @BeforeSuite
   public void setupReport(){
       extent = new ExtentReports();
       spark = new ExtentSparkReporter("index.html");
       spark.config().setTheme(Theme.DARK);
       spark.config().setDocumentTitle("Magneto eCommerce Test Results");
       spark.config().setReportName("TestReports");
       extent.attachReporter(spark);
   }
    @BeforeClass
    public void setUrl() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        /*switch (browserName){
        case "chrome":
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            break;
        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
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
            driver = new ChromeDriver();
    }*/
        ExtentTest test = extent.createTest("navigateToUrl");
        test.pass("Navigate to the homepage");
        driver.get("https://magento.softwaretestingboard.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        test.pass("Maximize the window");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }
    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            capture = (TakesScreenshot) driver;
            File screenshot = capture.getScreenshotAs(OutputType.FILE);
            File destination = new File("screenshots/"+ result.getName()+ ".png");
            try {
                Files.move(screenshot,destination);
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
        HomePage home= loginPage.clickLoginButton();
        home.clickCustomerMenu();
        home.clickMyAccountLink();
        return new MyAccountPage(driver);
    }
}

