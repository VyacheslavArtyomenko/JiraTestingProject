package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueWindow {
    private WebDriver driver;

    private By projectField = By.id("project-field");
    private By issueTypeField = By.id("issuetype-field");
    private By summaryField = By.id("summary");
    private By reporterField = By.id("reporter-field");
    private By createIssueButton = By.id("create-issue-submit");
    private By createIssueTitle = By.xpath("//input[@id = 'create-issue-submit']");
    private By cancelButton = By.xpath("//a[@class = 'cancel']");

    public CreateIssueWindow(WebDriver driver) {
        this.driver = driver;
    }

    private boolean isProjectFieldDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field"))).isDisplayed();
    }

    public void clearProjectField() {
        isProjectFieldDisplayed();
        driver.findElement(projectField).clear();
    }

    public void enterProjectField(String text) {
        driver.findElement(projectField).sendKeys(text);
    }

    public void pressTabAfterProjectField() {
        driver.findElement(projectField).sendKeys(Keys.TAB);
    }

    private boolean isIssueTypeFieldDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field"))).isDisplayed();
    }

    public void clearIssueTypeField() {
        isIssueTypeFieldDisplayed();
        driver.findElement(issueTypeField).clear();
    }

    public void enterIssueTypeField(String text) {
        driver.findElement(issueTypeField).sendKeys(text);
    }

    public void pressTabAfterIssueTypeField() {
        driver.findElement(issueTypeField).sendKeys(Keys.TAB);
    }

    private boolean isSummaryFieldDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("summary"))).isDisplayed();
    }

    public void enterSummary(String text) {
        isSummaryFieldDisplayed();
        driver.findElement(summaryField).sendKeys(text);
    }

    public void clearReporterField() {
        driver.findElement(reporterField).clear();
    }

    public void enterReporterField(String text) {
        driver.findElement(reporterField).sendKeys(text);
    }

    public void pressCreateIssueButton() {
        driver.findElement(createIssueButton).click();
    }

    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

}
