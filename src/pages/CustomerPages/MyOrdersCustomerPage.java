package pages.CustomerPages;
import org.openqa.selenium.WebElement;

import utils.Helper;
public class MyOrdersCustomerPage {
	
	
	//public static String orderName = By.linkText("test for webdriver");
//	public static String editedOrder = By.linkText("Edited order");
	public static String make_order_button = "//button[@data-atest='atest_order_create_elem_new_order_btn']";
	
	public void makeNewOrder(){
		WebElement makeNewOrderButton = Helper.cyclicElementSearchByXpath(make_order_button);
		Helper.sleep(1);
		makeNewOrderButton.click();
	}
	/*
	public void goToOrder(){
		driver.findElement(orderName).click();
	}
	
	public void goToEditedOrder(){
		driver.findElement(editedOrder).click(); */
}