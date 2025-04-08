import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BaseTest;

public class AmazonTest extends BaseTest {

    // @Parameters("browser")
    @Test
    public void searchProduct(String browser) {
        driver.get("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");

        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement searchResults = driver.findElement(By.xpath("//span[contains(text(),'results for')]"));
        Assert.assertTrue(searchResults.isDisplayed(), "نتائج البحث لم تظهر!" + browser);
    }

}
