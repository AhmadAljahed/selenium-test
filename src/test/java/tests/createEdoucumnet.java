package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pages.MersalCreateElctornicDocument;
import pages.MirsalHomePage;
import pages.MirsalLoginPage;
import pages.RecivedMailPage;
import utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static utils.General.scrollDownY;

public class createEdoucumnet {
    private WebDriver driver;
    private MirsalLoginPage mirsalLoginTest;
    private MirsalHomePage mirsalHomePage;
    private MersalCreateElctornicDocument mersalCreateEcopy;
    private RecivedMailPage recivedMailPage;

    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver();
        driver.get("http://10.0.1.18:801/ui/sqwf/");
        mirsalLoginTest = new MirsalLoginPage(driver);
        Thread.sleep(2000);
        driver.manage().window().maximize();

    }


    @Test(priority = 1)
    public void testLogin() throws InterruptedException, IOException {
        mirsalLoginTest.loginUser("seq2", "Ebla1234");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void creatExternalDocumentTest() throws InterruptedException {
        mirsalHomePage = new MirsalHomePage(driver);
        mersalCreateEcopy = new MersalCreateElctornicDocument(driver);
        Thread.sleep(1500);
        mersalCreateEcopy.clickOnCreateBook();
        Thread.sleep(1000);
        mersalCreateEcopy.clickOnCreateEdocument();
        Thread.sleep(1000);
        mersalCreateEcopy.selectDocumentType("Outgoing");
        Thread.sleep(1000);

        mersalCreateEcopy.writeSupjectFiled("كتاب معادله شهاده");
        Thread.sleep(1000);
        mersalCreateEcopy.ClickOnDirctFrom();
        Thread.sleep(1000);
        mersalCreateEcopy.clickOnSelectDirect();
        mersalCreateEcopy.ClickOnDirctFrom();
        Thread.sleep(1000);

        mersalCreateEcopy.ClickOnDirctTo();
        Thread.sleep(1000);
        mersalCreateEcopy.clickOnSelectDirectTo();
        mersalCreateEcopy.ClickOnDirctTo();
        Thread.sleep(1000);
        mersalCreateEcopy.enterBookNumber("2334");
        Thread.sleep(1000);
        mersalCreateEcopy.enterBookDate("12/3/2025");
        Thread.sleep(1000);
        mersalCreateEcopy.enterBookCatgorayNumber("32");
        Thread.sleep(1000);
        mersalCreateEcopy.enterSearchKeyword("Book ,Outgoing");
        Thread.sleep(1000);
        // mersalCreateEcopy.scrolldown();
        Thread.sleep(1000);
        mersalCreateEcopy.selectCatgory("External");
        Thread.sleep(1000);
        scrollDownY(driver, 500);
        Thread.sleep(1000);
        mersalCreateEcopy.clickTransferButton();
        Thread.sleep(1000);
        mersalCreateEcopy.enterDescripton("file about certificated");
        Thread.sleep(1000);

        mersalCreateEcopy.uplaodAttachedFile("/Users/mac/Downloads/photo_5951767990498345426_y.jpg");
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        mersalCreateEcopy.clickSaveButton();
        mersalCreateEcopy.clickConfirmButton();


    }

    @AfterTest
    public void tearDown() {
        DriverFactory.quitDriver();
    }


}
