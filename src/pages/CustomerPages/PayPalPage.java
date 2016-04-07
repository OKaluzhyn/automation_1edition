package pages.CustomerPages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.Config;
import utils.Helper;



public class PayPalPage  {
	
 //first log in
	public static String email = "//input[@id='email']";
	public static String pass = "//input[@id='password']";
	public static String logInButton = "//button[@type='submit']";
	// confirm pay - next page
	public static String continueButton = "//div[@id='main']//input[@id='confirmButtonTop']";
	

	public void setUserEmail(String strUserEmail) {
		WebElement e_mail = Helper.cyclicElementSearchByXpath(email);
		Helper.sleep(1);
		e_mail.clear();
		e_mail.sendKeys(strUserEmail);

	}
	

	public void setUserPassword(String strPassword) {
		WebElement password = Helper.cyclicElementSearchByXpath(pass);
		password.clear();
		password.sendKeys(strPassword);
		

	}

	public void clickLogBut() {
	WebElement login_button = Helper.cyclicElementSearchByXpath(logInButton);
	login_button.click();

	}
	public void clickContinue() {
		WebElement continue_button = Helper.cyclicElementSearchByXpath(continueButton);
		continue_button.click();
	}
	@Before
	public void setUp(){
		Helper.driverSetUp("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-9UV03772AM347504S#/checkout/login");

	}

	@After
	public void tearDown() {
		
		Helper.quit();
	}

	
@Test
public void fhdfh(){
	Helper.sleep(1);
	Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
      this.confirmPayPal(Config.paypall_login, Config.paypall_pass);
      
      Helper.driver.switchTo().defaultContent();
      this.clickContinue();
      Helper.sleep(10);
}

	public void confirmPayPal(String strUserEmail, String strPassword) {
		Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		this.setUserEmail(strUserEmail);
		this.setUserPassword(strPassword);
		this.clickLogBut();
		Helper.driver.switchTo().defaultContent();
		this.clickContinue();
		Helper.sleep(10);
		
		
		//Helper.sleep(10);

	}
	
	 //second log in
	public static String email2 = "//input[@id='login_email']";
	public static String pass2 = "//input[@id='login_password']";
	public static String logInButton2 = "//input[@id='submitLogin']";
	public static String confirmButton = "//div[@id='continueButtonSection']//input[@type='submit']";
	
	public void setUserEmail_2(String strUserEmail) {
		WebElement e_mail = Helper.cyclicElementSearchByXpath(email2);
		Helper.sleep(1);
		e_mail.clear();
		e_mail.sendKeys(strUserEmail);

	}
	

	public void setUserPassword_2(String strPassword) {
		WebElement password = Helper.cyclicElementSearchByXpath(pass2);
		password.clear();
		password.sendKeys(strPassword);
		

	}

	public void clickLogBut_2() {
	WebElement login_button = Helper.cyclicElementSearchByXpath(logInButton2);
	login_button.click();

	}
	public void clickConfirm() {
		WebElement confirm_button = Helper.cyclicElementSearchByXpath(confirmButton);
			confirm_button.click();
		
			
	}
	
	public void confirmPayPal_2(String strUserEmail, String strPassword) {
		this.setUserEmail_2(strUserEmail);
		this.setUserPassword_2(strPassword);
		this.clickLogBut_2();
		this.clickConfirm();
	}
}
