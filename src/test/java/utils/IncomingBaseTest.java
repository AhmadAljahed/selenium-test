package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v134.filesystem.model.Directory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CreateInternalElctornicDocument;
import pages.MirsalHomePage;
import pages.MirsalLoginPage;
import pages.RecivedMailPage;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static pages.BasePage.wait;
import static utils.ExtentReportManager.captureScreenshot;

public class IncomingBaseTest {
    public WebDriver driver;

    protected static ExtentReports extent;
    protected ExtentTest test;
    protected MirsalLoginPage mirsalLoginTest;


    protected MirsalHomePage mirsalHomePage;
    protected CreateInternalElctornicDocument createInternalElctornicDocument;
    protected RecivedMailPage recivedMailPage;

    protected String testName;
    private static final String LOGIN_URL = "http://10.0.1.18:801/ui/sqwf/";

    //@Parameters("browser")
    @BeforeTest
    public void verifyOpenLoginPage() {
        // Initialize Extent Test
        var test = ExtentReportManager.createTest("Verify Open Login Page",
                "Verify that user can access the login page");

        try {
            // Step 1: Initialize WebDriver
            test.log(Status.INFO, "Starting WebDriver...");
            driver = DriverFactory.getDriver();
            test.log(Status.PASS, "WebDriver initialized successfully");

            // Step 2: Navigate to Login Page and wait for URL (up to 10 seconds)
            test.log(Status.INFO, "Navigating to Mersal Login Page: " + LOGIN_URL);
            driver.get(LOGIN_URL);
            test.log(Status.PASS, "Successfully navigated to login page");

            // Step 3: Maximize Browser Window
            test.log(Status.INFO, "Maximizing browser window...");
            driver.manage().window().maximize();
            test.log(Status.PASS, "Browser window maximized");

            // Step 4: Initialize Page Object
            test.log(Status.INFO, "Initializing Mirsal Login Page object...");
            mirsalLoginTest = new MirsalLoginPage(driver);
            test.log(Status.PASS, "Page object created successfully");

        } catch (Exception e) {
            // Log failure and attach screenshot
            String errorMessage = "Test failed due to: " + e.getMessage();
            test.log(Status.FAIL, errorMessage);
            // Optionally log stack trace
            test.log(Status.FAIL, MarkupHelper.createLabel("Exception Stacktrace", ExtentColor.RED));
            String screenshotPath = captureScreenshot(driver, "LoginPageFailure");
            if (screenshotPath != null) {
                test.log(Status.FAIL, "Screenshot on failure:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
            throw new RuntimeException(errorMessage, e); // Re-throw to fail the test
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Get the error message from the test failure
            String errorMessage = result.getThrowable() != null
                    ? result.getThrowable().getMessage()
                    : "Unknown error occurred";

            // Sanitize error message for use in screenshot filename (remove special characters)
            String sanitizedError = errorMessage.replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();

            String truncatedError = sanitizedError.length() > 50
                    ? sanitizedError.substring(0, 50)
                    : sanitizedError;
            String screenshotName = result.getName() + "_" + truncatedError;

            // Capture screenshot with meaningful name
            String screenshotPath = captureScreenshot(driver, screenshotName);
            if (screenshotPath != null) {
                // Log failure with error message and screenshot in Extent Reports
                test.fail("Test '" + result.getName() + "' failed with error: " + errorMessage + " check the " +
                                "screenshot",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                test.fail("Test '" + result.getName() + "' failed with error: " + errorMessage +
                        ". Failed to capture screenshot.");
            }
        }
    }


    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    @AfterTest
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
