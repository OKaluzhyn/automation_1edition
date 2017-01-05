package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class MyBalanceCustomerPage  {
	

public static String loadMoneyButton = "html/body/div[6]/div/div[1]/div/button";

//Pop up
public static String refundButton = "btn-primary [2]"; 
public static String amountField = ".//*[@id='add_deposit_form_deposit_value']";
public static String load = ".//*[@id='add_deposit_form']/div[3]/button";
public static String payPal = "";
public static String creditCard = "";


WebElement load_Money_Button = app.getHelper().cyclicElementSearchByXpath(loadMoneyButton);
WebElement refund_Button = app.getHelper().cyclicElementSearchByXpath(refundButton);
WebElement amount_Field = app.getHelper().cyclicElementSearchByXpath(amountField);
WebElement load_button = app.getHelper().cyclicElementSearchByXpath(load);
WebElement creditCardRadioButton = app.getHelper().cyclicElementSearchByXpath(creditCard);
WebElement payPalRadioButton = app.getHelper().cyclicElementSearchByXpath(payPal);


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

//��������� ������ ����� ������ - ������� �� �������� �������
public void loadMoneyToBalancePayPal(String strAmount){
	this.clickLloadbutMain();
	this.choosePayPalSystem();
	this.setValue("10");
	this.clickload();
}
//��������� ������ � ������� ����� - ������� �� ������� �����
public void loadMoneyToBalanceCreditCard(String strAmount){
	this.clickLloadbutMain();
	this.chooseCreditCardPay();
	this.setValue("10");
	this.clickload();

}}
