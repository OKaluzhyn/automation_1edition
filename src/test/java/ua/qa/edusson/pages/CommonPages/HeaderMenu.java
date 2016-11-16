package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.TestBase.app;

public class HeaderMenu  {
	
	public static String logOut = "//a[@href='/logout']//span";

	public void userLogOut() {
		app.getHelper().waitElement(logOut);

		WebElement log_out_button = app.getHelper().cyclicElementSearchByXpath(logOut);
		app.getHelper().waitElement(logOut);
		log_out_button.click();
	}
}
