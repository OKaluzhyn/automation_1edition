package ua.qa.edusson.tests;


import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.utils.Config;

import static org.testng.Assert.assertEquals;

public class UserAutorizationProductionTests extends TestBase{
	




	@Test
	// success customer authorization
	public void customerAuthorization() throws Exception {

		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		app.getHelper().sleep(1);
		assertEquals("Edusson.com - My Orders", app.driver.getTitle());
		
	}
		
	

	@Test
	// success writer authorization
	public void writerAuthorization() throws Exception {
		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// �������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// �������� title ��������, �� ������� ������� ����� �����������
		app.getHelper().sleep(2);
		assertEquals("Edusson.com - My Orders. Current orders", app.driver.getTitle());
		
		// ��������� ��������� ������ ��� ��������
		//MyOrdersWriterPage writerHomePage = new MyOrdersWriterPage();
	//	writerHomePage.isWritersPopUpPresent();
	}
	
	@Test
	// login as deactivate User
	public void loginAsDeactivateUser() throws Exception {
		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// ��������� ���������������� �������������
		userAuthorizationPage.LogClick();
		app.getHelper().sleep(1);
		userAuthorizationPage.setUserName(Config.deactivateUser);
		userAuthorizationPage.continueClick();
		// ��������� ������� ���������
		userAuthorizationPage.checkErrorMessagePresent();
	}

	

	@Test
	public void loginWithWrongPassword() throws Exception {
		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// ��������� � ������ �������, �������� �������
		userAuthorizationPage.logIn(Config.customer1, Config.wrongPassword);
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}

	@Test
	public void loginWithEmptyEmail() throws Exception {
		app.getHelper().goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// �������� ������ �����, �� �������� ���� ����� �����������
		userAuthorizationPage.LogClick();
		app.getHelper().sleep(1);
		userAuthorizationPage.continueClick();
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}
}


