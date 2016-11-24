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


public class StandartCheckEdussonProductionTests extends TestBase {


    private static String orderUrl;
    public String customerReleasedPercent;
    public String writerReleasedPercent;


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


    @Test (enabled = false)
    // PayPall
    // 10%+90%

    public void standartCheck_PAyPal_Production_Edusson() {

        //app.getHelper().goToEdusson();
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("order#redirect_url=");
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        orderBiddingWriterPage.createBid("6");
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.clickReserveButton();
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        orderInProgressPage.uploadRevision();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);
        orderInProgressPage.releaseMoney("10");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);
        orderInProgressPage.releaseMoney("90");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();

        System.out.println("TEST PASSED" + " " + siteUrl);

    }

    @Test //(enabled = false)
    //CreditCard 100% release

    public void standartCheck_CreditCard_Production_Edusson() throws Exception {
        // app.getHelper().goToEdusson();
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        app.getHelper().waitLoading("order#redirect_url=");
        app.driver.navigate().refresh();
        orderUrl = app.driver.getCurrentUrl();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        orderBiddingWriterPage.createBid("6");
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);
        orderBiddingCustomerPage.bid1();
        orderPayCustomerPage.chooseCardPay();
        orderPayCustomerPage.clickReserveButton();
        creditCardPayment.setAllFields();
        app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        orderInProgressPage.uploadRevision();
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo(orderUrl);
        orderInProgressPage.releaseMoney("100");
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        myOrdersWriterPage.closePopup();
        app.getHelper().goTo(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();

        System.out.println("TEST PASSED" + " " + siteUrl);

    }

}

	