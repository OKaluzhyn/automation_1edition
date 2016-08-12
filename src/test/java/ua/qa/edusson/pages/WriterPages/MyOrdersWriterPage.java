package ua.qa.edusson.pages.WriterPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.TestBase.app;


public class MyOrdersWriterPage  {
	

	
	public static String writerInformPopUp = "//div[@id = 'popup_writer_terms_of_use']";
	public static String closePopUpButton = "//a[@aria-label='Close']";
//close pop up
	public void closePopup(){
		WebElement close_popup_button = app.getHelper().cyclicElementSearchByXpath(closePopUpButton);
		close_popup_button.click();
	}
	// ��������� ��������� ������ ��� �������
	public boolean isWritersPopUpPresent() {
		    	return app.getHelper().isElementPresent(writerInformPopUp);
	           
	}
}