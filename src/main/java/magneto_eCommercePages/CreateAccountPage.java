package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateAccountPage {
    private WebDriver driver;
    private final By createAccountPageHeader = By.xpath("//h1[@class='page-title']/span");
    private final By firstNameField= By.id("firstname");
    private final By lastNameField= By.id("lastname");
    private final By emailField= By.id("email_address");
    private final By passwordField= By.id("password");
    private final By confirmPasswordField= By.id("password-confirmation");
    private final By createAnAccountButton= By.xpath("//button[@title='Create an Account']");

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForHeader(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(createAccountPageHeader));
    }
    private void scrollToElement(WebElement element) {
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) driver).executeScript(script, element);
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
        WebElement emailfield= driver.findElement(emailField);
        scrollToElement(emailfield);
        emailfield.sendKeys(email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void setPassword(String password){
        WebElement passwordfield= driver.findElement(passwordField);
        scrollToElement(passwordfield);
        passwordfield.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void setConfirmPassword(String password){
        WebElement confirmpasswordfield=driver.findElement(confirmPasswordField);
        scrollToElement(confirmpasswordfield);
        confirmpasswordfield.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public MyAccountPage clickCreateAnAccountButton(){
        WebElement createbutton= driver.findElement(createAnAccountButton);
        scrollToElement(createbutton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        createbutton.click();
        return new MyAccountPage(driver);
    }

}
