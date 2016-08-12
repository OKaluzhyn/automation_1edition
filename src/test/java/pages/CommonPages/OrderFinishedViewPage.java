package pages.CommonPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderFinishedViewPage  {
	
		public static String finishedTextWriterPage = "//span[@data-atest='atest_order_view_writer_finished_elem_notify']";
		public static String finishedCustomerPage = "//span[@data-atest='atest_order_view_finished_elem_notify']";
		public static String closeRateWriterPopup = "//div//a[@aria-label='Close']";

		
		
		
		
		
	public boolean checkWriterPageFinishedText(){
		try {
			Helper.cyclicElementSearchByXpath(finishedTextWriterPage).getText()
			.contains("The order has been finished");
            return true;
        } catch (Exception e) {
            return false;
        } }
	
	public boolean checkCustomerPageFinishedText(){
		try {
			Helper.cyclicElementSearchByXpath(finishedCustomerPage).getText()
			.contains("The order has been finished");
            return true;
        } catch (Exception e) {
            return false;
        } }
	
	public void closePopup(){
		WebElement close_pop_up = Helper.cyclicElementSearchByXpath(closeRateWriterPopup);
		close_pop_up.click();
	}
	}
