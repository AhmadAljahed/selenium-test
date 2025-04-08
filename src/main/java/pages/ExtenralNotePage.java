package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.General.waitAndClick;

public class ExtenralNotePage extends BasePage {
    WebDriver driver;

    @FindBy(id = "ViewContentSubject")
    private WebElement subject;

    @FindBy(id = "PrepareDocumentBtn")
    private WebElement wordfile;

    @FindBy(id = "SaveDocument")
    private WebElement saveButton;

    @FindBy(id = "btnSearchCorrespondence")
    private WebElement searchButton;

    @FindBy(id = "searchattachBtn")
    private WebElement mainSearchButton;


    @FindBy(xpath = "//*[@id='GridBodySearch']/tr[1]/td[1]")
    public WebElement document;

    @FindBy(xpath = "//a[@rval='SearchDocument']")
    private WebElement attached;

    @FindBy(xpath = "//a[@rval='SearchDecisions']")
    private WebElement desicion;

    @FindBy(xpath = "//a[@rval='SearchInSijil']")
    private WebElement register;

    @FindBy(id = "SearchboxCloseBtn")
    private WebElement closeSearchBoxButton;

    @FindBy(id = "btnpersons")
    private WebElement personButton;

    @FindBy(css = "#usermnglist > li:nth-child(1) > div > ul > li > a > div")
    private WebElement personInput;

    @FindBy(id = "PrepareDocumentBox")
    private WebElement documentFram;

    @FindBy(xpath = "//button[@code-target='#ExDAWSiteFromCode']")
    private WebElement DirectFrom;

    @FindBy(xpath = "//li[@data-nodeid='1']")
    private WebElement selectDirectFrom;

    @FindBy(id = "btnaddsites")
    private WebElement addButton;

    @FindBy(id = "PrepareOutGoingDocumentBtn")
    private WebElement outGoingDocumentPrepeare;

    @FindBy(id = "SaveOutGoingDocument")
    private WebElement saveOutGoingDocumentBtn;

    @FindBy(xpath = "//button[text()='استمرار']")
    private WebElement confirmButton;

    @FindBy(xpath = "//button[text()='نعم']")
    private WebElement confirmButton2;

    @FindBy(xpath = "//button[text()='تم']")
    private WebElement confirmButton3;

    @FindBy(id = "EditWordAttachOuter")
    private WebElement editeWordDoucment;

    @FindBy(css = "#RatificationOutGoingDocument")
    private WebElement ratificationOutGoing;

    @FindBy(id = "AddSignBlockBtn")
    private WebElement addSigntur;

    @FindBy(xpath = "//*[@id='savesignimage']")
    private WebElement saveSignButton;

    @FindBy(id = "signcontainer")
    private WebElement source;

    public ExtenralNotePage(WebDriver driver) {
        super(driver);
    }

    public void writeSubject(String noteName) {
        wait.until(ExpectedConditions.elementToBeClickable(subject)).sendKeys(noteName);
    }


    public void loadWordFile() {

        wait.until(ExpectedConditions.elementToBeClickable(wordfile))
                .click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }


    @FindBy(className = "textLayer")
    private WebElement target;


    /*public void hanelAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    */

    //public String getNoteCode() {
    //   return NoteCode.getText();
    // }


    public static String filterEnglishLitters(String text) {
        return text.replaceAll("[^a-zA-Z0-9_]", "");
    }

    public void clickAtOffset(WebDriver driver, int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().sendKeys("hello world 1").perform();
    }

    public void scrollToButton(WebDriver driver1) {
        JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void scrollTo(WebDriver driver1) {
        JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("window.scrollTo(0,500);");
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void choosDocument() {
        wait.until(ExpectedConditions.elementToBeClickable(document)).click();
    }

    public void getAttached() {
        wait.until(ExpectedConditions.elementToBeClickable(attached)).click();
    }

    public void getDecision() {
        wait.until(ExpectedConditions.elementToBeClickable(desicion)).click();
    }

    public void getRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(register)).click();
    }

    public void clickOnManiSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(mainSearchButton)).click();
    }

    public void clickCloseSearchBox() {
        wait.until(ExpectedConditions.elementToBeClickable(closeSearchBoxButton)).click();
    }

    public void selectPersion() {

        wait.until(ExpectedConditions.elementToBeClickable(personButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(personInput)).click();
    }

    public void clickOnDirctFrom() {

        wait.until(ExpectedConditions.elementToBeClickable(DirectFrom)).click();
    }

    public void clickOnSelectDirect() {
        wait.until(ExpectedConditions.elementToBeClickable(selectDirectFrom)).click();
    }

    public void addDirection() {
        waitAndClick(addButton);
    }

    public void clickOutGonigBtn() {
        outGoingDocumentPrepeare.click();
    }

    public void clickSavaOutGoingBtn() {
        waitAndClick(saveOutGoingDocumentBtn);
    }

    public void clickConfirm() {
        waitAndClick(confirmButton3);
    }

    public void editeDocument() {
        waitAndClick(editeWordDoucment);
    }

    public void clickOnRafactring() {
        waitAndClick(ratificationOutGoing);
    }

    public void clickYes() {
        waitAndClick(confirmButton2);
    }

    public void addSigntur() {
        waitAndClick(addSigntur);
    }

    public void savaSign() {
        waitAndClick(saveSignButton);
        waitAndClick(confirmButton2);
    }

    public void confirmRatification() {
        waitAndClick(confirmButton3);
    }

    public void confirmeContonu() {
        waitAndClick(confirmButton);
    }


}
