package edu.example.tests;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.BiddingPage;
import edu.pages.MyOrdersCustomer;
import edu.pages.OrderPayPage;

public class ChooseBid {
	
	static FirefoxDriver driver;
	static edu.pages.AuthPage objLogin;
	static edu.pages.BiddingPage objBidding;
	static edu.pages.MyOrdersCustomer objCustomerOrders;
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
		objLogin.loginToAuthPage("Cust.23.02@i.ua", "5e2eee");
		
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
