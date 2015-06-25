package tests.Customer;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
import utils.Config;

public class PayOrder {
	static FirefoxDriver driver;
	static pages.CustomerPages.OrderPayCustomerPage objPayPage;
	static pages.CustomerPages.PayPalPage objConfirmPay;
	static pages.CommonPages.UserAuthorizationPage objLogin;
	static pages.CustomerPages.OrderBiddingCustomerPage objBidding;
	static pages.CustomerPages.MyOrdersCustomerPage objCustomerOrders;
	static OrderPayThankYouCustomerPage objThankYouPage;
	
	
		@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}
	
		@Test
		public void payOrder() throws Exception{
			//авторизация
			objLogin = new UserAuthorizationPage(driver);
			objLogin.login(Config.customer1, Config.password);
			
			//переходим к нужному заказу
			driver.get("http://edusson.com/customer/orders");
			objCustomerOrders = new MyOrdersCustomerPage(driver);
			objCustomerOrders.goToOrder();
			//выбираем бид
			objBidding = new OrderBiddingCustomerPage(driver);
			objBidding.bid1();
			//появилась страница оплаты
			objPayPage = new OrderPayCustomerPage(driver);
			//
			objPayPage.clickReserveButton();
			//open paypall page
			objConfirmPay = new PayPalPage(driver);
			objConfirmPay.confirmPayPal(Config.pp, Config.pass);
			//assert OrderPayThankYouCustomerPage
			objThankYouPage = new OrderPayThankYouCustomerPage(driver);
			
		}
		@After
		  public void tearDown(){
		    driver.quit();
		  }
}
