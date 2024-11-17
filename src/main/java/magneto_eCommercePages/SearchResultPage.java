package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage {
    private WebDriver driver;
    private By productList= By.className("products list items product-items");
    private By pageTitle = By.xpath("//h1[@class='page-title']/span");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isSearchResultsDisplayed() {
        return driver.findElement(productList).isDisplayed();
    }
    public String getPageTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(pageTitle)));
        String title= driver.findElement(pageTitle).getText();
        return title;
    }
}
