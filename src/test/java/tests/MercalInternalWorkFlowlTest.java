package tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.*;

import static pages.BasePage.waitForLoadPage;
import static pages.MirsalLoginPage.loginUser;
import static pages.TaskPage.scrollToBottom;
import static utils.ExtentReportManager.captureScreenshot;
import static utils.General.*;

public class MercalInternalWorkFlowlTest extends IncomingBaseTest {
    private InteralNotePage interalNotePage;
    private MirsalLogout mirsalLogout;
    private TaskPage mersalTaskPage;


    @Test(priority = 1)
    public void verifySuccessfulLoginWithValidCredentials() {
        Log.info("Starting login test with valid credentials...");
        test = ExtentReportManager.createTest("Verify Successful Login",
                "Verifies that a user can log in successfully with valid credentials");
        // Perform login with configurable credentials
        String username1 = ConfigReader.getProperty("username1", "");
        String password = ConfigReader.getProperty("password", "");

        try {
            // Log test step
            test.log(Status.INFO, "Entering user credentials: " + username1);
            boolean isLoggedIn = loginUser(driver, username1, password);
            // Validate input fields and login outcome
            if (username1.isEmpty() || password.isEmpty()) {
                String errorMessage = username1.isEmpty() ? "Username field is empty" : "Password field is empty";
                test.fail(errorMessage);
                Assert.fail(errorMessage);
            }

            if (isLoggedIn) {
                test.pass("Login successful for user: " + username1);
                Log.info("Login successful for user: " + username1);
            } else {
                test.fail("Login failed incorrect username or password ");
                Assert.fail("Login failed Incorrect username or password: ");
            }

        } catch (Exception e) {
            String errorMessage = "Test failed due to exception: " + e.getMessage();
            test.fail(errorMessage);
            captureScreenshot(driver, "login_exception");
            Log.error("Login test failed with exception: ", e);
            Assert.fail(errorMessage);
        } finally {
            test.log(Status.INFO, "Login test execution completed");
        }
    }


    @Test(priority = 2, dependsOnMethods = "verifySuccessfulLoginWithValidCredentials")
    public void verifyOpenInternalMemoCreationPage() {
        Log.info("Starting test to verify opening Internal Memo Creation Page...");
        test = ExtentReportManager.createTest("Verify Internal Memo Creation Page Access",
                "Verifies that a user can successfully open the Internal Memo Creation Page");

        try {
            // Initialize page object
            test.log(Status.INFO, "Navigating to Home Page");
            MirsalHomePage mirsalHomePage = new MirsalHomePage(driver);

            // Wait for page to load
            test.log(Status.INFO, "Waiting for Home Page to load");
            waitForLoadPage();

            // Perform actions to open Internal Memo Creation Page
            test.log(Status.INFO, "Clicking on Create and Index Books dropdown");
            mirsalHomePage.clickOnCreateBook();

            test.log(Status.INFO, "Clicking on Create Internal Memo Link");
            mirsalHomePage.clickOnCreateInteralNote();

            // Verify the Internal Memo Creation Page is opened
            test.log(Status.INFO, "Verifying Internal Memo Creation Page is displayed");
            boolean isPageOpened = mirsalHomePage.isInternalMemoPageDisplayed();

            if (isPageOpened) {
                test.pass("Internal Memo Creation Page opened successfully");
                Log.info("Internal Memo Creation Page opened successfully");
            } else {
                test.fail("Failed to open Internal Memo Creation Page");
                captureScreenshot(driver, "internal_memo_page_failure");
                Assert.fail("Internal Memo Creation Page was not displayed");
            }

        } catch (Exception e) {
            String errorMessage = "Test failed due to exception: " + e.getMessage();
            test.fail(errorMessage);
            captureScreenshot(driver, "internal_memo_page_exception");
            Log.error("Test failed with exception: ", e);
            Assert.fail(errorMessage);
        } finally {
            test.log(Status.INFO, "Test execution for Internal Memo Creation Page completed");
        }
    }

    @Test(priority = 3, dependsOnMethods = "verifyOpenInternalMemoCreationPage")
    public void verifyCreateInternalMemo() throws InterruptedException {
        test = ExtentReportManager.createTest("Verify Create Internal Memo ", "Verify that user can create internal " +
                "memo ");
        test.log(Status.INFO, "Get Internal Memo page class Instance");
        interalNotePage = new InteralNotePage(driver);
        test.log(Status.INFO, "Enter Internal Document Name");
        Thread.sleep(1000);
        interalNotePage.writeSubject();
        Thread.sleep(1000);
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
        waitForLoadPage();
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
        waitForLoadPage();
        test.log(Status.INFO, "Scroll To The Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Click On Execute Button ");
        interalNotePage.clickOnExecuteButton0();
        waitForLoadPage();
        //Thread.sleep(2000);
        test.log(Status.INFO, "Click On Confirm Execute Button ");
        interalNotePage.confirmNotify();
        //Thread.sleep(2000);
        test.log(Status.PASS, "Create Internal Document successfully");
    }

    @Test(priority = 4, dependsOnMethods = "verifyCreateInternalMemo")
    public void testRecverLogin() throws InterruptedException {
        test = extent.createTest("Login To Internal Manager Account Test");
        test.log(Status.INFO, "Click On Profile Page");
        mirsalHomePage.clickOnProfileimage();
        mirsalLogout = new MirsalLogout(driver);
        test.log(Status.INFO, "Click On Logout Button");
        mirsalLogout.clickLogoutButton();
        test.log(Status.INFO, "Enter Internal Manager UserName And Password");
        loginUser(driver, "seq1", "Ebla1234");
        test.log(Status.PASS, "Login To Internal Manager Account successfully");

    }

    @Test(priority = 5, dependsOnMethods = "testRecverLogin")
    public void compleatTaskS1_Test() throws InterruptedException {
        test = extent.createTest("Complete Task From Internal Manager Test");
        mersalTaskPage = new TaskPage(driver);
        test.log(Status.INFO, "select Received Document");
        waitForLoadPage();
        mersalTaskPage.selectRecivedDocument();
        mersalTaskPage.selectAddFolder();
        mersalTaskPage.selectInputDocument();
        test.log(Status.INFO, "Click On Complete Button");
        mersalTaskPage.clickOnCompletButton();
        waitForLoadPage();
        test.log(Status.INFO, "Click On Approved Button");
        mersalTaskPage.clickOnApprovrelButton();
        test.log(Status.INFO, "Scroll To Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(200);
        test.log(Status.INFO, "Switch To the Frame 1");
        driver.switchTo().frame("presentation-iframe");
        Thread.sleep(200);
        test.log(Status.INFO, "Click on Sign Button ");
        mersalTaskPage.AddSignBlockBtn();
        waitForLoadPage();
        test.log(Status.INFO, "Switch to default page 1");
        driver.switchTo().defaultContent();
        test.log(Status.INFO, "Scroll To Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(750);
        test.log(Status.INFO, "Switch To the Frame 2");
        driver.switchTo().frame("presentation-iframe");
        test.log(Status.INFO, "Drag Sing To the Bottom");
        mersalTaskPage.dragElementToBottomCenter();
        test.log(Status.INFO, "Switch to default page 2");
        driver.switchTo().defaultContent();
        test.log(Status.INFO, "Scroll To The Top Of The Page");
        scrollToTop(driver);
        Thread.sleep(700);
        test.log(Status.INFO, "Switch To the Frame 3");
        driver.switchTo().frame("presentation-iframe");
        Thread.sleep(700);
        test.log(Status.INFO, "Click on save Sign Button");
        mersalTaskPage.savaSign();
        test.log(Status.INFO, "Click on Confirm save Sign Button");
        mersalTaskPage.confirmButton1();
        test.log(Status.INFO, "Switch to default page 3");
        driver.switchTo().defaultContent();
        Thread.sleep(700);
        test.log(Status.INFO, "Scroll To Bottom Of The Page");
        scrollToBottom(driver);
        Thread.sleep(700);
        test.log(Status.INFO, "Click On execute Button");
        mersalTaskPage.executeTask();
        waitForLoadPage();
        test.log(Status.INFO, "Click On Confirm Execute Button");
        mersalTaskPage.confirmButton1();
        //waitForLoadPage();
        test.log(Status.PASS, "Complete Task From Internal Manager successfully");

    }

    @Test(priority = 6, dependsOnMethods = "compleatTaskS1_Test")
    public void compleatTaskS2_Test() throws InterruptedException {
        test = extent.createTest("Complete Task From General Manager Test");
        test.log(Status.INFO, "Click On Profile Page 2");
        waitForLoadPage();
        mirsalHomePage.clickOnProfileimage();
        waitForLoadPage();
        test.log(Status.INFO, "Click On Logout Button 2");
        mirsalLogout.clickLogoutButton();
        test.log(Status.INFO, "Enter General Manager UserName And Password");
        loginUser(driver, "seq2", "Ebla1234");
        mirsalHomePage = new MirsalHomePage(driver);
        test.log(Status.INFO, "Open Incoming Page");
        waitForLoadPage();
        mirsalHomePage.openIncomingPage();
        mersalTaskPage = new TaskPage(driver);
        waitForLoadPage();
        test.log(Status.INFO, "select Input Document");
        mersalTaskPage.selectInputDocument();
        test.log(Status.INFO, "click On Complet Button");
        mersalTaskPage.clickOnCompletButton();
        waitForLoadPage();
        test.log(Status.INFO, "Enter Note..");
        driver.findElement(By.className("swal2-textarea")).sendKeys("done");
        test.log(Status.INFO, "Confirm Complete Task ");
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        test.log(Status.PASS, "Complete Task From General Manager successfully");

    }


}