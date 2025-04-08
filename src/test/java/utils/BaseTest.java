package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    public WebDriver driver;

    protected static ExtentReports extent;
    protected ExtentTest test;


    //@Parameters("browser")
    @BeforeMethod
    public void setUp() {
       /* if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("browser not supported " + browser);
        }
        */
        Log.info("Starting WebDriver....");
        driver = DriverFactory.getDriver();
        Log.info("Navigate To URL...");
        driver.get("https://www.facebook.com");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "loginTest");
            assert screenshotPath != null;
            test.fail("Test falied check screenshots ",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            //test.addScreenCaptureFromPath(screenshotPath);
        }

        if (driver != null) {
            Log.info("Closing Browser...");
            driver.quit();
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
