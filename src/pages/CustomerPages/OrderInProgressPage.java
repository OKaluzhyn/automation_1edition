package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderInProgressPage extends BasePage {
	public OrderInProgressPage(FirefoxDriver driver) {
		this.driver = driver;
	}
	
	By releaseMoneyfield = By.cssSelector("div.release_money_value_div > input.js_compensation_value");
	By releaseButton = By.xpath("(//button[@type='button'])[5]");
	By popUpButtonRelease = By.xpath("//div[@id='popup_compensate']/div[2]/div/div/button[2]");
	
	By payPercent = By.xpath("//p[text()='Paid']/..//span[@class='block-data-left']");//процент релизнутых денег
   
    //получаем занчение релизнутых денег %
  	public String checkReleasedMoney(){
  		 driver.findElement(payPercent).getAttribute("value");
  		return ("value");
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
