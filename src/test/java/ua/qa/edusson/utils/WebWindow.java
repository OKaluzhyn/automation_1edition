package ua.qa.edusson.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import ua.qa.edusson.tests.TestBase;

import java.util.Set;

/**
 * Created by tester on 12.12.2016.
 */
public class WebWindow extends TestBase{
    /**
     * Creates and Handles a New window  *
     */


    private WebDriver driver = app.driver;
    private String handle;
    private String name;
    private String parentHandle;
    private static int instanceCount = 0;

    /*
     * Creates a new window for given web driver
     * @param parent WebDriver instance
     * @param url   Initial url to load
     * @return new WebWindow
     */
    public WebWindow(WebDriver parent, String url) {
        this.driver = parent;
        parentHandle = parent.getWindowHandle();
        name = createUniqueName();
        handle = createWindow(url);
        //Switch to that window and load the url to wait
        switchToWindow().get(url);
    }

    public String getWindowHandle() {
        return handle;
    }

    public String getParentHandle() {
        return parentHandle;
    }

    public void close() {
        switchToWindow().close();
        handle = "";
        //Switch back to the parent window
        driver.switchTo().window(parentHandle);
    }

    private static String createUniqueName() {
        return "a_Web_Window_" + instanceCount++;
    }

    public WebDriver switchToWindow() {
        checkForClosed();
        return driver.switchTo().window(handle);
    }

    public WebDriver switchToParent() {
        checkForClosed();
        return driver.switchTo().window(parentHandle);
    }

    private String createWindow(String url) {
        //Record old handles
        Set<String> oldHandles = driver.getWindowHandles();
        parentHandle = driver.getWindowHandle();
        //Inject an anchor element
        ((JavascriptExecutor) driver).
                executeScript(
                        injectAnchorTag(name, url)
                );
        //Click on the anchor element
        driver.findElement(By.id(name)).click();
        handle = getNewHandle(oldHandles);
        return handle;
    }

    private String getNewHandle(Set<String> oldHandles) {
        Set<String> newHandles = driver.getWindowHandles();
        newHandles.removeAll(oldHandles);
        //Find the new window
        for (String handle : newHandles)
            return handle;
        return null;
    }

    private void checkForClosed() {
        if (handle == null || handle.equals(""))
            throw new WebDriverException("Web Window closed or not initialized");
    }

    private String injectAnchorTag(String id, String url) {
        return String.format("var anchorTag = document.createElement('a'); " +
                "anchorTag.appendChild(document.createTextNode('nwh'));" +
                "anchorTag.setAttribute('id', '%s');" +
                "anchorTag.setAttribute('href', '%s');" +
                "anchorTag.setAttribute('target', '_blank');" +
                "anchorTag.setAttribute('style', 'display:block;');" +
                "document.getElementsByTagName('body')[0].appendChild(anchorTag);", id, url);
    }

    protected void CreateNewTab() {
        try {
            WebWindow tab2 = new WebWindow(driver, "url"); // УurlФ - ссылка новой вкладки
        } catch (Exception e) {
            System.err.println("Couldn't load second page");
        }
    }

    public void SwitchFromSecondTabToFirst() {
        try {
            driver.switchTo().window(handleHost);
            driver.switchTo().activeElement();
        } catch (Exception e) {
            System.err.println("Couldn't get back to first page");
        }
    }
    public void SwitchFromFirstPageToSecond() {
        try {
            for (String handle: driver.getWindowHandles()) {
                if (handle != handleHost) {
                    driver.switchTo().window(handle);
                    driver.switchTo().activeElement();
                } // смотрим все вкладки (а их две всего); если ≥-та€ вкладка не равна первой handleHost (инициализированой в пункте (а), тогда переключаемс€ на нее).
            }
        } catch (Exception e) {
            System.err.println("Couldn't get to second page");
        }
    }
}

