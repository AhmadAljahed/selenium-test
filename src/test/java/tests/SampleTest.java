package tests;

import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class SampleTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.facebook.com");
    }

    @Test
    public void googleSearchTest() {
        driver.findElement(By.id("email")).sendKeys("Selenium WebDriver");
        driver.findElement(By.name("login")).submit();
        // Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"));
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
