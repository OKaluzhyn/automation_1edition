package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class PayPalPage extends BasePage {
	public PayPalPage(FirefoxDriver driver) {
		this.driver = driver;
	}

	By email = By.xpath("//*[@id='login_email']");
	By pass = By.xpath("//*[@id='login_password']");
	By logInButton = By.xpath("//*[@id='submitLogin']");
	// confirm pay - next page
	By confirmButton = By.xpath("//*[@id='continue']");

	public void setUserEmail(String strUserEmail) {
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(strUserEmail);

	}

	public void setUserPassword(String strPassword) {
		driver.findElement(pass).sendKeys(strPassword);

	}

	public void clickLogBut() {
		driver.findElement(logInButton).click();

	}

	public void clickConfirm() {
		driver.findElement(confirmButton).click();
	}

	public void confirmPayPal(String strUserEmail, String strPassword) {
		this.setUserEmail(strUserEmail);
		this.setUserPassword(strPassword);
		this.clickLogBut();
		this.clickConfirm();

	}
}
