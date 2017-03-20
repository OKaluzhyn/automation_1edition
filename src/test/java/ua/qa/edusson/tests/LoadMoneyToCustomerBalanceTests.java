package ua.qa.edusson.tests;

import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.MyBalanceCustomerPage;
import ua.qa.edusson.pages.CustomerPages.PayPalPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;

/**
 * Created by tester on 16.03.2017.
 */
public class LoadMoneyToCustomerBalanceTests extends TestBase {
    String siteUrl;

    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    HeaderMenu headerMenu = new HeaderMenu();
    MyBalanceCustomerPage myBalanceCustomerPage = new MyBalanceCustomerPage();
    PayPalPage payPalPage = new PayPalPage();

    @Test
        public void loadMoney_PayPal() {
        siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        headerMenu.goTOMyBalanceCustomerPage();
        myBalanceCustomerPage.loadMoneyToBalancePayPal("30");
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
    }

}

