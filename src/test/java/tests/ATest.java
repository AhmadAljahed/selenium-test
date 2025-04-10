package tests;

import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class ATest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void googleSearchTest() {
        driver.findElement(By.className("gLFyf")).sendKeys("Selenium WebDriver");
        driver.findElement(By.name("btnK")).submit();
        Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"));
    }

    @Test
    public void failedTest() {
        //driver.findElement(By.name("wrongElement")).click();  // سيؤدي هذا إلى فشل الاختبار
    }

    @AfterTest
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
