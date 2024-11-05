package utils;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class AddNewAddress_DataProvider {
    public String state = "Quebec";
    public String country = "Canada";
    Faker faker = new Faker();
    public String phoneNumber = faker.phoneNumber().cellPhone();
    public String streetAddress1 = faker.address().streetAddress();
    public String city = faker.address().city();
    public String zipCode = faker.address().zipCode();

    @DataProvider(name = "AddNewAddressData")
    public Object[][] generateAddNewAddressData() {
        TestDataStorage.addedPhoneNumber = phoneNumber;
        TestDataStorage.addedStreetAddress1 = streetAddress1;
        TestDataStorage.addedCity = city;
        TestDataStorage.addedZipCode = zipCode;
        TestDataStorage.addedState = state;
        TestDataStorage.addedCountry = country;

        return new Object[][]{
                {phoneNumber, streetAddress1, city, zipCode, state, country}

        };
    }
}
