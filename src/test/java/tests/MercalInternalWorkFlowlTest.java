package tests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InteralNotePage;
import pages.MirsalHomePage;
import pages.MirsalLogout;
import pages.TaskPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.IncomingBaseTest;
import utils.Log;

import java.time.Duration;

import static pages.BasePage.wait;
import static pages.BasePage.waitForLoadPage;
import static pages.TaskPage.scrollToBottom;
import static utils.ExtentReportManager.captureScreenshot;
import static utils.General.scrollToTop;

public class MercalInternalWorkFlowlTest extends IncomingBaseTest {

    private InteralNotePage interalNotePage;
    private TaskPage mersalTaskPage;
    String username1 = ConfigReader.getProperty("username1", "");
    String username2 = ConfigReader.getProperty("username2", "");
    String password = ConfigReader.getProperty("password", "");


    @Test(priority = 1)
    public void verifySuccessfulLoginWithValidCredentials() {

        Log.info("Starting login test with valid credentials...");
        test = ExtentReportManager.createTest("verifySuccessfulLoginWithValidCredentials",
                "Verifies that a user can log in successfully with valid credentials");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");


        // Perform login with configurable credentials

        try {
            loginWithCredentials(username2, password, "verifySuccessfulLoginWithValidCredentials", test);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        } finally {
            test.log(Status.INFO, "Login test execution completed");
        }
    }


    @Test(priority = 2, dependsOnMethods = "verifySuccessfulLoginWithValidCredentials")
    public void verifyOpenInternalMemoCreationPage() {
        Log.info("Starting test to verify opening Internal Memo Creation Page...");
        test = ExtentReportManager.createTest("Verify Internal Memo Creation Page Access",
                "Verifies that a user can successfully open the Internal Memo Creation Page");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

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
            Log.error("Test failed with exception: ", e);
            Assert.fail(errorMessage);
        } finally {
            test.log(Status.INFO, "Test execution for Internal Memo Creation Page completed");
        }
    }

    @Test(priority = 3, dependsOnMethods = "verifyOpenInternalMemoCreationPage")
    public void verifyCreateInternalMemo() {
        // Initialize Extent Test
        test = ExtentReportManager.createTest("Verify Create Internal Memo",
                "Verify that user can create an internal memo");
        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

        try {
            // Initialize page object and wait
            test.info("Initializing Internal Memo page...");
            interalNotePage = new InteralNotePage(driver);


            // wait until page loaded
            waitForLoadPage();

            // Step 1: Enter Memo Name
            test.info("Entering internal Memo name...");
            interalNotePage.enterSubject("internal memo 2");


            // Step 2: Select Reason
            test.info("Selecting reason for the internal Memo...");
            interalNotePage.selectReason();


            // Step 3: Enter Note
            test.info("Entering note for the internal document...");
            interalNotePage.selectNote();


            // Step 4: Scroll Down
            test.info("Scrolling down by 200px...");
            interalNotePage.scrollTo(driver);


            // Step 5: Select Person
            test.info("Selecting the person for the transfer...");
            interalNotePage.selectPersion();
            interalNotePage.checkIfSelectPerson();


            // Step 6: Scroll to Bottom
            test.info("Scrolling to the bottom of the page...");
            interalNotePage.scrollToButton(driver);


            // Step 7: Load Word File
            test.info("Clicking on Load File Word button...");
            interalNotePage.loadWordFile();


            // Step 8: Write in Word Document
            test.info("Switching to Word iframe and writing content...");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Template-iframe"));

            Actions actions = new Actions(driver);
            for (int i = 0; i < 3; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(200)).perform();
            }
            actions.sendKeys("test internal memo 1").perform();

            // Switch back to default content
            driver.switchTo().defaultContent();


            // Step 9: Save Word File
            test.info("Saving the Word file...");
            interalNotePage.clickSaveButton();
            interalNotePage.confirmeSave();
            waitForLoadPage();


            // Step 10: Check If an Attachment Inserted
            interalNotePage.checkIfAttacmentFileExist();

            // Step 11: Execute and Confirm
            test.info("Scrolling to bottom and executing the memo...");
            scrollToBottom(driver);

            Thread.sleep(1000);

            interalNotePage.clickOnExecuteButton0();
            waitForLoadPage();
            interalNotePage.confirmNotify();


            // Final Status
            test.log(Status.PASS, MarkupHelper.createLabel("Internal Memo created successfully", ExtentColor.GREEN));

        } catch (Exception e) {
            // Log failure and attach screenshot
            String errorMessage = e.getMessage();
            Assert.fail(errorMessage);
        }
    }


    @Test(priority = 4, dependsOnMethods = "verifyCreateInternalMemo")
    public void verifyLogoutStep() {
        test = ExtentReportManager.createTest("verifyLogoutStep", "Logout from current user");
        test.assignCategory("User Switching");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

        logout("Logging Out From User " + username2, test);
        test.log(Status.PASS, MarkupHelper.createLabel("Logging Out From User " + username2 + "successfully",
                ExtentColor.GREEN));

    }

    @Test(priority = 5, dependsOnMethods = "verifyLogoutStep")
    public void verifyLoginAsAnotherUser() {
        test = ExtentReportManager.createTest("verify Login As User " + username1, "Login with General manager " +
                "account");
        test.assignCategory("User Switching");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");
        loginWithCredentials(username1, password, "Login with General Manager Account", test);

        test.log(Status.PASS, MarkupHelper.createLabel("Login to General Manager Account Successfully",
                ExtentColor.GREEN));


    }


    @Test(priority = 5, dependsOnMethods = "verifyLoginAsAnotherUser")
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
        // mirsalLogout.clickLogoutButton();
        test.log(Status.INFO, "Enter General Manager UserName And Password");
        mirsalLoginTest.loginUser("seq2", "Ebla1234");
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

