package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;
import utils.Config;

public class CreditCardPayment extends BasePage {
public CreditCardPayment (FirefoxDriver driver){
this.driver = driver;
}
/*WebElement inputName = driver.findElement(By.xpath("//input[@id='payment_form_customer_first_last_name']"));
WebElement inputEmail = driver.findElement(By.xpath("//input[@type='email']"));
WebElement inputCountry = driver.findElement(By.xpath("//select[@id='payment_form_billing_address_country']"));
WebElement inputState = driver.findElement(By.xpath("//select[@id='payment_form_billing_address_country_state']"));
WebElement inputCity = driver.findElement(By.xpath("//input[@id='payment_form_billing_address_city']"));
WebElement inputAdress = driver.findElement(By.xpath("//input[@id='payment_form_billing_address_address']"));
WebElement inputZIP = driver.findElement(By.xpath("//input[@id='payment_form_billing_address_zip_code']"));
WebElement inputPhone = driver.findElement(By.xpath("//input[@id='payment_form_billing_address_phone']"));
WebElement inputCardNumber = driver.findElement(By.xpath("//input[@id='payment_form_credit_card_number']"));
WebElement date = driver.findElement(By.xpath("//select[@id='payment_form_credit_card_expire_month']"));
WebElement year = driver.findElement(By.xpath("//select[@id='payment_form_credit_card_expire_year']"));
WebElement inputCVV = driver.findElement(By.xpath("//select[@id='payment_form_credit_card_cvv']"));
WebElement inputNameOnCard = driver.findElement(By.xpath("//input[@id='payment_form_credit_card_card_printed_name']"));
WebElement buttonPay = driver.findElement(By.xpath("//input[contains(@class,'js_submit_butt')]"));

public void setAllFields(){
	inputName.clear();
	inputName.sendKeys("Name");
	inputEmail.clear();
	inputEmail.sendKeys("user@email.com");
	new Select(inputCountry).selectByVisibleText("United States");
	new Select(inputState).selectByValue("41");
	inputAdress.sendKeys("My test adress");
	inputZIP.sendKeys("0000");
	inputPhone.sendKeys("1234567890");
	inputCardNumber.sendKeys(Config.cardNumber_VISA);
	new Select(date).selectByValue("10");
	new Select(year).selectByValue("20");
	inputCVV.sendKeys(Config.CVV);
	inputNameOnCard.sendKeys("Name");
	buttonPay.click();
	
}*/

By inputName = By.xpath("//input[@id='payment_form_customer_first_last_name']");
By inputEmail = By.xpath("//input[@type='email']");
By inputCountry = By.xpath("//select[@id='payment_form_billing_address_country']");
By inputState = By.xpath("//select[@id='payment_form_billing_address_country_state']");
By inputCity = By.xpath("//input[@id='payment_form_billing_address_city']");
By inputAdress = By.xpath("//input[@id='payment_form_billing_address_address']");
By inputZIP = By.xpath("//input[@id='payment_form_billing_address_zip_code']");
By inputPhone = By.xpath("//input[@id='payment_form_billing_address_phone']");
By inputCardNumber = By.xpath("//input[@id='payment_form_credit_card_number']");
By date = By.xpath("//select[@id='payment_form_credit_card_expire_month']");
By year = By.xpath("//select[@id='payment_form_credit_card_expire_year']");
By inputCVV = By.xpath("//input[@id='payment_form_credit_card_cvv']");
By inputNameOnCard = By.xpath("//input[@id='payment_form_credit_card_card_printed_name']");
By buttonPay = By.xpath("//input[contains(@class,'js_submit_butt')]");

public void setAllFields(){
	driver.findElement(inputName).clear();
	driver.findElement(inputName).sendKeys("Name");
	driver.findElement(inputEmail).clear();
	driver.findElement(inputEmail).sendKeys("user@email.com");
	new Select(driver.findElement(inputCountry)).selectByVisibleText("United States");
	new Select(driver.findElement(inputState)).selectByValue("41");
	driver.findElement(inputCity).clear();
	driver.findElement(inputCity).sendKeys("My City");
	driver.findElement(inputAdress).clear();
	driver.findElement(inputAdress).sendKeys("My test adress");
	driver.findElement(inputZIP).clear();
	driver.findElement(inputZIP).sendKeys("0000");
	driver.findElement(inputPhone).clear();
	driver.findElement(inputPhone).sendKeys("1234567890");
	driver.findElement(inputCardNumber).sendKeys(Config.cardNumber_VISA);
	new Select(driver.findElement(date)).selectByValue("10");
	new Select(driver.findElement(year)).selectByValue("20");
	driver.findElement(inputCVV).sendKeys(Config.CVV);
	driver.findElement(inputNameOnCard).sendKeys("Name");
	driver.findElement(buttonPay).click();

}
}