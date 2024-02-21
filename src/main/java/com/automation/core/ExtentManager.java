package com.automation.core;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * Below class will initialize extent report
 * 
 * @author ngupta
 */

public class ExtentManager { 
    private static ExtentReports extent;
    private static String reportFileName = "Test_Automaton_Report" + ".html";
    private static String reportFilepath = System.getProperty("user.dir") + "/" + "TestReport";
    private static String reportFileLocation = reportFilepath + "/" + reportFileName;


    /**
     * Below method is used to get instance of the extent report if null
     * 
     * @author ngupta
     * @return ExtentReports
     */
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    
    /**
     * Below method is used to create instance of extent report
     * 
     * @author ngupta
     * @return ExtentReports
     */
    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);
        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle(reportFileName);
        spark.config().setEncoding("utf-8");
        spark.config().setReportName(reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }


    /**
     * Below method is used to get test report directory or create directory if not present
     * 
     * @author ngupta
     * @param path
     * @return String
     */
    private static String getReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                return reportFileLocation;
            }
        }
        return reportFileLocation;
    }
}
