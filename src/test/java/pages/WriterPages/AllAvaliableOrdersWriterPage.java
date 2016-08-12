package pages.WriterPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class AllAvaliableOrdersWriterPage  {
	public static String orderName = "test for webdriver";

	WebElement order_Name = Helper.cyclicElementSearchByXpath(orderName);

	public void chooseOrder() {
		order_Name.click();
	}
}
