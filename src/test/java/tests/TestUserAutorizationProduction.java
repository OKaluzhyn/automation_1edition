package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

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
		Helper.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Helper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Helper.goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		assertEquals("Edusson.com - My Orders", Helper.driver.getTitle());
		
	}
		
	

	@Test
	// success writer authorization
	public void writerAuthorization() throws Exception {
		Helper.goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// �������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// �������� title ��������, �� ������� ������� ����� �����������
		Helper.sleep(2);
		assertEquals("Edusson.com - My Orders. Current orders", Helper.driver.getTitle());
		
		// ��������� ��������� ������ ��� ��������
		//MyOrdersWriterPage writerHomePage = new MyOrdersWriterPage();
	//	writerHomePage.isWritersPopUpPresent();
	}
	
	@Test
	// login as deactivate User
	public void loginAsDeactivateUser() throws Exception {
		Helper.goToEdusson();
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
		Helper.goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// ��������� � ������ �������, �������� �������
		userAuthorizationPage.logIn(Config.customer1, Config.wrongPassword);
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}

	@Test
	public void loginWithEmptyEmail() throws Exception {
		Helper.goToEdusson();
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		// �������� ������ �����, �� �������� ���� ����� �����������
		userAuthorizationPage.LogClick();
		Helper.sleep(1);
		userAuthorizationPage.continueClick();
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}
}


