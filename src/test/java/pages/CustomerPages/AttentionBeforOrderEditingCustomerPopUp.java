package pages.CustomerPages;

import org.openqa.selenium.WebElement;

import utils.Helper;

public class AttentionBeforOrderEditingCustomerPopUp {
	public static String applyButton = "//a[@data-atest='atest_order_popup_order_edit_ask_elem_cancel_btn']";
	
	

	public void applyPopupBeforEditingOrder() {
		WebElement apply_button = Helper.cyclicElementSearchByXpath(applyButton);
		apply_button.click();
	}
}