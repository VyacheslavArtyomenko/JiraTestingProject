package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.WebDriverFactory;

import java.util.List;

public class JiraTicketPage {

    private WebDriver driver;

    private By commentButton = By.id("footer-comment-button");
    private By commentField = By.id("comment");
    private By addCommentButton = By.id("issue-comment-add-submit");
    private By justNowElement = By.xpath("//div[@class='issuePanelWrapper']//time[contains(text(),'Just now')]");
    private By deleteDialogButton = By.id("comment-delete-submit");

    public JiraTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToJiraTicketPage() {
        driver.get("https://jira.hillel.it/browse/WEBINAR-9060");
    }

    public boolean isIssueTypePresent() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id("type-val"))).isDisplayed();
    }

    public boolean isTitleContains(String title) {
        return driver.getTitle().contains(title);
    }

    @Step("Check if comment button is displayed")
    public boolean isCommentButtonDisplayed() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("footer-comment-button"))).isDisplayed();
    }

    @Step("Click comment button")
    public void clickCommentButton() {
        driver.findElement(commentButton).click();
    }

    @Step("Send text to comment field")
    public void sendTextToCommentField(String text) {
        driver.findElement(commentField).sendKeys(text);
    }

    @Step("Click add comment button")
    public void clickAddCommentButton() {
        driver.findElement(addCommentButton).click();
    }

    @Step("Check if ticket is created")
    public boolean isTicketCreated() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(justNowElement));
        return driver.findElement(justNowElement).getText().contains("Just now");
    }

    @Step("Find last comment")
    public WebElement findLastComment() {
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@id, 'delete')]"));
        return elements.get(elements.size() - 1);
    }

    @Step("Click on delete last comment")
    public void clickOnDeleteLastComment() {
        findLastComment().click();
    }

    @Step("Check if delete dialog button is displayed")
    public boolean isDeleteDialogButtonDisplayed() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteDialogButton)).isDisplayed();
    }

    @Step("Click delete dialog button")
    public void clickDeleteDialogButton() {
        driver.findElement(deleteDialogButton).click();
    }

    @Step("Check if last comment is deleted")
    public boolean isLastCommentDeleted() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'aui-message closeable aui-message-success aui-will-close']")));

        return driver.findElements(justNowElement).size() == 0;
    }

}
