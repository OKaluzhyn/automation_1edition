package tests.Customer;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderSummaryCustomerPopUp;
import pages.CustomerPages.AttentionBeforOrderEditingCustomerPopUp;
import utils.Config;
import utils.DriverFireFox;


public class CreateOrder 
UserAuthorizationPage, OrderCreateCustomerPage, OrderBiddingCustomerPage, AttentionBeforOrderEditingCustomerPopUp,
OrderSummaryCustomerPopUp{

public  String orderUrlForEdition;

@Before
public	void beforeTest() throws Exception{
	setUp();
}


@Test

public void createNewOrder()throws Exception{
	// авторизация
	login(Config.customer1,Config.password);
	// create order
	createOrder("test for webdriver","test");
	Thread.sleep(5000);
	//assert bidding page
	//сохраняем урл страницы текущего заказа в переменную
	 
	assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
	driver.navigate().refresh();
	orderUrlForEdition = driver.getCurrentUrl();
	}


	@After
	public void afterTest() {
		tearDown();
}
	
}








	


