package tests;

import com.aventstack.extentreports.Status;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.IncomingBaseTest;
import utils.Log;

import java.time.Duration;

import static pages.MirsalLoginPage.loginUser;
import static pages.TaskPage.scrollToBottom;
import static utils.General.scrollDownY;
import static utils.General.scrollToTop;

public class MercalInternalWorkFlowlTest extends IncomingBaseTest {
    private InteralNotePage interalNotePage;
    private MirsalLogout mirsalLogout;
    private TaskPage mersalTaskPage;


    @Test(priority = 1)
    public void testLogin() {
        Log.info("Starting valid login test...");

        test = ExtentReportManager.createTest("Mercal Login Test");
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
        test = extent.createTest("Open Internal Document Page Test");
        test.log(Status.INFO, "Get Home page ");
        mirsalHomePage = new MirsalHomePage(driver);
        Thread.sleep(2000);
        test.log(Status.INFO, "Click on create book link");
        mirsalHomePage.clickOnCreateBook();
        Thread.sleep(2000);
        test.log(Status.INFO, "Click on Create Internal Document");
        mirsalHomePage.clickOnCreateInteralNote();
        Thread.sleep(1000);
        test.log(Status.PASS, "Internal document page opened successfully");
    }

    @Test(priority = 3, dependsOnMethods = "testOpenNote")
    public void CreatInteralNoteTest() throws InterruptedException {
        test = extent.createTest("Create Internal Document Test");
        test.log(Status.INFO, "Get Internal Document page class Instance");
        interalNotePage = new InteralNotePage(driver);
        //
        test.log(Status.INFO, "Enter Internal Document Name");
        interalNotePage.writeSubject("internal Note 38");
        //Thread.sleep(1000);
        test.log(Status.INFO, "Chose The Reason For The Internal Document");
        interalNotePage.selectReason();
        //Thread.sleep(2000);
        test.log(Status.INFO, "Enter Note For Internal Document");
        interalNotePage.selectNote();
        test.log(Status.INFO, "Scroll Down 200px");
        scrollDownY(driver, 200);
        Thread.sleep(1000);
        test.log(Status.INFO, "Select The Person To Whom The Transfer Is Made");
        interalNotePage.selectPersion();
        test.log(Status.INFO, "Scroll To The Bottom Of The Page");
        interalNotePage.scrollToButton(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Click on Load File Word Button");
        interalNotePage.loadWordFile();
        Thread.sleep(2000);
        test.log(Status.INFO, "Switch to Write in The Word File");
        driver.switchTo().frame("Template-iframe");
        test.log(Status.INFO, "Click On Arrow_Down Three Time");
        Actions actions = new Actions(driver);
        for (int i = 0; i < 3; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }
        test.log(Status.INFO, "Write In Word Document");
        actions.sendKeys("test book incoming ").perform();
        test.log(Status.INFO, "Switch To The Page ");
        driver.switchTo().defaultContent();
        Thread.sleep(500);
        test.log(Status.INFO, "Click On The Save Button To Save The Word File");
        interalNotePage.clickSaveButton();
        test.log(Status.INFO, "Click On the Confirm Save Word File Button");
        interalNotePage.confirmeSave();

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
        Thread.sleep(6000);
        test.log(Status.INFO, "Scroll To The Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Click On Execute Button ");
        interalNotePage.clickOnExecuteButton0();
        Thread.sleep(2000);
        test.log(Status.INFO, "Click On Confirm Execute Button ");
        interalNotePage.confirmNotify();
        Thread.sleep(2000);
        test.log(Status.PASS, "Create Internal Document successfully");
    }

    @Test(priority = 4, dependsOnMethods = "CreatInteralNoteTest")
    public void testRecverLogin() throws InterruptedException {
        test = extent.createTest("Login To Internal Manager Account Test");
        test.log(Status.INFO, "Click On Profile Page");
        mirsalHomePage.clickOnProfileimage();
        Thread.sleep(1000);
        mirsalLogout = new MirsalLogout(driver);
        test.log(Status.INFO, "Click On Logout Button");
        mirsalLogout.clickLogoutButton();
        Thread.sleep(500);
        // mirsalLoginTest.changLanguages();
        test.log(Status.INFO, "Enter Internal Manager UserName And Password");
        loginUser(driver, "seq1", "Ebla1234");
        Thread.sleep(2000);

        test.log(Status.PASS, "Login To Internal Manager Account successfully");

    }

    @Test(priority = 5, dependsOnMethods = "testRecverLogin")
    public void compleatTaskS1_Test() throws InterruptedException {
        test = extent.createTest("Complete Task From Internal Manager Test");
        mersalTaskPage = new TaskPage(driver);
        Thread.sleep(2000);
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert found.");
        }
        test.log(Status.INFO, "select Received Document");
        mersalTaskPage.selectRecivedDocument();
        mersalTaskPage.selectAddFolder();
        mersalTaskPage.selectInputDocument();
        test.log(Status.INFO, "Click On Complete Button");
        mersalTaskPage.clickOnCompletButton();
        Thread.sleep(4000);
        test.log(Status.INFO, "Click On Approved Button");
        mersalTaskPage.clickOnApprovrelButton();
        test.log(Status.INFO, "Scroll To Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(500);
        test.log(Status.INFO, "Switch To the Frame 1");
        driver.switchTo().frame("presentation-iframe");
        Thread.sleep(1000);
        test.log(Status.INFO, "Click on Sign Button ");
        mersalTaskPage.AddSignBlockBtn();
        Thread.sleep(2000);
        test.log(Status.INFO, "Switch to default page 1");
        driver.switchTo().defaultContent();
        test.log(Status.INFO, "Scroll To Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Switch To the Frame 2");
        driver.switchTo().frame("presentation-iframe");
        test.log(Status.INFO, "Drag Sing To the Bottom");
        mersalTaskPage.dragElementToBottomCenter();
        test.log(Status.INFO, "Switch to default page 2");
        driver.switchTo().defaultContent();
        test.log(Status.INFO, "Scroll To The Top Of The Page");
        scrollToTop(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Switch To the Frame 3");
        driver.switchTo().frame("presentation-iframe");
        test.log(Status.INFO, "Click on save Sign Button");
        mersalTaskPage.savaSign();
        test.log(Status.INFO, "Click on Confirm save Sign Button");
        mersalTaskPage.confirmButton1();
        test.log(Status.INFO, "Switch to default page 3");
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        test.log(Status.INFO, "Scroll To Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Click On execute Button");
        mersalTaskPage.executeTask();
        Thread.sleep(3000);
        test.log(Status.INFO, "Click On Confirm Execute Button");
        mersalTaskPage.confirmButton1();
        Thread.sleep(1000);
        test.log(Status.PASS, "Complete Task From Internal Manager successfully");

    }

    @Test(priority = 6, dependsOnMethods = "compleatTaskS1_Test")
    public void compleatTaskS2_Test() throws InterruptedException {
        test = extent.createTest("Complete Task From General Manager Test");
        Thread.sleep(500);
        test.log(Status.INFO, "Click On Profile Page 2");
        mirsalHomePage.clickOnProfileimage();
        Thread.sleep(1000);
        test.log(Status.INFO, "Click On Logout Button 2");
        mirsalLogout.clickLogoutButton();
        Thread.sleep(500);
        test.log(Status.INFO, "Enter General Manager UserName And Password");
        loginUser(driver, "seq2", "Ebla1234");
        Thread.sleep(2000);
        mirsalHomePage = new MirsalHomePage(driver);
        Thread.sleep(2000);
        test.log(Status.INFO, "Open Incoming Page");
        mirsalHomePage.openIncomingPage();
        mersalTaskPage = new TaskPage(driver);
        Thread.sleep(2000);
        test.log(Status.INFO, "select Input Document");
        mersalTaskPage.selectInputDocument();
        Thread.sleep(1000);
        test.log(Status.INFO, "click On Complet Button");
        mersalTaskPage.clickOnCompletButton();
        Thread.sleep(2000);
        test.log(Status.INFO, "Enter Note..");
        driver.findElement(By.className("swal2-textarea")).sendKeys("done");
        test.log(Status.INFO, "Confirm Complete Task ");
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        test.log(Status.PASS, "Complete Task From General Manager successfully");

    }


}
