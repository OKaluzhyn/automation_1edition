package ua.qa.edusson.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.tests.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;

public class Base extends TestBase {
    public String orderUrl;
    public String orderId;
    public String writerUrl;

    public String customerReleasedPercent;
    public String writerReleasedPercent;


    public void unhide(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['visibility']='visible';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(WebDriver driver, By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(driver, input);
        input.sendKeys(file);

    }


    @Test(enabled = false)
    public void uploadTest1() {

        app.driver.get("http://blueimp.github.io/jQuery-File-Upload/basic.html");
        attachFile(app.driver, By.id("fileupload"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(10);
        /*app.driver.get("http://imgup.net/");
        attachFile(app.driver, By.id("image_image"), app.getHelper().getRevision().getAbsolutePath());
		app.getHelper().sleep(10);
		app.driver.get("http://www.2imgs.com/");
		attachFile(app.driver, By.id("f_file"), app.getHelper().getRevision().getAbsolutePath());
*/
    }

    @Test(enabled = false)
    public void uploadTest2() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.clickNext1();
        app.getHelper().sleep(1);
        orderCreateCustomerPage.clickNext2();
        app.getHelper().sleep(1);
        attachFile(app.driver, By.id("attach_file"), "C:\\Users\\tester\\resources\\testFile.pdf");
        app.getHelper().sleep(10);
        //orderCreateCustomerPage.proceedToBidding();
    }

    @Test(enabled = false)
    public void uploadTest3() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();

        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get("http://edusson.com/order/view/169636#order-tabs3");
        attachFile(app.driver, By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(10);
    }

    @Test(enabled = false)
    public void customerUploadingTest() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        //app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.clickNext1();
        app.getHelper().sleep(1);
        orderCreateCustomerPage.clickNext2();
        app.getHelper().sleep(1);
        attachFile(app.driver, By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(2);
        orderCreateCustomerPage.proceedToBidding();
        app.getHelper().waitLoading("order#redirect_url=");
    }



    @Test(enabled = false)
    public void payPal() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        PayPalPage payPalPage = new PayPalPage();
        OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo("http://edusson.com/order/pay/174899");
        orderPayCustomerPage.clickReserveButton();
        payPalPage.payPayPal("edussonpay4@ukr.net", "123456789");
       // payPalPage.logInToPayPalMain("edussonpay4@ukr.net", "123456789");
        app.getHelper().waitLoading("thankyou");
        Helper.sleep(10);
    }

    @Test //(enabled = false)
    public void login() {
        //app.driver.get("http://essayvikings.com/");
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        HeaderMenu header = new HeaderMenu();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);

        app.getHelper().waitLoading("orders");
        header.userLogOut();
        Helper.sleep(3);
       /* app.driver.get("http://edubirdie.com/");
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        header.userLogOut();
        Helper.sleep(3);
        app.driver.get("http://edusson.com/");
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        header.userLogOut();
        Helper.sleep(3);
*/

    }
    @Test (enabled = false)
    public void paypal(){
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
        OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
        MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
        OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
        PayPalPage payPalPage = new PayPalPage();
        OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
        HeaderMenu headerMenu = new HeaderMenu();

        app.driver.get("http://customwriting.com/");
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl+ " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl+ " Reason: Payment is being reviewed by PayPal");
       // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        //app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();

        app.driver.get("http://essays.studymoose.com/");
        String siteUrl1 = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl1);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl1+ " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl1+ " Reason: Payment is being reviewed by PayPal");
       // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl1+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
       // app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();

        app.driver.get("http://paperial.com/");
        String siteUrl2 = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl2);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl2+ " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl2+ " Reason: Payment is being reviewed by PayPal");
       // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl2+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
       // app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();

        app.driver.get("http://customwriting.com/");

        String siteUrl3 = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl3);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl3+ " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl3+ " Reason: Payment is being reviewed by PayPal");
       // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl3+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        //app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();
    }
    }



