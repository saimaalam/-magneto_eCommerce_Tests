package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateAccountPage {
    private WebDriver driver;
    private By createAccountPageHeader = By.xpath("//h1[@class='page-title']/span");
    private By firstNameField= By.id("firstname");
    private By lastNameField= By.id("lastname");
    private By emailField= By.id("email_address");
    private By passwordField= By.id("password");
    private By confirmPasswordField= By.id("password-confirmation");
    private By continueButton= By.xpath("//button[@title='Create an Account']");

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForHeader(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(createAccountPageHeader));
    }
    public void setFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void setLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void setemail(String email){
        driver.findElement(emailField).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void setConfirmPassword(String password){
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}
