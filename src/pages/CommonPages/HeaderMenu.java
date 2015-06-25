package pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class HeaderMenu extends BasePage {
	public HeaderMenu(FirefoxDriver driver){
		this.driver = driver;
	}
	By logOut = By.xpath("html/body/div[5]/div/div/ul/li[1]/a");

	public void userLogOut() {
		driver.findElement(logOut).click();
	}
}
