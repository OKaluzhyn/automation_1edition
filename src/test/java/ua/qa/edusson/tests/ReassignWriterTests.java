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

/**
 * Created by tester on 09.09.2016.
 */
public class ReassignWriterTests extends TestBase {
    public String orderUrl;
    public String orderId;
    public String customerReleasedPercent;
    public String writerReleasedPercent;
    public String writerUrl;


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
    RequestAnotherWriterPopUp requestAnotherWriterPopUp = new RequestAnotherWriterPopUp();


    @Test
    public void standartReassign_Production_Edusson() throws Exception {
        app.getHelper().goToEdusson();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder("test order for reassign", "test");
        app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        String orderUrl = app.driver.getCurrentUrl();
        app.getHelper().sleep(1);
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        orderBiddingWriterPage.createBid("6");
        headerMenu.userLogOut();
        app.getHelper().sleep(1);
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        app.driver.get(orderUrl);
        orderBiddingCustomerPage.bid1();
        app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
        app.getHelper().sleep(1);
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
        headerMenu.userLogOut();
        app.getHelper().sleep(1);
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        headerMenu.userLogOut();
        //check: is order InProgress state
        // orderInProgressPage.
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        app.driver.get(orderUrl);
        System.out.println(orderInProgressPage.checkReleasedMoneyCustomerPage());
        Assert.assertEquals(orderInProgressPage.checkReleasedMoneyCustomerPage(), null);
        orderInProgressPage.clickReassignButton();
        requestAnotherWriterPopUp.typeReason();
        requestAnotherWriterPopUp.submitReassign();
        String orderReassignUrl = app.driver.getCurrentUrl();
        //assertEquals(edussonGetOrderId, edussonGetOrderId +1);
        headerMenu.userLogOut();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        //assert order cancel
        app.driver.get(orderReassignUrl);
        //assert order is not available

    }

   }