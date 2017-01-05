package ua.qa.edusson.tests;

import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;

public class UserForgotPassProductionTests extends TestBase {



	@Test
	// �������� �������������� ������
	public void forgotPass() {
		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.forgotPasswordSuccess(Config.emailForForgotPassword);
		
	}

	@Test
	// �������� ����� � ������ ����� Email
	public void forgotPassWithoutEmail() {
		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.forgotPassNoEmail("");
		
	}

}