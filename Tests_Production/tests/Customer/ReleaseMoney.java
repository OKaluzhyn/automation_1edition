package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.OrderInProgressPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import utils.Config;

public class ReleaseMoney {
static FirefoxDriver driver;
static OrderInProgressPage objInProgress;
static OrderFinishedViewPage objOrderFinished;
static UserAuthorizationPage objLogin;
static MyOrdersCustomerPage objCustomerOrders;




@Before
public void setUp() throws Exception {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
}

@Test

public void releaseMoney()throws Exception{
	//использовать метод авторизация
	objLogin = new UserAuthorizationPage(driver);
	objLogin.login(Config.customer1, Config.password);
	// choose order
	driver.get("http://edusson.com/customer/orders");
	objCustomerOrders = new MyOrdersCustomerPage(driver);
	objCustomerOrders.goToOrder();
	//release money
	objInProgress = new OrderInProgressPage(driver);
	objInProgress.releaseMoney("10");
	objInProgress.releaseMoney("90");
	//assert order is finished
	objOrderFinished = new OrderFinishedViewPage(driver);
	objOrderFinished.getOrderFinishedCustPageDashboardName();
}
	@After
	  public void tearDown() {
	    driver.quit();
	  }
	
}