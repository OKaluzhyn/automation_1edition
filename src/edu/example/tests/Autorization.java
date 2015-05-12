package edu.example.tests;

	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.MyOrdersWriter;
import edu.pages.OrderPage;


	public class Autorization {
		FirefoxDriver driver;
		edu.pages.AuthPage objLogin;
		edu.pages.OrderPage objOrderPage;
		edu.pages.MyOrdersWriter objMyOrdersWriter;
		
		
		
		@Before
	  public void setUp() {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://edusson.com");
	  }

	  @Test
	  public void customerAuthorization() throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginToAuthPage("Cust.23.02@i.ua","5e2eee");
		  objOrderPage = new OrderPage(driver); 
	
		 				
	}
	  @Test
	  public void customerAuthorizationError () throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginToAuthPage(null,null);
		  objLogin = new AuthPage(driver);
		  objLogin.isErrorMessagePresent();
		 
	  }
	  
	  @Test 
	  public void writerAuthorization1() throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginToAuthPage("Write.23.02@i.ua", "402438");
		  objMyOrdersWriter = new MyOrdersWriter (driver);
	  }

		@After
	  public void tearDown() {
	    driver.quit();
	  }
	}
	
	  

	
	

	 
