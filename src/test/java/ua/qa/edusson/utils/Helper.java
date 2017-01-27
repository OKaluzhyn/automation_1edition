package ua.qa.edusson.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import static ua.qa.edusson.tests.tools.TestBase.app;


public class Helper {
    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    HeaderMenu headerMenu = new HeaderMenu();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();


    protected WebDriver driver;
    public final Wait<WebDriver> wait;
    public String id;

    public WebDriver getDriver() {
        return driver;
    }

    public Helper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 120, 500).withMessage("Element was not found during 60 Sec");
    }

    public File getRevision() {
        return revision;
    }


    File revision = new File("src/test/resources/testFile.pdf");

    public void goTo(String url) {
        driver.get(url);
    }

    public void goToEdusson() {
        driver.get("https://edusson.com/");
    }

    public void goToEdubirdie() {
        driver.get("https://edubirdie.com");
    }

    public void goToStudyfaq() {
        driver.get("https://studyfaq.com/");
    }


    public WebElement cyclicElementSearchByXpath(String target) {
        waitElement(target);
        for (int i = 0; i < 50; i++) {
            if (driver.findElements(By.xpath(target)).size() > 0) {
                break;
            }

        }

        return driver.findElement(By.xpath(target));
    }

    public WebElement searchById(String target) {
        //waitElement(target);
        for (int i = 0; i < 50; i++) {
            if (driver.findElements(By.id(target)).size() > 0) {
                break;
            }

        }

        return driver.findElement(By.id(target));
    }

    public void waitElementNotVisible(String locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    public void waitElement(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

    }

    public void waitLoading(String patrUrl) {
        wait.until(urlContains(patrUrl));

    }

    public void waitLoading2(String patrUrl) {
        wait.until(ExpectedConditions.not(urlContains(patrUrl)));

    }

    public static void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public WebElement randomChoiceFromDropdown(String xpath) {
        waitElement(xpath);
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


    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) ((JavascriptExecutor) getDriver()).executeScript("id = jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) getDriver()).executeScript("id = document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }


    public void unhide(WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['visibility']='visible';"
                + "id = true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(input);
        input.sendKeys(file);
    }


    public static String isSiteEasybidding(String site) {
        String type = null;
        String[] sitesWithEasyBidding = {"https://customwriting.com/",
                "https://essays.studymoose.com/",
                "https://paperial.com/",
                "https://phdfy.com/", "https://essayontime.com/", "https://essaylab.com/", "https://essayblablawriting.com/"};
        for (String i : sitesWithEasyBidding) {
            if (i.equals(site)) {
                type = "easy";
                break;
            } else {
                type = "notEasy";
            }
        }
        return type;
    }


    public static boolean hasSiteCardPay(String site) {
        boolean type = false;
        String[] sitesWithCardPay = {"https://edusson.com/", "https://edubirdie.com/", "https://papersowl.com/",
                "https://studyfaq.com/"};
        for (String i : sitesWithCardPay) {
            if (i.equals(site)) {
                type = true;
                break;
            } else {
                type = false;
            }
        }
        return type;
    }

    public String idEasyBidding(String siteUrl) {

        if (siteUrl.equals("https://edusson.com/")) {
            id = app.driver.getCurrentUrl().substring(30);
        } else if (siteUrl.equals("https://customwriting.com/")) {
            id = app.driver.getCurrentUrl().substring(36);
        } else if (siteUrl.equals("https://essays.studymoose.com/")) {
            id = app.driver.getCurrentUrl().substring(40);
        } else if (siteUrl.equals("https://essayvikings.com/")) {
            id = app.driver.getCurrentUrl().substring(35);
        } else if (siteUrl.equals("https://edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(32);
        } else if (siteUrl.equals("https://eduzaurus.com/")) {
            id = app.driver.getCurrentUrl().substring(32);
        } else if (siteUrl.equals("https://paperdon.com/")) {
            id = app.driver.getCurrentUrl().substring(31);
        } else if (siteUrl.equals("https://papersowl.com/")) {
            id = app.driver.getCurrentUrl().substring(32);
        } else if (siteUrl.equals("https://studarea.com/")) {
            id = app.driver.getCurrentUrl().substring(31);
        } else if (siteUrl.equals("https://essaybison.com/")) {
            id = app.driver.getCurrentUrl().substring(33);
        } else if (siteUrl.equals("https://samedaypapers.com/")) {
            id = app.driver.getCurrentUrl().substring(36);
        } else if (siteUrl.equals("https://paperell.com/")) {
            id = app.driver.getCurrentUrl().substring(31);
        } else if (siteUrl.equals("https://essaytornado.com/")) {
            id = app.driver.getCurrentUrl().substring(35);
        } else if (siteUrl.equals("https://studyfaq.com/")) {
            id = app.driver.getCurrentUrl().substring(31);
        } else if (siteUrl.equals("https://ca.edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(35);
        } else if (siteUrl.equals("https://au.edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(35);
        } else if (siteUrl.equals("https://uk.edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(35);
        } else if (siteUrl.equals("https://gpaessay.com/")) {
            id = app.driver.getCurrentUrl().substring(31);
        } else if (siteUrl.equals("https://australianwritings.com.au/")) {
            id = app.driver.getCurrentUrl().substring(44);
        } else if (siteUrl.equals("https://papercp.com/")) {
            id = app.driver.getCurrentUrl().substring(30);
        } else if (siteUrl.equals("https://typemyessays.com/")) {
            id = app.driver.getCurrentUrl().substring(35);
        } else if (siteUrl.equals("https://paperial.com/")) {
            id = app.driver.getCurrentUrl().substring(31);
        } else if (siteUrl.equals("https://essayontime.com.au/")) {
            id = app.driver.getCurrentUrl().substring(37);
        } else if (siteUrl.equals("https://phdify.com/")) {
            id = app.driver.getCurrentUrl().substring(29);
        } else if (siteUrl.equals("https://essays.blablawriting.com/")) {
            id = app.driver.getCurrentUrl().substring(43);
        }
        return id;
    }


    public String idNotEasyBidding(String siteUrl) {
        if (siteUrl.equals("https://edusson.com/")) {
            id = app.driver.getCurrentUrl().substring(51);
        } else if (siteUrl.equals("https://edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(53);
        } else if (siteUrl.equals("https://papersowl.com/")) {
            id = app.driver.getCurrentUrl().substring(53);
        } else if (siteUrl.equals("https://studyfaq.com/")) {
            id = app.driver.getCurrentUrl().substring(52);
        } else if (siteUrl.equals("https://eduzaurus.com/")) {
            id = app.driver.getCurrentUrl().substring(53);
        } else if (siteUrl.equals("https://paperdon.com/")) {
            id = app.driver.getCurrentUrl().substring(52);
        } else if (siteUrl.equals("https://studarea.com/")) {
            id = app.driver.getCurrentUrl().substring(52);
        } else if (siteUrl.equals("https://essaybison.com/")) {
            id = app.driver.getCurrentUrl().substring(54);
        } else if (siteUrl.equals("https://samedaypapers.com/")) {
            id = app.driver.getCurrentUrl().substring(57);
        } else if (siteUrl.equals("https://paperell.com/")) {
            id = app.driver.getCurrentUrl().substring(52);
        } else if (siteUrl.equals("https://essaytornado.com/")) {
            id = app.driver.getCurrentUrl().substring(56);
        } else if (siteUrl.equals("https://essayvikings.com/")) {
            id = app.driver.getCurrentUrl().substring(56);
        } else if (siteUrl.equals("https://ca.edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(56);
        } else if (siteUrl.equals("https://au.edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(56);
        } else if (siteUrl.equals("https://uk.edubirdie.com/")) {
            id = app.driver.getCurrentUrl().substring(56);
        } else if (siteUrl.equals("https://gpaessay.com/")) {
            id = app.driver.getCurrentUrl().substring(52);
        } else if (siteUrl.equals("https://australianwritings.com.au/")) {
            id = app.driver.getCurrentUrl().substring(65);
        } else if (siteUrl.equals("https://papercp.com/")) {
            id = app.driver.getCurrentUrl().substring(51);
        } else if (siteUrl.equals("https://typemyessays.com/")) {
            id = app.driver.getCurrentUrl().substring(55);
        } else if (siteUrl.equals("https://essays.studymoose.com/")) {
            id = app.driver.getCurrentUrl().substring(61);
        }
        return id;
    }

    public void asWriter(String orderUrl) {
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
    }

    public void asCustomer(String orderUrl) {
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().goTo(orderUrl);
    }

    public boolean isUserLogged() {
        if (isElementPresent("//a[@class='user-name']")) {
            return true;
        } else {
            return false;
        }
    }
}






