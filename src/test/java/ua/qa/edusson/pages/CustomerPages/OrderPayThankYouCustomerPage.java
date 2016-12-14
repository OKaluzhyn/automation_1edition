package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.TestBase.app;

public class OrderPayThankYouCustomerPage  {
		

	public static String orderLink = "//a[@data-atest='atest_order_pay_elem_thankyou_page_order_link']";

	public static String popUpFailPayPal = "//div[@id='popup_customer_payment_failed']";

	public void stopTestBecouseFailedPayment() {
		app.getHelper().sleep(10);
		if (app.getHelper().isElementPresent(popUpFailPayPal)){
			System.out.println("Payment didn't go through");
			app.stop();
		}
	}


	public  void returnOrderPage() {
		WebElement order_link = app.getHelper().cyclicElementSearchByXpath(orderLink);
		order_link.click();
	}
}