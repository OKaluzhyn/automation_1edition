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
		Helper.driverSetUp();

	}

	@After
	public void tearDown() {
		Helper.quit();
	}

	@Test
	// �������� �������������� ������
	public void forgotPass() {
		Helper.goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.forgotPasswordSuccess(Config.emailForForgotPassword);
		
	}

	@Test
	// �������� ����� � ������ ����� Email
	public void forgotPassWithoutEmail() {
		Helper.goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.forgotPassNoEmail("");
		
	}

}