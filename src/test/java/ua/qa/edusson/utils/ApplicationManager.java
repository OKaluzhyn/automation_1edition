package ua.qa.edusson.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by tester on 12.08.2016.
 */
public class ApplicationManager {
    public WebDriver driver;
    private String browser;
    private Helper helper;

    public ApplicationManager(String browser) {
              this.browser = browser;
          }


    public void init(){
        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            driver = new ChromeDriver();;
        } else if (Objects.equals(browser, BrowserType.IE)){
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        helper = new Helper(driver);
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public void stop() {
        driver.quit();
    }
}
