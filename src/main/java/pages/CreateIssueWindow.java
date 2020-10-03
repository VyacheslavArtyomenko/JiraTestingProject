package pages;

import io.qameta.allure.Step;
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

    @Step("Clear project field")
    public void clearProjectField() {
        isProjectFieldDisplayed();
        driver.findElement(projectField).clear();
    }

    @Step("Enter project field")
    public void enterProjectField(String text) {
        driver.findElement(projectField).sendKeys(text);
    }

    @Step("Press tab after project field")
    public void pressTabAfterProjectField() {
        driver.findElement(projectField).sendKeys(Keys.TAB);
    }

    @Step("Check if issue type is displayed")
    private boolean isIssueTypeFieldDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field"))).isDisplayed();
    }

    @Step("Clear issue type field")
    public void clearIssueTypeField() {
        isIssueTypeFieldDisplayed();
        driver.findElement(issueTypeField).clear();
    }

    @Step("Enter issue type field field")
    public void enterIssueTypeField(String text) {
        driver.findElement(issueTypeField).sendKeys(text);
    }

    @Step("Press tab after issue tab field")
    public void pressTabAfterIssueTypeField() {
        driver.findElement(issueTypeField).sendKeys(Keys.TAB);
    }

    @Step("Check if summary field is displayed")
    private boolean isSummaryFieldDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("summary"))).isDisplayed();
    }

    @Step("Enter summary")
    public void enterSummary(String text) {
        isSummaryFieldDisplayed();
        driver.findElement(summaryField).sendKeys(text);
    }

    @Step("Clear reporter field")
    public void clearReporterField() {
        driver.findElement(reporterField).clear();
    }

    @Step("Enter reporter field")
    public void enterReporterField(String text) {
        driver.findElement(reporterField).sendKeys(text);
    }

    @Step("Press create issue button")
    public void pressCreateIssueButton() {
        driver.findElement(createIssueButton).click();
    }

    @Step("Click cancel button")
    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    @Step("Accept alert")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

}
