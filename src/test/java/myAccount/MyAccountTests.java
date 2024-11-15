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
import utils.TestDataStorage;


public class MyAccountTests extends BaseTests {

    private MyAccountPage myAccountPage;

    public void loginBeforeTest(String email, String password) {
        try {
            test.log(Status.INFO, "Prerequisite Step1 :Login with " + "Email: " + email);
            myAccountPage = loginUser(email, password);
            if (myAccountPage.getMyAccountpageHeader().equals("My Account")) {
                test.pass("Prerequisite Step1 : Passed. User have logged in");
            } else {
                test.fail("Prerequisite Step1 : Failed. Login failed");
            }
        } catch (Exception e) {
            test.fail("Prerequisite Step1 : Failed. User can not login due to " + e.getMessage().split("\n")[0]);
        }

    }

    @Test(description = "Verify that user can sign out")
    public void TC_5_user_can_sign_out() {
        LoginTest_DataProvider dataProvider = new LoginTest_DataProvider();
        String dataProviderSource = dataProvider.getTestDataProviderName();
        if (dataProviderSource.equalsIgnoreCase("Configuration")) {
            System.out.println(TestDataStorage.configuredEmail + "  " + TestDataStorage.configuredPassword);
            loginBeforeTest(TestDataStorage.configuredEmail, TestDataStorage.configuredPassword);
        }
        if (dataProviderSource.equalsIgnoreCase("Registration")) {
            loginBeforeTest(TestDataStorage.registeredEmail, TestDataStorage.registeredPassword);
        }

        String stepDescription = "";

        try {
            // Step 1: Click on logout button
            stepDescription = "Step 1: Click on logout button";
            test.log(Status.INFO, stepDescription);
            myAccountPage.clickSignoutButton();
            test.pass("Step 1: Passed. Logout button clicked");

            // Step 2: Verify user is redirected to the homepage
            stepDescription = "Step 2: Verify user is redirected to the homepage";
            test.log(Status.INFO, stepDescription);
            if (homePage.isHomepageUrlShowing()) {
                test.pass("Step 2: Passed. User is redirected to homepage");
            } else {
                test.fail("Step 2: Failed. User is not redirected to homepage");
                Assert.assertTrue(homePage.isHomepageUrlShowing(), "User was not redirected to homepage after logout");
            }

        } catch (AssertionError e) {
            takeFailedStepScreenshot("Assertion_Error");
            test.log(Status.FAIL, "This test failed due to an assertion error in " + stepDescription + ": " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (TimeoutException e) {
            takeFailedStepScreenshot("Timeout_Error");
            test.log(Status.FAIL, "This test failed due to a timeout in " + stepDescription + ": " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (NoSuchElementException e) {
            takeFailedStepScreenshot("NoSuchElement_Error");
            String conciseMessage = " - Element not found: " + e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test failed due to an element not found in " + stepDescription + ": " + conciseMessage);
            throw e;
        } catch (Exception e) {
            takeFailedStepScreenshot("Exception_Error");
            test.log(Status.FAIL, "This test failed due to an exception in " + stepDescription + ": " + e.getMessage().split("\n")[0]);
            throw e;
        }
    }

    @Test(description = "Verify that user can add new address in the address book", dataProvider = "AddNewAddressData", dataProviderClass = AddNewAddress_DataProvider.class)
    public void TC_6_Add_new_address_in_address_book(String phoneNumber, String streetAddress1, String city, String zipCode, String state, String country) {
        LoginTest_DataProvider dataProvider = new LoginTest_DataProvider();
        String dataProviderSource = dataProvider.getTestDataProviderName();
        if (dataProviderSource.equalsIgnoreCase("Configuration")) {
            loginBeforeTest(TestDataStorage.configuredEmail, TestDataStorage.configuredPassword);
        }
        if (dataProviderSource.equalsIgnoreCase("Registration")) {
            loginBeforeTest(TestDataStorage.registeredEmail, TestDataStorage.registeredPassword);
        }
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
            takeFailedStepScreenshot("Assertion_Error");
            test.log(Status.FAIL, "This test is failed due to an assertion error in " + stepDescription + " " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (TimeoutException e) {
            takeFailedStepScreenshot("Timeout_Error");
            test.log(Status.FAIL, "This test is failed due to timeout in " + stepDescription + " " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (NoSuchElementException e) {
            takeFailedStepScreenshot("NoSuchElement_Error");
            String conciseMessage = " - Element not found: " + e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test is failed due to element not found in " + stepDescription + " " + conciseMessage);
            throw e;
        } catch (Exception e) {
            takeFailedStepScreenshot("Exception_Error");
            test.log(Status.FAIL, "This test is failed due to an exception in " + stepDescription + " " + e.getMessage().split("\n")[0]);
            throw e;
        }

    }

    @Test(description = "Verify that correct address shows in address book after adding a new address", dependsOnMethods = "TC_6_Add_new_address_in_address_book")
    public void TC_7_correct_address_shows_in_address_book_after_adding_a_new_address() {
        String stepDescription = "";
        try {
            stepDescription = "Step:1 Fetch the Last added address from address book";
            test.log(Status.INFO, stepDescription);
            String billingAddressInformation = myAccountPage.getBillingAddressInformation().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            System.out.println("Actual: " + billingAddressInformation);
            test.pass("Step1: Passed. Address is fetched");

            stepDescription = "Step:2 Validate the Street Address in the address book is correct";
            test.log(Status.INFO, stepDescription);
            String address_street = TestDataStorage.addedStreetAddress1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (billingAddressInformation.contains(address_street)) {
                test.pass("Step2: Passed. Correct Street Address is showing in address book after adding a new address ");
            } else {
                test.fail("Step2: Failed. Correct Street Address is NOT showing in address book after adding a new address");
                Assert.assertTrue(billingAddressInformation.contains(address_street));
            }

            stepDescription = "Step:3 Validate the City name in the address book is correct";
            test.log(Status.INFO, stepDescription);
            String address_city = TestDataStorage.addedCity.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (billingAddressInformation.contains(address_city)) {
                test.pass("Step3: Passed. Correct City name is showing in address book after adding a new address ");
            } else {
                test.fail("Step3: Failed. Correct City name is NOT showing in address book after adding a new address");
                Assert.assertTrue(billingAddressInformation.contains(address_city));
            }

            stepDescription = "Step:4 Validate the Country name in the address book is correct";
            test.log(Status.INFO, stepDescription);
            String address_country = TestDataStorage.addedCountry.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (billingAddressInformation.contains(address_country)) {
                test.pass("Step4: Passed. Correct country name is showing in address book after adding a new address ");
            } else {
                test.fail("Step4: Failed. Correct Country name is NOT showing in address book after adding a new address");
                Assert.assertTrue(billingAddressInformation.contains(address_country));
            }

            stepDescription = "Step:5 Validate the State/Province name in the address book is correct";
            test.log(Status.INFO, stepDescription);
            String address_state = TestDataStorage.addedState.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (billingAddressInformation.contains(address_state)) {
                test.pass("Step5: Passed. Correct country name is showing in address book after adding a new address ");
            } else {
                test.fail("Step5: Failed. Correct State/Province name is NOT showing in address book after adding a new address");
                Assert.assertTrue(billingAddressInformation.contains(address_state));
            }

            stepDescription = "Step:6 Validate the Zip code in the address book is correct";
            test.log(Status.INFO, stepDescription);
            String address_zip = TestDataStorage.addedZipCode.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (billingAddressInformation.contains(address_zip)) {
                test.pass("Step6: Passed. Correct Zip code is showing in address book after adding a new address ");
            } else {
                test.fail("Step6: Failed. Correct Zip code is NOT showing in address book after adding a new address");
                Assert.assertTrue(billingAddressInformation.contains(address_zip));
            }

            stepDescription = "Step:7 Validate the Phone number in the address book is correct";
            test.log(Status.INFO, stepDescription);
            String address_phone = TestDataStorage.addedPhoneNumber.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (billingAddressInformation.contains(address_phone)) {
                test.pass("Step7: Passed. Correct Phone number is showing in address book after adding a new address ");
            } else {
                test.fail("Step7: Failed. Correct Phone number is NOT showing in address book after adding a new address");
                Assert.assertTrue(billingAddressInformation.contains(address_phone));
            }
        } catch (AssertionError e) {
            takeFailedStepScreenshot("Assertion_Error");
            test.log(Status.FAIL, "This test is failed due to an assertion error in " + stepDescription + " " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (TimeoutException e) {
            takeFailedStepScreenshot("Timeout_Error");
            test.log(Status.FAIL, "This test is failed due to timeout in " + stepDescription + " " + e.getMessage().split("\n")[0]);
            throw e;
        } catch (NoSuchElementException e) {
            takeFailedStepScreenshot("NoSuchElement_Error");
            String conciseMessage = " - Element not found: " + e.getMessage().split("\n")[0];
            test.log(Status.FAIL, "This test is failed due to element not found in " + stepDescription + " " + conciseMessage);
            throw e;
        } catch (Exception e) {
            takeFailedStepScreenshot("Exception_Error");
            test.log(Status.FAIL, "This test is failed due to an exception in " + stepDescription + " " + e.getMessage().split("\n")[0]);
            throw e;
        }

    }


}

