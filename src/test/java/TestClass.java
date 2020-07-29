import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CreateIssueWindow;
import pages.HomePage;
import pages.JiraTicketPage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertTrue;

public class TestClass {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private JiraTicketPage jiraTicketPage;
    private CreateIssueWindow createIssueWindow;

    @Parameters({"browserName"})
    @BeforeMethod()
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        jiraTicketPage = new JiraTicketPage(driver);
        createIssueWindow = new CreateIssueWindow(driver);
    }

    @Test
    public void successfulLoginTest() {
        homePage.navigateToHomePage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        assertTrue(homePage.isUserIconDisplayed());
    }

    @Test(dataProvider = "LoginTest")
    public void unsuccessfulLoginTest(String name, String password, String expectedResult) {
        homePage.navigateToHomePage();
        loginPage.enterUserName(name);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.errorMessageIsPresent(expectedResult));
    }

    @DataProvider(name = "LoginTest")
    public Object[][] LoginData() {
        return new Object[][]{
                {"VyacheslavArtyomenko", "", "Sorry, your username and password are incorrect - please try again"},
                {"", "VyacheslavArtyomenko", "Sorry, your username and password are incorrect - please try again"},
        };
    }

    @Test
    public void viewJiraTicket() {
        jiraTicketPage.navigateToJiraTicketPage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        Assert.assertTrue(jiraTicketPage.isIssueTypePresent());
        Assert.assertTrue(jiraTicketPage.isTitleContains("WEBINAR-9060"));
    }

    @Test
    public void createIssue() {
        homePage.navigateToHomePage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        homePage.clickCreateIssue();

        createIssueWindow.isProjectFieldDisplayed();
        createIssueWindow.clearProjectField();
        createIssueWindow.enterProjectField("Webinar");
        createIssueWindow.pressTabAfterProjectField();

        createIssueWindow.isIssueTypeFieldDisplayed();
        createIssueWindow.clearIssueTypeField();
        createIssueWindow.enterIssueTypeField("Task");
        createIssueWindow.pressTabAfterIssueTypeField();

        createIssueWindow.isSummaryFieldDisplayed();
        createIssueWindow.enterSummary("One more test summary");
        createIssueWindow.clearReporterField();
        createIssueWindow.enterReporterField("VyacheslavArtyomenko");

        createIssueWindow.pressCreateIssueButton();
        Assert.assertTrue(homePage.isIssueCreated());
    }

    @Test
    public void addComment() {
        jiraTicketPage.navigateToJiraTicketPage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        jiraTicketPage.isCommentButtonDisplayed();
        jiraTicketPage.clickCommentButton();
        jiraTicketPage.sendTextToCommentField("Test comment");
        jiraTicketPage.clickAddCommentButton();

        Assert.assertTrue(jiraTicketPage.isTicketCreated());

        jiraTicketPage.clickOnDeleteLastComment();
        jiraTicketPage.isDeleteDialogButtonDisplayed();
        jiraTicketPage.clickDeleteDialogButton();

        Assert.assertTrue(jiraTicketPage.isLastCommentDeleted());

        System.out.println("Done");
    }

    @Test
    public void failOnPurpose() {
        driver.get("https://www.google.com.ua/?hl=ru");
        Assert.fail();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
