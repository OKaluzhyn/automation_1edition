package ua.qa.edusson.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;


public class Helper {

    protected WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
    }


    public void goToEdusson() {
        driver.get("http://edusson.com/");
    }

    public void goToEdubirdie() {
        driver.get("http://edubirdie.com");
    }

    public void goToStudyfaq() {
        driver.get("http://studyfaq.com/");
    }

    public void quit() {
        driver.quit();
    }

    public WebElement cyclicElementSearchByXpath(String target) {

        for (int i = 0; i < 1000; i++) {
            if (driver.findElements(By.xpath(target)).size() > 0) {
                break;
            }

            sleep(1);
        }
        return driver.findElement(By.xpath(target));
    }

    public static void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public WebElement randomChoiceFromDropdown(String xpath) {

        List<WebElement> listOfElements = driver.findElements(By.xpath(xpath));
        // select a random one
        Random random = new Random();
        WebElement someRandomElement = listOfElements.get(random.nextInt(listOfElements.size()));
        someRandomElement.click();
        return someRandomElement;

    }

    public boolean isElementPresent(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public  void setClipboardData(String path) {
        StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void attachFile() {
        setClipboardData("C:\\Users\\tester\\resources\\testFile.pdf");
        try {
            Robot robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(300);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


}


