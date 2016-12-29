package ua.qa.edusson.tests.toDeletion;

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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StandartCheckStudyfaqProductionTests extends TestBase {
    public String orderUrl;
    public String orderId;
    public String writerUrl;
    public String siteUrl;

    public String customerReleasedPercent;
    public String writerReleasedPercent;
    // ������������� �������
    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
    OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
    HeaderMenu headerMenu = new HeaderMenu();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    PayPalPage payPalPage = new PayPalPage();
    //OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();


    @Test
    //PayPall
    // 20%+80%

    public void standartCheck_PAyPal_Production_Studyfaq() throws Exception {
        siteUrl = app.driver.getCurrentUrl();
        app.getHelper().goToStudyfaq();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        //go to order form
        myOrdersCustomerPage.makeNewOrder();
        // create order
        app.getHelper().sleep(1);
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().sleep(1);
        assertTrue(app.driver.getCurrentUrl().contains("order#redirect_url="));
        app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        orderId = orderUrl.substring(31);
        System.out.println(orderId);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        app.getHelper().sleep(1);
        app.getHelper().goToEdusson();
        app.getHelper().sleep(1);
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        System.out.println(app.driver.getCurrentUrl());
        orderBiddingWriterPage.createBid("6");
        app.getHelper().sleep(2);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(1);
        app.driver.get(orderUrl);
        app.getHelper().sleep(2);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.choosePayPal();
        app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
        app.getHelper().sleep(1);
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().sleep(2);
        app.getHelper().goToEdusson();
        app.getHelper().sleep(1);
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("20");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.getHelper().goToEdusson();
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("80");
        app.getHelper().sleep(2);
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.getHelper().sleep(2);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        app.getHelper().sleep(2);
        headerMenu.userLogOut();
        System.out.println("TEST PASSED");

    }


    @Test
    //  CardPay
    // 20%+80%

    public void standartCheck_CreditCard_Production_Studyfaq() throws Exception {
        app.getHelper().goToStudyfaq();
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        //go to order form
        myOrdersCustomerPage.makeNewOrder();
        // create order
        app.getHelper().sleep(1);
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        //assertTrue(app.driver.getCurrentUrl().contains("order#redirect_url="));
        app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        orderId = orderUrl.substring(31);
        System.out.println(orderId);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        app.getHelper().sleep(1);
        app.getHelper().goToEdusson();
        app.getHelper().sleep(1);
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        System.out.println(app.driver.getCurrentUrl());
        orderBiddingWriterPage.createBid("6");
        app.getHelper().sleep(2);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(1);
        app.driver.get(orderUrl);
        app.getHelper().sleep(2);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.chooseCardPay();
        app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
        creditCardPayment.setAllFields();
        app.getHelper().sleep(60);
        app.getHelper().goToEdusson();
        app.getHelper().sleep(1);
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("20");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.getHelper().goToEdusson();
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("80");
        app.getHelper().sleep(2);
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.getHelper().sleep(2);
        app.getHelper().goToStudyfaq();
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        app.getHelper().sleep(2);
        headerMenu.userLogOut();
        System.out.println("TEST PASSED");

    }
}
