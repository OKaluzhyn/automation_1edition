package pages.CustomerPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class MyBalanceCustomerPage  {
	

public static String loadMoneyButton = "html/body/div[6]/div/div[1]/div/button";

//Pop up
public static String refundButton = "btn-primary [2]"; 
public static String amountField = ".//*[@id='add_deposit_form_deposit_value']";
public static String load = ".//*[@id='add_deposit_form']/div[3]/button";
public static String payPal = "";
public static String creditCard = "";


WebElement load_Money_Button = Helper.cyclicElementSearchByXpath(loadMoneyButton);
WebElement refund_Button = Helper.cyclicElementSearchByXpath(refundButton);
WebElement amount_Field = Helper.cyclicElementSearchByXpath(amountField);
WebElement load_button = Helper.cyclicElementSearchByXpath(load);
WebElement creditCardRadioButton = Helper.cyclicElementSearchByXpath(creditCard);
WebElement payPalRadioButton = Helper.cyclicElementSearchByXpath(payPal);


public void choosePayPalSystem(){
	payPalRadioButton.click();
}

public void chooseCreditCardPay(){
	creditCardRadioButton.click();
	
}
public void clickLloadbutMain(){
	load_Money_Button.click();
}

public void setValue(String strAmount){
	amount_Field.sendKeys(strAmount);
}
public void clickload(){
	load_button.click();
}

//пополнять баланс через пейпал - переход на страницу пейпала
public void loadMoneyToBalancePayPal(String strAmount){
	this.clickLloadbutMain();
	this.choosePayPalSystem();
	this.setValue("10");
	this.clickload();
}
//пополнить баланс с помощью карты - переход на пеймент форму
public void loadMoneyToBalanceCreditCard(String strAmount){
	this.clickLloadbutMain();
	this.chooseCreditCardPay();
	this.setValue("10");
	this.clickload();

}}
