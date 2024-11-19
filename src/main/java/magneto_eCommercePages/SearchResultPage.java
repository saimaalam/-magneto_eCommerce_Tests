package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    private final WebDriver driver;
    private final By productListContainer = By.xpath("//div[@class='search results']/div[2]/ol");
    private final By productList = By.xpath("//div[@class='search results']/div[2]/ol/li");
    private final By pageTitle = By.xpath("//span[@data-ui-id='page-title-wrapper']");
    private final By noSearchResultMessage = By.xpath("//div[@class='message notice']/div");
    private final By totalNumberOfProduct = By.xpath("//div[@class='search results']/div[@class='toolbar toolbar-products'][1]/p[@id='toolbar-amount']/span");
    private final By pagination = By.xpath("//div[@class='search results']/div[@class='toolbar toolbar-products'][2]/div[@class='pages']");
    private final By paginationList = By.xpath("//div[@class='search results']/div[@class='toolbar toolbar-products'][2]/div[@class='pages']/ul/li");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchResultsDisplayed() {
        return driver.findElement(productListContainer).isDisplayed();
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
        List<WebElement> numberList = driver.findElements(totalNumberOfProduct);
        int numberOfProduct = 0;
        if (!numberList.isEmpty() && numberList.size() > 1) {
            numberOfProduct = Integer.parseInt(numberList.getLast().getText());
        }
        if (numberList.size() == 1) {
            numberOfProduct = Integer.parseInt(numberList.getFirst().getText());
        }
        System.out.println("Toolbar: "+numberOfProduct);
        return numberOfProduct;
    }

    public int getNumberOfProductFromSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(productListContainer)));
        int numberOfProduct = driver.findElements(productList).size();
        if (driver.findElement(pagination).isDisplayed()) {
            List<WebElement> paginationLinks = driver.findElements(paginationList);
            int numberOfPage = paginationLinks.size();
            for (int i = 1; i < numberOfPage - 1; i++) {
                paginationLinks.get(numberOfPage - 1).click();
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(productListContainer)));
                numberOfProduct += driver.findElements(productList).size();
            }
        } else {
            numberOfProduct = driver.findElements(productList).size();
        }
        System.out.println("SearchResult: "+numberOfProduct);
        return numberOfProduct;
    }
}

