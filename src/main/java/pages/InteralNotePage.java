package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.General.waitAndClick;

// Custom Exception Class
class CustomTestException extends RuntimeException {
    public CustomTestException(String message) {
        super(message);
    }

    public CustomTestException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class InteralNotePage extends BasePage {
    private WebDriverWait wait;
    private WebDriverWait wait5s; // For methods with 5-second waits

    @FindBy(id = "ViewContentSubject")
    private WebElement subjectField;

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

    @FindBy(css = "a[data-original-title='إخفاء قائمة الشاشات']")
    private WebElement navList;

    @FindBy(css = "a[data-filter-tags='الرئيسية']")
    private WebElement homelink;

    @FindBy(xpath = "/html/body/div[10]/div/div[3]/button[1]")
    private WebElement confirmNotify;

    @FindBy(xpath = "//label[@for='WfActionRd1']")
    private WebElement reason;

    @FindBy(id = "Notes")
    private WebElement notes;

    @FindBy(id = "btnSearchCorrespondence")
    private WebElement searchButton;

    @FindBy(id = "searchattachBtn")
    private WebElement mainSearchButton;

    @FindBy(xpath = "//*[@id='GridBodySearch']/tr[1]/td[1]")
    private WebElement document;

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

    @FindBy(id = "NoPersons")
    private WebElement numberOfPerson;

    public InteralNotePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wait5s = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void checkSubjectFieldIsEmpty() {
        try {
            String value = subjectField.getAttribute("value");
            if (value == null || value.trim().isEmpty()) {
                throw new CustomTestException("Memo Name Field is empty");
            }
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Subject field not found: " + subjectField, e);
        }
    }

    public void enterSubject(String name) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(subjectField));
            subjectField.clear(); // Clear existing text
            subjectField.sendKeys(name);
            String actualValue = subjectField.getAttribute("value");
            if (!actualValue.equals(name)) {
                throw new CustomTestException("Failed to enter subject: Expected '" + name + "', but got '" + actualValue + "'");
            }
            checkSubjectFieldIsEmpty();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Subject field not found: " + subjectField, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Subject field is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for subject field to be clickable", e);
        }
    }

    public void selectAuthorized() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(authorized)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Authorized element not found: " + authorized, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Authorized element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for authorized element to be clickable", e);
        }
    }

    public void loadWordFile() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wordfile)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Word file button not found: " + wordfile, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Word file button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for word file button to be clickable", e);
        }
    }

    public void clickSaveButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Save button not found: " + saveButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Save button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for save button to be clickable", e);
        }
    }

    public void clickOnExecuteButton0() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(excuteButton0)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Execute button not found: " + excuteButton0, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Execute button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for execute button to be clickable", e);
        }
    }

    public void confirmeSave() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confairmButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Confirm save button not found: " + confairmButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Confirm save button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for confirm save button to be clickable", e);
        }
    }

    public void getHomePage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(navList)).click();
            wait.until(ExpectedConditions.elementToBeClickable(homelink)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Navigation list or home link not found: " + navList + " or " + homelink, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Navigation list or home link is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for navigation list or home link to be clickable", e);
        }
    }

    public void confirmNotify() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmNotify)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Confirm notify button not found: " + confirmNotify, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Confirm notify button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for confirm notify button to be clickable", e);
        }
    }

    public void selectReason() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(reason)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Reason element not found: " + reason, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Reason element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for reason element to be clickable", e);
        }
    }

    public void selectNote() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(notes)).click();
            if (!notes.getAttribute("value").isEmpty()) {
                throw new CustomTestException("Notes field should be empty before selection");
            }
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Notes field not found: " + notes, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Notes field is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for notes field to be clickable", e);
        }
    }

    public void clickAtOffset(WebDriver driver, int x, int y) {
        try {
            Actions actions = new Actions(driver);
            actions.moveByOffset(x, y).click().sendKeys("hello world 1").perform();
        } catch (MoveTargetOutOfBoundsException e) {
            throw new CustomTestException("Invalid offset coordinates (" + x + ", " + y + ")", e);
        } catch (WebDriverException e) {
            throw new CustomTestException("Failed to perform click at offset: " + e.getMessage(), e);
        }
    }

    public void scrollToButton(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", excuteButton0);
            wait.until(driver1 -> {
                Number scrollY = (Number) js.executeScript("return window.scrollY + window.innerHeight;");
                Number buttonY = (Number) js.executeScript("return arguments[0].getBoundingClientRect().top + window" +
                        ".scrollY;", excuteButton0);
                return buttonY.doubleValue() < scrollY.doubleValue();
            });
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Execute button not found for scrolling: " + excuteButton0, e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for scroll to execute button", e);
        } catch (JavascriptException e) {
            throw new CustomTestException("Failed to execute scroll JavaScript: " + e.getMessage(), e);
        }
    }

    public void scrollTo(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 200);");
            wait.until(driver1 -> {
                Long currentScrollY = (Long) js.executeScript("return window.scrollY;");
                return Math.abs(currentScrollY - 200) < 10;
            });
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for scroll position to reach 500px", e);
        } catch (JavascriptException e) {
            throw new CustomTestException("Failed to execute scroll JavaScript: " + e.getMessage(), e);
        }
    }

    public void clickSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Search button not found: " + searchButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Search button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for search button to be clickable", e);
        }
    }

    public void choosDocument() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(document)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Document element not found: " + document, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Document element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for document element to be clickable", e);
        }
    }

    public void getAttached() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(attached)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Attached element not found: " + attached, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Attached element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for attached element to be clickable", e);
        }
    }

    public void getDecision() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(desicion)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Decision element not found: " + desicion, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Decision element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for decision element to be clickable", e);
        }
    }

    public void getRegister() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(register)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Register element not found: " + register, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Register element is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for register element to be clickable", e);
        }
    }

    public void clickOnManiSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(mainSearchButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Main search button not found: " + mainSearchButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Main search button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for main search button to be clickable", e);
        }
    }

    public void clickCloseSearchBox() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeSearchBoxButton)).click();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Close search box button not found: " + closeSearchBoxButton, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Close search box button is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for close search box button to be clickable", e);
        }
    }

    public void selectPersion() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(personButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(personInput)).click();
            // Validate selection
            checkIfSelectPerson();
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Person button or input not found: " + personButton + " or " + personInput, e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Person button or input is not interactable", e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for person button or input to be clickable", e);
        }
    }

    public void checkIfSelectPerson() {
        try {
            wait5s.until(ExpectedConditions.visibilityOf(numberOfPerson));
        } catch (TimeoutException e) {
            throw new CustomTestException("Person selection failed: No persons selected", e);
        }
    }

    public void checkIfAttacmentFileExist() {
        try {
            wait5s.until(ExpectedConditions.visibilityOfElementLocated(By.id("js-delete-selected_1")));
        } catch (TimeoutException e) {
            throw new CustomTestException("Attachment file not inserted: Please insert at least one attachment file", e);
        }
    }

    public static String filterEnglishLitters(String text) {
        try {
            return text.replaceAll("[^a-zA-Z0-9_]", "");
        } catch (NullPointerException e) {
            throw new CustomTestException("Input text for filtering is null", e);
        }
    }
}