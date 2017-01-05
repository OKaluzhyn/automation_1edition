package ua.qa.edusson.tests;


import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;

import static org.testng.Assert.assertEquals;

public class UserAutorizationProductionTests extends TestBase {


    @Test
    // success customer authorization
    public void customerAuthorization() throws Exception {

        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        assertEquals("My Orders - Edusson.com", app.driver.getTitle());

    }


    @Test
    // success writer authorization
    public void writerAuthorization() throws Exception {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        assertEquals("My Orders. Current orders - Edusson.com", app.driver.getTitle());

    }

    @Test
    // login as deactivate User
    public void loginAsDeactivateUser() throws Exception {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        userAuthorizationPage.LogClick();
        app.getHelper().sleep(1);
        userAuthorizationPage.setUserName(Config.deactivateUser);
        userAuthorizationPage.continueClick();
        userAuthorizationPage.checkErrorMessagePresent();
    }


    @Test
    public void loginWithWrongPassword() throws Exception {
        app.getHelper().goToEdusson();
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        userAuthorizationPage.userLogin(Config.customer1, Config.wrongPassword);
        userAuthorizationPage.checkErrorMessagePresent();
    }

    @Test
    public void loginWithEmptyEmail() throws Exception {
        app.getHelper().goToEdusson();
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        userAuthorizationPage.LogClick();
        app.getHelper().sleep(1);
        userAuthorizationPage.continueClick();
        userAuthorizationPage.checkErrorMessagePresent();
    }
}


