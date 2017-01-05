package ua.qa.edusson.pages.WriterPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class AllAvaliableOrdersWriterPage  {
	public static String orderName = "test for webdriver";

	WebElement order_Name = app.getHelper().cyclicElementSearchByXpath(orderName);

	public void chooseOrder() {
		order_Name.click();
	}
}
