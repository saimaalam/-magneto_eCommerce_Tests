package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {
    private WebDriver driver;
    private final By successMassage= By.xpath("//div[@class='message-success success message']//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    private final By MyAccountpageHeader= By.className("base");
    private final By dropdownMenu= By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[@class='customer-welcome']//button");
   private final By logoutButton=By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[2]/div/ul/li[@class='authorization-link']/a");
   private By customerName= By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[@class='greet welcome']/span");
    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

   public String getMyAccountpageHeader(){
       WebElement message= driver.findElement(successMassage);
       String text="";
       wait.until(ExpectedConditions.visibilityOf(message));
       if(message.isDisplayed()){
           text= message.getText();
       }
       else
       {
           text = "Header is not displayed";
       }
       return text;

   }
    public String getSuccessMessage(){
        return driver.findElement(successMassage).getText();
    }
    public HomePage clickLogoutButton(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dropdownMenu)));
        driver.findElement(dropdownMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(logoutButton)));
        driver.findElement(logoutButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return new HomePage(driver);
    }
    public String getCustomerName(){
        return driver.findElement(customerName).getText();
    }

}
