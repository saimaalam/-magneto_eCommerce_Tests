package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By createAnAccountLink = By.linkText("Create an Account");
    private By signinLink= By.xpath("//a[@class='action skip contentarea']/following-sibling::ul/li[@class='authorization-link']/a");
    private By customerMenu=By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[@class='customer-welcome']//button");
    private By myAccountLink=By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[2]/div/ul/li/a[text()='My Account']");

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
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
    public MyAccountPage clickMyAccountLink(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));
        driver.findElement(myAccountLink).click();
        return new MyAccountPage(driver);
    }

}
