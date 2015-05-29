package tests.Customer;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.BiddingPage;
import pages.CustomerPages.MyOrdersCustomer;
import pages.CustomerPages.OrderPayPage;
import pages.CustomerPages.PayPalPage;
import pages.CustomerPages.ThankYouPage;
import utils.Users;

public class PayOrder {
	static FirefoxDriver driver;
	static pages.CustomerPages.OrderPayPage objPayPage;
	static pages.CustomerPages.PayPalPage objConfirmPay;
	static pages.AuthPage objLogin;
	static pages.CustomerPages.BiddingPage objBidding;
	static pages.CustomerPages.MyOrdersCustomer objCustomerOrders;
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
			objLogin.login(Users.customer1, Users.password);
			
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
			objConfirmPay.confirmPayPal(Users.pp, Users.pass);
			//assert ThankYouPage
			objThankYouPage = new ThankYouPage(driver);
			
		}
		@After
		  public void tearDown(){
		    driver.quit();
		  }
}
