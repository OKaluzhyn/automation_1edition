package pages.CustomerPages;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;
public class MyOrdersCustomerPage extends BasePage{
	public MyOrdersCustomerPage(FirefoxDriver driver) {
		this.driver = driver;
	}
	
	By orderName = By.linkText("test for webdriver");
	By editedOrder = By.linkText("Edited order");
	
	
	public void goToOrder(){
		driver.findElement(orderName).click();
	}
	
	public void goToEditedOrder(){
		driver.findElement(editedOrder).click();
}}