package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IframPage {
    WebDriver driver;
    private By ifram = By.id("mce_0_ifr");
    private By textBox = By.id("tinymce");

    public IframPage(WebDriver driver) {
        this.driver = driver;
    }

    public void interactWithFrame(String text) {
        driver.switchTo().frame(driver.findElement(ifram));
        driver.findElement(textBox).clear();
        driver.findElement(textBox).sendKeys(text);
        driver.switchTo().defaultContent();
    }
}
