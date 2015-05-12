package edu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;


public class MyOrdersWriter {
	FirefoxDriver driver;
	By myOrdersWriterPageName = By.xpath("html/body/div[6]/div/h1");
	
	public MyOrdersWriter(FirefoxDriver driver){
		this.driver = driver;
	}
	
	//Get the Page name from Home Page
	public String getOrderPageDashboardName(){
		 return	driver.findElement(myOrdersWriterPageName).getText();
	

}
}