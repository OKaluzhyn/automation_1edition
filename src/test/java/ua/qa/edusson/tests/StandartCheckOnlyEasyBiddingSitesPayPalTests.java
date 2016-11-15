package ua.qa.edusson.tests;


import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.MyOrdersCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderCreateCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderPayCustomerPage;
import ua.qa.edusson.pages.CustomerPages.PayPalPage;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StandartCheckOnlyEasyBiddingSitesPayPalTests extends TestBase {

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
    // PayPall
    // 20%+80%

    public void standartCheck_Only_EasyBidding_Production() throws Exception {

        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        if (siteUrl.equals("http://studyfaq.com/")) {
            orderCreateCustomerPage.createOrderForStudyfaq("test for webdriver", "test");
        } else {
            orderCreateCustomerPage.createOrder("test for webdriver", "test");
        }
        app.getHelper().WaitLoading("/order/pay/");


        if (Objects.equals(siteUrl, "http://paperial.com/")) {
            orderId = app.driver.getCurrentUrl().substring(30);
            System.out.println(orderId);
        } else if (Objects.equals(siteUrl, "http://essayontime.com.au/")) {
            orderId = app.driver.getCurrentUrl().substring(36);
            System.out.println(orderId);
        } else if (Objects.equals(siteUrl, "http://phdify.com/")) {
            orderId = app.driver.getCurrentUrl().substring(28);
            System.out.println(orderId);
        } else if (Objects.equals(siteUrl, "http://customwriting.com/")) {
            orderId = app.driver.getCurrentUrl().substring(35);
            System.out.println(orderId);
        } else if (Objects.equals(siteUrl, "http://typemyessays.com/")) {
            orderId = app.driver.getCurrentUrl().substring(34);
            System.out.println(orderId);
        } else if (Objects.equals(siteUrl, "http://essays.studymoose.com/")) {
            orderId = app.driver.getCurrentUrl().substring(39);
            System.out.println(orderId);
        } else if (Objects.equals(siteUrl, "http://essays.blablawriting.com/")) {
            orderId = app.driver.getCurrentUrl().substring(36);
            System.out.println(orderId);
        }

        writerUrl = "http://edusson.com/order/view/" + orderId;
        customerUrl = siteUrl + "order/view/" + orderId;
        System.out.println( "writerUrl" + "-"+  writerUrl+";"+
                "customerUrl" + "-"+  customerUrl);
        Helper.sleep(1);
        orderPayCustomerPage.confirmPay();
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().WaitLoading("thankyou");
        app.getHelper().goToEdusson();
        if (app.getHelper().isElementPresent(userAuthorizationPage.getloginLink())) {
            userAuthorizationPage.userLogin(Config.writer1, Config.password);
            Helper.sleep(2);
            myOrdersWriterPage.closePopup();
        }
        app.driver.get(writerUrl);
        Helper.sleep(2);
        orderBiddingWriterPage.easyBiddingApplyprice();
        Helper.sleep(2);
        orderInProgressPage.uploadRevision();
        Helper.sleep(2);
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

