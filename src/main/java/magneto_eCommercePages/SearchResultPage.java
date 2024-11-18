package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private By productListContainer= By.className("products list items product-items");
    private By productList=By.className("item product product-item");
    private By pageTitle = By.xpath("//span[@data-ui-id='page-title-wrapper']");
    private By noSearchResultMessage= By.xpath("//div[@class='message notice']/div");
    private By totalNumberOfProduct= By.xpath("//div[@class='search results']/div[@class='toolbar toolbar-products'][1]/p[@id='toolbar-amount']/span");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isSearchResultsDisplayed() {
        boolean result=false;

        if(driver.findElement(productListContainer).isDisplayed()){
            result=true;
        }
        if(driver.findElement(noSearchResultMessage).isDisplayed()){
            result =false;
        }
        return result;
    }
    public String getNoSearchResultMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(noSearchResultMessage)));
        String message= driver.findElement(noSearchResultMessage).getText();
        return message;
    }
    public String getPageTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(pageTitle)));
        String title= driver.findElement(pageTitle).getText();
        return title;
    }
    public int getNumberOfProductFromToolbar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(totalNumberOfProduct)));
        int numberOfProduct= Integer.parseInt((driver.findElement(totalNumberOfProduct).getText()));
        return numberOfProduct;
    }
    public int getNumberOfProductFromSearchResult(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(productListContainer)));
        int numberOfProduct= driver.findElements(productList).size();
        return numberOfProduct;
    }


}
