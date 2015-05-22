package edu.example.tests;

	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.CustomerPages.OrderPage;
import edu.pages.WriterPages.MyOrdersWriter;


	public class Autorization {
		FirefoxDriver driver;
		edu.pages.AuthPage objLogin;
		edu.pages.CustomerPages.OrderPage objOrderPage;
		edu.pages.WriterPages.MyOrdersWriter objMyOrdersWriter;
		
		
		
		@Before
	  public void setUp() {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://edusson.com");
	  }

	  @Test
	  public void customerAuthorization() throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginAsCustomer1(null, null);
		  objOrderPage = new OrderPage(driver); 
	
		 				
	}
	  @Test
	  public void userAuthorizationError1 () throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginWithWrongUserName(null, null);
		  objLogin = new AuthPage(driver);
		  objLogin.isErrorMessagePresent();
		 
	  }
	  
	  @Test
	  public void userAuthorizationError2 () throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginWithWrongPass(null, null);
		  objLogin = new AuthPage(driver);
		  objLogin.isErrorMessagePresent();
	  }
	  
	  
	  @Test 
	  public void writerAuthorization1() throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.loginAsWriter1(null,null);
		  objMyOrdersWriter = new MyOrdersWriter (driver);
	  }

		@After
	  public void tearDown() {
	    driver.quit();
	  }
	}
	
	  

	
	

	 
