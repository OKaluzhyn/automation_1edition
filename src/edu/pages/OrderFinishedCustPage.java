package edu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderFinishedCustPage {
FirefoxDriver driver;
By finishedPageName = By.xpath("html/body/div[6]/div/div[1]/span[2]");

public OrderFinishedCustPage(FirefoxDriver driver){
	this.driver = driver;
}

//Get the Page name from Home Page
public String getOrderFinishedCustPageDashboardName(){
	 return	driver.findElement(finishedPageName).getText();


}
}