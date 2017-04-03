package ua.qa.edusson.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

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
        if (!app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
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
        userAuthorizationPage.userLogin(Config.customer3, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        if (!app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
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
    }

    @Test
    public void payPartlyFromBalance() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        headerMenu.goTOMyBalanceCustomerPage();
        String loadedAmount = "10";
        myBalanceCustomerPage.loadMoneyToBalanceCreditCard(loadedAmount);
        app.getHelper().waitLoading("/pay/");
        creditCardPayment.setAllFields(siteUrl);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
        myBalanceCustomerPage.returnBalancePage();
        Double balance = Math.rint(100.0 * myBalanceCustomerPage.userBalance()/ 100.0);
        headerMenu.orderNow();
        if (!app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrderWithVasChooseBestWriter(siteUrl, "test for webdriver", "test");
        } else if (app.getHelper().isSiteEasybidding(siteUrl).equals("easy")) {
            orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        }
        app.getHelper().waitLoading("/order/pay/");
        Double totalPrice = Math.rint(100.0 * (Double.parseDouble(orderPayCustomerPage.getOrderTotal())/ 100.0));
        Double depositAmount = Math.rint(100.0 * (Double.parseDouble(orderPayCustomerPage.getDepositAmount())/ 100.0));
        Assert.assertEquals(depositAmount, totalPrice - balance);
        orderPayCustomerPage.chooseCardPay();
        orderPayCustomerPage.payOrder(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent("//*[text()='Your order was paid!']"));
        headerMenu.userLogOut();
    }
    
}