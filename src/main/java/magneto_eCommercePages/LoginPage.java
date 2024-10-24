package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private By emailField= By.id("email");
    private By passwordField= By.id("pass");
    private By signinButton= By.id("send2");
    private By loginpageHeader=By.xpath("//h1[@class='page-title']/span");
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public String getHearder(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginpageHeader)));
        String header="";
        if(driver.findElement(loginpageHeader).isDisplayed()){
             header = driver.findElement(loginpageHeader).getText();
        }
        else {
            header= "Login page header is not displayed";
        }
        return header;
    }
    public void waitForHeader(){
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
    public HomePage clickLoginButton(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(signinButton)));
        Actions action= new Actions(driver);
        action.moveToElement(driver.findElement(signinButton)).click().perform();
        return new HomePage(driver);
    }
}
