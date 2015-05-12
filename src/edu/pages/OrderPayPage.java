package edu.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

	
	
	public class OrderPayPage {

		FirefoxDriver driver;
		By reserveMoneyButton = By.xpath("//*[@id='form_payment_type']/div[1]/div[1]/div[2]/div[2]/button");
		
		public OrderPayPage(FirefoxDriver driver){
			this.driver = driver;
		}
		
		//Get the Page name from Home Page
		public void clickReserveButton(){
			driver.findElement(reserveMoneyButton).click();
		
		}
	}

