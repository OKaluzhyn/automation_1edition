package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.BiddingPage;
import pages.CustomerPages.CancelOrderPopUp;
import pages.CustomerPages.MyOrdersCustomer;
import pages.CustomerPages.OrderSummary;
import pages.CustomerPages.PopUpAttention;
import utils.Users;

public class CancelOrder {

	FirefoxDriver driver;
	
	AuthPage objAuth;
	MyOrdersCustomer objOrders;
	BiddingPage objOrderInBidding;
	PopUpAttention objPopup;
	OrderSummary objCancelPopup;
	CancelOrderPopUp objCancel;
	OrderSummary objSummary;
	@Before
	public void setUp() {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://edusson.com");
	  }
	@Test
	public void cancelBiddingOrder() throws Exception{
		objAuth = new AuthPage(driver);
		objAuth.login(Users.customer1, Users.password);
		driver.get("http://edusson.com/customer/orders");
		objOrders = new MyOrdersCustomer(driver);
		objOrders.goToOrder();
		
		objOrderInBidding = new BiddingPage(driver);
		objOrderInBidding.clickEdit();
		
		objPopup = new PopUpAttention(driver);
		objPopup.clickApply();
		
		//переключаемся на фрейм
				WebElement iframe = driver.findElement(By.className("order_edit_iframe"));
				driver.switchTo().frame(iframe);
		objCancelPopup = new OrderSummary(driver);
		
		
        objCancelPopup.openCancelPopUp();
        
        driver.switchTo().defaultContent();
		objCancel = new CancelOrderPopUp(driver);
		objCancel.cancelOrder();
	
}
		
	
	@After
	  public void tearDown() {
	    driver.quit();
	  }
}
