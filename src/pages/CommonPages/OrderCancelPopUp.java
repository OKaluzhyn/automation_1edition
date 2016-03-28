package pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.Helper;

public class OrderCancelPopUp {


public static String popUp = "";
public static String reason = "//ul[@data-atest='atest_order_popup_cancel_elem_reason_case']";
public static String otherReason = "";
public static String textfield = "";
public static String apply = "";
public static String cancel = "";


public void chooseReason(){
	Helper.randomChoiceFromDropdown(reason, "//ul[@data-atest='atest_order_popup_cancel_elem_reason_case']/label");
		
	}
	
public void inputYourReason(String strreason){
	driver.findElement(otherReason).click();
	driver.findElement(textfield).sendKeys(strreason);
	
}
public void clickApply(){
	driver.findElement(apply).click();
}
public void clickCancel(){
	driver.findElement(cancel).click();
	}
public void cancelOrder(){
	this.chooseReason();
	this.clickApply();
}
public void cancelWithOtherReason(String strreason){
	this.inputYourReason("test reason");
	this.clickApply();
}
}
