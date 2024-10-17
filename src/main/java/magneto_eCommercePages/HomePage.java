package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By createAnAccountLink = By.linkText("Create an Account");
    private By signinLink= By.xpath("//a[@class='action skip contentarea']/following-sibling::ul/li[@class='authorization-link']/a");
    private By customerMenu=By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[@class='customer-welcome']//button");
    private By myAccountLink=By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[2]/div/ul/li/a[text()='My Account']");
    private By customerName= By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[1]/span");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public CreateAccountPage clickCreateAnAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(createAnAccountLink));
        driver.findElement(createAnAccountLink).click();
        return new CreateAccountPage(driver);
    }
    public LoginPage clickSigninLink(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(signinLink));
        driver.findElement(signinLink).click();
        return new LoginPage(driver);
    }

    public boolean isSigninLinklPresent(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signinLink)));
        boolean result = driver.findElement(signinLink).isDisplayed();
        return result;
    }
    public void clickCustomerMenu(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(customerMenu)));
        driver.findElement(customerMenu).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void clickMyAccountLink(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));
        driver.findElement(myAccountLink).click();
    }
    public String getCustomerName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(customerName)));
        return driver.findElement(customerName).getText();
    }

    public WebElement getSignInLink(){
       return driver.findElement(signinLink);
    }
}
