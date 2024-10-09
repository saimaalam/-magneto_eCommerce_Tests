package login;

import base.BaseTests;
import magneto_eCommercePages.LoginPage;
import magneto_eCommercePages.MyAccountPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.LoginTest_DataProvider;

public class LoginTests extends BaseTests {
    @Test(dataProvider = "LoginData",dataProviderClass = LoginTest_DataProvider.class)
    public void TC_4_registered_user_can_sign_in(String email,String password,String fname, String lname){
       LoginPage loginPage= homePage.clickSigninLink();
       loginPage.waitForHeader();
       SoftAssert softAssert= new SoftAssert();
       softAssert.assertEquals(loginPage.getHearder(),"Customer Login");
       loginPage.setEmailField(email);
       loginPage.setPasswordField(password);
       MyAccountPage myAccountPage= loginPage.clickLoginButton();
       softAssert.assertEquals(myAccountPage.getMyAccountpageHeader(),"My Account");
       softAssert.assertEquals(myAccountPage.getCustomerName(),"Welcome, "+fname+" "+lname+"!");
    }
}
