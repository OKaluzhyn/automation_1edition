package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.UserAuthorizationPage;
import utils.Config;
import utils.Helper;

public class TestUserForgotPassProduction {
	

	@Before
	public void setUp(){
		Helper.driverSetUp("http://edusson.com");

	}

	@After
	public void tearDown() {
		Helper.quit();
	}

	@Test
	// успешное восстановления пароля
	public void forgotPass() {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.forgotPasswordSuccess(Config.emailForForgotPassword);
		
	}

	@Test
	// отправка формы с пустым полем Email
	public void forgotPassWithoutEmail() {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.forgotPassNoEmail("");
		
	}

}