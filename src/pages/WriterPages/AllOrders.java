package pages.WriterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AllOrders {
	
	FirefoxDriver driver;
	By orderName = By.linkText("test for webdriver");

	public AllOrders(FirefoxDriver driver) {
	this.driver = driver;
	}
	
	public void chooseOrder(){
		driver.findElement(orderName).click();
	}
}

