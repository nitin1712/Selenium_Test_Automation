package com.automation.webdriver.core;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * OptionsManager class is used to get options based on browser type
 * 
 * @author ngupta
 */
public class OptionsManager {

    /**
     * Below code is used to get chrome options
     * 
     * @author ngupta
     * @return ChromeOptions
     */
    public ChromeOptions getChromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability (CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.merge(capabilities);
        return options;
    }

    /**
     * Below code is used to get firefox options
     * 
     * @author ngupta
     * @return FirefoxOptions
     */
    public FirefoxOptions getFirefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        return options;
    }
}