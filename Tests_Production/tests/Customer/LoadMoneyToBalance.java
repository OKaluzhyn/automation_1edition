package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyBalanceCustomerPage;
import pages.CustomerPages.CustomerFillUpBalanceThankyouPage;
import pages.CustomerPages.PayPalPage;
import utils.Config;

public class LoadMoneyToBalance {

	FirefoxDriver driver;
	pages.CommonPages.UserAuthorizationPage objLogin;
	MyBalanceCustomerPage objCustBalanacePage;
	PayPalPage objPayPall;
	CustomerFillUpBalanceThankyouPage objCBC;
	
	

@Before
public void setUp() {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
  objLogin = new UserAuthorizationPage(driver);
  objLogin.login(Config.customer1, Config.password);
  driver.get("http://edusson.com/customer/balance");
}

@Test
public void loadingMoney(){
	//открыть форму, ввести сумму
	objCustBalanacePage = new MyBalanceCustomerPage(driver);
	objCustBalanacePage.loadMoneyToBalance(null);
	
			// переход на пп
	objPayPall = new PayPalPage(driver);
	objPayPall.confirmPayPal(Config.pp, Config.pass);
	
	//assert payment success
	objCBC = new CustomerFillUpBalanceThankyouPage(driver);
	objCBC.assertPaySuccess();
}
@After
public void tearDown(){
  driver.quit();
}
}