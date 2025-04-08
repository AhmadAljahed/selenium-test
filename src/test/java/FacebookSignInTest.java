import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;


public class FacebookSignInTest {
    public String baseUrl = "https://jqueryui.com/droppable/";
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void FluentWait() throws InterruptedException, IOException {
        driver.get(baseUrl);
        driver.switchTo().frame(0);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        action.dragAndDrop(source, target).perform();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source1 = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source1, new File("./Screenshots/Screen.png"));
        System.out.println("the screen shoots is taken ");

    }

    @AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit(); // يغلق جميع النوافذ المفتوحة
    }
}
