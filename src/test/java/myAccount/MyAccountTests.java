package myAccount;

import base.BaseTests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import login.LoginTests;
import magneto_eCommercePages.MyAccountPage;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.AddNewAddress_DataProvider;
import utils.LoginTest_DataProvider;
import utils.TestDataStorage;


public class MyAccountTests extends BaseTests {

    private MyAccountPage myAccountPage;
    @Test(dataProvider = "LoginDatafromConfiguration",dataProviderClass = LoginTest_DataProvider.class,description = "login before a test execution")
    public void loginBeforeTest(String email, String password, String fname, String lname) {
        test.log(Status.INFO, "login with "+"Email: "+email );
        myAccountPage = loginUser(email, password);
    }

    @Test(description = "Verify that user can add new address in address book",dataProvider = "AddNewAddressData",dataProviderClass = AddNewAddress_DataProvider.class,dependsOnMethods ={"loginBeforeTest"} )
    public void TC_6_Add_new_address_in_address_book(String phoneNumber,String streetAddress1,String city,String zipCode,String state,String country) {
        //ExtentTest test = extent.createTest("Verify that user can add new address in address book");
        test.log(Status.INFO, "Click on the 'Address Book' link ");
        myAccountPage.clickAddressBookLink();
        test.log(Status.INFO,"Enter Phone Number");
         myAccountPage.setPhoneNumber(phoneNumber);
        test.log(Status.INFO, "Enter First Street Address");
        myAccountPage.setStreetAddress1(streetAddress1);
        test.log(Status.INFO, "Enter City");
        myAccountPage.setCity(city);
        test.log(Status.INFO, "Enter Zip/Postal Code");
        myAccountPage.setZipCode(zipCode);
        test.log(Status.INFO, "Enter Country");
        myAccountPage.selectCountry(country);
        test.log(Status.INFO, "Enter State/Province");
        myAccountPage.selectState(state);
        test.log(Status.INFO, "Click on 'Save Address' Button ");
        myAccountPage.clickSaveAddressButton();
        test.log(Status.INFO,"Validating the Success message: 'You saved the address.'");
        if(myAccountPage.getSuccessMessage().equals("You saved the address.")){
            test.pass("Address is added Successfully. Success message is showing correctly");
        }
        else
        {
            test.fail(" Test Failed: Failed to add New Address");
        }


    }
}
