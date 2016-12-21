package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.tests.TestBase.app;


public class PayPalPage {


    //first log in
    public static String email = "//div[@id='login_emaildiv']//input[@type='email']";
    public static String pass = "//div[@id='login_passworddiv']//input[@id='password']";
    public static String logInButton = "//button[@type='submit']";
    public static String frame = "//iframe[@name='injectedUl']";
    // confirm pay - next page
    public static String continueButton = "//form[@name='confirm']//input[@validate-submit='onPay()']";


    //second log in
    public static String email2 = "//input[@id='login_email']";
    public static String pass2 = "//input[@id='login_password']";
    public static String logInButton2 = "//input[@id='submitLogin']";
    public static String confirmButton = "//div[@id='continueButtonSection']//input[@type='submit']";

    public void setUserEmail(String strUserEmail) {
        WebElement e_mail = app.getHelper().cyclicElementSearchByXpath(email);
        e_mail.clear();
        e_mail.sendKeys(strUserEmail);

    }


    public void setUserPassword(String strPassword) {
        WebElement password = app.getHelper().cyclicElementSearchByXpath(pass);
        password.clear();
        password.sendKeys(strPassword);


    }

    public void clickLogBut() {
        WebElement login_button = app.getHelper().cyclicElementSearchByXpath(logInButton);
        login_button.click();

    }

    public void clickContinue() {
        WebElement continue_button = app.getHelper().cyclicElementSearchByXpath(continueButton);
        continue_button.click();
    }


    public void payPayPal(String strUserEmail, String strPassword) {
        app.getHelper().waitForJSandJQueryToLoad();
        app.getHelper().waitElementNotVisible("//p[@class='loader']");
        app.getHelper().waitElementNotVisible("//div[@ng-if='loading']");

        app.getHelper().sleep(2);
        String pageBefore = app.driver.getCurrentUrl();
        System.out.println(pageBefore);
        String page = app.driver.getCurrentUrl().substring(95);
        System.out.println(page);
        if (page.equals("/checkout/review")) {
            app.getHelper().waitElement(continueButton);
            Helper.sleep(1);
            this.clickContinue();
        } else if (page.equals("/checkout/login")) {
            this.logInToPayPalMain(strUserEmail, strPassword);
        } else {
            this.confirmPayPal_2(strUserEmail, strPassword);
        }
        app.getHelper().waitElementNotVisible("//p[@class='loader']");
        app.getHelper().waitElementNotVisible("//div[@ng-if='loading']");
       // app.getHelper().waitForJSandJQueryToLoad();

    }


    public void logInToPayPalMain(String strUserEmail, String strPassword) {

        app.driver.switchTo().frame(app.driver.findElement(By.name("injectedUl")));
        app.getHelper().waitElement(email);
        this.setUserEmail(strUserEmail);
        this.setUserPassword(strPassword);
        Helper.sleep(1);
        this.clickLogBut();
        Helper.sleep(1);

        app.getHelper().waitForJSandJQueryToLoad();
        app.driver.switchTo().defaultContent();
        app.getHelper().waitElementNotVisible("//p[@class='loader']");
        app.getHelper().waitElementNotVisible("//div[@ng-if='loading']");
        app.getHelper().waitLoading2("/checkout/login");
        //String page2 = app.driver.getCurrentUrl();
        //System.out.println(page2 + "  " + "2");
        if (app.driver.getCurrentUrl().substring(95).equals("/checkout/review")) {
            //app.getHelper().waitElementNotVisible("//p[@class='loader']");
            app.getHelper().waitElement(continueButton);
            this.clickContinue();
        } else {
            this.confirmPayPal_2(strUserEmail, strPassword);
        }
    }

    public void confirmPayPal_2(String strUserEmail, String strPassword) {
        app.getHelper().waitElementNotVisible("//p[@class='loader']");
        app.getHelper().waitForJSandJQueryToLoad();
        // app.getHelper().sleep(1);
        if (app.getHelper().isElementPresent(confirmButton)) {
            this.clickConfirm();
        } else if (app.getHelper().isElementPresent(email2)) {
            this.setUserEmail_2(strUserEmail);
            this.setUserPassword_2(strPassword);
            this.clickLogBut_2();
            this.clickConfirm();
        }
        app.getHelper().waitForJSandJQueryToLoad();
        app.getHelper().waitElementNotVisible("//*[text()='loading']");
        app.getHelper().sleep(1);
       // app.getHelper().waitElement(confirmButton);
       // this.clickConfirm();

    }

    public void setUserEmail_2(String strUserEmail) {
        WebElement e_mail = app.getHelper().cyclicElementSearchByXpath(email2);
        Helper.sleep(1);
        e_mail.clear();
        e_mail.sendKeys(strUserEmail);

    }


    public void setUserPassword_2(String strPassword) {
        WebElement password = app.getHelper().cyclicElementSearchByXpath(pass2);
        password.clear();
        password.sendKeys(strPassword);


    }

    public void clickLogBut_2() {
        WebElement login_button = app.getHelper().cyclicElementSearchByXpath(logInButton2);
        login_button.click();

    }

    public void clickConfirm() {
        WebElement confirm_button = app.getHelper().cyclicElementSearchByXpath(confirmButton);
        confirm_button.click();


    }


}