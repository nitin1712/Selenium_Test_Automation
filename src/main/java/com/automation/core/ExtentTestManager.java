package com.automation.core;

import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * Below class is used to create a test in extent report
 * 
 * @author ngupta
 */

public class ExtentTestManager {
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    private static ExtentReports extent = ExtentManager.getInstance();
    

    /**
     * Below method is used to get the current executing test method
     * 
     * @author ngupta
     */
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    
    /**
     * Below method is used to flush data to extent report at the end of test method
     * 
     * @author ngupta
     */
    public static synchronized void endTest() {
        getExtent().flush();
    }

    
    /**
     * Below method is used to pass name of the current test method and class name to extent report
     * 
     * @author ngupta
     * @param testName
     * @param className
     */
    public static synchronized ExtentTest startTest(String testName,String className) {
        ExtentTest test = getExtent().createTest(testName);
        test.assignCategory(className);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
    
    
    /**
     * Below method is used to return instance of extent
     * 
     * @author ngupta
     */
    public static ExtentReports getExtent() {
        return extent;
    }
}
