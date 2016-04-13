package pages.CommonPages;

import org.openqa.selenium.WebElement;

import utils.Helper;

public class HeaderMenu  {
	
	public static String logOut = "//a[@href='/logout']//span";

	public void userLogOut() {
		WebElement log_out_button = Helper.cyclicElementSearchByXpath(logOut);
		log_out_button.click();
	}
}
