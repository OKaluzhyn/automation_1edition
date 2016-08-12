package ua.qa.edusson.pages.WriterPages;

import static ua.qa.edusson.tests.TestBase.app;

public class WriterProfile {
	
	public static String hireButton = "";
	public String popUpHireWriter = "";
public String createButton = "";
public String applyRequestButton = "";
	public void clickHireButton() {
		app.getHelper().cyclicElementSearchByXpath(hireButton).click();
	}

	public void clickCreateOrder() {
		app.getHelper().cyclicElementSearchByXpath(createButton).click();
		
	}

	public  void applyRequest() {
		app.getHelper().cyclicElementSearchByXpath(applyRequestButton ).click();
	}
	
}