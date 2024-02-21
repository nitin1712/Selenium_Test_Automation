package com.automation.core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.automation.webdriver.utility.Utility;
import com.aventstack.extentreports.Status;

/**
 * Below code will listen to the Test case execution status and make required entries to Extent Reporter
 * 
 * @author ngupta
 */

public class ExtentTestNGListener implements ITestListener {
    Utility utility = new Utility();

    public void onStart(ITestContext context) {}

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName(),result.getTestClass().getRealClass().getSimpleName());
    }

    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test Case is Passed ----> " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = utility.getScreenshot(result.getMethod().getMethodName());
            if (screenshotPath != null)
                ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
            ExtentTestManager.getTest().log(Status.FAIL, "Exception is " + result.getThrowable());
            ExtentTestManager.getTest().log(Status.FAIL, "Test Case is Failed ----> " + result.getName());
        }
    }

    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Exception is " + result.getThrowable());
        ExtentTestManager.getTest().log(Status.SKIP, "Test Case is Skipped -----> " + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
