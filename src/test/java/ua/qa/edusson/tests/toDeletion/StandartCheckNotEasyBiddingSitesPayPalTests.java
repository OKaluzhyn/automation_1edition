package ua.qa.edusson.tests.toDeletion;

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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;

public class StandartCheckNotEasyBiddingSitesPayPalTests extends TestBase {

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
    PayPalPage payPalPage = new PayPalPage();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();
    OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();


    @Test
    // PayPall
    // 100%


    public void standartCheck_PayPal_Production_Not_EasyBidding() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().waitLoading("order#redirect_url=");
        orderId = app.getHelper().idNotEasyBidding(siteUrl);
        System.out.println(orderId);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        customerUrl = siteUrl + "order/view/" + orderId;
        app.getHelper().goToEdusson();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        orderBiddingWriterPage.createBid("6");
        app.getHelper().goTo(customerUrl);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.choosePayPal();
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("100");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED" + " " + siteUrl);
    }
}


