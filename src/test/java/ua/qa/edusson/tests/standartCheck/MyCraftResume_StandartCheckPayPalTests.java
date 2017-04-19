package ua.qa.edusson.tests.standartCheck;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.WebWindow;

import static org.testng.Assert.assertTrue;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;


public class MyCraftResume_StandartCheckPayPalTests extends TestBase {


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
    PayPalPage payPalPage = new PayPalPage();


    @Test
    //работает со всеми сайтами
    public void resume_standartCheck_PayPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createResumeOrder(siteUrl);
        app.getHelper().waitLoading("/order/pay/");
        orderId = app.getHelper().idEasyBidding(siteUrl);
        writerUrl = "http://edusson.com/order/view/" + orderId;
        customerUrl = siteUrl + "order/view/" + orderId;
        System.out.println("Order ID = " + orderId);
        orderPayCustomerPage.choosePayPal();
        orderPayCustomerPage.payOrder(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
        WebWindow ww = new WebWindow(app.driver, "https://edusson.com/");
        userAuthorizationPage.userLogin(Config.resume_writer1, Config.password);
        app.getHelper().waitLoading("/writer/orders/current");
        System.out.println(writerUrl);
        //app.driver.get("https://edusson.com/order/view/318884");
        app.getHelper().goTo(writerUrl);
        orderBiddingWriterPage.easyBiddingApplyprice();
        app.driver.navigate().refresh();
        orderInProgressPage.uploadRevision();
        ww.switchToParent();
        orderPayThankYouCustomerPage.goToOrder();
        orderInProgressPage.approveProduct();
        orderFinishedViewPage.closePopup();
        ww.switchToWindow();
        app.driver.navigate().refresh();
        assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
        headerMenu.userLogOut();
        ww.switchToParent();

        ww.close();
        //app.driver.navigate().refresh();
        System.out.println("TEST PASSED" + " " + siteUrl);
        //app.getHelper().sleep(5);
    }

}



	