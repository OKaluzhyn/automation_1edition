package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.OrderCancelPopUp;
import pages.CommonPages.OrderCancelViewPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderSummaryCustomerPopUp;
import pages.CustomerPages.PopUpAttention;
import utils.Config;

public class CancelOrder {

	FirefoxDriver driver;
	
	UserAuthorizationPage objAuth;
	MyOrdersCustomerPage objOrders;
	OrderBiddingCustomerPage objOrderInBidding;
	PopUpAttention objPopup;
	OrderSummaryCustomerPopUp objCancelPopup;
	OrderCancelPopUp objCancel;  
	OrderSummaryCustomerPopUp objSummary;
	OrderCancelViewPage objOrderView;
	
	
	@Before
	public void setUp() {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://edusson.com");
	  }
	@Test
	public void cancelBiddingOrder() throws Exception{
		objAuth = new UserAuthorizationPage(driver);
		objAuth.login(Config.customer1, Config.password);
		driver.get("http://edusson.com/customer/orders");
		objOrders = new MyOrdersCustomerPage(driver);
		objOrders.goToEditedOrder();
		
		objOrderInBidding = new OrderBiddingCustomerPage(driver);
		objOrderInBidding.clickEdit();
		
		objPopup = new PopUpAttention(driver);
		objPopup.clickApply();
		
		//переключаемся на фрейм
				WebElement iframe = driver.findElement(By.className("order_edit_iframe"));
				driver.switchTo().frame(iframe);
		objCancelPopup = new OrderSummaryCustomerPopUp(driver);
		
		
        objCancelPopup.openCancelPopUp();
        
        driver.switchTo().defaultContent();
		objCancel = new OrderCancelPopUp(driver);
		objCancel.cancelOrder();
		
		objOrderView = new OrderCancelViewPage(driver);
		objOrderView.isElementPresent();
	
}
			
	@After
	  public void tearDown() {
	    driver.quit();
	  }
}
