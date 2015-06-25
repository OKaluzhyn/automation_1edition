package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyBalanceCustomerPage {
FirefoxDriver driver;
By loadButton = By.xpath("html/body/div[6]/div/div[1]/div/button");
By refundButton = By.className("btn-primary [2]"); 
By amountField = By.xpath(".//*[@id='add_deposit_form_deposit_value']");
By load = By.xpath(".//*[@id='add_deposit_form']/div[3]/button");

public MyBalanceCustomerPage(FirefoxDriver driver){
	this.driver = driver;
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

public void loadMoneyToBalance(String strAmount){
	this.clickLloadbut();
	this.setValue("10");
	this.clickload();
}
}
