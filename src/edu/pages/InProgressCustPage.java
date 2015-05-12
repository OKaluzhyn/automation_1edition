package edu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InProgressCustPage {
	FirefoxDriver driver;
	By releaseMoneyfield = By.cssSelector("div.release_money_value_div > input.js_compensation_value");
	By releaseButton = By.xpath("(//button[@type='button'])[5]");
	By popUpButtonRelease = By.xpath("//div[@id='popup_compensate']/div[2]/div/div/button[2]");
	
	
	public InProgressCustPage(FirefoxDriver driver) {
	this.driver = driver;
}

	
	public void setCompensationPercent(String strPercent){
		driver.findElement(releaseMoneyfield).clear();
		driver.findElement(releaseMoneyfield).sendKeys(strPercent);
	}
	
	public void clickReleaseButton(){
		driver.findElement(releaseButton).click();
		
	}
	
	public void clickConfirmButton(){
		driver.findElement(popUpButtonRelease).click();
	}
	
	public void releaseMoney(String strPercent){
		this.setCompensationPercent(strPercent);
		this.clickReleaseButton();
		this.clickConfirmButton();
	}
}
