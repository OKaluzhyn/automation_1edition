package pages.CommonPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderFinishedViewPage  {
	
		public static String finishedText = "//span[@data-atest='atest_order_view_finished_elem_notify']";
		public static String closeRateWriterPopup = "//a[@aria-label='Close']";

		
	public boolean check(){
		try {
			Helper.cyclicElementSearchByXpath(finishedText).getText()
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
