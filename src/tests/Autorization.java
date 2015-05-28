package tests;

	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.OrderPage;
import pages.WriterPages.MyOrdersWriter;
import utils.Users;


	public class Autorization {
		FirefoxDriver driver;
		pages.AuthPage objLogin;
		pages.CustomerPages.OrderPage objOrderPage;
		pages.WriterPages.MyOrdersWriter objMyOrdersWriter;
		
		
		
		@Before
	  public void setUp() {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://edusson.com");
	  }

	  @Test
	  public void customerAuthorization() throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.login(Users.customer1, Users.password);
		  objOrderPage = new OrderPage(driver); 
	
		 				
	}
	  @Test
	  public void userAuthorizationError1 () throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.login(null, null);
		  objLogin = new AuthPage(driver);
		  objLogin.isErrorMessagePresent();
		 
	  }
	  
	  @Test
	  public void userAuthorizationError2 () throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.login(null, null);
		  objLogin = new AuthPage(driver);
		  objLogin.isErrorMessagePresent();
	  }
	  
	  
	  @Test 
	  public void writerAuthorization() throws Exception{
		  objLogin = new AuthPage(driver);
		  objLogin.login(null,null);
		  objMyOrdersWriter = new MyOrdersWriter (driver);
	  }

		@After
	  public void tearDown() {
	    driver.quit();
	  }
	}
	
	  

	
	

	 
