package ua.qa.edusson.tests.VasTests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.WebWindow;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;


public class ChooseBestWriterVasPayPalTests extends TestBase {


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

    public void standartCheck_PayPal() {
        siteUrl = app.driver.getCurrentUrl();
        if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
            userAuthorizationPage.userLogin(Config.customer1, Config.password);
            myOrdersCustomerPage.makeNewOrder();
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
            app.getHelper().waitLoading("/order/pay/");
            orderId = app.getHelper().idEasyBidding(siteUrl);
            writerUrl = "https://edusson.com/order/view/" + orderId;
            customerUrl = siteUrl + "order/view/" + orderId;
            System.out.println("Order ID = " + orderId);
            orderPayCustomerPage.choosePayPal();
            orderPayCustomerPage.payOrder(siteUrl);
            Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
            Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
            WebWindow ww = new WebWindow(app.driver, "https://edusson.com/");

            if (siteUrl.equals("https://edusson.com/")) {
                app.getHelper().asWriter(writerUrl);
            } else {
                userAuthorizationPage.userLogin(Config.writer1, Config.password);
                myOrdersWriterPage.closePopup();
                app.getHelper().goTo(writerUrl);
            }
            orderBiddingWriterPage.easyBiddingApplyprice();
            app.driver.navigate().refresh();
            orderInProgressPage.uploadRevision();
            ww.switchToParent();
            if (siteUrl.equals("https://edusson.com/")) {
                app.getHelper().asCustomer(customerUrl);
                app.getHelper().goTo(customerUrl);
            }
            app.getHelper().goTo(customerUrl);
            orderInProgressPage.releaseMoney("100");
            orderFinishedViewPage.closePopup();
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            ww.switchToWindow();
            if (siteUrl.equals("https://edusson.com/")) {
                app.getHelper().asWriter(writerUrl);
            } else {
                app.driver.navigate().refresh();
            }
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
            headerMenu.userLogOut();
            ww.close();
            headerMenu.userLogOut();
            System.out.println("TEST PASSED" + " " + siteUrl);
        } else {
            System.out.println("There is not such vas on site " + siteUrl);
        }
    }
}



	