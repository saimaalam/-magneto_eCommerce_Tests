package login;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LoginTest_DataProvider;

public class LoginTests extends BaseTests {
    @Test(description = "Validate user can login with valid credentials", dataProvider = "LoginDatafromConfiguration", dataProviderClass = LoginTest_DataProvider.class)
    public void TC_4_registered_user_can_sign_in(String email, String password, String fname, String lname) {
        try {
            test.log(Status.INFO, "Clicking on Sign-in link");
            LoginPage loginPage = homePage.clickSigninLink();
            test.pass("Sign in link clicked");
            test.log(Status.INFO, "Waiting for page header to load");
            loginPage.waitForHeader();

            // Step 2: Verify the login page header
            test.log(Status.INFO, "Verifying login page header is showing");
            String header = loginPage.getHearder();
            if (header.equals("Customer Login")) {
                test.pass("Login page header is showing correctly." +
                        "Expected : Customer Login" + "Actual : " + header);
            } else {
                test.fail("Header is not showing correctly. " +
                        "Expected : Customer Login " + "Actual : " + header);
            }

            // Step 3: Set email field
            test.log(Status.INFO, "Entering email: " + email);
            loginPage.setEmailField(email);

            // Step 4: Set password field
            test.log(Status.INFO, "Entering password.");
            loginPage.setPasswordField(password);

            // Step 5: Click login button and verify account page
            test.log(Status.INFO, "Clicking login button");
            loginPage.clickLoginButton();
            test.pass(" login button clicked.");

            // Step 6: Verify sign in link not showing
            test.log(Status.INFO, "Verify User is redirected to homepage ");
            if (homePage.isHomepageUrlShowing()) {
                test.pass("User is in homepage. Login is successful");
                Assert.assertTrue(homePage.isHomepageUrlShowing());
            } else {
                test.fail("Login is not successful");
            }

        } catch (AssertionError e) {
            test.log(Status.FAIL, "This test is failed due to an assertion error : " + e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "This test is failed due to timeout: " + e.getMessage());
            throw e;

        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "This test is failed due to element not found: " + e.getMessage());
            throw e;
        }
    }
}
