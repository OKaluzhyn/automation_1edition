package edu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ThankYouPage {

	FirefoxDriver driver;
	By thankYouPageName = By.partialLinkText("http://edusson.com/order/pay/thankyou");

public ThankYouPage(FirefoxDriver driver){
	this.driver = driver;
}

//Get the Page name 
	public String getThankYouPageDashboardName(){
		 return	driver.findElement(thankYouPageName).getText();
		}
}