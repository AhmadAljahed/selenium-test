package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecivedMailPage extends BasePage {

    @FindBy(xpath = "//input[@class='GridCheckBoxes']")
    private WebElement checkBoxInput;
    @FindBy(xpath = "//a[contains(text(),'كتاب معادله شهاده']")
    private WebElement documentName;


    public RecivedMailPage(WebDriver driver) {
        super(driver);
    }

    public String getDocumentName() {
        return documentName.getText();
    }


}
