package ua.qa.edusson.tests.standartCheck;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.tests.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.WebWindow;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;


public class StandartCheckCreditCardTests extends TestBase {


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


    @Test
    // работает со всеми сайтами
    public void standartCheck_CardPay() {
        siteUrl = app.driver.getCurrentUrl();
        if (app.getHelper().hasSiteCardPay(siteUrl)) {
            userAuthorizationPage.userLogin(Config.customer1, Config.password);
            myOrdersCustomerPage.makeNewOrder();
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
            app.getHelper().waitLoading("order#redirect_url=");
            orderId = app.getHelper().idNotEasyBidding(siteUrl);
            System.out.println(orderId);
            customerUrl = siteUrl + "order/view/" + orderId;
            writerUrl = "http://edusson.com/order/view/" + orderId;
            WebWindow ww = new WebWindow(app.driver, "http://edusson.com/");
            if (siteUrl.equals("http://edusson.com/")) {
                asWriter(writerUrl);
            } else {
                userAuthorizationPage.userLogin(Config.writer1, Config.password);
                myOrdersWriterPage.closePopup();
                app.getHelper().goTo(writerUrl);
            }
            orderBiddingWriterPage.createBid("6");
            ww.switchToParent();
            if (siteUrl.equals("http://edusson.com/")) {
                this.asCustomer(customerUrl);
                app.getHelper().goTo(customerUrl);
            }
            orderBiddingCustomerPage.bid1();
            orderPayCustomerPage.chooseCardPay();
            orderPayCustomerPage.clickReserveButton();
            creditCardPayment.setAllFields();
            orderPayCustomerPage.confirmPay();
            app.getHelper().waitLoading(siteUrl);
            Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
            Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
            ww.switchToWindow();
            if (siteUrl.equals("http://edusson.com/")) {
                orderFinishedViewPage.closePopup();
                asWriter(writerUrl);
            } else {
                orderBiddingWriterPage.goToOrder();
            }
            orderInProgressPage.uploadRevision();
            ww.switchToParent();
            if (siteUrl.equals("http://edusson.com/")) {
                asCustomer(customerUrl);
                app.getHelper().goTo(customerUrl);
            } else {
                orderPayThankYouCustomerPage.goToOrder();
            }
            orderInProgressPage.releaseMoney("100");
            orderFinishedViewPage.closePopup();
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            ww.switchToWindow();
            if (siteUrl.equals("http://edusson.com/")) {
                asWriter(writerUrl);
            } else {
                app.driver.navigate().refresh();
            }
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
            headerMenu.userLogOut();
            ww.close();
            orderFinishedViewPage.closePopup();
            headerMenu.userLogOut();
            System.out.println("TEST PASSED" + " " + siteUrl);
        } else {
            System.out.println("There is not CardPay on site " + siteUrl);
        }
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

}




	