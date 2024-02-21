package com.automation.webdriver.utility;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class Wait{

    /**
     * This wait is used to Check the element clickable using Fluent wait, polling every 5ms
     * @author ngupta
     * @param driver
     * @return boolean
     */
    public boolean isAlertPresent(WebDriver driver){
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (Exception e) {
            foundAlert = false;
        }
        return foundAlert;
    }


    /**
     * This wait is used to Check element clickability using Fluent wait, polling every 5ms
     * @author ngupta
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForElementToBeClickable(WebDriver driver,int timeout, WebElement element) {
        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class);
        fwait.until(ExpectedConditions.elementToBeClickable(element));
    }      



    /**
     * This wait is used to Check element visiblity using Fluent wait, polling every 5ms
     * @author ngupta
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForElementToBeVisible(WebDriver driver,int timeout, WebElement element) {
        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class);
        fwait.until(ExpectedConditions.visibilityOf(element));
    } 




    /**
     * This wait is used to Check element invisiblity using Fluent wait, polling every 5ms
     * @author ngupta
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForElementNotToBeVisible(WebDriver driver,int timeout, WebElement element) {
        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class);
        fwait.until(ExpectedConditions.invisibilityOf(element));
    } 


}