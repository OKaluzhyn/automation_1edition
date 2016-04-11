package pages.CustomerPages;

import org.openqa.selenium.WebElement;
import utils.Helper;

public class OrderPayThankYouCustomerPage  {
		

	public static String orderLink = "//a[@data-atest='atest_order_pay_elem_thankyou_page_order_link']";
	
	

	public  void returnOrderPage() {
		WebElement order_link = Helper.cyclicElementSearchByXpath(orderLink);
		order_link.click();
	}
}