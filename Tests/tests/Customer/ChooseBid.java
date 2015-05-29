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
import utils.Users;

public class ChooseBid {
	
	static FirefoxDriver driver;
	static pages.AuthPage objLogin;
	static pages.CustomerPages.BiddingPage objBidding;
	static pages.CustomerPages.MyOrdersCustomer objCustomerOrders;
	static OrderPayPage objPayPage;



	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}
	  


	@Test
	public void choosingBid()throws Exception{
		//использовать метод авторизация
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
	}
	
	@After
	  public void tearDown() {
	    driver.quit();
	  }
	
}
