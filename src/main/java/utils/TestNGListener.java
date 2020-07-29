package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestNGListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("On Test Start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("On Test Success");
    }

    public void onTestFailure(ITestResult iTestResult) {
        File screenshotFolder = new File(System.getProperty("user.dir") + "/screenshots");

        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdir();
        }

        File screenshot = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            String screenshotName = screenshotFolder + "/screenshot" + java.time.LocalTime.now().toString().replace(":", ".") + ".jpg";
            FileUtils.copyFile(screenshot, new File(screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("On Start");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("On Finish");
    }
}
