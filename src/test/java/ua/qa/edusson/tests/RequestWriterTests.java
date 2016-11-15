package ua.qa.edusson.tests;


import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.OrderBiddingCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderCreateCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderPayCustomerPage;
import ua.qa.edusson.pages.CustomerPages.PayPalPage;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.pages.WriterPages.WriterProfile;
import ua.qa.edusson.utils.Config;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RequestWriterTests extends TestBase{

    public String writerProfileUrl = "http://edubirdie.com/writer/public/31122";
    public String customerReleasedPercent;
    public String writerReleasedPercent;


    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    WriterProfile writerProfile = new WriterProfile();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
    OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
    HeaderMenu headerMenu = new HeaderMenu();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    PayPalPage payPalPage = new PayPalPage();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();

    @Test
    public void test(){
        app.driver.get(writerProfileUrl);
        System.out.println(writerProfile.writerName());
    }

    @Test
	public  void requestWriter(){
        app.getHelper().goToEdusson();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(1);
        app.getHelper().goToEdubirdie();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.driver.navigate().refresh();
        app.driver.get(writerProfileUrl);
        String writer = writerProfile.writerName();
		writerProfile.clickHireButton();
        //app.getHelper().isElementPresent(writerProfile.popUpRequest);
		writerProfile.clickCreateOrder();
        assertEquals(app.driver.getCurrentUrl(), "http://edubirdie.com/order?requested_writer=1");
        //assertEquals(writer, orderCreateCustomerPage.requestedWriter());
		orderCreateCustomerPage.createOrder("test for webdriver - writer request", "test");
        app.getHelper().sleep(1);
		app.driver.navigate().refresh();

        String orderUrl = app.driver.getCurrentUrl();
        String custOrderId = orderUrl.substring(32);
       // headerMenu.userLogOut();
		//userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(1);
        app.getHelper().goToEdusson();

        //райтеру, котрый оффлайн попап не показывается. придумать, как проверить отображение попапа
        app.getHelper().isElementPresent(writerProfile.popUpRequest);
		writerProfile.viewRequestingOrder();
        String writerOrderUrl = app.driver.getCurrentUrl();
        String writerOrderId = orderUrl.substring(24);
        assertEquals(custOrderId, writerOrderId);
        orderBiddingWriterPage.createBid("6");
       // headerMenu.userLogOut();
       // app.getHelper().sleep(1);
       // userAuthorizationPage.userLogin(Config.customer1, Config.password);
       // app.getHelper().sleep(1);
        app.getHelper().goToEdubirdie();
        app.driver.get(orderUrl);
        orderBiddingCustomerPage.bid1();
        app.getHelper().sleep(2);
        orderPayCustomerPage.clickReserveButton();
        app.getHelper().sleep(1);
        payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
       // headerMenu.userLogOut();
        app.getHelper().sleep(1);
        //userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
       // myOrdersWriterPage.closePopup();
       // app.driver.get(orderUrl);
       // app.getHelper().goToEdusson();
        app.driver.get(writerOrderUrl);
        orderInProgressPage.uploadRevision();
        app.getHelper().sleep(2);
        app.getHelper().sleep(2);
       // headerMenu.userLogOut();
       // app.getHelper().sleep(2);
        //userAuthorizationPage.userLogin(Config.customer1, Config.password);
        //app.getHelper().sleep(2);
        app.driver.get(orderUrl);
        orderInProgressPage.releaseMoney("100");
        app.getHelper().sleep(2);
        orderFinishedViewPage.closePopup();
        customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
        assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
        app.getHelper().sleep(2);
       // headerMenu.userLogOut();
        app.getHelper().sleep(2);
      //  userAuthorizationPage.userLogin(Config.writer1, Config.password);
      //  app.getHelper().sleep(2);
      //  myOrdersWriterPage.closePopup();
      //  app.driver.get(orderUrl);
        app.driver.get(writerOrderUrl);
        writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
        assertEquals(customerReleasedPercent, writerReleasedPercent);
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        app.getHelper().sleep(2);
        headerMenu.userLogOut();
        System.out.println("TEST PASSED");


		
	}
}
