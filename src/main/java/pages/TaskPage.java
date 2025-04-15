package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Element;


public class TaskPage extends BasePage {
    public TaskPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@appsid='3']")
    private WebElement recivedDocument;
    @FindBy(xpath = "//a[@href='http://10.0.1.18:801/ui/sqwf/workflow/wfinbox' and @data-filter-tags='الكل']")
    private WebElement allFolder;
    @FindBy(css = "table tr:first-child td:first-child")
    private WebElement inputDocument;
    @FindBy(id = "AddCompleteBtn")
    private WebElement completeButton;
    @FindBy(id = "NewApproveBtn")
    private WebElement approvelButton;
    @FindBy(xpath = "//button[text()='نعم']")
    private WebElement confirmButton;
    @FindBy(id = "AddSignBlockBtn")
    private WebElement signButton;
    @FindBy(id = "signcontainer")
    private WebElement source;
    @FindBy(className = "textLayer")
    private WebElement target;
    @FindBy(id = "savesignimage")
    private WebElement saveSignButton;

    @FindBy(id = "ExcuteTaskBtn")
    private WebElement excuteTaskBtn;
    @FindBy(css = ".swal2-confirm")
    private WebElement confirmButton1;

    public void selectRecivedDocument() {
        wait.until(ExpectedConditions.elementToBeClickable(recivedDocument));
        recivedDocument.click();
    }

    public void selectInputDocument() {
        wait.until(ExpectedConditions.elementToBeClickable(inputDocument));
        inputDocument.click();
    }

    public void clickOnCompletButton() {
        wait.until(ExpectedConditions.elementToBeClickable(completeButton)).click();
    }

    public void clickOnApprovrelButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(approvelButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();

    }

    public void handelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void AddSignBlockBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(signButton));
        signButton.click();
    }

    public void dragElementToBottomCenter() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(target));
        //Get target element size
        Dimension targetSize = target.getSize();
        int width = targetSize.getWidth();
        int height = targetSize.getHeight();
        int targetWidth = width / 2;
        int targetHeight = height / 2;
        //Get target element position
        Point targetLocation = target.getLocation();
        int targetX = targetLocation.getX();
        int targetY = targetLocation.getY();
        // Calculate middle bottom position
        int middleX = targetX + (targetWidth / 2);
        int bottomY = targetY + targetHeight - 5; // 5px above the bottom

        // Perform drag and drop
        // Perform drag and drop using offsets
        Actions actions = new Actions(driver);
        actions.clickAndHold(source)
                .moveByOffset(middleX - source.getLocation().getX() - 30, bottomY - source.getLocation().getY() - 120)
                .release()
                .perform();

    }


    public void selectAddFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(allFolder));
        allFolder.click();
    }

    public void savaSign() {
        wait.until(ExpectedConditions.elementToBeClickable(saveSignButton));
        saveSignButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void executeTask() {
        wait.until(ExpectedConditions.elementToBeClickable(excuteTaskBtn));
        excuteTaskBtn.click();


    }

    public void confirmButton1() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton1)).click();

    }


}
