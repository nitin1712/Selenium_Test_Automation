package com.automation.webdriver.utility;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import com.automation.core.ExtentTestManager;
import com.automation.core.ReadConfig;
import com.automation.webdriver.core.BaseTest;
import com.aventstack.extentreports.Status;

/**
 * @author ngupta
 *
 */
public class Utility extends BaseTest{
    Wait wait=new Wait();
    ReadConfig readConfig=new ReadConfig();


    /**
     * This method is used to get screenshot
     * 
     * @author ngupta
     * @param  screenshotName
     * @return String
     * 
     */
    public String getScreenshot(String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
        try {
            TakesScreenshot takeScreenShot = (TakesScreenshot)getDriver();
            File source = takeScreenShot.getScreenshotAs(OutputType.FILE);
            File finalDestination = new File(System.getProperty("user.dir") + "/" + "TestReport" + "/Snapshots/" + screenshotName + dateName + ".png");
            FileUtils.copyFile(source, finalDestination);
            return "./Snapshots/" + screenshotName + dateName + ".png";
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * This method is used to type character to a input field
     * @author ngupta
     * @param  element
     * @param data
     * @throws Exception 
     * 
     */
    public void enterInput(WebElement element,String data) throws IOException {
        try {
            wait.waitForElementToBeVisible(getDriver(),Integer.parseInt(readConfig.readPropertiesFile("config.properties").getProperty("timeout")), element);
            element.clear();
            element.sendKeys(data);
            ExtentTestManager.getTest().log(Status.INFO, "enterInput equals true -> " + element +" "+data);
        }catch(Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "enterInput equals false -> " + element+" "+data);
            throw e;
        }
    }



    /**
     * This method is used to click Element
     * @author ngupta
     * @param  element
     * @throws Exception 
     */
    public void clickElement(WebElement element) throws IOException {
        try {
            wait.waitForElementToBeClickable(getDriver(), Integer.parseInt(readConfig.readPropertiesFile("config.properties").getProperty("timeout")), element);
            element.click();
            ExtentTestManager.getTest().log(Status.INFO, "clickElement equals true -> " + element);
        }catch(Exception e) {
            try {
                ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView();", element);
                element.click();
            }catch(Exception e1) {
                ExtentTestManager.getTest().log(Status.FAIL, "clickElement equals false -> " + element);
                throw e;
            }
        }
    }



    /**
     * This method is used to accept alert
     * @author ngupta
     */
    public void acceptAlert() {
        if(wait.isAlertPresent(getDriver())) {
            getDriver().switchTo().alert().accept();
            ExtentTestManager.getTest().log(Status.INFO, "acceptAlert equals true -> ");
        }else
            ExtentTestManager.getTest().log(Status.INFO, "No Alert present");
    }


    /**
     * This method is used to wait for element visibility
     * @author ngupta
     * @param  element
     * @return boolean
     * 
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            wait.waitForElementToBeVisible(getDriver(), Integer.parseInt(readConfig.readPropertiesFile("config.properties").getProperty("timeout")), element);
            boolean flag=element.isDisplayed();
            ExtentTestManager.getTest().log(Status.INFO, "isElementDisplayed equals true -> " + element);
            return flag;
        }catch(Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "isElementDisplayed equals false -> " + element);
            return false;
        }
    }


    /**
     * This method is used to wait for element invisibility
     * @author ngupta
     * @param  element
     * @return boolean
     */
    public boolean isElementNotDisplayed(WebElement element) {
        try {
            wait.waitForElementNotToBeVisible(getDriver(), Integer.parseInt(readConfig.readPropertiesFile("config.properties").getProperty("timeout")), element);
            ExtentTestManager.getTest().log(Status.INFO, "isElementNotDisplayed equals true -> " + element);
            return true;
        }catch(Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "isElementNotDisplayed equals false -> " + element);
            return false;
        }
    }



    /**
     * This method is used to get element text
     * @author ngupta
     * @param  element
     * @return String
     * @throws Exception 
     */
    public String getElementText(WebElement element) throws IOException {
        wait.waitForElementToBeVisible(getDriver(), Integer.parseInt(readConfig.readPropertiesFile("config.properties").getProperty("timeout")), element);
        return element.getText();
    }
}



