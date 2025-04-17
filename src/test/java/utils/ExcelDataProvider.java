package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {


    @DataProvider(name = "validLoginCredentials")
    public Object[][] validLoginCredentials() {
        return new Object[][]{
                {ConfigReader.getProperty("username1", ""), ConfigReader.getProperty("password", "")},
                {ConfigReader.getProperty("username2", ""), ConfigReader.getProperty("password", "")},
                // Add more credentials if needed
        };
    }
}
