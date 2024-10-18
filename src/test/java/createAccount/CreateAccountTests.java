package createAccount;

import base.BaseTests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.CreateAccountPage;
import magneto_eCommercePages.MyAccountPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CreateAccountTest_DataProvider;

public class CreateAccountTests extends BaseTests {
    @Test(dataProvider = "CreateAccountData",dataProviderClass = CreateAccountTest_DataProvider.class)
    public void TC_1_User_can_create_account(String firstName, String lastName, String email,String password){
        ExtentTest test = extent.createTest("Verify that a new user can register").assignAuthor("Saima").assignCategory("Smoke").assignDevice("Chrome");
        test.log(Status.INFO,"Click on 'Create an Account' link");
        CreateAccountPage createAccountPage= homePage.clickCreateAnAccount();
        createAccountPage.waitForHeader();
        test.log(Status.INFO,"Enter First Name " +firstName);
        createAccountPage.setFirstName(firstName);
        test.log(Status.INFO,"Enter Last Name " +lastName);
        createAccountPage.setLastName(lastName);
        test.log(Status.INFO,"Enter Email address "+email);
        createAccountPage.setemail(email);
        test.log(Status.INFO,"Enter Password");
        createAccountPage.setPassword(password);
        test.log(Status.INFO,"Enter Confirm Password" +password);
        createAccountPage.setConfirmPassword(password);
        test.log(Status.INFO,"Click the Create an Account button");
        MyAccountPage myAccountPage= createAccountPage.clickCreateAnAccountButton();
        test.log(Status.INFO,"Validate Registration success message is showing");
        String successMessage= myAccountPage.getSuccessMessage();
        if(successMessage != null && successMessage.equals("Thank you for registering with Main Website Store.")){
            test.pass("Registration is successful. Success message is showing correctly");
        }
        else {
            test.fail("Success message is not showing");
        }
        test.log(Status.INFO,"Validate My Account header is showing");
        if(myAccountPage.getMyAccountpageHeader().equals("My Account"))
        {
            test.pass("My Account header is showing");
        }
        else
        {
            test.fail("My account header is not matching.Expected: 'My Account' || Showing :"+myAccountPage.getMyAccountpageHeader());
        }

        test.log(Status.INFO,"Click on logout button");
        myAccountPage.clickLogoutButton();
        test.log(Status.INFO,"Redirect to home page");
        if(homePage.isSigninLinklPresent()){
            test.pass("User is redirect to home page");
        }
        else {
            test.fail("User is not redirect to home page");
        }

    }
}
