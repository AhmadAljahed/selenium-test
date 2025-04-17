package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MirsalLoginPage extends BasePage {
    private WebDriverWait wait;

    @FindBy(id = "txtUserName")
    private WebElement usernameField;

    @FindBy(id = "txtPassword")
    private WebElement passwordField;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    @FindBy(id = "UserName")
    private WebElement usernameId;

    @FindBy(id = "loginLang")
    private WebElement languageButton;

    @FindBy(id = "ReceivedInboxCount")
    private WebElement dashboard;

    public MirsalLoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void loginUser(String username, String password) {
        try {
            // Enter username
            wait.until(ExpectedConditions.elementToBeClickable(usernameField));
            usernameField.clear();
            usernameField.sendKeys(username);
            String actualUsername = usernameField.getAttribute("value");
            if (!actualUsername.equals(username)) {
                throw new CustomTestException(
                        "Failed to enter username: Expected '" + username + "', but got '" + actualUsername + "'");
            }

            // Enter password
            wait.until(ExpectedConditions.elementToBeClickable(passwordField));
            passwordField.clear();
            passwordField.sendKeys(password);
            String actualPassword = passwordField.getAttribute("value");
            if (!actualPassword.equals(password)) {
                throw new CustomTestException(
                        "Failed to enter password: Expected '" + password + "', but got '" + actualPassword + "'");
            }

            // Click login button
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            // Verify dashboard is displayed
            boolean isDashboardDisplayed = wait.until(ExpectedConditions.visibilityOf(dashboard)).isDisplayed();
            if (!isDashboardDisplayed) {
                throw new CustomTestException("Login failed: Dashboard element is not visible");
            }

        } catch (NoSuchElementException e) {
            throw new CustomTestException(
                    "Login failed: Element not found (usernameField, passwordField, loginButton, or dashboard)", e);
        } catch (TimeoutException e) {
            throw new CustomTestException(
                    "Login failed: Timeout waiting for element to be clickable or visible", e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException(
                    "Login failed: Element is not interactable (usernameField, passwordField, or loginButton)", e);
        }
    }

    public void changeLanguage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(languageButton)).click();
            // Optional: Validate language change (e.g., check if a specific element's text changes)
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Language button not found: " + languageButton, e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for language button to be clickable", e);
        } catch (ElementNotInteractableException e) {
            throw new CustomTestException("Language button is not interactable", e);
        }
    }

    // Getter for validation in tests (if needed)
    public WebElement getDashboard() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(dashboard));
        } catch (NoSuchElementException e) {
            throw new CustomTestException("Dashboard element not found: " + dashboard, e);
        } catch (TimeoutException e) {
            throw new CustomTestException("Timeout waiting for dashboard to be visible", e);
        }
    }
}