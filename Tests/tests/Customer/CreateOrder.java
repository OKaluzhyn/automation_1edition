package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.BiddingPage;
import pages.CustomerPages.OrderPage;
import utils.Users;


public class CreateOrder  {
static FirefoxDriver driver;
static pages.AuthPage objLogin;
static pages.CustomerPages.OrderPage objOrderPage;
static pages.CustomerPages.BiddingPage objBidding;




@Before
public void setUp() throws Exception {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
}


@Test

public void createOrder()throws Exception{
	// авторизация
	objLogin = new AuthPage(driver);
	objLogin.login(Users.customer1, Users.password);
	// create order
	objOrderPage = new OrderPage(driver);
	objOrderPage.createOrder("test for webdriver","test");
	//assert bidding page
	objBidding = new BiddingPage(driver);
}
	@After
	  public void tearDown() {
	    driver.quit();
	  }
	
	

}






	


