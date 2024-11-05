package myAccount;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import magneto_eCommercePages.MyAccountPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AddNewAddress_DataProvider;
import utils.LoginTest_DataProvider;


public class MyAccountTests extends BaseTests {

    private MyAccountPage myAccountPage;

    @Test(dataProvider = "LoginDatafromRegistration", dataProviderClass = LoginTest_DataProvider.class, description = "login before a test execution")
    public void loginBeforeTest(String email, String password, String fname, String lname) {
        try {
            test.log(Status.INFO, "login with " + "Email: " + email);
            myAccountPage = loginUser(email, password);
            if(myAccountPage.getMyAccountpageHeader().equals("My Account")){
                test.pass("User have logged in");
            }
            else {
                test.fail("Login failed");
            }
        } catch (Exception e) {
            test.fail("User can not login due to " + e.getMessage().split("\n")[0]);
        }

    }

    @Test(description = "Verify that user can add new address in the address book", dataProvider = "AddNewAddressData", dataProviderClass = AddNewAddress_DataProvider.class, dependsOnMethods = {"loginBeforeTest"})
    public void TC_6_Add_new_address_in_address_book(String phoneNumber, String streetAddress1, String city, String zipCode, String state, String country) {
        String stepDescription = "";
        try {
            stepDescription = "Step:1 Click on the 'Address Book' link";
            test.log(Status.INFO, stepDescription);
            myAccountPage.clickAddressBookLink();
            test.pass("Step1: Passed. Address book link is clicked");

            stepDescription = "Step:2 Enter Phone Number: " + phoneNumber;
            test.log(Status.INFO, stepDescription);
            myAccountPage.setPhoneNumber(phoneNumber);
            test.pass("Step2: Passed. Phone Number is entered");

            stepDescription = "Step:3 Enter First Street Address: " + streetAddress1;
            test.log(Status.INFO, stepDescription);
            myAccountPage.setStreetAddress1(streetAddress1);
            test.pass("Step3: Passed. Street address is entedred");

            stepDescription = "Step:4 Enter City: " + city;
            test.log(Status.INFO, stepDescription);
            myAccountPage.setCity(city);
            test.pass("Step4: Passed. City is entered");

            stepDescription = "Step:5 Enter Zip/Postal Code: " + zipCode;
            test.log(Status.INFO, stepDescription);
            myAccountPage.setZipCode(zipCode);
            test.pass("Step5: Passed. Zip code is entered");

            stepDescription = "Step:6 Select Country: " + country;
            test.log(Status.INFO, stepDescription);
            myAccountPage.selectCountry(country);
            test.pass("Step6: Passed. Country is selected");

            stepDescription = "Step:7 Enter State/Province: " + state;
            test.log(Status.INFO, stepDescription);
            myAccountPage.selectState(state);
            test.pass("Step7: Passed. State/Province is selected");

            stepDescription = "Step:8 Click on 'Save Address' Button";
            test.log(Status.INFO, stepDescription);
            myAccountPage.clickSaveAddressButton();
            test.pass("Step8: Passed. Save Address button is clicked");

            stepDescription = "Step:9 Validating the Success message: 'You saved the address.'";
            test.log(Status.INFO, stepDescription);
            if (myAccountPage.getSuccessMessage().equals("You saved the address.")) {
                test.pass("Step9: Passed. Address is added Successfully. Success message is showing correctly");
            } else {
                test.fail("Step9: Failed. Failed to add New Address");
                Assert.assertEquals(myAccountPage.getSuccessMessage(), "You saved the address.");
            }


        } catch (AssertionError e) {
            test.log(Status.FAIL, "This test is failed due to an assertion error in " + stepDescription + " " + e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "This test is failed due to timeout: " + e.getMessage());
            throw e;

        } catch (NoSuchElementException e) {
            String conciseMessage = " - Element not found: " + e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test is failed due to element not found in " + stepDescription + " " + conciseMessage);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "This test is failed due to an exception in " + stepDescription + " " + e.getMessage());
            throw e;
        }

    }
}
