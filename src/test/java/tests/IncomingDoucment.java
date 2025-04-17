package tests;

import com.aventstack.extentreports.Status;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateInternalElctornicDocument;
import pages.MirsalHomePage;
import pages.RecivedMailPage;
import utils.ExtentReportManager;
import utils.IncomingBaseTest;
import utils.Log;

import java.io.IOException;
import java.time.Duration;

import static utils.General.scrollDownY;

@Listeners(TestListener.class)
public class IncomingDoucment extends IncomingBaseTest {

    @Test(priority = 1)
    public void testLogin() throws InterruptedException, IOException {
        Log.info("Starting login  valid test...");
        test = extent.createTest("Mercal Login Test", "Verify successful login with valid " + "credentials");
        test.info("enter and verify user information");
        mirsalLoginTest.loginUser("seq1", "Ebla1234");
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void creatIncomingDocumentTest() throws InterruptedException {
        test = extent.createTest("Create Incoming Document");
        test.log(Status.INFO, "Get Home page class Instance");
        mirsalHomePage = new MirsalHomePage(driver);
        Thread.sleep(2000);
        test.log(Status.INFO, "Get Internal E Document ");
        createInternalElctornicDocument = new CreateInternalElctornicDocument(driver);
        Thread.sleep(1500);
        test.log(Status.INFO, "Click on create book link");
        createInternalElctornicDocument.clickOnCreateBook();
        Thread.sleep(1000);
        test.log(Status.INFO, "Click on create Electronic Document");
        createInternalElctornicDocument.clickOnCreateEdocument();
        Thread.sleep(1000);
        test.log(Status.INFO, "Select Incoming Document Type ");
        createInternalElctornicDocument.selectDocumentType("Incoming");
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter Electronic Document Name");
        createInternalElctornicDocument.writeSupjectFiled("Test Two");
        Thread.sleep(1000);
        test.log(Status.INFO, "Display Direct-From Options ");
        createInternalElctornicDocument.clickOnDirctFrom();
        Thread.sleep(1000);
        test.log(Status.INFO, "Select Direct-From Option ");
        createInternalElctornicDocument.clickOnSelectDirect();
        test.log(Status.INFO, "Hide Direct-From Options");
        createInternalElctornicDocument.clickOnDirctFrom();
        Thread.sleep(1000);
        test.log(Status.INFO, "Display Direct-To Options ");
        createInternalElctornicDocument.clickOnDirctTo();
        Thread.sleep(1000);
        test.log(Status.INFO, "Select Direct-To Option ");
        createInternalElctornicDocument.clickOnSelectDirectTo();
        test.log(Status.INFO, "Hide Direct-To Options");
        createInternalElctornicDocument.clickOnDirctTo();
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter Book Number");
        createInternalElctornicDocument.enterBookNumber("2334");
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter Book Date");
        createInternalElctornicDocument.enterBookDate("16/3/2025");
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter Category Number ");
        createInternalElctornicDocument.enterBookCatgorayNumber("32");
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter Search Keyword");
        createInternalElctornicDocument.enterSearchKeyword("Book ,Outgoing");
        Thread.sleep(1000);
        test.log(Status.INFO, "Scroll Down ");
        scrollDownY(driver, 275);
        Thread.sleep(1000);
        test.log(Status.INFO, "Select Category of Document :External");
        createInternalElctornicDocument.selectCatgory("External");
        Thread.sleep(1000);
        test.log(Status.INFO, "Enter Description about Document");
        createInternalElctornicDocument.enterDescripton("file about certificated");
        Thread.sleep(1000);
        test.log(Status.INFO, "upload File From Device");
        createInternalElctornicDocument.uplaodAttachedFile("/Users/mac/Downloads/photo_5951767990498345426_y.jpg");
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        test.log(Status.INFO, "Click on Save Button");
        createInternalElctornicDocument.clickSaveButton();
        Thread.sleep(2000);
        test.log(Status.INFO, "Get Actual Document Code ");
        String eInternalNotCode = createInternalElctornicDocument.getActualNotecode();
        test.log(Status.INFO, "Click on Confirm Button");
        createInternalElctornicDocument.clickConfirmButton();
        Thread.sleep(1000);
        test.log(Status.INFO, "Click on Create Book To Show The Received Mail Menu");
        // here to test failed
        mirsalHomePage.clickOnCreateBook();
        Thread.sleep(500);
        recivedMailPage = new RecivedMailPage(driver);
        Thread.sleep(1000);
        test.log(Status.INFO, "Open Received Mail Menu");
        mirsalHomePage.openRecivedMailMenu();
        Thread.sleep(1000);
        test.log(Status.INFO, "Open Received Mail Page");
        mirsalHomePage.openRecivedDoucmentPage();
        Thread.sleep(3000);
        test.log(Status.INFO, "Get Document Code On The to Of Received Mail Page");
        WebElement code = driver.findElement(By.xpath("//table//tr[1]/td[6]"));
        test.log(Status.INFO, "Compare Between The Actual Document Code And Document Code on The Top Of tha Received " +
                "Mail Page");
        Assert.assertEquals(eInternalNotCode, code.getText());
        test.log(Status.INFO, "Select  Incoming Document ");
        driver.findElement(By.xpath("//table//tr[1]/td[1]")).click();
        Thread.sleep(2000);
        test.log(Status.INFO, "Click on Received Button ");
        createInternalElctornicDocument.clickOnRecivedButton();
        Thread.sleep(1000);


    }

}
