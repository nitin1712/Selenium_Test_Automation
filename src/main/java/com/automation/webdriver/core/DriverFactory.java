package com.automation.webdriver.core;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * DriverFactory class is used to initiate driver based on browser type passed
 * 
 * @author ngupta
 */
public class DriverFactory {
    WebDriver driver;

    public WebDriver getDriver(String browser) throws MalformedURLException {
        switch(browser)
        {
            case "chrome":
                driver=new ChromeDriver(new OptionsManager().getChromeOptions()); 
                break;
            case "firefox" :
                driver=new FirefoxDriver(new OptionsManager().getFirefoxOptions());
                break;
            default : 
                driver=new ChromeDriver(new OptionsManager().getChromeOptions()); 
        }
        return driver;
    }
}
