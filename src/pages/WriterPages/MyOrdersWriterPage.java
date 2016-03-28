package pages.WriterPages;

import java.util.NoSuchElementException;

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
		//WebElement writer_inform_popup = Helper.cyclicElementSearchByXpath(writerInformPopUp);
		
	        try {
	        	Helper.cyclicElementSearchByXpath(writerInformPopUp);
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        } }
	}
