package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ua.qa.edusson.utils.Config;

import static ua.qa.edusson.tests.TestBase.app;

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
WebElement input_First_Name = app.getHelper().cyclicElementSearchByXpath(inputFirstName);
WebElement input_Last_Name = app.getHelper().cyclicElementSearchByXpath(inputLastName);
WebElement input_Email = app.getHelper().cyclicElementSearchByXpath(inputEmail);
WebElement input_Country = app.getHelper().cyclicElementSearchByXpath(inputCountry);
WebElement input_State = app.getHelper().cyclicElementSearchByXpath(inputState);
WebElement input_City = app.getHelper().cyclicElementSearchByXpath(inputCity);
WebElement input_Adress = app.getHelper().cyclicElementSearchByXpath(inputAdress);
WebElement input_ZIP = app.getHelper().cyclicElementSearchByXpath(inputZIP);
WebElement input_Phone = app.getHelper().cyclicElementSearchByXpath(inputPhone);
WebElement input_CardNumber = app.getHelper().cyclicElementSearchByXpath(inputCardNumber);
WebElement input_date = app.getHelper().cyclicElementSearchByXpath(date);
WebElement input_year = app.getHelper().cyclicElementSearchByXpath(year);
WebElement input_CVV = app.getHelper().cyclicElementSearchByXpath(inputCVV);
WebElement input_NameOnCard = app.getHelper().cyclicElementSearchByXpath(inputNameOnCard);
WebElement button_Pay = app.getHelper().cyclicElementSearchByXpath(buttonPay);


    input_First_Name.clear();
    input_First_Name.sendKeys("Tester");
    input_Last_Name.clear();
    input_Last_Name.sendKeys("Tester");
	input_Email.clear();
	input_Email.sendKeys("user@email.com");
	new Select(input_Country).selectByVisibleText("United States");
	new Select(input_State).selectByValue("37");
	input_City.clear();
	input_City.sendKeys("New York");
	input_Adress.clear();
	input_Adress.sendKeys("100 5th AveNew York");
	input_ZIP.clear();
	input_ZIP.sendKeys("0000");
	input_Phone.clear();
	input_Phone.sendKeys("1234567890");
	input_CardNumber.sendKeys(Config.cardNumber_VISA_Edusson);
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
