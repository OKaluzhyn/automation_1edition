package pages.WriterPages;

import org.openqa.selenium.WebElement;

import utils.Helper;




public class MyOrdersWriterPage  {
	

	
	public static String writerInformPopUp = "//div[@id = 'popup_writer_terms_of_use']";
	public static String closePopUpButton = "//a[@aria-label='Close']";
//close pop up
	public void closePopup(){
		WebElement close_popup_button = Helper.cyclicElementSearchByXpath(closePopUpButton);
		close_popup_button.click();
	}
	// проверяем появление попапа для райтера
	public boolean isWritersPopUpPresent() {
		    	return Helper.isElementPresent(writerInformPopUp);
	           
	}
}