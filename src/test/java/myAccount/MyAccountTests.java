package myAccount;

import base.BaseTests;
import org.testng.annotations.Test;

public class MyAccountTests extends BaseTests {
    @Test
    public void TC_6_Add_new_address_in_address_book(){
        homePage.clickCustomerMenu();
        homePage.clickMyAccountLink();

    }
}
