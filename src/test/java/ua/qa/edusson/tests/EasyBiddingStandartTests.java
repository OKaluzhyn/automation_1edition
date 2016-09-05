package ua.qa.edusson.tests;


import org.testng.annotations.Test;
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

public class EasyBiddingStandartTests extends TestBase {

    String orderUrl; //= Helper.driver.getCurrentUrl();
    String siteUrl; //= "http://paperial.com/";
    String orderId; //= Helper.driver.getCurrentUrl().substring(30);
    String writerUrl; //= "http://edusson.com/order/view/"+orderId;
    String customerUrl; //= siteUrl+"order/view/"+orderId;
    String customerReleasedPercent;
    String writerReleasedPercent;

    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
    MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    PayPalPage payPalPage = new PayPalPage();
    //OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
    OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
    OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();


    @Test
    // PayPall
    // 20%+80%

    public void standartCheck_EasyBidding_Production_All() throws Exception {

        String[] sites = {
                "http://paperial.com/",
                "http://essayontime.com.au/",
                "http://phdify.com/",
                //"http://customwriting.com/",
                "http://typemyessays.com/"
        };
        for (String site : sites) {

            siteUrl = site;
            app.driver.get(siteUrl);
            userAuthorizationPage.logIn(Config.customer1, Config.password);
            Helper.sleep(1);
            myOrdersCustomerPage.makeNewOrder();
            Helper.sleep(1);
            orderCreateCustomerPage.createOrderForOtherSites("test for webdriver", "test");
            Helper.sleep(3);
            assertTrue(app.driver.getCurrentUrl().contains("/order/pay/"));
            Helper.sleep(1);
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
            } else if (Objects.equals(siteUrl, "http://customwriting.com/")) {
                orderId = app.driver.getCurrentUrl().substring(36);
                System.out.println(orderId);
            }

            writerUrl = "http://edusson.com/order/view/" + orderId;
            customerUrl = siteUrl + "order/view/" + orderId;
            Helper.sleep(1);
            orderPayCustomerPage.confirmPay();
            Helper.sleep(1);
            payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
            Helper.sleep(1);
            assertTrue(app.driver.getCurrentUrl().contains("thankyou"));
            app.getHelper().goToEdusson();
            if (app.getHelper().isElementPresent(userAuthorizationPage.getloginLink())) {
                userAuthorizationPage.logIn(Config.writer1, Config.password);
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
            orderInProgressPage.releaseMoney("30");
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            Helper.sleep(2);
            app.driver.get(writerUrl);
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            Helper.sleep(2);
            app.driver.get(customerUrl);
            orderInProgressPage.releaseMoney("70");
            Helper.sleep(2);
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
            Helper.sleep(2);
            app.driver.get(writerUrl);
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
            Helper.sleep(2);
            System.out.println("TEST PASSED" + " " + siteUrl);
        }
    }

/*@Test
    public void testPaypal(){
    app.driver.get("http://paperial.com");
    userAuthorizationPage.logIn(Config.customer1, Config.password);
    app.driver.get("http://paperial.com/order/pay/145215");

    orderPayCustomerPage.confirmPay();
    Helper.sleep(1);
    payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
   // assertTrue(app.driver.getCurrentUrl().contains("thankyou"));

}*/
}
