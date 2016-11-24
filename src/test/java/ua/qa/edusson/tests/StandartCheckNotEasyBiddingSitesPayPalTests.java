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


    @Test
    // PayPall
    // 100%


    public void standartCheck_PayPal_Production_Not_EasyBidding() {

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
        } else if (siteUrl.equals("http://essaytornado.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        } else if (siteUrl.equals("http://essayvikings.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
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

       /* if (app.getHelper().isElementPresent("//a[@data-atest='atest_login_elem_popup_open']")) {
            userAuthorizationPage.userLogin(Config.writer1, Config.password);
            app.getHelper().sleep(2);
            myOrdersWriterPage.closePopup();
            app.driver.get(writerUrl);
        } else {
            app.driver.get(writerUrl);
        }*/
        app.getHelper().goToEdusson();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        System.out.println(app.driver.getCurrentUrl());
        orderBiddingWriterPage.createBid("6");
        app.getHelper().goTo(customerUrl);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.choosePayPal();
        orderPayCustomerPage.confirmPay();
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().waitLoading("thankyou");
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("100");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();

        assertEquals(customerReleasedPercent, writerReleasedPercent);
       /* app.driver.get(customerUrl);
        orderInProgressPage.releaseMoney("80");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();

        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();

        assertEquals(customerReleasedPercent, writerReleasedPercent);

       */ assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        System.out.println("TEST PASSED" + " " + siteUrl);
    }
}


