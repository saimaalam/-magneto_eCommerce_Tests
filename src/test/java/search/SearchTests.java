package search;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LoginTest_DataProvider;
import utils.TestDataStorage;

public class SearchTests extends BaseTests {

    @Test(description = "Verify that user can search for a product")
    public void TC_8_user_can_search_for_a_product() {

        String stepDescription = "";
        String productName="Tea";

        try {
            // Step 1: Search for a 'Product Name' into the search bar
            stepDescription = "Step 1: Search for a 'Product Name' into the search bar";
            test.log(Status.INFO, stepDescription);
            homePage.searchProduct(productName);
            test.pass("Step 1: Passed.Search button is clicked");

            // Step 2: Verify user is redirected to the homepage
            stepDescription = "Step 2: Verify user is redirected to the homepage";
            test.log(Status.INFO, stepDescription);
            if (homePage.isHomepageUrlShowing()) {
                test.pass("Step 2: Passed. User is redirected to homepage");
            } else {
                test.fail("Step 2: Failed. User is not redirected to homepage");
                Assert.assertTrue(homePage.isHomepageUrlShowing(), "User was not redirected to homepage after logout");
            }

        } catch (AssertionError e) {
            takeFailedStepScreenshot("Assertion_Error");
            test.log(Status.FAIL, "This test failed due to an assertion error in " + stepDescription + ": " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (TimeoutException e) {
            takeFailedStepScreenshot("Timeout_Error");
            test.log(Status.FAIL, "This test failed due to a timeout in " + stepDescription + ": " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (NoSuchElementException e) {
            takeFailedStepScreenshot("NoSuchElement_Error");
            String conciseMessage = " - Element not found: " + e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test failed due to an element not found in " + stepDescription + ": " + conciseMessage);
            throw e;
        } catch (Exception e) {
            takeFailedStepScreenshot("Exception_Error");
            test.log(Status.FAIL, "This test failed due to an exception in " + stepDescription + ": " + e.getMessage().split("\n")[0]);
            throw e;
        }
    }

}
