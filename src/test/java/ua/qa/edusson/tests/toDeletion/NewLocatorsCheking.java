package ua.qa.edusson.tests.toDeletion;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

public class NewLocatorsCheking extends TestBase {
	//XPath ��� ���������� �������� �����������
		public static String login_link = "//a[@data-atest='atest_login_elem_popup_open']";
		public static String usre_Name_field = "//input[@data-atest='atest_login_form_email']";
		public static String continue_button = "//button[@data-atest='atest_login_form_submit']";
		public static String user_Password_field = "//input[@data-atest='atest_login_form_password']";
		public static String login_button = "//button[@data-atest='atest_login_form_submit']";
		// ����� �������������� ������
		public static String forgot_password_link = "//a[@data-atest='atest_forgot_pass_elem_popup_open']";
		public static String email_for_change_password_field = "//input[@data-atest='atest_forgot_pass_form_email']";
		public static String submit_button = "//button[@data-atest='atest_forgot_pass_form_submit']";
		public static String success_pass_change = "f";
		public static String error_pass_change = "//div[@class='errorText js_forgot_pass_error']";
		public static String errorMessage = "//div[@class='errorText']";
		String siteUrl; 

		@Test
		
		public void auth(){
			String [] sites = {"http://paperial.com/", "http://paperdon.com/", "http://phdify.com/", "http://papersowl.com/"};
	    	for (int i = 0; i<sites.length; i++){
	    	   	
	    	siteUrl = sites[i];
	    //	System.out.println(siteUrl);
	    	app.driver.get(siteUrl);
			this.logIn(Config.customer1, Config.password);
			Helper.sleep(1);
			System.out.println(app.driver.getCurrentUrl());
	    	}
			//Helper.isElementPresent("//div[@data-atest='atest_order_bid_elem_bid_open']");
			/*if (Helper.driver.getTitle().equals("Edusson.com - My Orders")){
			
				System.out.println("Test passed");
			}
		else {
			System.out.println("Title does not match");}
		*/
			//Assert.assertFalse(Helper.isElementPresent(login_button));
		}
		
		
		
		/*public boolean is_ElementPresent(String xpath){ 
		    if(Helper.cyclicElementSearchByXpath(xpath).getSize() > 0){ 
		           return true; 
		     }else{ 
		           return false; 
		     } 
		
		}
		*/
		   
		
		
		
		
		// ������ ������ ����� �� ������� - ��������� ����� �����������
		public void LogClick(){
			WebElement openAuthorizationPopUp = app.getHelper().cyclicElementSearchByXpath(login_link);
			openAuthorizationPopUp.click();
		}
	
		// ����� ����� ���������
		public void logIn(String strUserName, String strPassword) {
			
			this.LogClick();
			Helper.sleep(1);
			this.setUserName(strUserName);
			this.continueClick();
			this.setPassword(strPassword);
			this.clickLoginButton();
		}

		

		// ���� ������ � ���� ����� �����������
		public void setUserName(String strUserName) {
			WebElement userEmail = app.getHelper().cyclicElementSearchByXpath(usre_Name_field);
			userEmail.click();
			userEmail.sendKeys(strUserName);
		}
	public void continueClick(){
		WebElement continueButton = app.getHelper().cyclicElementSearchByXpath(continue_button);
		continueButton.click();
	}
		// ���� ������ � ���� ����� �����������
		public void setPassword(String strPassword) {
			WebElement userPassword = app.getHelper().cyclicElementSearchByXpath(user_Password_field);
			userPassword.click();
			userPassword.sendKeys(strPassword);
		}

		// ������ ������ ����� � ����� �����������
		public void clickLoginButton() {
			WebElement submit = app.getHelper().cyclicElementSearchByXpath(login_button);
			submit.click();
		}
		
		
		
		// Forgot Password methods
			// ������� �� ����� �������������� ������
			public void clickForgotPasswordlink() {
				WebElement forgotPassword = app.getHelper().cyclicElementSearchByXpath(forgot_password_link);
				forgotPassword.click();
			}

			
			public void clickForgotPasswordSubmit() {
				WebElement submit = app.getHelper().cyclicElementSearchByXpath(submit_button);
				submit.click();
				
			}

			// �������� ����������� ��������� ��� ������ ���� �����
			public void assertErrorForgotPassword() {
				WebElement errorText = app.getHelper().cyclicElementSearchByXpath(error_pass_change);
				errorText.getText()
						.contains("This is an obligatory field.");
			}

			// ���� ������
			public void setEmail(String strEmail) {
				WebElement email = app.getHelper().cyclicElementSearchByXpath(email_for_change_password_field);
				email.sendKeys(strEmail);
			}

			// �������� ��������� ��� �������� �������� ����� forgot password
			public void assertSuccessPasswordChange() {
				WebElement successMessage = app.getHelper().cyclicElementSearchByXpath(success_pass_change);
				successMessage.getText()
						.contains("We have just sent temporary password to your email.Use these details to login.");
			}

			
			
			
			// �������� �������������� ������
			public void forgotPasswordSuccess(String strEmail) {
				this.LogClick();
				Helper.sleep(1);
				this.clickForgotPasswordlink();
				this.setEmail(strEmail);
				this.assertSuccessPasswordChange();

			}

			// �������� ����� � ������ �����
			public void forgotPassNoEmail(String strEmail) {
				this.LogClick();
				Helper.sleep(1);
				this.clickForgotPasswordlink();
				this.setEmail(strEmail);
				this.clickForgotPasswordSubmit();
				this.assertErrorForgotPassword();
				}
			
		

		// ����� ��������� �� ������ ��� ����� ���������� ������
		public boolean checkErrorMessagePresent() {
			
			try {
				app.getHelper().cyclicElementSearchByXpath(errorMessage);
				return true;
			} catch (ElementNotVisibleException ex) {
				return false;
			}
		}

		

		
}
