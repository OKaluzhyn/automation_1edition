package ua.qa.edusson.tests.toDeletion;

import org.testng.annotations.Test;
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

public class StandartCheckEdubirdieProductionTests extends TestBase {
    public String orderUrl;
    public String orderId;
    public String writerUrl;

    public String customerReleasedPercent;
    public String writerReleasedPercent;
    // ������������� �������
    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
    OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    PayPalPage payPalPage = new PayPalPage();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();


    @Test
    //PayPal
    // 20%+80%

    public void standartCheck_PAyPal_Production_Edubirdie() throws Exception {

        app.driver.get("http://edubirdie.com/");
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        app.getHelper().sleep(1);
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        //assertTrue(app.driver.getCurrentUrl().contains("order#redirect_url="));
        app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        orderId = orderUrl.substring(32);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        app.getHelper().sleep(1);
        app.getHelper().goToEdusson();
        app.getHelper().sleep(1);
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        orderBiddingWriterPage.createBid("6");
        app.getHelper().sleep(2);
        //app.getHelper().goToEdubirdie();
        //app.getHelper().sleep(1);
        // ����� ��� �������� ������ �� ���������� � ��������� �� ����
        app.driver.get(orderUrl);
        app.getHelper().sleep(2);
        orderBiddingCustomerPage.bid1();
        app.getHelper().sleep(2);
        orderPayCustomerPage.choosePayPal();
        app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
        //app.driver.switchTo().frame(app.driver.findElement(By.name("injectedUl")));
        app.getHelper().sleep(1);
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        //payPalPage.clickContinue();
        //app.getHelper().sleep(30);
        //payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("20");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("80");
        app.getHelper().sleep(2);
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        app.getHelper().sleep(2);
        System.out.println("TEST PASSED");

    }


    @Test
    // CreditCard
    // 20%+80%

    public void standartCheck_CreditCard_Production_Edubirdie() throws Exception {
        app.driver.get("http://edubirdie.com/");
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        app.getHelper().sleep(1);
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        orderId = orderUrl.substring(32);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        app.getHelper().sleep(1);
        app.getHelper().goToEdusson();
        app.getHelper().sleep(1);
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerUrl);
        app.getHelper().sleep(2);
        orderBiddingWriterPage.createBid("6");
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        app.getHelper().sleep(2);
        orderBiddingCustomerPage.bid1();
        app.getHelper().sleep(2);
        orderPayCustomerPage.chooseCardPay();
        app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
        creditCardPayment.setAllFields();
        app.getHelper().sleep(60);
        app.driver.get(writerUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("20");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("80");
        app.getHelper().sleep(2);
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.getHelper().sleep(2);
        app.driver.get(writerUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        app.getHelper().sleep(2);
        System.out.println("TEST PASSED");

    }
}

