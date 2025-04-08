package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MirsalLogout extends BasePage {
    public MirsalLogout(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "btnLogout")
    private WebElement logoutButton;

    public void clickLogoutButton() {
        logoutButton.click();
    }

}
