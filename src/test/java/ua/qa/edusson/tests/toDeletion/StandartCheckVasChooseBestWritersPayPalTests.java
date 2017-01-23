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
import ua.qa.edusson.utils.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;

public class StandartCheckVasChooseBestWritersPayPalTests extends TestBase {

    String siteUrl;
    String orderId;
    String writerUrl;
    String customerUrl;

    String customerReleasedPercent;
    String writerReleasedPercent;

    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    PayPalPage payPalPage = new PayPalPage();
    HeaderMenu headerMenu = new HeaderMenu();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();


    @Test
    // PayPall 100%


    public void standartCheck_Vas_ChooseBestWriter_Production() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderId = app.getHelper().idEasyBidding(siteUrl);
        System.out.println(orderId);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        customerUrl = siteUrl + "order/view/" + orderId;
        orderPayCustomerPage.choosePayPal();
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
        headerMenu.userLogOut();
        if (!app.driver.getCurrentUrl().equals("http://edusson.com/")) {
            app.getHelper().goToEdusson();
        }
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        orderBiddingWriterPage.easyBiddingApplyprice();
        orderInProgressPage.uploadRevision();
        headerMenu.userLogOut();
        app.driver.get(siteUrl);
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        Helper.sleep(1);
        app.getHelper().goTo(customerUrl);
        orderInProgressPage.releaseMoney("30");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
        if (!app.driver.getCurrentUrl().equals("http://edusson.com/")) {
            app.getHelper().goToEdusson();
        }
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        headerMenu.userLogOut();
        app.driver.get(siteUrl);
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        Helper.sleep(1);
        app.getHelper().goTo(customerUrl);
        orderInProgressPage.releaseMoney("70");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
        if (!app.driver.getCurrentUrl().equals("http://edusson.com/")) {
            app.getHelper().goToEdusson();
        }
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED" + " " + siteUrl);
    }
}

