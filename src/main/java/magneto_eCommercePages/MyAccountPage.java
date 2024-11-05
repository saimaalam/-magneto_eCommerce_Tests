package magneto_eCommercePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyAccountPage {
    private final By successMassage = By.xpath("//div[@class='message-success success message']//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    private final By MyAccountpageHeader = By.className("base");
    private final By dropdownMenu = By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[@class='customer-welcome']//button");
    private final By logoutButton = By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[2]/div/ul/li[@class='authorization-link']/a");
    WebDriverWait wait;
    private final WebDriver driver;
    private final By customerName = By.xpath("//a[@class='action skip contentarea']/following::ul[1]/li[@class='greet welcome']/span");
    private final By addressBookLink = By.linkText("Address Book");
    private final By phoneNumberField = By.id("telephone");
    private final By streetAddress1Field = By.id("street_1");
    private final By cityField = By.id("city");
    private final By zipField = By.id("zip");
    private final By stateDropdown = By.id("region_id");
    private final By countryDropdown = By.id("country");
    private final By saveAddressButton = By.xpath("//button[@title='Save Address']");
    private final By addNewAddressButton = By.xpath("//button[@title='Add New Address']");

    public MyAccountPage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public String getMyAccountpageHeader() {
        WebElement message = driver.findElement(MyAccountpageHeader);
        String text = "";
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MyAccountpageHeader)));
        if (message.isDisplayed()) {
            text = message.getText();
        } else {
            text = "Header is not displayed";
        }
        return text;

    }

    public String getSuccessMessage() {
        return driver.findElement(successMassage).getText();
    }

    public HomePage clickLogoutButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dropdownMenu)));
        driver.findElement(dropdownMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(logoutButton)));
        driver.findElement(logoutButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return new HomePage(driver);
    }

    public String getCustomerName() {
        return driver.findElement(customerName).getText();
    }

    private void scrollToElement(WebElement element) {
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void clickAddressBookLink() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(addressBookLink)));
        driver.findElement(addressBookLink).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        List<WebElement> addressButton = driver.findElements(addNewAddressButton);
        if (!addressButton.isEmpty()) {
            System.out.println("default address is present");
            scrollToElement(driver.findElement(addNewAddressButton));
            driver.findElement(addNewAddressButton).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(phoneNumberField)));
            scrollToElement(driver.findElement(phoneNumberField));
        } else {
            System.out.println("No default address found. Entering new address details.");
        }
    }

    public void setPhoneNumber(String phone) {
        wait.until(ExpectedConditions.presenceOfElementLocated(phoneNumberField));
        scrollToElement(driver.findElement(phoneNumberField));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(phoneNumberField).sendKeys(phone);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    public void setStreetAddress1(String street1) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(streetAddress1Field)));
        scrollToElement(driver.findElement(streetAddress1Field));
        driver.findElement(streetAddress1Field).sendKeys(street1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void setCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(cityField)));
        scrollToElement(driver.findElement(cityField));
        driver.findElement(cityField).sendKeys(city);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void setZipCode(String zip) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(zipField)));
        scrollToElement(driver.findElement(zipField));
        driver.findElement(zipField).sendKeys(zip);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void selectCountry(String country) {
        scrollToElement(driver.findElement(countryDropdown));
        Select dropdown = new Select(driver.findElement(countryDropdown));
        dropdown.selectByVisibleText(country);
        System.out.println("Country: " + dropdown.getFirstSelectedOption().getText());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void selectState(String state) {
        scrollToElement(driver.findElement(stateDropdown));
        Select dropdown = new Select(driver.findElement(stateDropdown));
        dropdown.selectByVisibleText(state);
        System.out.println("State: " + dropdown.getFirstSelectedOption().getText());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void clickSaveAddressButton() {
        scrollToElement(driver.findElement(saveAddressButton));
        driver.findElement(saveAddressButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }


}
