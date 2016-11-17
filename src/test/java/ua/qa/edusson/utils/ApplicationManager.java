package ua.qa.edusson.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by tester on 12.08.2016.
 */
public class ApplicationManager {
    public WebDriver driver;

    private final Properties properties;
    private String browser;
    private Helper helper;


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if ("".equals(properties.getProperty("selenium.server"))) {

            if (Objects.equals(browser, BrowserType.FIREFOX)) {
                driver = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)) {
                driver = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.IE)) {
                driver = new InternetExplorerDriver();
            }

        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win7")));
            capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
            driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

        }
        // driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        // driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        // driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(System.getProperty("site", "http://edusson.com/"));
        helper = new Helper(driver);
    }

    public void stop() {
        driver.quit();
    }


    public Helper getHelper() {

        return helper;
    }

    public byte[] takeScreenshot() {
       return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

