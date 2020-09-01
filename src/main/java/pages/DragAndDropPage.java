package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {
    private WebDriver driver;

    @FindBy(how = How.ID, id = "fourth")
    private WebElement element5000;

    @FindBy(how = How.ID, id = "amt7")
    private WebElement debitSideAmount;

    public DragAndDropPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public DragAndDropPage navigateToDragAndDropPage() {
        driver.get("http://demo.guru99.com/test/drag_drop.html");
        return new DragAndDropPage(driver);
    }

    public DragAndDropPage dragAndDropElement5000ToDebitAmount() {
        Actions action = new Actions(driver);
        action.dragAndDrop(element5000, debitSideAmount).build().perform();
        return new DragAndDropPage(driver);
    }

    public String getElement5000Text() {
        return element5000.getText();
    }

    public String getDebitSideAmountText() {
        return debitSideAmount.getText();
    }
}
