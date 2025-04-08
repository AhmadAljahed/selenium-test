package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MersalCreateElctornicDocument extends BasePage {
    @FindBy(xpath = "//*[@id='js-nav-menu']/li[2]/a/span")
    private WebElement createbook;
    @FindBy(xpath = "//*[@id='js-nav-menu']/li[2]/ul/li[2]/a")
    private WebElement createEdocument;

    @FindBy(id = "ddlCorrespType")
    private WebElement selectFiled;

    @FindBy(id = "txtDocSubject")
    private WebElement subjectFiled;

    @FindBy(xpath = "//div/button[@id='sitefrom']")
    private WebElement DirectFrom;

    @FindBy(xpath = "//li[@data-nodeid='7']")
    private WebElement selectDirect;
    @FindBy(xpath = "//button[@id='siteto']")
    private WebElement DirectTo;
    @FindBy(xpath = "//li[@name-target='صناعة']")
    private WebElement selectDirectTo;

    @FindBy(id = "txtReferenceNo")
    private WebElement bookNumber;

    @FindBy(id = "txtDocDate")
    private WebElement bookDate;

    @FindBy(id = "txtHeadLinesCode")
    private WebElement bookCode;

    @FindBy(id = "txtSearchKeyword")
    private WebElement searchKeyword;

    @FindBy(id = "ddlAttachCatogory")
    private WebElement attachCatagory;

    @FindBy(id = "txtAttachDesc")
    private WebElement descrption;

    @FindBy(xpath = "//*[@id='SendToG2G']/div[1]/div/label")
    private WebElement trasferInput;
    @FindBy(id = "FileUploadDocument")
    private WebElement attachedFile;
    @FindBy(id = "btnAddNewDoc")
    private WebElement saveButton;
    @FindBy(css = ".swal2-confirm")
    private WebElement confirmButton;


    public MersalCreateElctornicDocument(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreateBook() {
        createbook.click();
    }

    public void clickOnCreateEdocument() {
        try {
            //set up waita
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement eDocument = wait.until(ExpectedConditions.elementToBeClickable(createEdocument));
            eDocument.click();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    ;


    public void selectDocumentType(String type) {
        selectFiled.click();
        Select dropdown = new Select(selectFiled);
        dropdown.selectByValue(type);
    }

    public void writeSupjectFiled(String subjectName) {
        subjectFiled.sendKeys(subjectName);
    }

    public void ClickOnDirctFrom() {
        DirectFrom.click();
    }

    public void clickOnSelectDirect() {
        selectDirect.click();
    }

    public void ClickOnDirctTo() {
        DirectTo.click();
    }

    public void clickOnSelectDirectTo() {
        selectDirectTo.click();
    }

    public void enterBookNumber(String number) {
        bookNumber.sendKeys(number);
    }

    public void enterBookDate(String BookDate) {
        bookDate.sendKeys(BookDate);
    }

    public void enterBookCatgorayNumber(String bookcatogary) {
        bookCode.sendKeys(bookcatogary);
    }

    public void enterSearchKeyword(String keyword) {
        searchKeyword.sendKeys(keyword);
    }


    public void selectCatgory(String catogray) {
        attachCatagory.click();
        Select dropdown = new Select(attachCatagory);
        dropdown.selectByValue(catogray);
    }

    public void enterDescripton(String des) {
        descrption.sendKeys(des);
    }

    public void clickTransferButton() {
        trasferInput.click();
    }

    public void uplaodAttachedFile(String filepath) {
        attachedFile.sendKeys(filepath);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void scrolldown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
    }

    public void clickConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }
}