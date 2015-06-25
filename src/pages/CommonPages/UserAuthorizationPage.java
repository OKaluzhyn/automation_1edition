package pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class UserAuthorizationPage extends BasePage {
	public  UserAuthorizationPage(FirefoxDriver driver) {
		this.driver = driver;
	}
	
	By login = By.className("login-link");
	// форма авторизации
	By userName = By.id("_username");
	By password = By.id("_password");
	By loginButton = By.name("login");
	By errorMessage = By.className("errorText");
	By messageForDeactivateUser = By.className("errorText");
	By forgotPass = By.xpath(".//*[@id='js_hide_popup_login']");
	// форма восстановления пароля
	By email = By.className("js_forgot_password_email");
	By submitButton = By.className("btn");
	By successMessage = By.className("js_forgot_password_content_2");
	By errorMes2 = By.className("errorText");

	// логин юзера полностью
	public void logIn(String strUserName, String strPassword) {
		this.clickLogin();
		this.setUserName(strUserName);
		this.setPassword(strPassword);
		this.clickLoginButton();
	}

	// нажать кнопку логин на главной - открывает форму авторизации
	public void clickLogin() {
		driver.findElement(login).click();
	}

	// ввод логина в поле формы авторизации
	public void setUserName(String strUserName) {
		driver.findElement(userName).sendKeys(strUserName);
	}

	// ввод пароля в поле формы авторизации
	public void setPassword(String strPassword) {
		driver.findElement(password).sendKeys(strPassword);
	}

	// нажать кнопку логин в форме авторизации
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	// найти сообщение об ошибке при вводе неверного логина\пароля
	public boolean checkErrorMessagePresent() {
		try {
			driver.findElement(errorMessage);
			return true;
		} catch (ElementNotVisibleException ex) {
			return false;
		}
	}

	// проверка отображения сообщения при попытке авторизации деактивированого
	// пользователя
	public boolean checkErrorMessagePresentForDeactivateUser() {
		try {
			driver.findElement(messageForDeactivateUser).getText()
					.contains("Ooops, your account has been deactivated");
			return true;
		} catch (ElementNotVisibleException ex) {
			return false;
		}
	}

	// Forgot Password methods
	// переход на форму восстановления пароля
	public void clickFPlink() {
		driver.findElement(forgotPass).click();
	}

	//
	public void clickSubmit() {
		driver.findElement(submitButton).click();
	}

	// проверка отображения сообщения при пустом поле емейл
	public void assertError() {
		driver.findElement(errorMes2).getText()
				.contains("This is an obligatory field.");
	}

	// ввод емейла
	public void setEmail(String strEmail) {
		driver.findElement(email).sendKeys("user@email");
	}

	// проверка сообщения при успешной отправке формы
	public void assertSuccMessage() {
		driver.findElement(successMessage)
				.getText()
				.contains(
						"We have just sent temporary password to your email.Use these details to login.");
	}

	// успешное восстановления пароля
	public void forgotPass(String strEmail) {
		this.clickLogin();
		this.clickFPlink();
		this.setEmail(null);

	}

	// отправка формы с пустым полем
	public void forgotPassNoEmail() {
		this.clickLogin();
		this.clickFPlink();
		this.clickSubmit();

	}

}
