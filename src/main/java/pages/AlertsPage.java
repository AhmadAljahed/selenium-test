package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertsPage {
    WebDriver driver;

    private By simpleAlert = By.xpath("//button[text()='Click for JS Alert']");
    private By confirmAlert = By.xpath("//button[text()='Click for JS Confirm']");
    private By promptAlert = By.xpath("//button[text()='Click for JS Prompt']");
    private By successEnterMessage = By.id("result");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void handelSimpleAlert() {
        driver.findElement(simpleAlert).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void handelConfirmAlert() {
        driver.findElement(confirmAlert).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void handelPromptAlert(String inputText) {
        driver.findElement(promptAlert).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(inputText);
        alert.accept();
    }

    public String getSuccessMessage() {
        String successMeassage = driver.findElement(successEnterMessage).getText();
        return successMeassage;
    }

}
