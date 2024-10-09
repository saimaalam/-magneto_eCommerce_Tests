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
}
