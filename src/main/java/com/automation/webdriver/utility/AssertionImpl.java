package com.automation.webdriver.utility;


import org.testng.Assert;
import com.automation.core.ExtentTestManager;
import com.aventstack.extentreports.Status;


public class AssertionImpl {

    static public void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
        ExtentTestManager.getTest().log(Status.PASS, "Assert " + message);

    }


    static public void assertEquals(Object actual, String expected, String message) {
        Assert.assertEquals((Object) actual, (Object) expected, message);
        ExtentTestManager.getTest().log(Status.PASS, "Actual:- "+actual+"|"+" Expected:- "+expected);
    }


    static public void assertEquals(Object actual, int expected) {
        Assert.assertEquals(actual, expected, null);
        ExtentTestManager.getTest().log(Status.PASS, "Actual:- "+actual+"|"+" Expected:- "+expected);
    }

}