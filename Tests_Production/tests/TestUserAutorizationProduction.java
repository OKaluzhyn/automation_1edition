package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.UserAuthorizationPage;
import utils.Config;
import utils.Helper;

public class TestUserAutorizationProduction {
	


	@Before
	public void setUp(){
		Helper.driverSetUp();

	}

	@After
	public void tearDown() {
		Helper.quit();
	}
	
	@Test
	// success customer authorization
	public void customerAuthorization() throws Exception {
		
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		if (Helper.driver.getTitle().equals("Edusson.com - My Orders")){
		
			System.out.println("Test passed");
		}
	else {
		System.out.println("Title does not match");
	}
		
	}

	@Test
	// success writer authorization
	public void writerAuthorization() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// �������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// �������� title ��������, �� ������� ������� ����� �����������
		Helper.sleep(1);
		if (Helper.driver.getTitle().equals("Edusson.com - My Orders. Current orders")){
		
			System.out.println("Test passed");
		}
	else {
		System.out.println("Test failed");
		// ��������� ��������� ������ ��� ��������
		//MyOrdersWriterPage writerHomePage = new MyOrdersWriterPage();
	//	writerHomePage.isWritersPopUpPresent();
	}
	}
	@Test
	// login as deactivate User
	public void loginAsDeactivateUser() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// ��������� ���������������� �������������
		userAuthorizationPage.LogClick();
		Helper.sleep(1);
		userAuthorizationPage.setUserName(Config.deactivateUser);
		userAuthorizationPage.continueClick();
		// ��������� ������� ���������
		userAuthorizationPage.checkErrorMessagePresent();
	}

	

	@Test
	public void loginWithWrongPassword() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// ��������� � ������ �������, �������� �������
		userAuthorizationPage.logIn(Config.customer1, Config.wrongPassword);
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}

	@Test
	public void loginWithEmptyEmail() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// �������� ������ �����, �� �������� ���� ����� �����������
		userAuthorizationPage.LogClick();
		Helper.sleep(1);
		userAuthorizationPage.continueClick();
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}
}


