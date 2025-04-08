package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.General.waitAndClick;

public class LoginPage extends BasePage {
    WebDriver driver;
    @FindBy(id = "email")
    private WebElement usernameFiled;
    @FindBy(id = "pass")
    private WebElement passwordFiled;
    @FindBy(id = "u_0_5_u6")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterUsername(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        // waitAndClick(usernameFiled);
    }

    public void enterPssword(String password) {
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }


}
