package pages.CommonPages;

import org.openqa.selenium.WebElement;

import utils.Helper;

public class HeaderMenu  {
	
	public static String logOut = "//li[@class='logout']/a";

	public void userLogOut() {
		WebElement log_out_button = Helper.cyclicElementSearchByXpath(logOut);
		log_out_button.click();
	}
}
