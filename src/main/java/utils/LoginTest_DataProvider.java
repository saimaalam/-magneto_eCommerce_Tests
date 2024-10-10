package utils;

import org.testng.annotations.DataProvider;

public class LoginTest_DataProvider {
    @DataProvider(name = "LoginDatafromRegistration")
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
    @DataProvider(name = "LoginDatafromConfiguration")
    public Object[][] ConfigureLoginData() {
        // Access the data stored in TestDataStorage clas

        return new Object[][]{
                {"AmosDietrich@gmail.com","Abcdefgh!","Amos","Dietrich"},
        };
    }
}
