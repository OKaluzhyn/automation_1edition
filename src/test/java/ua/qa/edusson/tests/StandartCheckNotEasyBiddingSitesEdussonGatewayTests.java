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

public class StandartCheckNotEasyBiddingSitesEdussonGatewayTests extends TestBase {

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

    @Test
    //  Edusson Gateway
    // 20%+80%


    public void standartCheck_CreditCard_Production_Not_EasyBidding() {

        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        if (siteUrl.equals("http://studyfaq.com/")) {
            orderCreateCustomerPage.createOrderForStudyfaq("test for webdriver", "test");
        } else {
            orderCreateCustomerPage.createOrder("test for webdriver", "test");
        }
        app.getHelper().waitLoading("order#redirect_url=");
        app.driver.navigate().refresh();
        app.getHelper().sleep(5);
        customerUrl = app.driver.getCurrentUrl();
        if (siteUrl.equals("http://papersowl.com/")) {
            orderId = app.driver.getCurrentUrl().substring(32);
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
        }

        writerUrl = "http://edusson.com/order/view/" + orderId;
        app.getHelper().goToEdusson();

      /*  if (app.getHelper().isElementPresent(userAuthorizationPage.getloginLink()) == true) {
            userAuthorizationPage.userLogin(Config.writer1, Config.password);
            app.getHelper().sleep(2);
            myOrdersWriterPage.closePopup();
            app.driver.get(writerUrl);
        } else {
            app.driver.get(writerUrl);
        }*/
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        System.out.println(app.driver.getCurrentUrl());
        orderBiddingWriterPage.createBid("6");
        app.getHelper().goTo(customerUrl);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.chooseCardPay();
        orderPayCustomerPage.clickReserveButton();
        creditCardPayment.setAllFields();
        orderPayCustomerPage.confirmPay();
        orderPayThankYouCustomerPage.stopTestBecouseFailedPayment();
        app.getHelper().waitLoading("thankyou");
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("100");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        headerMenu.userLogOut();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED" + " " + siteUrl);
    }
}


