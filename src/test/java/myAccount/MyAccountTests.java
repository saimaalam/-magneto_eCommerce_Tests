package myAccount;

import base.BaseTests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class MyAccountTests extends BaseTests {
    @Test
    public void TC_6_Add_new_address_in_address_book(){
        ExtentTest test= extent.createTest("Verify that  user can add new address in address book");
        test.log(Status.INFO,"Click on Customer Menu");
        homePage.clickCustomerMenu();
        test.log(Status.INFO,"Click on My Account link");
        homePage.clickMyAccountLink();
    }
}
