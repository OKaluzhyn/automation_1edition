package ua.qa.edusson.tests;

import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.utils.Config;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StandartCheckOtherSitesTests extends TestBase {
    String orderUrl;
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


    @Test
    // PayPall
    // 20%+80%


    public void standartCheck_PayPal_Production_Not_EasyBidding() throws Exception {

       /* String[] sites = {
                "http://eduzaurus.com/",
                "http://paperdon.com/",
                "http://papersowl.com/",
                "http://studarea.com/",
                "http://essaybison.com/",
                "http://samedaypapers.com/",
                "http://paperell.com/",
                "http://essaystorm.com/",
                "http://essayvikings.com/",
                //"http://customwriting.com/",
        };
        for (int i = 0; i < sites.length; i++) {

            siteUrl = sites[i];
            app.driver.get(siteUrl);
*/
        app.driver.get("http://papersowl.com/");
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.logIn(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrderForOtherSites("test for webdriver", "test");
        app.getHelper().WaitLoading("order#redirect_url=");
        //assertTrue(app.driver.getCurrentUrl().contains("order#redirect_url="));
        app.driver.navigate().refresh();
        app.getHelper().sleep(5);
        customerUrl = app.driver.getCurrentUrl();
        if (siteUrl.equals("http://eduzaurus.com/")) {
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
        } else if (siteUrl.equals("http://essaystorm.com/")) {
            orderId = app.driver.getCurrentUrl().substring(33);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://essayvikings.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        }


        writerUrl = "http://edusson.com/order/view/" + orderId;
        app.getHelper().goToEdusson();

        if (app.getHelper().isElementPresent(userAuthorizationPage.getloginLink()) == true) {
            userAuthorizationPage.logIn(Config.writer1, Config.password);

            app.getHelper().sleep(2);
            myOrdersWriterPage.closePopup();
            app.driver.get(writerUrl);
        } else {
            app.driver.get(writerUrl);
        }
        app.getHelper().sleep(2);
        System.out.println(app.driver.getCurrentUrl());
        orderBiddingWriterPage.createBid("6");
        app.getHelper().goTo(customerUrl);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.confirmPay();
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().WaitLoading("thankyou");
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("20");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("80");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED");
    }
}


