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
import ua.qa.edusson.utils.Helper;

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
    // � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
    // 20%+80%

    public void standartCheck_EasyBidding_Production_All() throws Exception {

        String[] sites = {"http://paperial.com/",
                "http://essayontime.com.au/",
                "http://phdify.com/",
                //"http://customwriting.com/",
                "http://typemyessays.com/"
        };
        for (int i = 0; i < sites.length; i++) {

            siteUrl = sites[i];
            app.driver.get(siteUrl);


            // ��������� ��������
            userAuthorizationPage.logIn(Config.customer1, Config.password);
            Helper.sleep(1);
            //go to order form
            myOrdersCustomerPage.makeNewOrder();
            // create order
            Helper.sleep(1);
            orderCreateCustomerPage.createOrderForOtherSites("test for webdriver", "test");
            Helper.sleep(3);
            assertTrue(app.driver.getCurrentUrl().contains("/order/pay/"));
            Helper.sleep(1);
            if (siteUrl == "http://paperial.com/") {
                orderId = app.driver.getCurrentUrl().substring(30);
                System.out.println(orderId);
            } else if (siteUrl == "http://essayontime.com.au/") {
                orderId = app.driver.getCurrentUrl().substring(36);
                System.out.println(orderId);
            } else if (siteUrl == "http://phdify.com/") {
                orderId = app.driver.getCurrentUrl().substring(28);
                System.out.println(orderId);
            } else if (siteUrl == "http://customwriting.com/") {
                orderId = app.driver.getCurrentUrl().substring(35);
                System.out.println(orderId);
            } else if (siteUrl == "http://typemyessays.com/") {
                orderId = app.driver.getCurrentUrl().substring(34);
                System.out.println(orderId);
            } else if (siteUrl == "http://customwriting.com/") {
                orderId = app.driver.getCurrentUrl().substring(36);
                System.out.println(orderId);
            }

            writerUrl = "http://edusson.com/order/view/" + orderId;
            customerUrl = siteUrl + "order/view/" + orderId;
            Helper.sleep(1);

            //reserve money
            orderPayCustomerPage.reserveMoney();
            Helper.sleep(1);
            // ��������� � PayPall � ����������� ������
            payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
            //Helper.sleep(2);
            //assert thankyou page

            assertTrue(app.driver.getCurrentUrl().contains("thankyou"));
        /*Helper.driver.get(siteUrl);
		Helper.driver.get(customerUrl);
		//assert true - text is present
		assertTrue(Helper.isElementPresent("//*[text()='Your payment was successful, we are searching for the best writer now. Please wait a few minutes.']"));
		
		// ��������� ���������
		*/
            app.getHelper().goToEdusson();
            Helper.sleep(1);
            if (app.getHelper().isElementPresent(userAuthorizationPage.getloginLink()) == true) {
                userAuthorizationPage.logIn(Config.writer1, Config.password);
                //��������� ���������� �����
                Helper.sleep(2);
                myOrdersWriterPage.closePopup();
                app.driver.get(writerUrl);
            } else {
                app.driver.get(writerUrl);
            }

            // ����� ��� �������� ������ �� ���������� � ��������� �� ����

            Helper.sleep(2);
            System.out.println(app.driver.getCurrentUrl());
            // ������� ���
            orderBiddingWriterPage.easyBiddingApplyprice();
            Helper.sleep(2);
            //��������� �������
            orderInProgressPage.uploadRevision();
            Helper.sleep(2);
            app.driver.get(customerUrl);
            // ������� �������� 30%
            orderInProgressPage.releaseMoney("30");
            // �������� �������� % ���������� ����� �� �������� �������
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            Helper.sleep(2);
            app.driver.get(writerUrl);
            // �������� �������� % ���������� ����� �� �������� ��������
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            // ���������� �������� ���������� ����� � ������� � � ��������
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            Helper.sleep(2);
            app.driver.get(customerUrl);
            // ������� �������� 70%
            orderInProgressPage.releaseMoney("70");
            Helper.sleep(2);
            //orderFinishedViewPage.closePopup();
            // �������� �������� % ���������� ����� �� �������� �������
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
            Helper.sleep(2);
            app.driver.get(writerUrl);
            // �������� �������� % ���������� ����� �� �������� ��������
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            // ���������� �������� ���������� ����� � ������� � � ��������
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            //��������� ������� ������ order finished
            assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
            Helper.sleep(2);
            //headerMenu.userLogOut();
            //Helper.sleep(2);
            System.out.println("TEST PASSED");
        }
    }


}
