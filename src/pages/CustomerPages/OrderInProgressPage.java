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
	
	By payPercent = By.xpath("html/body/div[6]/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/span[1]");//процент релизнутых денег
   
    //получаем занчение релизнутых денег %
  	public String checkReleasedMoney(){
  		return driver.findElement(payPercent).getText();
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
