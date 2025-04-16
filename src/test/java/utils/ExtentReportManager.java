package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;

    private static ExtentTest test;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = "reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Reports");
            sparkReporter.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String details) {
        test = extent.createTest(testName, details);
        return test;

    }


    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Define the screenshot directory and file path
            String screenshotDir = "screenshots";
            String path = screenshotDir + "/" + testName + ".png";

            // Create the screenshots directory if it doesn't exist
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Creates directory and any necessary parent directories
            }

            // Capture the screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File(path));

            // Return the relative path for reporting
            return "/" + path;
        } catch (Exception e) {
            System.err.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }


    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
