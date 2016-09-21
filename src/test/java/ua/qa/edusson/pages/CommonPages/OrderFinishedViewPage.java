package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.TestBase.app;


public class OrderFinishedViewPage  {
	
		public static String finishedTextWriterPage = "//span[@data-atest='atest_order_view_writer_finished_elem_notify']";
		public static String finishedCustomerPage = "//span[@data-atest='atest_order_view_finished_elem_notify']";
		public static String closeRateWriterPopup = "//div[@class='modal-content js_popup_content']//a[@aria-label='Close']";

		
		
		
		
		
	public boolean checkWriterPageFinishedText(){
		try {
			app.getHelper().cyclicElementSearchByXpath(finishedTextWriterPage).getText()
			.contains("The order has been finished");
            return true;
        } catch (Exception e) {
            return false;
        } }
	
	public boolean checkCustomerPageFinishedText(){
		try {
			app.getHelper().cyclicElementSearchByXpath(finishedCustomerPage).getText()
			.contains("The order has been finished");
            return true;
        } catch (Exception e) {
            return false;
        } }
	
	public void closePopup(){
		WebElement close_pop_up = app.getHelper().cyclicElementSearchByXpath(closeRateWriterPopup);
		close_pop_up.click();
	}
	}
