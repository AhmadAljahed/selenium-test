package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CustomTestException;

import java.io.File;
import java.io.IOException;

import static utils.DriverFactory.driver;

// Utility Class (assumed from previous responses)
class Utility {
    public static void waitForLoadPage(WebDriver driver, WebDriverWait wait) {
        try {
            wait.until(driver1 -> ((JavascriptExecutor) driver1)
                    .executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for page to load", e);
        }
    }

    public static void scrollToTop(WebDriver driver, WebDriverWait wait) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            wait.until(driver1 -> {
                Long currentScrollY = (Long) js.executeScript("return window.scrollY;");
                return currentScrollY == 0;
            });
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for scroll to top", e);
        } catch (JavascriptException e) {
            throw new CustomTestException("Failed to execute scroll-to-top JavaScript: " + e.getMessage(), e);
        }
    }


}