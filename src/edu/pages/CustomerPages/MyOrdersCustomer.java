package edu.pages.CustomerPages;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
public class MyOrdersCustomer {

	FirefoxDriver driver;
	By orderName = By.xpath("html/body/div[6]/div/table/tbody/tr[1]/td[1]/a");
	
	public MyOrdersCustomer(FirefoxDriver driver){
		this.driver = driver;
	}
	
	public void goToOrder(){
		driver.findElement(orderName).click();
	}
}