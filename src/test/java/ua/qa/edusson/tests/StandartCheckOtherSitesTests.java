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

public class StandartCheckOtherSitesTests extends TestBase {
    String orderUrl;
    String siteUrl;
    String orderId;
    String writerUrl;
    String customerUrl;
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


    public void standartCheck_Production_Other_All() throws Exception {

        String[] sites = {
                "http://eduzaurus.com/",
                "http://paperdon.com/",
                "http://papersowl.com/",
                "http://studarea.com/",
                "http://essaybison.com/",
                "http://samedaypapers.com/",
                "http://paperell.com/",
                "http://essaystorm.com/",
                "http://essayvikings.com/",
                //"http://customwriting.com/",
        };
        for (int i = 0; i < sites.length; i++) {

            siteUrl = sites[i];
            app.driver.get(siteUrl);


            // ��������� ��������
            userAuthorizationPage.logIn(Config.customer1, Config.password);
            app.getHelper().sleep(1);
            //go to order form
            myOrdersCustomerPage.makeNewOrder();
            // create order
            app.getHelper().sleep(1);
            orderCreateCustomerPage.createOrderForOtherSites("test for webdriver", "test");
            app.getHelper().sleep(3);
            assertTrue(app.driver.getCurrentUrl().contains("order#redirect_url="));
            app.getHelper().sleep(1);
            //������� �������� ����� �������� ������
            app.driver.navigate().refresh();
            // ��������� ��� �������� �������� ������ � ����������
            customerUrl = app.driver.getCurrentUrl();
            if (siteUrl == "http://eduzaurus.com/") {
                orderId = app.driver.getCurrentUrl().substring(32);
                System.out.println(orderId);
            } else if (siteUrl == "http://paperdon.com/") {
                orderId = app.driver.getCurrentUrl().substring(31);
                System.out.println(orderId);
            } else if (siteUrl == "http://papersowl.com/") {
                orderId = app.driver.getCurrentUrl().substring(32);
                System.out.println(orderId);
            } else if (siteUrl == "http://studarea.com/") {
                orderId = app.driver.getCurrentUrl().substring(31);
                System.out.println(orderId);
            } else if (siteUrl == "http://essaybison.com/") {
                orderId = app.driver.getCurrentUrl().substring(33);
                System.out.println(orderId);
            } else if (siteUrl == "http://samedaypapers.com/") {
                orderId = app.driver.getCurrentUrl().substring(36);
                System.out.println(orderId);
            } else if (siteUrl == "http://paperell.com/") {
                orderId = app.driver.getCurrentUrl().substring(31);
                System.out.println(orderId);
            } else if (siteUrl == "http://essaystorm.com/") {
                orderId = app.driver.getCurrentUrl().substring(33);
                System.out.println(orderId);
            } else if (siteUrl == "http://essayvikings.com/") {
                orderId = app.driver.getCurrentUrl().substring(35);
                System.out.println(orderId);
            }


            writerUrl = "http://edusson.com/order/view/" + orderId;
            app.getHelper().sleep(1);
            app.getHelper().goToEdusson();
            app.getHelper().sleep(1);
            if (app.getHelper().isElementPresent(userAuthorizationPage.getloginLink()) == true) {
                userAuthorizationPage.logIn(Config.writer1, Config.password);
                //��������� ���������� �����
                app.getHelper().sleep(2);
                myOrdersWriterPage.closePopup();
                app.driver.get(writerUrl);
            } else {
                app.driver.get(writerUrl);
            }
            app.getHelper().sleep(2);
            System.out.println(app.driver.getCurrentUrl());
            // ������� ���
            orderBiddingWriterPage.createBid("6");
            app.getHelper().sleep(2);
            //app.getHelper().goToEdubirdie();
            //app.getHelper().sleep(1);
            // ����� ��� �������� ������ �� ���������� � ��������� �� ����
            app.driver.get(customerUrl);
            app.getHelper().sleep(2);
            // �������� ��� ������� ��������
            orderBiddingCustomerPage.bid1();
            app.getHelper().sleep(2);
            // ����������� ���, ��������� �� �������� ������
            orderPayCustomerPage.confirmPay();
            app.getHelper().sleep(2);
            //orderPayCustomerPage.clickReserveButton();
            //������������� �� frame �� �������� �������
            //app.getHelper().sleep(1);
            //app.driver.switchTo().frame(app.driver.findElement(By.name("injectedUl")));
            app.getHelper().sleep(1);
            // ��������� � PayPall � ����������� ������
            payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
            //payPalPage.clickContinue();
            // ���� ����������� �� ����
            //app.getHelper().sleep(30);
            //payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
            app.getHelper().sleep(2);
            //app.getHelper().goToEdusson();
            //app.getHelper().sleep(1);
            //����� ��� �������� ������ �� ���������� � ��������� �� ����
            app.driver.get(writerUrl);
            //��������� �������
            orderInProgressPage.uploadRevision();
            app.getHelper().sleep(2);
            //app.getHelper().goToEdubirdie();
            //app.getHelper().sleep(2);
            // ����� ��� �������� ������ �� ���������� � ��������� �� ����
            app.driver.get(customerUrl);
            // ������� �������� 10%
            orderInProgressPage.releaseMoney("20");
            // �������� �������� % ���������� ����� �� �������� �������
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            // app.getHelper().goToEdusson();
            //app.getHelper().sleep(2);
            // ����� ��� �������� ������ �� ���������� � ��������� �� ����
            app.driver.get(writerUrl);
            // �������� �������� % ���������� ����� �� �������� ��������
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            // ���������� �������� ���������� ����� � ������� � � ��������
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            //app.getHelper().goToEdubirdie();
            //app.getHelper().sleep(2);
            // ����� ��� �������� ������ �� ���������� � ��������� �� ����
            app.driver.get(customerUrl);
            // ������� �������� 90%
            orderInProgressPage.releaseMoney("80");
            app.getHelper().sleep(2);
            //orderFinishedViewPage.closePopup();
            // �������� �������� % ���������� ����� �� �������� �������
            customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
            assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
            app.getHelper().sleep(2);
            //app.getHelper().goToEdusson();
            //app.getHelper().sleep(2);
            // ����� ��� �������� ������ �� ���������� � ��������� �� ����
            app.driver.get(writerUrl);
            // �������� �������� % ���������� ����� �� �������� ��������
            writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
            // ���������� �������� ���������� ����� � ������� � � ��������
            assertEquals(customerReleasedPercent, writerReleasedPercent);
            //��������� ������� ������ order finished
            assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
            app.getHelper().sleep(2);
            //headerMenu.userLogOut();
            System.out.println("TEST PASSED");
        }
    }

}
