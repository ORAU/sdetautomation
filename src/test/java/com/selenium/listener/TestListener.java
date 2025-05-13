package com.selenium.listener;

import com.selenium.utils.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        takingScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takingScreenshot(result);
    }

    private void takingScreenshot(ITestResult result) {
        TakesScreenshot driver =(TakesScreenshot)result.getTestContext().getAttribute(Constants.DRIVER);
        String screenShot= driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        String htmlImage= String.format(htmlImageFormat,screenShot);
        Reporter.log(htmlImage);

    }

}
