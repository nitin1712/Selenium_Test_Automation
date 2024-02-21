package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class BasePage {
    protected WebDriver webDriver;
    protected String deviceType;

    /**
     * The below Constructor method is used to initialize page factory elements
     *
     * @author ngupta
     */
    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

}
