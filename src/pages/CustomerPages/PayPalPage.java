package pages.CustomerPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class PayPalPage  {
	
 //first log in
	public static String email = "//input[@id='email']";
	public static String pass = "//input[@id='password']";
	public static String logInButton = "//button[@type='submit']";
	// confirm pay - next page
	public static String confirmButton = "//div[@id='continueButtonSection']//input[@type='submit']";

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

	public void clickConfirm() {
		WebElement confirm_button = Helper.cyclicElementSearchByXpath(confirmButton);
			confirm_button.click();
		
			
	}

	public void confirmPayPal(String strUserEmail, String strPassword) {
		this.setUserEmail(strUserEmail);
		this.setUserPassword(strPassword);
		this.clickLogBut();
		Helper.sleep(10);
		//this.clickConfirm();
		
		//Helper.sleep(10);

	}
	
	 //second log in
	public static String email2 = "//input[@id='login_email']";
	public static String pass2 = "//input[@id='login_password']";
	public static String logInButton2 = "//input[@id='submitLogin']";
	
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

	
	public void confirmPayPal_2(String strUserEmail, String strPassword) {
		this.setUserEmail_2(strUserEmail);
		this.setUserPassword_2(strPassword);
		this.clickLogBut_2();
		this.clickConfirm();
	}
}
