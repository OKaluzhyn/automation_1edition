package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;

public class AttentionBeforOrderEditingCustomerPopUp {
	public static String applyButton = "//a[@data-atest='atest_order_popup_order_edit_ask_elem_cancel_btn']";
	
	

	public void applyPopupBeforEditingOrder() {
		WebElement apply_button = app.getHelper().cyclicElementSearchByXpath(applyButton);
		apply_button.click();
	}
}