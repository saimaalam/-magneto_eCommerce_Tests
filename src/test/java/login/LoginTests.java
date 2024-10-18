package login;

import base.BaseTests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.LoginTest_DataProvider;

public class LoginTests extends BaseTests {
    @Test(dataProvider = "LoginDatafromRegistration",dataProviderClass = LoginTest_DataProvider.class)
    public void TC_4_registered_user_can_sign_in(String email,String password,String fname, String lname){
        ExtentTest test = extent.createTest("Verify that registered user can Sign in").assignAuthor("Saima").assignCategory("Smoke").assignDevice("Chrome");
        try {
            // Step 1: Click on Sign-in link
            test.log(Status.INFO, "Clicking on Sign-in link");
            LoginPage loginPage = homePage.clickSigninLink();
            test.pass("Sign in link clicked");
            test.log(Status.INFO,"Waiting for page header to load");
             loginPage.waitForHeader();

            // Step 2: Verify the login page header
            test.log(Status.INFO, "Verifying login page header");
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(loginPage.getHearder(), "Customer Login", "Header mismatch");
            if(loginPage.getHearder().equals("Customer Login")) {
                test.pass("Login page header is displayed correctly.");
            }
            else {
                test.fail("Header mismatch. Expeacted: Customer Login Actual :" +loginPage.getHearder());
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
            test.log(Status.INFO, "sign in link not showing");
            if (homePage.getSignInLink().isDisplayed()) {
                test.fail("login is not successful");
            } else {
                test.pass("Login successful");
            }

            // Assert all
            softAssert.assertAll();
        } catch (AssertionError e) {
            test.fail("Test failed due to an assertion error: " + e.getMessage());
            throw e;  // Ensure test fails if assertions are not met
        } catch (Exception e) {
            test.fail("Test encountered an exception: " + e.getMessage());
            throw e;  // Ensure test fails on unexpected exceptions
        }
    }
}
