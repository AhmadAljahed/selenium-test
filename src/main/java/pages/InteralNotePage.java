package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.w3c.dom.html.HTMLInputElement;

import static utils.General.waitAndClick;

public class InteralNotePage extends BasePage {
    WebDriver driver;

    @FindBy(id = "ViewContentSubject")
    private WebElement subject;

    @FindBy(xpath = "//*[@id='MainUsersList']/div[1]/a/span[1]")
    private WebElement authorized;

    @FindBy(id = "PrepareDocumentBtn")
    private WebElement wordfile;

    @FindBy(id = "SaveDocument")
    private WebElement saveButton;

    @FindBy(id = "ExcuteTaskBtn")
    private WebElement excuteButton0;

    @FindBy(css = ".swal2-confirm")
    private WebElement confairmButton;
    // @FindBy(css = ".swal2-confirm")
    //private WebElement NoteCode;
    // @FindBy(id = "swal2-title")
    // private WebElement atcualnoteCode;
    @FindBy(css = "a[data-original-title='إخفاء قائمة الشاشات']")
    private WebElement navList;
    @FindBy(css = "a[data-filter-tags= 'الرئيسية']")
    private WebElement homelink;

    @FindBy(xpath = "/html/body/div[10]/div/div[3]/button[1]")
    private WebElement confirmNotify;

    @FindBy(xpath = "//label[@for='WfActionRd1']")
    private WebElement reason;

    @FindBy(id = "Notes")
    private WebElement Notes;

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

    public InteralNotePage(WebDriver driver) {
        super(driver);
    }

    public void writeSubject(String noteName) {

        wait.until(ExpectedConditions.visibilityOf(subject)).sendKeys(noteName);
    }

    public void selectAuthorized() {

        wait.until(ExpectedConditions.elementToBeClickable(authorized)).click();
    }

    public void loadWordFile() {

        waitAndClick(wordfile);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void
    clickOnExecuteButton0() {
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("PrepareDocumentBox")));
        // wait.until(ExpectedConditions.elementToBeClickable(By.id("ExcuteTaskBtn"))).click();
        excuteButton0.click();


    }

    public void confirmeSave() {
        wait.until(ExpectedConditions.elementToBeClickable(confairmButton))
                .click();
    }


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

    public void getHomePage() {
        wait.until(ExpectedConditions.elementToBeClickable(navList)).click();
        wait.until(ExpectedConditions.elementToBeClickable(homelink)).click();
    }

    public void confirmNotify() {

        wait.until(ExpectedConditions.visibilityOf(confirmNotify)).click();
    }

    public void selectReason() {
        wait.until(ExpectedConditions.elementToBeClickable(reason)).click();
    }

    public void selectNote() {
        wait.until(ExpectedConditions.elementToBeClickable(Notes)).click();
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

        waitAndClick(personButton);

        waitAndClick(personInput);
    }
}
