package pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderFinishedViewPage  {
	
		public static String finishedText = "//span[@class='notify-text']";
		public static String closeRateWriterPopup = "//a[@aria-label='Close']";
public void finished(){
	System.out.println(Helper.driver.findElement(By.xpath(finishedText)).getText());
}
		
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
