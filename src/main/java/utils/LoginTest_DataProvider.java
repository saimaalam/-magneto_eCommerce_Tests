package utils;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginTest_DataProvider{
    String email, password,fname,lname;
    public void setConfiguredLoginData(){
        Properties properties = new Properties();
        String projectPath = System.getProperty("user.dir");
        try {
            InputStream input = new FileInputStream(projectPath + "/src/main/java/config/config.propertise");
            properties.load(input);
            fname = properties.getProperty("firstname");
            lname = properties.getProperty("lastname");
            email = properties.getProperty("email");
            password = properties.getProperty("password");
            System.out.println("First Name : "+fname);
            System.out.println("Last Name : "+lname);
            System.out.println("Email : "+email);
            System.out.println("Password : "+password);
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
    @DataProvider(name = "LoginDatafromRegistration")
    public Object[][] generateLoginData() {
        // Access the data stored in TestDataStorage class
        String fname = TestDataStorage.registeredFirstName;
        String lname = TestDataStorage.registeredLastName;
        String email = TestDataStorage.registeredEmail;
        String password =TestDataStorage.registeredPassword;

        return new Object[][]{
                {email, password,fname,lname},
        };
    }
    @DataProvider(name = "LoginDatafromConfiguration")
    public Object[][] ConfigureLoginData() {
        setConfiguredLoginData();
        TestDataStorage.configuredFirstName=fname;
        TestDataStorage.configuredLastName=lname;
        TestDataStorage.configuredEmail=email;
        TestDataStorage.configuredPassword=password;

        return new Object[][]{
                {email, password,fname,lname},
        };
    }
}
