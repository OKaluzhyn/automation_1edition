package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.InProgressCustPage;
import pages.CustomerPages.MyOrdersCustomer;
import pages.CustomerPages.OrderFinishedCustPage;
import utils.Users;

public class ReleaseMoney {
static FirefoxDriver driver;
static InProgressCustPage objInProgress;
static OrderFinishedCustPage objOrderFinished;
static AuthPage objLogin;
static MyOrdersCustomer objCustomerOrders;




@Before
public void setUp() throws Exception {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
}

@Test

public void releaseMoney()throws Exception{
	//использовать метод авторизация
	objLogin = new AuthPage(driver);
	objLogin.login(Users.customer1, Users.password);
	// choose order
	driver.get("http://edusson.com/customer/orders");
	objCustomerOrders = new MyOrdersCustomer(driver);
	objCustomerOrders.goToOrder();
	//release money
	objInProgress = new InProgressCustPage(driver);
	objInProgress.releaseMoney("10");
	objInProgress.releaseMoney("90");
	//assert order is finished
	objOrderFinished = new OrderFinishedCustPage(driver);
	objOrderFinished.getOrderFinishedCustPageDashboardName();
}
	@After
	  public void tearDown() {
	    driver.quit();
	  }
	
}