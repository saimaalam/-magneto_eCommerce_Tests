package createAccount;

import base.BaseTests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.CreateAccountPage;
import magneto_eCommercePages.MyAccountPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CreateAccountTest_DataProvider;

public class CreateAccountTests extends BaseTests {
    @Test(description = "Verify that a new user can register",dataProvider = "CreateAccountData",dataProviderClass = CreateAccountTest_DataProvider.class)
    public void TC_1_User_can_create_account(String firstName, String lastName, String email,String password){
        String stepDescription="";
        SoftAssert softassert= new SoftAssert();
        try {
            stepDescription="Step1: Click on 'Create an Account' link";
            test.log(Status.INFO, stepDescription);
            CreateAccountPage createAccountPage = homePage.clickCreateAnAccount();
            test.pass("Step1: Passed. Create Account link clicked");

            stepDescription="Step2: Wait for loading page header";
            test.log(Status.INFO, stepDescription);
            createAccountPage.waitForHeader();
            test.pass("Step2: Passed. Page header is showing");

            stepDescription="Step3: Enter First Name: "+ firstName;
            test.log(Status.INFO, stepDescription);
            createAccountPage.setFirstName(firstName);
            test.pass("Step3: Passed. First Name is entered ");

            stepDescription="Step4: Enter Last Name: "+ lastName;
            test.log(Status.INFO, stepDescription);
            createAccountPage.setLastName(lastName);
            test.pass("Step4: Passed. Last Name is entered ");

            stepDescription="Step5: Enter Email address: " + email;
            test.log(Status.INFO, stepDescription);
            createAccountPage.setemail(email);
            test.pass("Step5: Passed. Email is entered ");

            stepDescription="Step6: Enter Password";
            test.log(Status.INFO, stepDescription);
            createAccountPage.setPassword(password);
            test.pass("Step6: Passed. Password is entered");

            stepDescription="Step7: Enter Confirm Password";
            test.log(Status.INFO, stepDescription);
            createAccountPage.setConfirmPassword(password);
            test.pass("Step7: Passed. Confirm Password is entered");

            stepDescription="Step8: Click the Create an Account button";
            test.log(Status.INFO, stepDescription);
            MyAccountPage myAccountPage = createAccountPage.clickCreateAnAccountButton();
            test.pass("Step8: Passed. Create an Account button is clicked");

            stepDescription="Step9: Validate Registration success message is showing";
            test.log(Status.INFO, stepDescription);
            String successMessage = myAccountPage.getSuccessMessage();
            if (successMessage != null && successMessage.equals("Thank you for registering with Main Website Store.")) {
                test.pass("Step9: Passed. Registration is successful. Success message is showing correctly");
            }
            else {
                test.fail("Step9: Failed.Success message is not showing correctly");
                softassert.assertEquals(successMessage,"Thank you for registering with Main Website Store.");
            }

            stepDescription="Step10: Validate My Account header is showing";
            test.log(Status.INFO, stepDescription);
            if (myAccountPage.getMyAccountpageHeader().equals("My Account")) {
                test.pass("Step10: Passed. My Account header is showing");
            } else {
                test.fail("step10: Failed. My account header is not matching.Expected: 'My Account' || Showing :" + myAccountPage.getMyAccountpageHeader());
                Assert.assertEquals(myAccountPage.getMyAccountpageHeader(),"My Account");
            }

           /* test.log(Status.INFO, "Click on logout button");
            myAccountPage.clickLogoutButton();
            test.log(Status.INFO, "Redirect to home page");
            if (homePage.isSigninLinklPresent()) {
                test.pass("User is redirect to home page");
            } else {
                test.fail("User is not redirect to home page");
            }*/
        }
        catch (AssertionError e) {
            test.log(Status.FAIL, "This test is failed due to an assertion error in "+stepDescription+"  " +e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "This test is failed due to timeout in "+stepDescription+"  "  + e.getMessage());
            throw e;

        } catch (NoSuchElementException e) {
            String conciseMessage =" - Element not found: "+e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test is failed due to an element not found in "+stepDescription+"  "  +conciseMessage);
            throw e;
        }
        catch (Exception e){
            test.log(Status.FAIL,"This test is failed due to an exception in "+stepDescription+"  "  +e.getMessage());
            throw e;
        }

    }

}
