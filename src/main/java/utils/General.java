package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

import static pages.BasePage.wait;


public class General {
    public static void waitAndClick(WebElement w) {
        wait.until(ExpectedConditions.elementToBeClickable(w)).click();
    }

    public static void scrollDownY(WebDriver driver, int pixels) {
        // Cast the WebDriver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Execute JavaScript to scroll down by the specified pixels
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public static void scrollToTop(WebDriver driver) {
        // Cast the WebDriver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Execute JavaScript to scroll to the top (0, 0)
        js.executeScript("window.scrollTo(0, 0);");
    }
}
