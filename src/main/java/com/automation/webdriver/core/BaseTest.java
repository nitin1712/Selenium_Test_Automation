package com.automation.webdriver.core;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.core.ExtentManager;
import com.automation.core.ExtentTestManager;
import com.automation.core.ReadConfig;



/**
 * BaseTest class is used to create driver thread local instances
 * 
 * @author ngupta
 */
public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public DriverFactory driverFactory = new DriverFactory();

    @BeforeMethod
    public void setup () throws IOException {
        driver.set(driverFactory.getDriver(new ReadConfig().readPropertiesFile("config.properties").getProperty("browserName")));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        getDriver().navigate().to("https://www.demoblaze.com/index.html");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
        getDriver().close();
    }
}