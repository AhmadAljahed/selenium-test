package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MirsalProfilePage extends BasePage {
    @FindBy(id = "UserName")
    private WebElement usernameId;

    public MirsalProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getUsernameId() {
        return usernameId.getText();
    }
}
