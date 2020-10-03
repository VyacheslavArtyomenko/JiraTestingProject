import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CreateIssueWindow;
import pages.HomePage;
import pages.JiraTicketPage;
import pages.LoginPage;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestClass {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private JiraTicketPage jiraTicketPage;
    private CreateIssueWindow createIssueWindow;

    @Parameters({"browserName"})
    @BeforeMethod()
    public void setUp(@Optional("chrome)") String browserName) {
        WebDriverFactory.createInstance(browserName);
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        jiraTicketPage = new JiraTicketPage(driver);
        createIssueWindow = new CreateIssueWindow(driver);
    }

    @Story("Login")
    @Feature("Successful login test")
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

    @Story("Tickets")
    @Test
    public void viewJiraTicket() {
        jiraTicketPage.navigateToJiraTicketPage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        Assert.assertTrue(jiraTicketPage.isIssueTypePresent());
        Assert.assertTrue(jiraTicketPage.isTitleContains("WEBINAR-9060"));
    }

    @Story("Issues")
    @Feature("Create new issue")
    @Test
    public void createIssue() {
        homePage.navigateToHomePage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        homePage.clickCreateIssueWithRetry();

        createIssueWindow.clearProjectField();
        createIssueWindow.enterProjectField("Webinar");
        createIssueWindow.pressTabAfterProjectField();

        createIssueWindow.clearIssueTypeField();
        createIssueWindow.enterIssueTypeField("Task");
        createIssueWindow.pressTabAfterIssueTypeField();

        createIssueWindow.enterSummary("One more test summary");
        createIssueWindow.clearReporterField();
        createIssueWindow.enterReporterField("VyacheslavArtyomenko");

        createIssueWindow.pressCreateIssueButton();
        Assert.assertTrue(homePage.isIssueCreated());
    }

    @Feature("Canceling create issue")
    @Test
    public void cancelCreateIssue(){
        homePage.navigateToHomePage();
        loginPage.enterUserName("VyacheslavArtyomenko");
        loginPage.enterPassword("VyacheslavArtyomenko");
        loginPage.clickLoginButton();

        homePage.clickCreateIssueWithRetry();

        createIssueWindow.enterSummary("One more test summary");
        createIssueWindow.clickCancelButton();
        createIssueWindow.acceptAlert();

        Assert.assertTrue(homePage.isUserIconDisplayed());
    }

    @Story("Comments")
    @Feature("Add comment")
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
