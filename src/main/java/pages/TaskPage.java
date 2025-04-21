package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TaskPage extends BasePage {
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@appsid='3']")
    private WebElement receivedDocument;

    @FindBy(xpath = "//a[@data-filter-tags='الكل']")
    private WebElement allFolder;

    @FindBy(css = "table tr:first-child td:first-child")
    private WebElement inputDocument;

    @FindBy(id = "AddCompleteBtn")
    private WebElement completeButton;

    @FindBy(id = "NewApproveBtn")
    private WebElement approvalButton;

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
    private WebElement executeTaskBtn;

    @FindBy(css = ".swal2-confirm")
    private WebElement confirmButton1;

    public TaskPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectReceivedDocument() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(receivedDocument)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Received document link not found: " + receivedDocument, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Received document link is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for received document link to be clickable", e);
        }
    }

    public void selectInputDocument() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputDocument)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Input document element not found: " + inputDocument, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Input document element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for input document element to be clickable", e);
        }
    }

    public void clickOnCompleteButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(completeButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Complete button not found: " + completeButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Complete button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for complete button to be clickable", e);
        }
    }

    public void clickOnApprovalButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(approvalButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Approval or confirm button not found: " + approvalButton + " or " + confirmButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Approval or confirm button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for approval or confirm button to be clickable", e);
        }
    }

    public void handleAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (NoAlertPresentException e) {
            throw new CustomTestException("No alert present to handle", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for alert to appear", e);
        }
    }

    public void addSignBlock() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Sign button not found: " + signButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Sign button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for sign button to be clickable", e);
        }
    }

    public void dragElementToBottomCenter() throws InterruptedException {
        try {
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

            // Validate drag-and-drop (check if source is within target bounds)
            Point sourceLocationAfter = source.getLocation();
            if (sourceLocationAfter.getY() < targetY || sourceLocationAfter.getY() > targetY + height) {
                throw new CustomTestException("Drag-and-drop failed: Source element not positioned within target bounds");
            }

        } catch (NoSuchElementException e) {
            throw new CustomTestException("Source or target element not found: " + source + " or " + target, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Source or target element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for source or target element to be clickable", e);
        } catch (MoveTargetOutOfBoundsException e) {
            throw new CustomTestException("Drag-and-drop failed: Target position out of bounds", e);
        } catch (WebDriverException e) {
            throw new CustomTestException("Drag-and-drop failed due to WebDriver issue: " + e.getMessage(), e);
        }

    }

    public void selectAllFolder() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(allFolder)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("All folder link not found: " + allFolder, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("All folder link is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for all folder link to be clickable", e);
        }
    }

    public void saveSign() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveSignButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Save sign or confirm button not found: " + saveSignButton + " or " + confirmButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Save sign or confirm button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for save sign or confirm button to be clickable", e);
        }
    }

    public void scrollToButton(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", executeTaskBtn);
            wait.until(driver1 -> {
                Number scrollY = (Number) js.executeScript("return window.scrollY + window.innerHeight;");
                Number buttonY = (Number) js.executeScript("return arguments[0].getBoundingClientRect().top + window" +
                        ".scrollY;", executeTaskBtn);
                return buttonY.doubleValue() < scrollY.doubleValue();
            });
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Execute button not found for scrolling: " + executeTaskBtn, e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for scroll to execute button", e);
        } catch (JavascriptException e) {
            throw new CustomTestException("Failed to execute scroll JavaScript: " + e.getMessage(), e);
        }
    }

    public void executeTask() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(executeTaskBtn)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Execute task button not found: " + executeTaskBtn, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Execute task button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for execute task button to be clickable", e);
        }
    }

    public void confirmButton1() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmButton1)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Confirm button not found: " + confirmButton1, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Confirm button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for confirm button to be clickable", e);
        }
    }
}