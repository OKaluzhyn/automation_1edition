package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.UserAuthorizationPage;
import pages.WriterPages.MyOrdersWriterPage;
import utils.Config;

public class TestUserAutorizationProduction {
	public FirefoxDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.get("http://edusson.com");

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	// success customer authorization
	public void customerAuthorization() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// �������� title ��������, �� ������� ������� ����� �����������
		driver.getTitle().contains("Edusson.com - Place your Order");
	}

	@Test
	// success writer authorization
	public void writerAuthorization() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		// �������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// �������� title ��������, �� ������� ������� ����� �����������
		driver.getTitle().contains("Edusson.com - My Orders. Current orders");
		// ��������� ��������� ������ ��� ��������
		MyOrdersWriterPage writerHomePage = new MyOrdersWriterPage(driver);
		writerHomePage.isWritersPopUpPresent();
	}

	@Test
	// login as deactivate User
	public void loginAsDeactivateUser() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		// ��������� ���������������� �������������
		userAuthorizationPage.logIn(Config.deactivateUser, Config.password);
		// ��������� ������� ���������
		userAuthorizationPage.checkErrorMessagePresentForDeactivateUser();
	}

	@Test
	public void loginWithWrongUsername() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		// �������� � �������� ������� � ������ �������
		userAuthorizationPage.logIn(Config.wrongUsername, Config.password);
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();

	}

	@Test
	public void loginWithWrongPassword() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		// ��������� � ������ �������, �������� �������
		userAuthorizationPage.logIn(Config.customer1, Config.wrongPassword);
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}

	@Test
	public void loginWithEmptyFields() throws Exception {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		// �������� ������ �����, �� �������� ���� ����� �����������
		userAuthorizationPage.logIn(null, null);
		// ��������� ������� ��������� �� ������
		userAuthorizationPage.checkErrorMessagePresent();
	}

}
