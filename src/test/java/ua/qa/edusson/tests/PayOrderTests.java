package ua.qa.edusson.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;

/**
 * Created by tester on 30.03.2017.
 */
public class PayOrderTests extends TestBase {

    String siteUrl;

    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
    OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
    HeaderMenu headerMenu = new HeaderMenu();
    OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();
    PayPalPage payPalPage = new PayPalPage();
    MyBalanceCustomerPage myBalanceCustomerPage = new MyBalanceCustomerPage();

    @Test
    public void payFromBalanceByPayPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer3, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        } else {
            System.out.println("Choose another site");
        }
        app.getHelper().waitLoading("/order/pay/");
        String orderPayUrl = app.driver.getCurrentUrl();
        String orderTotal = orderPayCustomerPage.getOrderTotal();
        headerMenu.goTOMyBalanceCustomerPage();
        myBalanceCustomerPage.loadMoneyToBalancePayPal(orderTotal);
        //app.getHelper().waitLoading("sandbox");
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        payPalPage.checkForError();
        app.getHelper().waitLoading(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
        app.driver.get(orderPayUrl);
        orderPayCustomerPage.confirmPay();
        app.getHelper().waitLoading(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
        headerMenu.userLogOut();
    }

    @Test
    public void payFromBalanceByCreditCard() {
        siteUrl = app.driver.getCurrentUrl();
        if (app.getHelper().hasSiteCardPay(siteUrl)) {
            userAuthorizationPage.userLogin(Config.customer3, Config.password);
            myOrdersCustomerPage.makeNewOrder();
            if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
                orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
            } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
                orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
            } else {
                System.out.println("Choose another site");
            }
            app.getHelper().waitLoading("/order/pay/");
            String orderPayUrl = app.driver.getCurrentUrl();
            String orderTotal = orderPayCustomerPage.getOrderTotal();
            headerMenu.goTOMyBalanceCustomerPage();
            myBalanceCustomerPage.loadMoneyToBalanceCreditCard(orderTotal);
            app.getHelper().waitLoading("/pay/");
            creditCardPayment.setAllFields(siteUrl);
            app.getHelper().waitLoading(siteUrl);
            Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
            app.driver.get(orderPayUrl);
            orderPayCustomerPage.confirmPay();
            app.getHelper().waitLoading(siteUrl);
            Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
            headerMenu.userLogOut();
        } else {
            System.out.println("There is not CardPay on site " + siteUrl);
        }
    }

    @Test
    public void payPartlyFromBalance_creditCard() {
        siteUrl = app.driver.getCurrentUrl();
        if (app.getHelper().hasSiteCardPay(siteUrl)) {
            userAuthorizationPage.userLogin(Config.customer1, Config.password);
            headerMenu.goTOMyBalanceCustomerPage();
            String loadedAmount = "10";
            myBalanceCustomerPage.loadMoneyToBalanceCreditCard(loadedAmount);
            app.getHelper().waitLoading("/pay/");
            creditCardPayment.setAllFields(siteUrl);
            app.getHelper().waitLoading(siteUrl);
            Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
            myBalanceCustomerPage.returnBalancePage();
            Double balance = Math.rint(100.0 * myBalanceCustomerPage.userBalance() / 100.0);
            headerMenu.orderNow();
            if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
                orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
            } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
                orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
            } else {
                System.out.println("Choose another site");
            }
            app.getHelper().waitLoading("/order/pay/");
            Double totalPrice = Math.rint(100.0 * (Double.parseDouble(orderPayCustomerPage.getOrderTotal()) / 100.0));
            Double depositAmount = Math.rint(100.0 * (Double.parseDouble(orderPayCustomerPage.getDepositAmount()) / 100.0));
            Assert.assertEquals(depositAmount, totalPrice - balance);
            orderPayCustomerPage.choosePaymentSystem("card");
            orderPayCustomerPage.payOrder(siteUrl);
            Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
            headerMenu.userLogOut();
        } else {
            System.out.println("There is not CardPay on site " + siteUrl);
        }
    }

    @Test
    public void payPartlyFromBalance_payPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        headerMenu.goTOMyBalanceCustomerPage();
        String loadedAmount = "10";
        myBalanceCustomerPage.loadMoneyToBalancePayPal(loadedAmount);
        app.getHelper().waitLoading("sandbox");
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
        myBalanceCustomerPage.returnBalancePage();
        Double balance = Math.rint(100.0 * myBalanceCustomerPage.userBalance() / 100.0);
        headerMenu.orderNow();
        if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        } else {
            System.out.println("Choose another site");
        }
        app.getHelper().waitLoading("/order/pay/");
        Double totalPrice = Math.rint(100.0 * (Double.parseDouble(orderPayCustomerPage.getOrderTotal()) / 100.0));
        Double depositAmount = Math.rint(100.0 * (Double.parseDouble(orderPayCustomerPage.getDepositAmount()) / 100.0));
        Assert.assertEquals(depositAmount, totalPrice - balance);
        orderPayCustomerPage.choosePaymentSystem("paypal");
        orderPayCustomerPage.payOrder(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
        headerMenu.userLogOut();
    }

    @Test
    public void payOrder_payPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        headerMenu.orderNow();
        if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        } else {
            System.out.println("Choose another site");
        }
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.choosePaymentSystem("paypal");
        orderPayCustomerPage.payOrder(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");

        Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
        headerMenu.userLogOut();

    }

    @Test
    public void payOrder_creditCard() {
        siteUrl = app.driver.getCurrentUrl();
        if (app.getHelper().hasSiteCardPay(siteUrl)) {
            userAuthorizationPage.userLogin(Config.customer1, Config.password);
            headerMenu.orderNow();
            if (app.getHelper().hasSiteVas_ChooseBestWriter(siteUrl)) {
                orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
            } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
                orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
            } else {
                System.out.println("Choose another site");
            }
            app.getHelper().waitLoading("/order/pay/");
            orderPayCustomerPage.choosePaymentSystem("card");
            orderPayCustomerPage.payOrder(siteUrl);
            Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
            headerMenu.userLogOut();
        } else {
            System.out.println("There is not CardPay on site " + siteUrl);
        }
    }
}