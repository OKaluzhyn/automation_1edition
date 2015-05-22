package edu.example.tests.Customer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.CustomerPages.CustomerBalance;
import edu.pages.CustomerPages.CustomerBalanceThankyou;
import edu.pages.CustomerPages.PayPalPage;

public class LoadMoneyToBalance {

	FirefoxDriver driver;
	edu.pages.AuthPage objLogin;
	CustomerBalance objCustBalanacePage;
	PayPalPage objPayPall;
	CustomerBalanceThankyou objCBC;
	
	

@Before
public void setUp() {
  driver = new FirefoxDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("http://edusson.com");
  objLogin = new AuthPage(driver);
  objLogin.loginAsCustomer1("Cust.23.02@i.ua","test11");
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