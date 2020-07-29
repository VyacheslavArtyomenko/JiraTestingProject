package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    private By createIssueButton = By.id("create_link");
    private By tempWindowIssueCreated = By.xpath("//*[contains(@class,'aui-will-close')]");
    private By createIssueTitle = By.xpath("//h2[@title='Create Issue']"); // locator from another page, breaks logic

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
            driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    }

    public boolean isUserIconDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).isDisplayed();
    }

    public void clickCreateIssue() {
        clickOnElementWithRetry(createIssueButton, createIssueTitle, 3, 3);
    }

    private void clickOnElementWithRetry(By elementToBeClicked, By successCriteriaElement, int attempts, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        for (int i = 0; i < attempts; i++) {
            try{
                wait.until(ExpectedConditions.visibilityOfElementLocated(successCriteriaElement)).isDisplayed();
                break;
            }   catch (TimeoutException e){
                wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
                driver.findElement(elementToBeClicked).click();
            }
        }
    }

    public boolean isIssueCreated() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tempWindowIssueCreated)).isDisplayed()
                && driver.findElement(By.xpath("//*[contains(@class,'aui-will-close')]")).getText().contains("WEBINAR");
    }
}
