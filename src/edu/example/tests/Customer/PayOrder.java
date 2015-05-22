package edu.example.tests.Customer;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.CustomerPages.BiddingPage;
import edu.pages.CustomerPages.MyOrdersCustomer;
import edu.pages.CustomerPages.OrderPayPage;
import edu.pages.CustomerPages.PayPalPage;
import edu.pages.CustomerPages.ThankYouPage;

public class PayOrder {
	static FirefoxDriver driver;
	static edu.pages.CustomerPages.OrderPayPage objPayPage;
	static edu.pages.CustomerPages.PayPalPage objConfirmPay;
	static edu.pages.AuthPage objLogin;
	static edu.pages.CustomerPages.BiddingPage objBidding;
	static edu.pages.CustomerPages.MyOrdersCustomer objCustomerOrders;
	static ThankYouPage objThankYouPage;
	
	
		@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}
	
		@Test
		public void payOrder() throws Exception{
			//авторизация
			objLogin = new AuthPage(driver);
			objLogin.loginAsCustomer1(null, null);
			
			//переходим к нужному заказу
			driver.get("http://edusson.com/customer/orders");
			objCustomerOrders = new MyOrdersCustomer(driver);
			objCustomerOrders.goToOrder();
			//выбираем бид
			objBidding = new BiddingPage(driver);
			objBidding.bid1();
			//появилась страница оплаты
			objPayPage = new OrderPayPage(driver);
			//
			objPayPage.clickReserveButton();
			//open paypall page
			objConfirmPay = new PayPalPage(driver);
			objConfirmPay.confirmPayPal("edussonpay2@ukr.net", "ghbdtndctv12");
			//assert ThankYouPage
			objThankYouPage = new ThankYouPage(driver);
			
		}
		@After
		  public void tearDown(){
		    driver.quit();
		  }
}
