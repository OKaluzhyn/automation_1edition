package edu.pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HeaderMenu {

	FirefoxDriver driver;
	
	By logOut = By.xpath("html/body/div[5]/div/div/ul/li[1]/a");
	
			
	
	public void userLogOut(){
		driver.findElement(logOut).click();
	}
}
