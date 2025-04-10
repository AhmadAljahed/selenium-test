package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CreateInternalElctornicDocument;
import pages.MirsalHomePage;
import pages.MirsalLoginPage;
import pages.RecivedMailPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class IncomingBaseTest {
    public WebDriver driver;

    protected static ExtentReports extent;
    protected ExtentTest test;
    protected MirsalLoginPage mirsalLoginTest;


    protected MirsalHomePage mirsalHomePage;
    protected CreateInternalElctornicDocument createInternalElctornicDocument;
    protected RecivedMailPage recivedMailPage;

    protected String testName;

    //@Parameters("browser")
    @BeforeTest
    public void setUp() {
        Log.info("Starting WebDriver....");
        driver = DriverFactory.getDriver();
        Log.info("Navigate To URL...");
        driver.get("http://10.0.1.18:801/ui/sqwf/");
        Log.info("Maximize Browser window");
        driver.manage().window().maximize();
        Log.info("Create page class");
        mirsalLoginTest = new MirsalLoginPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "testLogin");
            assert screenshotPath != null;
            test.fail("Test falied check screenshots ",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            //test.addScreenCaptureFromPath(screenshotPath);
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

}
