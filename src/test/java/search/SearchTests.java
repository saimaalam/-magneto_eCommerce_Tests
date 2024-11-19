package search;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.SearchResultPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.LoginTest_DataProvider;
import utils.TestDataStorage;

public class SearchTests extends BaseTests {

    @Test(description = "Verify that user can search for a product")
    public void TC_8_user_can_search_for_a_product() {

        String stepDescription = "";
        SoftAssert softAssert= new SoftAssert();
        String product= BaseTests.productName;//replace it with data-provider/read from property file

        try {
            // Step 1: Search for a 'Product Name' into the search bar
            stepDescription = "Step 1: Search for a 'Product Name' using the search bar";
            test.log(Status.INFO, stepDescription);
            SearchResultPage searchResultPage= homePage.searchProduct(product);
            test.pass("Step 1: Passed.Search button is clicked");

            // Step 2: Verify that the search result is displayed
            stepDescription = "Verify that the search result is displayed";
            test.log(Status.INFO, stepDescription);
            if(searchResultPage.isSearchResultsDisplayed()){
                test.pass("Step 2: Passed. Search result is displayed");
            }
            else {
                test.fail("Step 2: Failed. No Search result is showing");
                softAssert.assertTrue(searchResultPage.isSearchResultsDisplayed());
            }

            // Step 3: Verify the correct header is showing
            stepDescription = "Step 3: Verify the correct header is showing";
            test.log(Status.INFO, stepDescription);
            String expeactedHeader= "Search results for: "+"'"+productName+"'";
            if (searchResultPage.getPageTitle().equals(expeactedHeader)) {
                test.pass("Step 3: Passed. Correct header is showing");
            } else {
                test.fail("Step 3: Failed. Header is not showing correctly.");
                softAssert.assertEquals(searchResultPage.getPageTitle(),expeactedHeader);
            }

            // Step 4: Verify the number of items showing in the toolbar matches with total number of product showing as a search result
            stepDescription = "Step 4: Verify the number of items showing in the toolbar matches with total number of product showing as a search result";
            test.log(Status.INFO, stepDescription);
            if (searchResultPage.getNumberOfProductFromToolbar()==searchResultPage.getNumberOfProductFromSearchResult()) {
                test.pass("Step 4: Passed. Number of items showing in the toolbar matched with total number of product showing as a search result");
            } else {
                test.fail("Step 4: Failed.Number of items showing in the toolbar is not matching with total number of product showing as a search result ");
                softAssert.assertEquals(searchResultPage.getNumberOfProductFromToolbar(),searchResultPage.getNumberOfProductFromSearchResult());
            }

            softAssert.assertAll();

        } catch (AssertionError e) {
            takeFailedStepScreenshot("Assertion_Error");
            test.log(Status.FAIL, "This test failed due to an assertion error in " + stepDescription + ": " + e.getMessage());
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
