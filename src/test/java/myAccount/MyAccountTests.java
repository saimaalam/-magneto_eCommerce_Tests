package myAccount;

import base.BaseTests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import login.LoginTests;
import magneto_eCommercePages.MyAccountPage;
import net.bytebuddy.build.Plugin;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.AddNewAddress_DataProvider;
import utils.LoginTest_DataProvider;
import utils.TestDataStorage;


public class MyAccountTests extends BaseTests {

    private MyAccountPage myAccountPage;
    @Test(dataProvider = "LoginDatafromConfiguration",dataProviderClass = LoginTest_DataProvider.class)
    public void loginBeforeTest(String email, String password, String fname, String lname) {
        myAccountPage = loginUser(email, password);
    }

    @Test(dataProvider = "AddNewAddressData",dataProviderClass = AddNewAddress_DataProvider.class,dependsOnMethods ={"loginBeforeTest"} )
    public void TC_6_Add_new_address_in_address_book(String phoneNumber,String streetAddress1,String city,String zipCode,String state,String country) {
        ExtentTest test = extent.createTest("Verify that user can add new address in address book");
        test.log(Status.INFO, "Navigate to Address Book");
        myAccountPage.clickAddressBookLink();
        test.log(Status.INFO, "Enter Phone Number");
        myAccountPage.setPhoneNumber(phoneNumber);
    }
}
