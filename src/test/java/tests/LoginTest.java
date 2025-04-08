package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.Log;


public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void loginTest() throws InterruptedException {
        Log.info("Starting login  valid test...");
        test = ExtentReportManager.createTest("Login Test");
        Log.info("Navigate to Login Page");
        loginPage = new LoginPage(driver);
        Log.info("Entere credentails and click Login");
        loginPage.enterUsername("ahmad434@gmail.com");
        loginPage.enterPssword("1234o0303kkdk");
        loginPage.clickLogin();
        test.info("Entered credentails and clicked Login");
        Log.info("Verifying Page Title");
        test.info("Verifying Page Title");
        Assert.assertEquals(driver.getTitle(), "Log in to Facebook123");
        Log.info("Login Test Completed");
        test.pass("Login successful");
    }


}
