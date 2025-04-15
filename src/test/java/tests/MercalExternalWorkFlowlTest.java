package tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ExtentReportManager;
import utils.IncomingBaseTest;
import utils.Log;

import static pages.MirsalLoginPage.loginUser;
import static pages.TaskPage.scrollToBottom;

public class MercalExternalWorkFlowlTest extends IncomingBaseTest {
    private ExtenralNotePage extenralNote;
    private MirsalLogout mirsalLogout;


    @Test(priority = 1)
    public void testLogin() {
        Log.info("Starting valid login test...");

        test = extent.createTest("Mercal Login Test", "Verify successful login with valid " + "credentials");
        test.info("Entering and verifying user credentials");

        try {
            // Perform login
            boolean isLoggedIn = loginUser(driver, "seq2", "Ebla1234");
            // Assertion to ensure login success
            Assert.assertTrue(isLoggedIn, "Login failed with valid credentials");

            test.pass("Login was successful");
            Log.info("Login test passed.");

        } catch (Exception e) {
            test.fail("Login test failed: " + e.getMessage());
            Log.error("Exception during login test: ", e);
            Assert.fail("Exception during login test: " + e.getMessage());
        }
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testOpenNote() throws InterruptedException {
        test = extent.createTest("Open External Document Page Test");
        test.log(Status.INFO, "Get Home page ");
        mirsalHomePage = new MirsalHomePage(driver);
        Thread.sleep(3000);
        test.log(Status.INFO, "Click on create book link");
        mirsalHomePage.clickOnCreateBook();
        Thread.sleep(1000);
        test.log(Status.INFO, "Click on Create External Document");
        mirsalHomePage.openExternalBook();
        test.log(Status.PASS, "External document page opened successfully");
    }

    @Test(priority = 3, dependsOnMethods = "testOpenNote")
    public void CreatExternalNote() throws InterruptedException {
        test = extent.createTest("Create External Document Test");
        test.log(Status.INFO, "Get External Document page class Instance");
        extenralNote = new ExtenralNotePage(driver);
        //
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter External Document Name");
        extenralNote.writeSubject("First external note 10 ");
        Thread.sleep(1000);
        test.log(Status.INFO, "Display Direct-From Options ");
        extenralNote.clickOnDirctFrom();
        Thread.sleep(500);
        test.log(Status.INFO, "Select Direct-From Option ");
        extenralNote.clickOnSelectDirect();
        Thread.sleep(500);
        test.log(Status.INFO, "Hide Direct-From Options");
        extenralNote.clickOnDirctFrom();
        Thread.sleep(500);
        test.log(Status.INFO, "Select Direct-To Options");
        extenralNote.addDirection();
        test.log(Status.INFO, "Scroll To the Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Click on Word Document Button");
        extenralNote.clickOutGonigBtn();
        Thread.sleep(500);
        test.log(Status.INFO, "Click on Save Word Document Button");
        extenralNote.clickSavaOutGoingBtn();
        Thread.sleep(500);
        test.log(Status.INFO, "Click on Confirm Save Word Document Button");
        extenralNote.confirmeContonu();
        Thread.sleep(500);
        test.log(Status.INFO, "Click on Edit Word Document Button");
        extenralNote.editeDocument();
        Thread.sleep(500);
        test.log(Status.INFO, "Switch To Word Iframe ");
        driver.switchTo().frame("OutGoingTemplate-iframe");
        Thread.sleep(500);
        test.log(Status.INFO, "Perform Arrow_Down Key 7 time");
        Actions actions = new Actions(driver);
        for (int i = 0; i < 7; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            Thread.sleep(500);
        }
        test.log(Status.INFO, "Enter Content of The Word Document");
        actions.sendKeys("test External Book ").perform();
        Thread.sleep(500);
        test.log(Status.INFO, "Switch To default Page ");
        driver.switchTo().defaultContent();
        Thread.sleep(500);
        test.log(Status.INFO, "Click on Ratification Out Going Document");
        driver.findElement(By.cssSelector("#RatificationOutGoingDocument")).click();
        Thread.sleep(500);
        test.log(Status.INFO, "Confirm The outGoing");
        extenralNote.clickYes();
        Thread.sleep(2000);
        test.log(Status.INFO, "Switch To Word Iframe 1");
        driver.switchTo().frame("OutGoingTemplate-iframe");
        Thread.sleep(500);
        test.log(Status.INFO, "Click On Add Sign Button");
        extenralNote.addSigntur();
        Thread.sleep(500);
        test.log(Status.INFO, "Click On Save Sign Button");
        extenralNote.savaSign();
        test.log(Status.INFO, "Click On Confirm Save Sign Button");
        extenralNote.clickConfirm();
        test.log(Status.INFO, "Switch To Default Page 1");
        driver.switchTo().defaultContent();
        test.log(Status.INFO, "Click On Confirm  Button");
        extenralNote.confirmeContonu();
        test.log(Status.PASS, "Create External Document  Successfully");


        //nteralNotePage.clickOnManiSearchButton();
        //Thread.sleep(400);
        //interalNotePage.clickSearchButton();
        //Thread.sleep(2000);
        //interalNotePage.choosDocument();
        //interalNotePage.getAttached();
        //Thread.sleep(500);
        //interalNotePage.getDecision();
        //Thread.sleep(500);
        //interalNotePage.getRegister();
        //interalNotePage.clickCloseSearchBox();
    }
}
