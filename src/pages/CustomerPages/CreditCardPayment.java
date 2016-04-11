package pages.CustomerPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.Config;
import utils.Helper;

public class CreditCardPayment {
	
	public static String inputFirstName = "//input[@id='payment_form_customer_first_name']";
	public static String inputLastName = "//input[@id='payment_form_customer_last_name']";
	public static String inputEmail = "//input[@id='payment_form_customer_email']";
	public static String inputCountry = "//select[@id='payment_form_billing_address_country']";
	public static String inputState = "//select[@id='payment_form_billing_address_country_state']";
	public static String inputCity = "//input[@id='payment_form_billing_address_city']";
	public static String inputAdress = "//input[@id='payment_form_billing_address_address']";
	public static String inputZIP = "//input[@id='payment_form_billing_address_zip_code']";
	public static String inputPhone = "//input[@id='payment_form_billing_address_phone']";
	public static String inputCardNumber = "//input[@id='payment_form_credit_card_number']";
	public static String date = "//select[@id='payment_form_credit_card_expire_month']";
	public static String year = "//select[@id='payment_form_credit_card_expire_year']";
	public static String inputCVV = "//input[@id='payment_form_credit_card_cvv']";
	public static String inputNameOnCard = "//input[@id='payment_form_credit_card_card_printed_name']";
	public static String buttonPay = "//input[@type = 'submit']";
	
	
	
	public void setAllFields(){
WebElement input_First_Name = Helper.cyclicElementSearchByXpath(inputFirstName);
WebElement input_Last_Name = Helper.cyclicElementSearchByXpath(inputLastName);
WebElement input_Email = Helper.cyclicElementSearchByXpath(inputEmail);
WebElement input_Country = Helper.cyclicElementSearchByXpath(inputCountry);
WebElement input_State = Helper.cyclicElementSearchByXpath(inputState);
WebElement input_City = Helper.cyclicElementSearchByXpath(inputCity);
WebElement input_Adress = Helper.cyclicElementSearchByXpath(inputAdress);
WebElement input_ZIP = Helper.cyclicElementSearchByXpath(inputZIP);
WebElement input_Phone = Helper.cyclicElementSearchByXpath(inputPhone);
WebElement input_CardNumber = Helper.cyclicElementSearchByXpath(inputCardNumber);
WebElement input_date = Helper.cyclicElementSearchByXpath(date);
WebElement input_year = Helper.cyclicElementSearchByXpath(year);
WebElement input_CVV = Helper.cyclicElementSearchByXpath(inputCVV);
WebElement input_NameOnCard = Helper.cyclicElementSearchByXpath(inputNameOnCard);
WebElement button_Pay = Helper.cyclicElementSearchByXpath(buttonPay);


    input_First_Name.clear();
    input_First_Name.sendKeys("Tester");
    input_Last_Name.clear();
    input_Last_Name.sendKeys("Tester");
	input_Email.clear();
	input_Email.sendKeys("user@email.com");
	new Select(input_Country).selectByVisibleText("United States");
	new Select(input_State).selectByValue("37");
	input_City.sendKeys("New York");
	input_Adress.sendKeys("100 5th AveNew York");
	input_ZIP.sendKeys("0000");
	input_Phone.sendKeys("1234567890");
	input_CardNumber.sendKeys(Config.cardNumber_VISA);
	new Select(input_date).selectByValue("10");
	new Select(input_year).selectByValue("2020");
	input_CVV.sendKeys(Config.CVV);
	input_NameOnCard.sendKeys("Tester Tester");
	button_Pay.click();
	
}



/*public void setAllFields(){
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
*/
}
