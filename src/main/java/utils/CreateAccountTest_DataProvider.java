package utils;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class CreateAccountTest_DataProvider {
    Faker faker = new Faker();
    String fname = faker.name().firstName();
    String lname = faker.name().lastName();
    String email = faker.internet().emailAddress(fname.toLowerCase() + lname.toLowerCase());
    String password = "Abcdefgh!";

    @DataProvider(name = "CreateAccountData")
    public Object[][] generateCreateAccountData() {

        TestDataStorage.registeredFirstName = fname;
        TestDataStorage.registeredLastName = lname;
        TestDataStorage.registeredEmail = email;
        TestDataStorage.registeredPassword = password;

        return new Object[][]{
                {fname, lname, email, password},
        };
    }

}
