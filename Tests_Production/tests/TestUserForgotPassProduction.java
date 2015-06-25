package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.UserAuthorizationPage;
import utils.Config;

public class TestUserForgotPassProduction {
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
	// �������� �������������� ������
	public void forgotPass() {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		userAuthorizationPage.forgotPass(Config.emailForForgotPassword);
		// �������� ����������� ��������� ��� �������� �������� �����
		userAuthorizationPage.assertSuccMessage();
	}

	@Test
	// �������� ����� � ������ ����� Email
	public void forgotPassWithoutEmail() {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		userAuthorizationPage.forgotPass(null);
		// �������� ����������� ���������, ��� ���� ����������� ��� ����������
		userAuthorizationPage.assertError();
	}

}