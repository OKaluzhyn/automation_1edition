package pages.CustomerPages;

import org.openqa.selenium.WebElement;
import utils.Helper;

public class OrderPayThankYouCustomerPage  {
		

	public static String orderLink = "";
	
	

	public static void returnOrderPage() {
		WebElement order_link = Helper.cyclicElementSearchByXpath(orderLink);
		order_link.click();
	}
}