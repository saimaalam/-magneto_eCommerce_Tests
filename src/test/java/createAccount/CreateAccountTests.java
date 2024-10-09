package createAccount;

import base.BaseTests;
import magneto_eCommercePages.CreateAccountPage;
import magneto_eCommercePages.MyAccountPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CreateAccountTest_DataProvider;

public class CreateAccountTests extends BaseTests {
    @Test(dataProvider = "CreateAccountData",dataProviderClass = CreateAccountTest_DataProvider.class)
    public void TC_1_User_can_create_account(String firstName, String lastName, String email,String password){
        CreateAccountPage createAccountPage= homePage.clickCreateAnAccount();
        createAccountPage.waitForHeader();
        createAccountPage.setFirstName(firstName);
        createAccountPage.setLastName(lastName);
        createAccountPage.setemail(email);
        createAccountPage.setPassword(password);
        createAccountPage.setConfirmPassword(password);
        MyAccountPage myAccountPage= createAccountPage.clickCreateAnAccountButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(myAccountPage.getMyAccountpageHeader(),"My Account");
        softAssert.assertEquals(myAccountPage.getSuccessMessage(),"Thank you for registering with Main Website Store.");
        myAccountPage.clickLogoutButton();
        softAssert.assertTrue(homePage.isSigninLinklPresent());
    }
}
