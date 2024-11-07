package login;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.LoginTest_DataProvider;


public class LoginTests extends BaseTests {


    @Test(description = "Validate user can login with valid credentials", dataProvider = "LoginDataProvider", dataProviderClass = LoginTest_DataProvider.class)
    public void TC_4_registered_user_can_sign_in(String email, String password, String fname, String lname) {
        String stepDescription = "";
        SoftAssert softassert = new SoftAssert();
        try {
            stepDescription = "Step1: Click on Sign-in link";
            test.log(Status.INFO, stepDescription);
            LoginPage loginPage = homePage.clickSigninLink();
            test.pass("Step1: Passed. Sign in link clicked");

            stepDescription = "Step2: Wait for page header to load";
            test.log(Status.INFO, stepDescription);
            loginPage.waitForHeader();
            test.pass("Step2: Passed. Page header is loaded");

            // Step 2: Verify the login page header
            stepDescription = "Step3: Verify login page header is showing";
            test.log(Status.INFO, stepDescription);
            String header = loginPage.getHearder();
            if (header.equals("Customer Login")) {
                test.pass("Step3: Passed. Login page header is showing correctly. " +
                        "Expected : Customer Login " + "Actual : " + header);
            } else {
                softassert.assertEquals(header, "Customer Login");
                test.fail("Step3: Failed. Header is not showing correctly. " +
                        "Expected : Customer Login " + "Actual : " + header);
            }

            // Step 3: Set email field
            stepDescription = "Step: 4 Enter email: " + email;
            test.log(Status.INFO, stepDescription);
            loginPage.setEmailField(email);
            test.pass("Step4: Passed. Email is entered");

            // Step 4: Set password field
            stepDescription = "Step4: Enter Password";
            test.log(Status.INFO, stepDescription);
            loginPage.setPasswordField(password);
            test.pass("Step5: Passed. Password is entered");

            // Step 5: Click login button
            stepDescription = "Step6: Click on login button";
            test.log(Status.INFO, stepDescription);
            loginPage.clickLoginButton();
            test.pass("Step6: Passed. login button is clicked.");

            // Step 6: Verify home page url is showing
            stepDescription = "Step7: Verify User is redirected to the homepage";
            test.log(Status.INFO, stepDescription);
            if (homePage.isHomepageUrlShowing()) {
                test.pass("Step7: Passed. User is redirect to homepage");
            } else {
                test.fail("Step7: Failed. User is not redirect to homepage");
                Assert.assertTrue(homePage.isHomepageUrlShowing());
            }
            softassert.assertAll();

        } catch (AssertionError e) {
            takeFailedStepScreenshot("Assertion_Error");
            test.log(Status.FAIL, "This test is failed due to an assertion error in " + stepDescription + "  " + e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            takeFailedStepScreenshot("Timeout_Error");
            test.log(Status.FAIL, "This test is failed due to timeout in " + stepDescription + "  " + e.getMessage());
            throw e;

        } catch (NoSuchElementException e) {
            takeFailedStepScreenshot("NoSuchElement_Error");
            String conciseMessage = " - Element not found: " + e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test is failed due to an element not found in " + stepDescription + "  " + conciseMessage);
            throw e;
        } catch (Exception e) {
            takeFailedStepScreenshot("Exception_Error");
            test.log(Status.FAIL, "This test is failed due to an exception in " + stepDescription + "  " + e.getMessage());
            throw e;
        }
    }
}
