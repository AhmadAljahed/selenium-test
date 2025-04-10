package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MirsalLogout;

import java.time.Duration;

public class DriverFactory {
    public static WebDriver driver;
    static ChromeOptions options = new ChromeOptions();

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver getDriverHedless() {
        if (driver == null) {

            options.addArguments("--headless");  // Enable headless mode
            options.addArguments("--disable-gpu"); // Fixes issues on some OS
            options.addArguments("--window-size=1920,1080"); // Set screen size

            options.addArguments("--no-sandbox"); // Bypass OS security (useful in CI/CD)
            options.addArguments("--disable-dev-shm-usage"); // Prevent crashes in Docker
            options.addArguments("--remote-allow-origins=*"); // Fix cross-origin issues

            driver = new ChromeDriver(options);

        }
        return driver;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }


}
