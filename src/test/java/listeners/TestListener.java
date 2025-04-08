package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        WebDriver driver = DriverFactory.getDriver();
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenShotDir = new File("screenShots");
        if (!screenShotDir.exists()) {
            screenShotDir.mkdir();
        }
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            FileUtils.copyFile(screenShot, new File("screenShots/" + result.getName() + "_" + timestamp + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("✅✅✅ All Tests Finished ✅✅✅");
    }
}
