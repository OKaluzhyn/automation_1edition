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
	// успешное восстановления пароля
	public void forgotPass() {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		userAuthorizationPage.forgotPass(Config.emailForForgotPassword);
		// проверка отображения сообщения при успешной отправке формы
		userAuthorizationPage.assertSuccMessage();
	}

	@Test
	// отправка формы с пустым полем Email
	public void forgotPassWithoutEmail() {
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		userAuthorizationPage.forgotPass(null);
		// проверка отображения сообщения, что поле обязательно для заполнения
		userAuthorizationPage.assertError();
	}

}