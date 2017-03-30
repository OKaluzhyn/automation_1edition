package ua.qa.edusson.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.CreditCardPayment;
import ua.qa.edusson.pages.CustomerPages.MyBalanceCustomerPage;
import ua.qa.edusson.pages.CustomerPages.PayPalPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

/**
 * Created by tester on 16.03.2017.
 */
public class LoadMoneyToCustomerBalanceTests extends TestBase {
    String siteUrl;

    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    HeaderMenu headerMenu = new HeaderMenu();
    MyBalanceCustomerPage myBalanceCustomerPage = new MyBalanceCustomerPage();
    PayPalPage payPalPage = new PayPalPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();

    @Test
        public void loadMoney_PayPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        headerMenu.goTOMyBalanceCustomerPage();
        Double balanceBefore = myBalanceCustomerPage.userBalance();
        System.out.println(balanceBefore + " Balance before");
        String loadedAmount = "1";
        myBalanceCustomerPage.loadMoneyToBalancePayPal(loadedAmount);
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        payPalPage.checkForError();
        app.getHelper().waitLoading(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
        myBalanceCustomerPage.returnBalancePage();
        Double balanceAfter = myBalanceCustomerPage.userBalance();
        System.out.println(balanceAfter+" Balance after");
        Assert.assertEquals((balanceAfter), balanceBefore+(Integer.valueOf(loadedAmount)));
    }

    @Test
    public void loadMoney_Card() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        headerMenu.goTOMyBalanceCustomerPage();
        Double balanceBefore = myBalanceCustomerPage.userBalance();
        System.out.println(balanceBefore + " Balance before");
        String loadedAmount = "1";
        myBalanceCustomerPage.loadMoneyToBalanceCreditCard(loadedAmount);
        app.getHelper().waitLoading("pay");
        creditCardPayment.setAllFields(siteUrl);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertTrue(app.getHelper().isElementPresent(myBalanceCustomerPage.thanksMessage()));
        myBalanceCustomerPage.returnBalancePage();
        Double balanceAfter = myBalanceCustomerPage.userBalance();
        System.out.println(balanceAfter + " Balance after");
        Assert.assertEquals((balanceAfter), balanceBefore + (Integer.valueOf(loadedAmount)));
    }
}

