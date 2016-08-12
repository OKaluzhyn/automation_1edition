package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.WriterPages.WriterProfile;
import utils.Config;
import utils.Helper;

public class TestRequestWriter {
	
	public String writerProfileUrl = "";
	
	
	@Before
	public void setUp(){
		Helper.driverSetUp();

	}

	@After
	public void tearDown() {
		
		Helper.quit();
	}

	@Test
	public  void requestwriter(){
		// инициализация страниц
				UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
				WriterProfile writerProfile = new WriterProfile();
				OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();

		
		Helper.goToEdusson();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		//goToWriterProfile
		Helper.driver.get(writerProfileUrl);
		writerProfile.clickHireButton();
		Helper.isElementPresent(writerProfile.popUpHireWriter);
		writerProfile.clickCreateOrder();
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		assertTrue(Helper.driver.getCurrentUrl().contains("order#redirect_url="));
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		Helper.isElementPresent(writerProfile.popUpHireWriter);
		writerProfile.applyRequest();
		
	}
}
