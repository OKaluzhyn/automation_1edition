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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.*;

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
    OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();


    @Test
    // PayPall
    // 100%

    public void standartCheck_Vas_ChooseBestWriter_Production() {

        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        if (siteUrl.equals("http://studyfaq.com/")) {
            orderCreateCustomerPage.createOrderForStudyfaqWithVasChooseBestWriter("test for webdriver", "test");
        } else {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter("test for webdriver", "test");
        }
        app.getHelper().waitLoading("/order/pay/");
        if (siteUrl.equals("http://edusson.com/")) {
            orderId = app.driver.getCurrentUrl().substring(29);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://eduzaurus.com/")) {
            orderId = app.driver.getCurrentUrl().substring(32);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://paperdon.com/")) {
            orderId = app.driver.getCurrentUrl().substring(31);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://papersowl.com/")) {
            orderId = app.driver.getCurrentUrl().substring(32);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://studarea.com/")) {
            orderId = app.driver.getCurrentUrl().substring(31);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://essaybison.com/")) {
            orderId = app.driver.getCurrentUrl().substring(33);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://samedaypapers.com/")) {
            orderId = app.driver.getCurrentUrl().substring(36);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://paperell.com/")) {
            orderId = app.driver.getCurrentUrl().substring(31);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://essaytornado.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://essayvikings.com/")) {
            orderId = app.driver.getCurrentUrl().substring(34);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://studyfaq.com/")) {
            orderId = app.driver.getCurrentUrl().substring(31);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://edubirdie.com/")) {
            orderId = app.driver.getCurrentUrl().substring(32);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://ca.edubirdie.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://au.edubirdie.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://uk.edubirdie.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://gpaessay.com/")) {
            orderId = app.driver.getCurrentUrl().substring(31);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://australianwritings.com.au/")) {
            orderId = app.driver.getCurrentUrl().substring(42);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://papercp.com/")) {
            orderId = app.driver.getCurrentUrl().substring(28);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://typemyessays.com/")) {
            orderId = app.driver.getCurrentUrl().substring(33);
            System.out.println(orderId);
        }

        writerUrl = "http://edusson.com/order/view/" + orderId;
        customerUrl = siteUrl + "order/view/" + orderId;
        System.out.println("writerUrl" + "-" + writerUrl + " ; " +
                "customerUrl" + "-" + customerUrl);
        Helper.sleep(1);
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl+ " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl+ " Reason: Payment is being reviewed by PayPal");
        //Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        //app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();
        app.getHelper().goToEdusson();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        Helper.sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        Helper.sleep(2);
        orderBiddingWriterPage.easyBiddingApplyprice();
        Helper.sleep(2);
        orderInProgressPage.uploadRevision();
        Helper.sleep(2);
        headerMenu.userLogOut();
        app.driver.get(siteUrl);
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        Helper.sleep(1);
        app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("100");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
        app.getHelper().goToEdusson();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        Helper.sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED" + " " + siteUrl);
    }
}

