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
import pages.CustomerPages.MyOrdersCustomer;
import pages.CustomerPages.OrderPage;
import pages.CustomerPages.OrderSummary;
import pages.CustomerPages.PopUpAttention;
import utils.Users;

public class EditOrder {
	FirefoxDriver driver;
	AuthPage objLogin;
	OrderPage objOrderPage;
	BiddingPage objBidding;
	MyOrdersCustomer objCustomerOrders;
	PopUpAttention objPopUp;
	OrderSummary objSummary;
	


	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}


	@Test

	public void edit()throws Exception{
		// авторизация
		objLogin = new AuthPage(driver);
		objLogin.login(Users.customer1, Users.password);
		// найти свой заказ в бидинге
		driver.get("http://edusson.com/customer/orders");
		objCustomerOrders = new MyOrdersCustomer(driver);
		objCustomerOrders.goToOrder();
		
		objBidding = new BiddingPage(driver);
		objBidding.clickEdit();
		
		//apply pop-up info
		objPopUp = new PopUpAttention(driver);
		objPopUp.clickApply();
		//editing
		
		//переключаемся на фрейм
		WebElement iframe = driver.findElement(By.className("order_edit_iframe"));
		driver.switchTo().frame(iframe);
		objSummary = new OrderSummary(driver);
		objSummary.editOrder(null, null);		
}
	@After
	  public void tearDown() {
	    driver.quit();
	  }
}
