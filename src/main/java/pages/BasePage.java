package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasePage {
    WebDriver driver;
    public static WebDriverWait wait;

    public static WebDriverWait wait5s;

    public static WebDriverWait wait1s;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        // this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait5s = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait1s = new WebDriverWait(driver, Duration.ofSeconds(1));

    }


    public static void waitForLoadPage() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.pace.pace-active")));
    }


}
