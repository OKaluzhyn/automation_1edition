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


    @Test
    // PayPall
    // 10%+90%

    public void standartCheck_PAyPal_Production_Edusson() throws Exception {

        app.getHelper().goToEdusson();
        userAuthorizationPage.logIn(Config.customer1, Config.password);
       // app.getHelper().sleep(1);
        //go to order form
        myOrdersCustomerPage.makeNewOrder();
        // create order
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        // assert bidding page
        //assertEquals("Edusson.com - Place your Order", app.driver.getTitle());
        //assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
      //  app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        String orderUrl = app.driver.getCurrentUrl();
     //   app.getHelper().sleep(1);
        headerMenu.userLogOut();
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        orderBiddingWriterPage.createBid("6");
        headerMenu.userLogOut();
     //   app.getHelper().sleep(1);
        userAuthorizationPage.changeUser(Config.customer1, Config.password);
     //   app.getHelper().sleep(1);
        app.driver.get(orderUrl);
        orderBiddingCustomerPage.bid1();
     //   app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
      //  app.getHelper().sleep(1);
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
        headerMenu.userLogOut();
      //  app.getHelper().sleep(1);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
     //   app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        orderInProgressPage.uploadRevision();
      //  app.getHelper().sleep(2);
     //   app.getHelper().sleep(2);
        headerMenu.userLogOut();
     //   app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.customer1, Config.password);
      //  app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("10");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
      //  app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        headerMenu.userLogOut();
      //  app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.customer1, Config.password);
     //   app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("90");
      //  app.getHelper().sleep(2);
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
    //    app.getHelper().sleep(2);
        headerMenu.userLogOut();
     //   app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
     //   app.getHelper().sleep(2);
        headerMenu.userLogOut();
        System.out.println("TEST PASSED");

    }

    @Test
    //CreditCard
    // 50%+50%
    public void standartCheck_CreditCard_Production_Edusson() throws Exception {


        app.getHelper().goToEdusson();
        userAuthorizationPage.logIn(Config.customer1, Config.password);
     //   app.getHelper().sleep(1);
        //go to order form
        myOrdersCustomerPage.makeNewOrder();
     //   app.getHelper().sleep(2);
        // create order
        orderCreateCustomerPage.createOrder("test for webdriver", "test");
        // assert bidding page
        //assertEquals("Edusson.com - Place your Order", app.driver.getTitle());
        //assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
    //    app.getHelper().sleep(1);
        app.driver.navigate().refresh();
        String orderUrl = app.driver.getCurrentUrl();
        headerMenu.userLogOut();
      //  app.getHelper().sleep(1);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        orderBiddingWriterPage.createBid("6");
        headerMenu.userLogOut();
     //   app.getHelper().sleep(1);
        userAuthorizationPage.changeUser(Config.customer1, Config.password);
      //  app.getHelper().sleep(1);
        app.driver.get(orderUrl);
      //  app.getHelper().sleep(2);
        orderBiddingCustomerPage.bid1();
      //  app.getHelper().sleep(10);
        orderPayCustomerPage.chooseCardPay();
      //  app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
       // app.getHelper().sleep(2);
        creditCardPayment.setAllFields();
     //   app.getHelper().sleep(60);
        headerMenu.userLogOut();
     //   app.getHelper().sleep(1);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
     //   app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        orderInProgressPage.uploadRevision();
      //  app.getHelper().sleep(2);
        headerMenu.userLogOut();
      //  app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.customer1, Config.password);
     //   app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("50");
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        headerMenu.userLogOut();
      //  app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        headerMenu.userLogOut();
      //  app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.customer1, Config.password);
      //  app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("50");
       // app.getHelper().sleep(2);
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
      //  app.getHelper().sleep(2);
        headerMenu.userLogOut();
      //  app.getHelper().sleep(2);
        userAuthorizationPage.changeUser(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get(orderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
      //  app.getHelper().sleep(2);
        headerMenu.userLogOut();
        System.out.println("TEST PASSED");

    }

}

	