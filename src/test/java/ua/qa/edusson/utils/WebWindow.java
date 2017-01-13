package ua.qa.edusson.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import ua.qa.edusson.tests.tools.TestBase;

import java.util.Set;

/**
 * Created by tester on 12.12.2016.
 */
public class WebWindow extends TestBase {
    /**
     * Creates and Handles a New window  *
     */


    private static WebDriver driver = app.driver;
    private static String handle;
    private String name;
    private static String parentHandle;
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
        do {
            handle = createWindow(url);
        }
        while (handle.equals(url));


        app.getHelper().wait.until((WebDriver driver) -> driver.getWindowHandles().size() > 1);
        //Switch to that window and load the url to wait
        switchToWindow().get(url);

    }


    private String createWindow(String url) {
        //Record old handles
        Set<String> oldHandles = driver.getWindowHandles();
        parentHandle = driver.getWindowHandle();
        //Inject an anchor element
        Object script = ((JavascriptExecutor) driver).
                executeScript(injectAnchorTag(name, url));
        //Click on the anchor element
        app.getHelper().searchById(name).click();
        //driver.findElement(By.id(name)).click();
        handle = getNewHandle(oldHandles);
        return handle;
    }


    public String getWindowHandle() {
        return handle;
    }

    public String getParentHandle() {
        return parentHandle;
    }

    public static void close() {
        switchToWindow().close();
        handle = "";
        //Switch back to the parent window
        driver.switchTo().window(parentHandle);
    }

    private static String createUniqueName() {
        return "a_Web_Window_" + instanceCount++;
    }

    public static WebDriver switchToWindow() {
        checkForClosed();
        return driver.switchTo().window(handle);
    }

    public static WebDriver switchToParent() {
        checkForClosed();
        return driver.switchTo().window(parentHandle);
    }


    private String getNewHandle(Set<String> oldHandles) {
        Set<String> newHandles = driver.getWindowHandles();
        newHandles.removeAll(oldHandles);
        //Find the new window
        for (String handle : newHandles)
            return handle;
        return null;
    }

    private static void checkForClosed() {
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

    public void CreateNewTab() {
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
            for (String handle : driver.getWindowHandles()) {
                if (handle != handleHost) {
                    driver.switchTo().window(handle);
                    driver.switchTo().activeElement();
                } // смотрим все вкладки (а их две всего); если ≥-та€ вкладка не равна первой handleHost (инициализированой в пункте (а), тогда переключаемс€ на нее).
            }
        } catch (Exception e) {
            System.err.println("Couldn't get to second page");
        }
    }


    public static void closeUnusedTabs() {
        System.out.println("After test");
        int handlesCount = app.driver.getWindowHandles().size();
        System.out.println(handlesCount);
        if (handlesCount > 1) {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(app.driver.getWindowHandles().size());
        }
    }
}

