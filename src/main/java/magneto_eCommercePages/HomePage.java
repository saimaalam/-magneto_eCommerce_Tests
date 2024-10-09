package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By createAnAccountLink = By.linkText("Create an Account");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public CreateAccountPage clickCreateAnAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(createAnAccountLink));
        driver.findElement(createAnAccountLink).click();
        return new CreateAccountPage(driver);
    }
}
