package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class MyBalanceCustomerPage extends BasePage {
	public MyBalanceCustomerPage (FirefoxDriver driver){
		this.driver = driver;
		}

By loadButton = By.xpath("html/body/div[6]/div/div[1]/div/button");
By refundButton = By.className("btn-primary [2]"); 
By amountField = By.xpath(".//*[@id='add_deposit_form_deposit_value']");
By load = By.xpath(".//*[@id='add_deposit_form']/div[3]/button");

WebElement creditCardRadioButton = driver.findElement(By.xpath("//span[text()='Visa / Master Card']"));
WebElement payPalRadioButton = driver.findElement(By.xpath("//span[text()='PayPa']"));


public void choosePayPalSystem(){
	payPalRadioButton.click();
}

public void chooseCreditCardPay(){
	creditCardRadioButton.click();
	
}
public void clickLloadbut(){
	driver.findElement(loadButton).click();
}

public void setValue(String strAmount){
	driver.findElement(amountField).sendKeys(strAmount);
}
public void clickload(){
	driver.findElement(load).click();
}
//пополнять баланс через пейпал - переход на страницу пейпала
public void loadMoneyToBalancePayPal(String strAmount){
	this.clickLloadbut();
	this.choosePayPalSystem();
	this.setValue("10");
	this.clickload();
}
//пополнить баланс с помощью карты - переход на пеймент форму
public void loadMoneyToBalanceCreditCard(String strAmount){
	this.clickLloadbut();
	this.chooseCreditCardPay();
	this.setValue("10");
	this.clickload();

}}
