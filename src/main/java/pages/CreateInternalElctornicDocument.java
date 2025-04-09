package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.General.waitAndClick;

public class CreateInternalElctornicDocument extends BasePage {
    @FindBy(xpath = "//*[@id='js-nav-menu']/li[2]/a/span")
    private WebElement createbook;
    @FindBy(xpath = "//*[@id='js-nav-menu']/li[2]/ul/li[2]/a")
    private WebElement createEdocument;

    @FindBy(id = "ddlCorrespType")
    private WebElement selectFiled;

    @FindBy(id = "txtDocSubject")
    private WebElement subjectFiled;

    @FindBy(xpath = "//button[@name-target='#DAWSiteFrom']")
    private WebElement DirectFrom;

    @FindBy(xpath = "//li[@data-nodeid='1']")
    private WebElement selectDirect;
    @FindBy(xpath = "//button[@id='siteto']")
    private WebElement DirectTo;
    @FindBy(xpath = "//li[@data-nodeid='0']")
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
    @FindBy(css = ".menutopcard")
    private WebElement rightSection;
    @FindBy(id = "swal2-title")
    private WebElement atcualnoteCode;

    @FindBy(id = "AddRecieveBtn")
    private WebElement recievedButton;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public CreateInternalElctornicDocument(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreateBook() {
        waitAndClick(createbook);
    }

    public void clickOnCreateEdocument() {
        try {
            //set up wait

            WebElement eDocument = wait.until(ExpectedConditions.elementToBeClickable(createEdocument));
            eDocument.click();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    ;


    public void selectDocumentType(String type) {
        wait.until(ExpectedConditions.elementToBeClickable(selectFiled)).click();
        Select dropdown = new Select(selectFiled);
        dropdown.selectByValue(type);
    }

    public void writeSupjectFiled(String subjectName) {
        wait.until(ExpectedConditions.elementToBeClickable(subjectFiled)).sendKeys(subjectName);
    }

    public void clickOnDirctFrom() {
        waitAndClick(DirectFrom);
    }

    public void clickOnSelectDirect() {
        waitAndClick(selectDirect);
    }

    public void clickOnDirctTo() {
        waitAndClick(DirectTo);
    }

    public void clickOnSelectDirectTo() {
        waitAndClick(selectDirectTo);
    }

    public void enterBookNumber(String number) {
        wait.until(ExpectedConditions.elementToBeClickable(bookNumber)).sendKeys(number);
    }

    public void enterBookDate(String BookDate) {
        wait.until(ExpectedConditions.elementToBeClickable(bookDate)).sendKeys(BookDate);
    }

    public void enterBookCatgorayNumber(String bookcatogary) {
        wait.until(ExpectedConditions.elementToBeClickable(bookCode)).sendKeys(bookcatogary);
    }

    public void enterSearchKeyword(String keyword) {
        wait.until(ExpectedConditions.elementToBeClickable(searchKeyword)).sendKeys(keyword);

    }


    public void selectCatgory(String catogray) {
        waitAndClick(attachCatagory);
        Select dropdown = new Select(attachCatagory);
        dropdown.selectByValue(catogray);
    }

    public void enterDescripton(String des) {
        wait.until(ExpectedConditions.elementToBeClickable(descrption)).sendKeys(des);

    }

    public void clickTransferButton() {
        waitAndClick(trasferInput);
    }

    public void uplaodAttachedFile(String filepath) {
        wait.until(ExpectedConditions.elementToBeClickable(attachedFile)).sendKeys(filepath);
    }

    public void clickSaveButton() {
        waitAndClick(saveButton);
    }

    public void scrolldown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
    }

    public void clickConfirmButton() {
        waitAndClick(confirmButton);
    }

    public void clickRightSection() {
        waitAndClick(rightSection);
    }

    public String getActualNotecode() {
        String altCode = filterEnglishLitters(atcualnoteCode.getText());
        return altCode;
    }

    public static String filterEnglishLitters(String text) {
        return text.replaceAll("[^a-zA-Z0-9_]", "");
    }

    public void clickOnRecivedButton() {
        waitAndClick(recievedButton);
    }
}

