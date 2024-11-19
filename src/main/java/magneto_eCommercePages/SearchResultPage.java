package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage {
    private final WebDriver driver;
    private final By productListContainer = By.xpath("//div[@class='search results']/div[2]/ol");
    private final By productList = By.xpath("//div[@class='search results']/div[2]/ol/li");
    private final By pageTitle = By.xpath("//span[@data-ui-id='page-title-wrapper']");
    private final By noSearchResultMessage = By.xpath("//div[@class='message notice']/div");
    private final By totalNumberOfProduct = By.xpath("//div[@class='search results']/div[@class='toolbar toolbar-products'][1]/p[@id='toolbar-amount']/span");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchResultsDisplayed() {
        boolean result = driver.findElement(productListContainer).isDisplayed();
        return result;
    }

    public String getNoSearchResultMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(noSearchResultMessage)));
        String message = driver.findElement(noSearchResultMessage).getText();
        return message;
    }

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(pageTitle)));
        String title = driver.findElement(pageTitle).getText();
        return title;
    }

    public int getNumberOfProductFromToolbar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(totalNumberOfProduct)));
        int numberOfProduct = Integer.parseInt((driver.findElement(totalNumberOfProduct).getText()));
        return numberOfProduct;
    }

    public int getNumberOfProductFromSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(productListContainer)));
        int numberOfProduct = driver.findElements(productList).size();
        return numberOfProduct;
    }


}
