package tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.CustomerBalance;
import pages.CustomerPages.CustomerBalanceThankyou;
import pages.CustomerPages.PayPalPage;
import utils.Users;

public class LoadMoneyToBalance {

	FirefoxDriver driver;
	pages.AuthPage objLogin;
	CustomerBalance objCustBalanacePage;
	PayPalPage objPayPall;
	CustomerBalanceThankyou objCBC;
	
	

@Before
public void setUp() {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
  objLogin = new AuthPage(driver);
  objLogin.login(Users.customer1, Users.password);
  driver.get("http://edusson.com/customer/balance");
}

@Test
public void loadingMoney(){
	//открыть форму, ввести сумму
	objCustBalanacePage = new CustomerBalance(driver);
	objCustBalanacePage.loadMoneyToBalance(null);
	
			// переход на пп
	objPayPall = new PayPalPage(driver);
	objPayPall.confirmPayPal(null, null);
	
	//assert payment success
	objCBC = new CustomerBalanceThankyou(driver);
	objCBC.assertPaySuccess();
}
@After
public void tearDown(){
  driver.quit();
}
}