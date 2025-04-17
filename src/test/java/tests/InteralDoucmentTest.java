package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import utils.DriverFactory;

import java.time.Duration;

import static utils.General.scrollDownY;


public class InteralDoucmentTest {
    private WebDriver driver;
    private MirsalLoginPage mirsalLoginTest;
    private MirsalHomePage mirsalHomePage;
    private CreateInternalElctornicDocument createInternalElctornicDocument;
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
    public void testLogin() {
        mirsalLoginTest.loginUser("seq1", "Ebla1234");
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void creatInternalDocumentTest() throws InterruptedException {
        mirsalHomePage = new MirsalHomePage(driver);
        Thread.sleep(1000);
        createInternalElctornicDocument = new CreateInternalElctornicDocument(driver);
        Thread.sleep(1000);
        createInternalElctornicDocument.clickOnCreateBook();
        Thread.sleep(1000);
        createInternalElctornicDocument.clickOnCreateEdocument();
        Thread.sleep(1000);
        createInternalElctornicDocument.selectDocumentType("Internal");
        Thread.sleep(1000);

        createInternalElctornicDocument.writeSupjectFiled("Test one ");
        Thread.sleep(1000);
        createInternalElctornicDocument.clickOnDirctFrom();
        Thread.sleep(1000);
        createInternalElctornicDocument.clickOnSelectDirect();
        createInternalElctornicDocument.clickOnDirctFrom();
        Thread.sleep(1000);

        createInternalElctornicDocument.clickOnDirctTo();
        Thread.sleep(1000);
        createInternalElctornicDocument.clickOnSelectDirectTo();
        createInternalElctornicDocument.clickOnDirctTo();
        Thread.sleep(1000);
        createInternalElctornicDocument.enterBookNumber("2334");
        Thread.sleep(1000);
        createInternalElctornicDocument.enterBookDate("16/3/2025");
        Thread.sleep(1000);
        createInternalElctornicDocument.enterBookCatgorayNumber("32");
        Thread.sleep(1000);
        createInternalElctornicDocument.enterSearchKeyword("Book ,Outgoing");
        Thread.sleep(1000);
        createInternalElctornicDocument.scrolldown();
        createInternalElctornicDocument = new CreateInternalElctornicDocument(driver);
        createInternalElctornicDocument.selectCatgory("External");
        Thread.sleep(1000);
        scrollDownY(driver, 400);
        createInternalElctornicDocument.enterDescripton("file about certificated");
        Thread.sleep(1000);

        createInternalElctornicDocument.uplaodAttachedFile("/Users/mac/Downloads/photo_5951767990498345426_y.jpg");
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        createInternalElctornicDocument.clickSaveButton();
        Thread.sleep(2000);
        String eInternalNotCode = createInternalElctornicDocument.getActualNotecode();
        createInternalElctornicDocument.clickConfirmButton();
        Thread.sleep(1000);
        mirsalHomePage.clickOnCreateBook();
        Thread.sleep(500);
        recivedMailPage = new RecivedMailPage(driver);
        Thread.sleep(1000);
        mirsalHomePage.openRecivedMailMenu();
        Thread.sleep(1000);
        mirsalHomePage.openRecivedDoucmentPage();
        Thread.sleep(3000);
        WebElement code = driver.findElement(By.xpath("//table//tr[1]/td[6]"));
        Assert.assertEquals(eInternalNotCode, code.getText());
        driver.findElement(By.xpath("//table//tr[1]/td[1]")).click();
        Thread.sleep(2000);


    }

    @AfterTest
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}