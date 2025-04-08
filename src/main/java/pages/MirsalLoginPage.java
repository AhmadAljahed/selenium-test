package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

public class MirsalLoginPage extends BasePage {
    @FindBy(id = "txtUserName")
    private static WebElement usernameFiled;

    @FindBy(id = "txtPassword")
    private static WebElement passwordFiled;

    @FindBy(id = "btnLogin")
    private static WebElement loginButton;

    @FindBy(id = "UserName")
    private WebElement usernameId;

    @FindBy(id = "loginLang")
    private WebElement languageButton;

    @FindBy(id = "panel-56")
    static WebElement dashboard;

    public MirsalLoginPage(WebDriver driver) {
        super(driver);
    }

    public static boolean loginUser(WebDriver driver, String username, String password) {
        try {
            usernameFiled.sendKeys(username);
            passwordFiled.sendKeys(password);
            loginButton.click();

            return wait.until(ExpectedConditions.visibilityOf(dashboard)).isDisplayed();

        } catch (Exception e) {
            Log.error("Login failed: ", e);
            return false;
        }
    }

    public void changLanguages() {
        languageButton.click();
    }
}
