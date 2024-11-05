package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriverWait wait;
    private final WebDriver driver;
    private final By emailField = By.id("email");
    private final By passwordField = By.id("pass");
    private final By signinButton = By.id("send2");
    private final By loginpageHeader = By.xpath("//h1[@class='page-title']/span");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public String getHearder() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginpageHeader)));
        String header = "";
        if (driver.findElement(loginpageHeader).isDisplayed()) {
            header = driver.findElement(loginpageHeader).getText();
        } else {
            header = "Login page header is not displayed";
        }
        return header;
    }

    public void waitForHeader() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginpageHeader));
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public HomePage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(signinButton)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signinButton));
        driver.findElement(signinButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return new HomePage(driver);
    }
}
