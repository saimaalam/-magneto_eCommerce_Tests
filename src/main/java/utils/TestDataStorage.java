package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataStorage {

    //User data which are coming from configuration file
    public  static String configuredFirstName;
    public  static String configuredLastName;
    public static String configuredEmail;
    public  static String configuredPassword;

    //User data which have used in create a new account test
    public static String registeredFirstName;
    public static String registeredLastName;
    public static String registeredEmail;
    public static String registeredPassword;

    //Data for adding new address
    public static String addedPhoneNumber;
    public static String addedStreetAddress1;
    public static String addedCity;
    public static String addedZipCode;
    public static String addedState;
    public static String addedCountry;

}

