package utils;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginTest_DataProvider {
    String email, password, fname, lname;
    Properties properties = new Properties();
    String projectPath = System.getProperty("user.dir");

    public String getTestDataProviderName() {
        String dataProviderSource = "";
        try {
            InputStream input = new FileInputStream(projectPath + "/src/main/java/config/config.propertise");
            properties.load(input);
            dataProviderSource = properties.getProperty("dataProvider");
            System.out.println(dataProviderSource);
            if(dataProviderSource.equalsIgnoreCase("Configuration")){
                setConfiguredLoginData();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return dataProviderSource;

    }

    public void setConfiguredLoginData() {
        try {
            InputStream input = new FileInputStream(projectPath + "/src/main/java/config/config.propertise");
            properties.load(input);
            fname = properties.getProperty("firstname");
            lname = properties.getProperty("lastname");
            email = properties.getProperty("email");
            password = properties.getProperty("password");
            TestDataStorage.configuredFirstName = fname;
            TestDataStorage.configuredLastName = lname;
            TestDataStorage.configuredEmail = email;
            TestDataStorage.configuredPassword = password;
            System.out.println("First Name : " + fname);
            System.out.println("Last Name : " + lname);
            System.out.println("Email : " + email);
            System.out.println("Password : " + password);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @DataProvider(name = "LoginDataProvider")
    public Object[][] generateLoginData() {
        String dataProvider = getTestDataProviderName();

        if (dataProvider.equalsIgnoreCase("Configuration")) {
            setConfiguredLoginData();
        }
        if (dataProvider.equalsIgnoreCase("Registration")) {
            fname = TestDataStorage.registeredFirstName;
            lname = TestDataStorage.registeredLastName;
            email = TestDataStorage.registeredEmail;
            password = TestDataStorage.registeredPassword;
            System.out.println("First Name : " + fname);
            System.out.println("Last Name : " + lname);
            System.out.println("Email : " + email);
            System.out.println("Password : " + password);
        }
        return new Object[][]{
                {email, password, fname, lname},
        };
    }
}
