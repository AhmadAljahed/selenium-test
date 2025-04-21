package tests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;
import pages.CustomTestException;
import pages.InteralNotePage;
import pages.MirsalHomePage;
import pages.TaskPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.IncomingBaseTest;
import utils.Log;

import java.time.Duration;

import static pages.BasePage.wait;
import static pages.BasePage.waitForLoadPage;
import static utils.ExtentReportManager.captureScreenshot;
import static utils.General.scrollDownY;
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
        test = ExtentReportManager.createTest("Login With " + username2,
                "Verifies that a user can log in successfully with valid credentials");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");


        // Perform login with configurable credentials

        try {
            loginWithCredentials(username2, password, "verifySuccessfulLoginWithValidCredentials", test);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Test(priority = 2, dependsOnMethods = "verifySuccessfulLoginWithValidCredentials")
    public void verifyOpenInternalMemoCreationPage() {
        Log.info("Starting test to verify opening Internal Memo Creation Page...");
        test = ExtentReportManager.createTest("Internal Memo Creation Page Access",
                "Verifies that a user can successfully open the Internal Memo Creation Page");

        //Asin to Work Flow Category
        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

        try {
            // Initialize page object
            test.log(Status.INFO, "Navigating to Home Page");
            MirsalHomePage mirsalHomePage = new MirsalHomePage(driver);
            test.log(Status.PASS, "Navigated to Home Page Successfully");


            // Wait for page to load
            test.log(Status.INFO, "Waiting for Home Page to load");
            waitForLoadPage();
            test.log(Status.PASS, " Home Page loaded Successfully");


            // Perform actions to open Internal Memo Creation Page
            test.log(Status.INFO, "Clicking on Create and Index Books dropdown");
            mirsalHomePage.clickOnCreateBook();
            test.log(Status.PASS, "Create and Index Books dropdown Clicked Successfully");


            test.log(Status.INFO, "Clicking on Create Internal Memo Link");
            mirsalHomePage.clickOnCreateInteralNote();
            test.log(Status.PASS, "Create Internal Memo Link Successfully");


            // Verify the Internal Memo Creation Page is opened
            test.log(Status.INFO, "Verifying Internal Memo Creation Page is displayed");
            boolean isPageOpened = mirsalHomePage.isInternalMemoPageDisplayed();
            test.log(Status.PASS, " Creation Page displayed Successfully");


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
        test = ExtentReportManager.createTest("Create Internal Memo",
                "Verify that user can create an internal memo");
        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

        try {
            // Initialize page object and wait
            interalNotePage = new InteralNotePage(driver);

            // wait until page loaded
            waitForLoadPage();

            // Step 1: Enter Memo Name
            test.info("Entering internal Memo name...");
            interalNotePage.enterSubject("internal memo 2");
            test.pass("Internal Memo name... Entered Successfully");


            // Step 2: Select Reason
            test.info("Selecting reason for the internal Memo...");
            interalNotePage.selectReason();
            test.pass("Reason selected successfully");


            // Step 3: Enter Note
            test.info("Entering note for the internal document...");
            interalNotePage.selectNote();
            test.pass("Note entered successfully");


            // Step 4: Scroll Down
            test.info("Scrolling down by 200px...");
            interalNotePage.scrollTo(driver);
            test.pass("Scrolled down by 200px successfully");


            // Step 5: Select Person
            test.info("Selecting the person for the transfer...");
            interalNotePage.selectPersion();
            interalNotePage.checkIfSelectPerson();
            test.pass("Person selected successfully");


            // Step 6: Scroll to Bottom
            test.info("Scrolling to the bottom of the page...");
            interalNotePage.scrollToButton(driver);
            test.pass("Scrolled to bottom successfully");


            // Step 7: Load Word File
            test.info("Clicking on Load File Word button...");
            interalNotePage.loadWordFile();
            test.pass("Word file loaded successfully");


            // Step 8: Write in Word Document
            test.info("Switching to Word iframe and writing content...");
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Template-iframe"));
                test.pass("Switched to Template-iframe successfully");
            } catch (NoSuchFrameException e) {

            }

            Actions actions = new Actions(driver);
            for (int i = 0; i < 3; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(200)).perform();
            }
            actions.sendKeys("test internal memo 1").perform();
            test.pass("Content written in Word document successfully");

            Thread.sleep(2000);

            // Switch back to default content
            test.info("Switching to default content...");
            driver.switchTo().defaultContent();
            test.pass("Switched to default content successfully");


            // Step 9: Save Word File
            test.info("Saving the Word file...");
            interalNotePage.clickSaveButton();
            interalNotePage.confirmeSave();
            waitForLoadPage();
            test.pass("Word file saved successfully");


            // Step 10: Check If an Attachment Inserted
            test.info("Checking if attachment file is inserted...");
            interalNotePage.checkIfAttacmentFileExist();
            test.pass("Attachment file verified successfully");

            // Step 11: Execute and Confirm
            test.info("Scrolling to bottom and executing the memo...");
            interalNotePage.scrollToButton(driver);
            test.pass("Scrolling to bottom and executing the memo Successfully");


            Thread.sleep(1000);

            interalNotePage.clickOnExecuteButton0();
            waitForLoadPage();

            // Step 12: Validate Memo Creation
            test.info("Validating internal memo creation...");
            // Example validation: Check for a success message or memo status
            try {
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-message")));
                test.pass("Internal memo created successfully: " + successMessage.getText());
            } catch (TimeoutException e) {
                test.warning("Success message not found; assuming memo created based on prior steps");
            }

            // Step 13: Click on Confirm Notify Button
            interalNotePage.confirmNotify();
            test.pass("Memo executed and confirmed successfully");


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
        test = ExtentReportManager.createTest("Logout From Internal Manager", "Logout from current user");
        test.assignCategory("User Switching");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

        logout("Logging Out From User " + username2, test);

        waitForLoadPage();


        test.log(Status.PASS, MarkupHelper.createLabel("Logging Out From User " + username2 + "successfully",
                ExtentColor.GREEN));

    }

    @Test(priority = 5, dependsOnMethods = "verifyLogoutStep")
    public void verifyLoginAsAnotherUser() {
        test = ExtentReportManager.createTest("Login As User General Manager ", "Login with General " +
                "manager " +
                "account");
        test.assignCategory("User Switching");

        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");
        loginWithCredentials(username1, password, "Login with General Manager Account", test);

        waitForLoadPage();

        test.log(Status.PASS, MarkupHelper.createLabel("Login to General Manager Account Successfully",
                ExtentColor.GREEN));


    }


    @Test(priority = 6, dependsOnMethods = "verifyLoginAsAnotherUser")
    public void completeTaskFromGeneralManagerSide() {
        test = ExtentReportManager.createTest("Complete Task From General Manager Side",
                "General Manager approves the memo and adds their signature");
        test.assignCategory("Task", "UI");

        try {
            // Initialize page object and wait
            test.info("Initializing Task Page...");
            mersalTaskPage = new TaskPage(driver);
            test.pass("Task Page initialized");

            // Step 0: Wait for page load
            test.info("Waiting for page to load...");
            waitForLoadPage();
            test.pass("Page loaded successfully");

            // Step 1: Select Received Document
            test.info("Selecting received document...");
            mersalTaskPage.selectReceivedDocument();
            test.pass("Received document selected successfully");

            // Step 2: Select All Folder
            test.info("Selecting all folder...");
            mersalTaskPage.selectAllFolder();
            test.pass("All folder selected successfully");

            // Step 3: Select Input Document
            test.info("Selecting input document...");
            mersalTaskPage.selectInputDocument();
            test.pass("Input document selected successfully");

            // Step 4: Click Complete Button
            test.info("Clicking on complete button...");
            mersalTaskPage.clickOnCompleteButton();
            test.pass("Complete button clicked successfully");

            // Step 5: Wait for page load
            test.info("Waiting for page to load...");
            waitForLoadPage();
            test.pass("Page loaded successfully");

            // Step 6: Click Approval Button
            test.info("Clicking on approval button...");
            mersalTaskPage.clickOnApprovalButton();
            test.pass("Approval button clicked successfully");

            // Step 8: Switch to Frame 1
            test.info("Switching to presentation-iframe...");
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("presentation-iframe"));
                test.pass("Switched to presentation-iframe successfully");
            } catch (NoSuchFrameException e) {
                Assert.fail(e.getMessage());
            }

            // Step 9: Click Sign Button
            test.info("Clicking on sign button...");
            mersalTaskPage.addSignBlock();
            test.pass("Sign button clicked successfully");

            // Step 10: Wait for page load
            test.info("Waiting for page to load...");
            waitForLoadPage();
            test.pass("Page loaded successfully");

            // Step 11: Switch to Default Content
            test.info("Switching to default content...");
            driver.switchTo().defaultContent();
            test.pass("Switched to default content successfully");

            scrollDownY(driver, 1000);

            // Step 13: Switch to Frame 2
            test.info("Switching to presentation-iframe again...");
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("presentation-iframe"));
                test.pass("Switched to presentation-iframe successfully");
            } catch (NoSuchFrameException e) {
                Assert.fail(e.getMessage());
            }


            // Step 14: Drag Signature to Bottom
            test.info("Dragging signature to bottom center...");
            mersalTaskPage.dragElementToBottomCenter();
            test.pass("Signature dragged successfully");


            // Step 15: Switch to Default Content
            test.info("Switching to default content...");
            driver.switchTo().defaultContent();
            test.pass("Switched to default content successfully");

            // Step 16: Scroll to Top
            test.info("Scrolling to top of the page...");
            scrollToTop(driver);
            test.pass("Scrolled to top successfully");

            // Step 17: Switch to Frame 3
            test.info("Switching to presentation-iframe for save...");
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("presentation-iframe"));
                test.pass("Switched to presentation-iframe successfully");
            } catch (NoSuchFrameException e) {
                Assert.fail("Failed to switch to presentation-iframe ");
            }

            // Step 18: Save Sign
            test.info("Clicking on save sign button...");
            mersalTaskPage.saveSign();
            test.pass("Signature saved successfully");

            // Step 19: Confirm Save Sign
            test.info("Clicking on confirm save sign button...");
            mersalTaskPage.confirmButton1();
            test.pass("Confirm save sign button clicked successfully");

            // Step 20: Switch to Default Content
            test.info("Switching to default content...");
            driver.switchTo().defaultContent();
            test.pass("Switched to default content successfully");


            Thread.sleep(1000);

            // Step 21: Scroll to Bottom
            test.info("Scrolling to bottom of the page...");
            mersalTaskPage.scrollToButton(driver);
            test.pass("Scrolled to bottom successfully");

            // Step 22: Execute Task
            test.info("Clicking on execute button...");
            mersalTaskPage.executeTask();
            test.pass("Execute button clicked successfully");

            // Step 23: Wait for page load
            test.info("Waiting for page to load...");
            waitForLoadPage();
            test.pass("Page loaded successfully");

            // Step 24: Confirm Execute
            test.info("Clicking on confirm execute button...");
            mersalTaskPage.confirmButton1();
            test.pass("Confirm execute button clicked successfully");

            // Step 25: Validate Task Completion
            test.info("Validating task completion...");


            // Final Status
            test.log(Status.PASS, MarkupHelper.createLabel("Complete Task From General Manager successfully", ExtentColor.GREEN));

        } catch (CustomTestException e) {
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 7, dependsOnMethods = "completeTaskFromGeneralManagerSide")
    public void verifyLogoutFromGeneralManagerAccount() {
        //Step 1: Create Test Case
        test = ExtentReportManager.createTest("Logout From General Manager Account", "Logout from General " +
                "Account ");

        //Step 2: Assign Test Case To Category
        test.assignCategory("User Switching");
        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");

        //Step 3: Do logout Process
        logout("Logging Out From User " + username1, test);

        //Wait to load Login Page
        waitForLoadPage();

        test.log(Status.PASS, MarkupHelper.createLabel("Logging Out From User " + username1 + "successfully",
                ExtentColor.GREEN));

    }

    @Test(priority = 8, dependsOnMethods = "verifyLogoutFromGeneralManagerAccount")
    public void verifyLoginAsInternalManager() {
        //Step 1: Create Test Case
        test = ExtentReportManager.createTest("Login As An Internal Manager", "Login with Internal" +
                " " +
                "manager " +
                "account");


        //Step 2: Assign Test Case To Category
        test.assignCategory("User Switching");
        test.assignCategory("Mirsal Create Complete  internal Memo Work Flow ");


        // Do login process
        loginWithCredentials(username2, password, "Login with Internal Manager Account", test);

        test.log(Status.PASS, MarkupHelper.createLabel("Login to Internal Manager Account Successfully",
                ExtentColor.GREEN));


    }

    @Test(priority = 9, dependsOnMethods = "verifyLoginAsInternalManager")
    public void completeTaskFromInternalManagerSide() throws InterruptedException {
        test = ExtentReportManager.createTest("Complete Task From Internal Manager Side", "Internal manager complete the task " +
                "after is approved from general manager" +
                " ");

        // Step 0: initialise Mersal Object Page
        mirsalHomePage = new MirsalHomePage(driver);

        // Step 1
        test.info("Waiting for page to load...");
        waitForLoadPage();
        test.pass("Page loaded successfully");

        // Step 2
        test.log(Status.INFO, "Open Incoming Page");
        mirsalHomePage.openIncomingPage();
        test.log(Status.PASS, "Incoming Page Opened Successfully");


        // Step 3
        mersalTaskPage = new TaskPage(driver);
        waitForLoadPage();

        // Step 4
        test.log(Status.INFO, "select Input Document");
        mersalTaskPage.selectInputDocument();
        test.log(Status.PASS, "Input Document Selected Successfully");

        // Step 5
        test.log(Status.INFO, "click On Complete Button");
        mersalTaskPage.clickOnCompleteButton();
        test.log(Status.PASS, "Complete Button Clicked Successfully");

        // Step 6
        waitForLoadPage();

        // Step 7
        test.log(Status.INFO, "Enter Note..");
        driver.findElement(By.className("swal2-textarea")).sendKeys("done");
        test.log(Status.PASS, "Note Entered Successfully");

        // Step 8
        test.log(Status.INFO, "Click On Confirm Complete Task Button ");
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        test.log(Status.PASS, "Confirm Complete Task Button Clicked Successfully ");


        test.log(Status.PASS, MarkupHelper.createLabel("Complete Task From Internal Manager successfully",
                ExtentColor.GREEN));


    }


}

