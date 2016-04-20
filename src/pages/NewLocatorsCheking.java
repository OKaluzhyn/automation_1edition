package pages;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import pages.CommonPages.UserAuthorizationPage;
import utils.Config;
import utils.Helper;

public class NewLocatorsCheking {
	//XPath для эелементов страницы авторизации
		public static String login_link = "//a[@data-atest='atest_login_elem_popup_open']";
		public static String usre_Name_field = "//input[@data-atest='atest_login_form_email']";
		public static String continue_button = "//button[@data-atest='atest_login_form_submit']";
		public static String user_Password_field = "//input[@data-atest='atest_login_form_password']";
		public static String login_button = "//button[@data-atest='atest_login_form_submit']";
		// форма восстановления пароля
		public static String forgot_password_link = "//a[@data-atest='atest_forgot_pass_elem_popup_open']";
		public static String email_for_change_password_field = "//input[@data-atest='atest_forgot_pass_form_email']";
		public static String submit_button = "//button[@data-atest='atest_forgot_pass_form_submit']";
		public static String success_pass_change = "f";
		public static String error_pass_change = "//div[@class='errorText js_forgot_pass_error']";
		public static String errorMessage = "//div[@class='errorText']";
		String siteUrl; 
		public static void main(String[] args){
			
		}
		@Before
		public void setUp() throws Exception {
			Helper.driverSetUp();
		}
		@After
		public void theEnd(){
				Helper.quit();
					
			}
		@Test
		
		public void auth(){
			String [] sites = {"http://paperial.com/", "http://paperdon.com/", "http://phdify.com/", "http://papersowl.com/"};
	    	for (int i = 0; i<sites.length; i++){
	    	   	
	    	siteUrl = sites[i];
	    //	System.out.println(siteUrl);
	    	Helper.driver.get(siteUrl);
			this.logIn(Config.customer1, Config.password);
			Helper.sleep(1);
			System.out.println(Helper.driver.getCurrentUrl());
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
		   
		
		
		
		
		// нажать кнопку логин на главной - открывает форму авторизации
		public void LogClick(){
			WebElement openAuthorizationPopUp = Helper.cyclicElementSearchByXpath(login_link);
			openAuthorizationPopUp.click();
		}
	
		// логин юзера полностью
		public void logIn(String strUserName, String strPassword) {
			
			this.LogClick();
			Helper.sleep(1);
			this.setUserName(strUserName);
			this.continueClick();
			this.setPassword(strPassword);
			this.clickLoginButton();
		}

		

		// ввод логина в поле формы авторизации
		public void setUserName(String strUserName) {
			WebElement userEmail = Helper.cyclicElementSearchByXpath(usre_Name_field);
			userEmail.click();
			userEmail.sendKeys(strUserName);
		}
	public void continueClick(){
		WebElement continueButton = Helper.cyclicElementSearchByXpath(continue_button);
		continueButton.click();
	}
		// ввод пароля в поле формы авторизации
		public void setPassword(String strPassword) {
			WebElement userPassword = Helper.cyclicElementSearchByXpath(user_Password_field);
			userPassword.click();
			userPassword.sendKeys(strPassword);
		}

		// нажать кнопку логин в форме авторизации
		public void clickLoginButton() {
			WebElement submit = Helper.cyclicElementSearchByXpath(login_button);
			submit.click();
		}
		
		
		
		// Forgot Password methods
			// переход на форму восстановления пароля
			public void clickForgotPasswordlink() {
				WebElement forgotPassword = Helper.cyclicElementSearchByXpath(forgot_password_link);
				forgotPassword.click();
			}

			
			public void clickForgotPasswordSubmit() {
				WebElement submit = Helper.cyclicElementSearchByXpath(submit_button);
				submit.click();
				
			}

			// проверка отображения сообщения при пустом поле емейл
			public void assertErrorForgotPassword() {
				WebElement errorText = Helper.cyclicElementSearchByXpath(error_pass_change);
				errorText.getText()
						.contains("This is an obligatory field.");
			}

			// ввод емейла
			public void setEmail(String strEmail) {
				WebElement email = Helper.cyclicElementSearchByXpath(email_for_change_password_field);
				email.sendKeys(strEmail);
			}

			// проверка сообщения при успешной отправке формы forgot password
			public void assertSuccessPasswordChange() {
				WebElement successMessage = Helper.cyclicElementSearchByXpath(success_pass_change);
				successMessage.getText()
						.contains("We have just sent temporary password to your email.Use these details to login.");
			}

			
			
			
			// успешное восстановления пароля
			public void forgotPasswordSuccess(String strEmail) {
				this.LogClick();
				Helper.sleep(1);
				this.clickForgotPasswordlink();
				this.setEmail(strEmail);
				this.assertSuccessPasswordChange();

			}

			// отправка формы с пустым полем
			public void forgotPassNoEmail(String strEmail) {
				this.LogClick();
				Helper.sleep(1);
				this.clickForgotPasswordlink();
				this.setEmail(strEmail);
				this.clickForgotPasswordSubmit();
				this.assertErrorForgotPassword();
				}
			
		

		// найти сообщение об ошибке при вводе невалидных данных
		public boolean checkErrorMessagePresent() {
			
			try {
				Helper.cyclicElementSearchByXpath(errorMessage);
				return true;
			} catch (ElementNotVisibleException ex) {
				return false;
			}
		}

		

		
}
