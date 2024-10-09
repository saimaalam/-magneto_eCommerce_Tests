package utils;

import org.testng.annotations.DataProvider;

public class LoginTest_DataProvider {
    @DataProvider(name = "LoginData")
    public Object[][] generateLoginData() {
        // Access the data stored in TestDataStorage class
        String fname = TestDataStorage.registeredFirstName;
        String lname = TestDataStorage.registeredLastName;
        String email = TestDataStorage.registeredEmail;
        String password = TestDataStorage.registeredPassword;

        return new Object[][]{
                {email, password,fname,lname},
        };
    }
}
