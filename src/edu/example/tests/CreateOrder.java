package edu.example.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.BiddingPage;
import edu.pages.OrderPage;


public class CreateOrder  {
static FirefoxDriver driver;
static edu.pages.AuthPage objLogin;
static edu.pages.OrderPage objOrderPage;
static edu.pages.BiddingPage objBidding;



@Before
public void setUp() throws Exception {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
}
  


@Test

public void createOrder()throws Exception{
	//использовать метод авторизация
	objLogin = new AuthPage(driver);
	objLogin.loginToAuthPage("Cust.23.02@i.ua","5e2eee");
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






	


