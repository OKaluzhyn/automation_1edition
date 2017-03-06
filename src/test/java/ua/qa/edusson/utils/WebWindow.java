package ua.qa.edusson.utils;

import org.openqa.selenium.*;
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
    private static int instanceCount = 1;

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
        //System.out.println(name);
        handle = createWindow(url);
        //try again
        checkForClosedAndTryCreate(url);
        switchToWindow();
        System.out.println(url);

    }


    private String createWindow(String url) {
        try {
            //Record old handles
            Set<String> oldHandles = driver.getWindowHandles();
            parentHandle = driver.getWindowHandle();
            //Inject an anchor element
            ((JavascriptExecutor) driver).
                    executeScript(injectAnchorTag(name, url));
            //Click on the anchor element
            app.getHelper().searchById(name).click();
            handle = getNewHandle(oldHandles);

        } catch (Exception ex) {
            System.out.println("Web Window was not initialized");
        }
        return handle;
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

    private void checkForClosedAndTryCreate(String url) {
        if (handle == null || handle.equals("")) {
            System.out.println("Try again to create a new Web Window ");
            for (int i = 0; i < 5; i++) {
                handle = createWindow(url);
                if (handle != null) {
                    break;
                }
                System.out.println(handle);
            }
        }
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

    public static void closeUnusedTabs() {
        if (app.driver.getWindowHandles().size() > 1) {
            close();
        }


           /*
            do {
            //найти способ пройтись циклом по всем открытым окнам кроме главного и закрыть их
                driver.switchTo().window(handle).close();
                System.out.println(app.driver.getWindowHandles().size() + " - handlesCount after close unused tabs");
            }
            while (app.driver.getWindowHandles().size() != 1);

         int handlesCount = app.driver.getWindowHandles().size();
        System.out.println(handlesCount + " - handlesCount after test");
        for(int i=0; i < handlesCount; i++ ){
            close();
            if (i==1){
                break;
            }
            System.out.println(app.driver.getWindowHandles().size() + " - handlesCount after close unused tabs");
        }*/
    }

}





