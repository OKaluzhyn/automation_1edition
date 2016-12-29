package ua.qa.edusson.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;
import ua.qa.edusson.utils.WebWindow;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;


public class StandartCheckInTwoTabsTests extends TestBase {


    String siteUrl;
    String orderId;
    String writerUrl;
    String customerUrl;
    String customerReleasedPercent;
    String writerReleasedPercent;


    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
    OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
    HeaderMenu headerMenu = new HeaderMenu();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();
    OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
    PayPalPage payPalPage = new PayPalPage();


/*
    @Test //(enabled = false)
    // PayPall
    // 10%+90%

    public void standartCheck_PAyPal_Production_Edusson() {

        //app.getHelper().goToEdusson();
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("order#redirect_url=");
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        WebWindow ww = new WebWindow(app.driver, "http://edusson.com/");
       // headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        orderBiddingWriterPage.createBid("6");
       // headerMenu.userLogOut();
        //userAuthorizationPage.userLogin(Config.customer1, Config.password);
       // app.getHelper().waitLoading("orders");
       // app.getHelper().goTo(orderUrl);
        ww.switchToParent();
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.clickReserveButton();

        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl+ " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl+ " Reason: Payment is being reviewed by PayPal");
        //Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
       // app.getHelper().waitLoading("thankyou");
       // headerMenu.userLogOut();
       // userAuthorizationPage.userLogin(Config.writer1, Config.password);
     //   myOrdersWriterPage.closePopup();
      //  app.getHelper().goTo(orderUrl);
        ww.switchToWindow();
       /* orderInProgressPage.uploadRevision();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);
        ww.switchToParent();
       orderInProgressPage.releaseMoney("10");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);

        ww.switchToWindow();
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);

        ww.switchToParent();
        orderInProgressPage.releaseMoney("90");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        ww.switchToWindow();
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();

        System.out.println("TEST PASSED" + " " + siteUrl);

    }
*/

    @Test
    // PayPall
    // 100%


    public void standartCheck_CardPay() {
        siteUrl = app.driver.getCurrentUrl();
        if (app.getHelper().hasSiteCardPay(siteUrl)) {
            userAuthorizationPage.userLogin(Config.customer1, Config.password);
            myOrdersCustomerPage.makeNewOrder();
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
            app.getHelper().waitLoading("order#redirect_url=");
            orderId = app.getHelper().idNotEasyBidding(siteUrl);
            System.out.println(orderId);
            customerUrl = siteUrl + "/order/view/" + orderId;
            writerUrl = "http://edusson.com/order/view/" + orderId;
            WebWindow ww = new WebWindow(app.driver, "http://edusson.com/");
            userAuthorizationPage.userLogin(Config.writer1, Config.password);
            myOrdersWriterPage.closePopup();
            app.getHelper().goTo(writerUrl);
            orderBiddingWriterPage.createBid("6");
            ww.switchToParent();
            orderBiddingCustomerPage.bid1();
            orderPayCustomerPage.chooseCardPay();
            orderPayCustomerPage.clickReserveButton();
            creditCardPayment.setAllFields();
            orderPayCustomerPage.confirmPay();
            app.getHelper().waitLoading(siteUrl);
            Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
            Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
            ww.switchToWindow();
            orderBiddingWriterPage.goToOrder();
            orderInProgressPage.uploadRevision();
            ww.switchToParent();
            orderPayThankYouCustomerPage.goToOrder();
            orderInProgressPage.releaseMoney("100");
            orderFinishedViewPage.closePopup();
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            ww.switchToWindow();
            app.driver.navigate().refresh();
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
            headerMenu.userLogOut();
            System.out.println("TEST PASSED" + " " + siteUrl);
        }else{
            System.out.println("There is not CardPay on site "+ siteUrl);
        }
    }

    @Test
    public void standartCheck_PayPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        WebWindow ww = new WebWindow(app.driver, "http://edusson.com/");
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        ww.switchToParent();
        if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            app.getHelper().waitLoading("/order/pay/");
            orderId = app.getHelper().idEasyBidding(siteUrl);
            writerUrl = "http://edusson.com/order/view/" + orderId;
            customerUrl = siteUrl + "order/view/" + orderId;
            System.out.println(orderId);
            orderPayCustomerPage.confirmPay();
            payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
            Helper.sleep(1);
            app.getHelper().waitLoading(siteUrl);
            Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
            Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
            ww.switchToWindow();
            System.out.println(writerUrl);
            app.getHelper().goTo(writerUrl);
            orderBiddingWriterPage.easyBiddingApplyprice();
        } else {
            app.getHelper().waitLoading("order#redirect_url=");
            orderId = app.getHelper().idNotEasyBidding(siteUrl);
            writerUrl = "http://edusson.com/order/view/" + orderId;
            customerUrl = siteUrl + "order/view/" + orderId;
            System.out.println(orderId);
            ww.switchToWindow();
            System.out.println(writerUrl);
            app.getHelper().goTo(writerUrl);
            orderBiddingWriterPage.createBid("6");
            ww.switchToParent();
            orderBiddingCustomerPage.bid1();
            orderPayCustomerPage.choosePayPal();
            orderPayCustomerPage.confirmPay();
            payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
            app.getHelper().waitLoading(siteUrl);
            Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
            Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
            ww.switchToWindow();
            orderBiddingWriterPage.goToOrder();
        }
        orderInProgressPage.uploadRevision();
        ww.switchToParent();
        orderPayThankYouCustomerPage.goToOrder();
        orderInProgressPage.releaseMoney("100");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        ww.switchToWindow();
        app.driver.navigate().refresh();
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED" + " " + siteUrl);
    }
}




	