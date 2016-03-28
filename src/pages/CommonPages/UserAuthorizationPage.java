package pages.CommonPages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import utils.Helper;



public class UserAuthorizationPage {
	
	
	//XPath для эелементов страницы авторизации
	
	public static String login_link = "//a[@data-atest='atest_login_elem_popup_open']";
	public static String user_name_field = "//input[@data-atest='atest_login_form_email']";
	public static String continue_button = "//button[@data-atest='atest_login_form_submit']";
	public static String user_password_field = "//input[@data-atest='atest_login_form_password']";
	public static String login_button = "//button[@data-atest='atest_login_form_submit']";
	public static String login_button2 = "(//button[@data-atest='atest_login_form_submit'])[1]";
	// форма восстановления пароля
	public static String forgot_password_link = "//a[@data-atest='atest_forgot_pass_elem_popup_open']";
	public static String email_for_change_password_field = "//input[@data-atest='atest_forgot_pass_form_email']";
	public static String submit_button = "//button[@data-atest='atest_forgot_pass_form_submit']";
	public static String change_user = "//a[@data-atest='atest_login_elem_change_user']";
	//for editing
	public static String success_pass_change = "//span[@class='js_forgot_password_content_2']";
	public static String error_pass_change = "//div[@class='errorText js_forgot_pass_error']";
	public static String submit_button_after_change_user = "//button[@data-atest='atest_login_form_submit' and text()='Continue']";
	public static String error_message = "//div[@class='errorText']";
	// нажать кнопку логин на главной - открывает форму авторизации
	//public static String messageForDeactivateUser = "//div[@class='errorText']";
	
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
	public void changeUser(String strUserName, String strPassword){
		this.LogClick();
		Helper.sleep(1);
		this.changeUserClick();
		
		this.setUserName(strUserName);
		
		this.submitClickAfterChangeUser();
		Helper.sleep(1);
		this.setPassword(strPassword);
		this.clickLoginButton2();
	}
	

	// ввод логина в поле формы авторизации
	public void setUserName(String strUserName) {
		WebElement userEmail = Helper.cyclicElementSearchByXpath(user_name_field);
		userEmail.click();
		userEmail.sendKeys(strUserName);
	}
   public void continueClick(){
	WebElement continueButton = Helper.cyclicElementSearchByXpath(continue_button);
	continueButton.click();
}

	// ввод пароля в поле формы авторизации
	public void setPassword(String strPassword) {
		WebElement userPassword = Helper.cyclicElementSearchByXpath(user_password_field);
		userPassword.click();
		userPassword.sendKeys(strPassword);
	}

	// нажать кнопку логин в форме авторизации
	public void clickLoginButton() {
		WebElement submit = Helper.cyclicElementSearchByXpath(login_button);
		submit.click();
	}
	
	
	//change user methods
	public void changeUserClick(){
		WebElement changeUserLink = Helper.cyclicElementSearchByXpath(change_user);
		changeUserLink.click();
	}
	//login после смены юзера
	public void clickLoginButton2() {
		WebElement submit = Helper.cyclicElementSearchByXpath(login_button);
		submit.click();
	}
	//сабмит после смены юзера
	public void submitClickAfterChangeUser(){
		WebElement submitButton2 = Helper.cyclicElementSearchByXpath(submit_button_after_change_user);
		Helper.sleep(5);
		submitButton2.click();
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
			Helper.cyclicElementSearchByXpath(error_message);
			return true;
		} catch (ElementNotVisibleException ex) {
			return false;
		}
	}

	

	

	
	}


