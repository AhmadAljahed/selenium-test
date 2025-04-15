package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.General.waitAndClick;


public class MirsalHomePage extends BasePage {
    @FindBy(id = "PersonalProfileLink")
    private WebElement profileLink;

    @FindBy(linkText = "إنشاء وفهرسة الكتب")
    private WebElement createbook;

    @FindBy(xpath = "//*[@id='js-nav-menu']/li[2]/ul/li[3]/a/span")
    private WebElement createInteralNote;
    @FindBy(xpath = "//a[@appsid='121']")
    private WebElement recivedMailMenu;

    @FindBy(xpath = "//a[@data-filter-tags='اسـتـلام الوثايق']")
    private WebElement recivedDocumentPage;
    @FindBy(xpath = "//a[@appsid='5']")
    private WebElement outGoingButton;
    @FindBy(xpath = "//a[@appsid='3']")
    private WebElement incoming;
    @FindBy(xpath = "/html/body/div[2]/div/div/header/div[7]/div[3]/a")
    private WebElement profileImage;
    @FindBy(xpath = "//span[text()='اصدار كتاب خارجي']")
    private WebElement externalBook;

    @FindBy(xpath = "//div[@data-progress-test]")
    private WebElement dataProgress;

    public MirsalHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreateBook() {
        wait.until(ExpectedConditions.elementToBeClickable(createbook)).click();
    }

    public void clickOnCreateInteralNote() {


        wait.until(ExpectedConditions.elementToBeClickable(createInteralNote)).click();
    }

    public void openRecivedMailMenu() {

        wait.until(ExpectedConditions.elementToBeClickable(recivedMailMenu)).click();
    }

    public void openRecivedDoucmentPage() {

        wait.until(ExpectedConditions.elementToBeClickable(recivedDocumentPage)).click();
    }

    public void openOutGoingPage() {

        wait.until(ExpectedConditions.elementToBeClickable(outGoingButton)).click();
    }


    public void openIncomingPage() {
        wait.until(ExpectedConditions.elementToBeClickable(incoming));
        incoming.click();

    }

    public void clickOnProfileimage() {
        profileImage.click();
    }

    public void openExternalBook() {
        externalBook.click();
    }
}
