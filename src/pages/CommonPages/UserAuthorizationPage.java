package pages.CommonPages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import utils.Helper;



public class UserAuthorizationPage {
	
	
	//XPath для эелементов страницы авторизации
	public static String login_link = "//li[@class='login']//span[text()='Log in']";
	public static String usre_Name_field = "//div[@class='uk-form']//input[@id='_pre_login_username']";
	public static String continue_button = "//form[@class='login_form js_pre_login_form']//button[@type='submit']";
	public static String user_Password_field = "//form[@class='login_form js_login_form']//input[@type='password']";
	public static String login_button = "//button[text()='Log in']";
	// форма восстановления пароля
	public static String forgot_password_link = "//a[contains(@class,'js_login_open_forgot_popup')]";
	public static String email_for_change_password_field = "//input[@class='js_forgot_password_email']";
	public static String submit_button = "//span[@class='js_forgot_password_content_1']//button[@type='submit']";
	public static String success_pass_change = "//span[@class='js_forgot_password_content_2']";
	public static String error_pass_change = "//div[@class='errorText js_forgot_pass_error']";
	public static String errorMessage = "//div[@class='errorText']";
	//public static String messageForDeactivateUser = "//div[@class='errorText']";
	
	
	
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


