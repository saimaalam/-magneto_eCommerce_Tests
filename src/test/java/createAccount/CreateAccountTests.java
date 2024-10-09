package createAccount;

import base.BaseTests;
import magneto_eCommercePages.CreateAccountPage;
import org.testng.annotations.Test;
import utils.CreateAccountTest_DataProvider;

public class CreateAccountTests extends BaseTests {
    @Test(dataProvider = "CreateAccountData",dataProviderClass = CreateAccountTest_DataProvider.class)
    public void TC_1_User_can_create_account(String firstName, String lastName, String email,String password){
        CreateAccountPage createAccountPage= homePage.clickCreateAnAccount();
        createAccountPage.waitForHeader();
        createAccountPage.setFirstName(firstName);
        createAccountPage.setLastName(lastName);

    }
}
