import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DragAndDropPage;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class DragAndDropTests {
    private WebDriver driver;
    private DragAndDropPage dragAndDropPage;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("chrome");
        driver = WebDriverFactory.getDriver();
        dragAndDropPage = new DragAndDropPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void dragAndDrop() {
        dragAndDropPage.navigateToDragAndDropPage()
                .dragAndDropElement5000ToDebitAmount();

        Assert.assertEquals(dragAndDropPage.getElement5000Text(), dragAndDropPage.getDebitSideAmountText());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
