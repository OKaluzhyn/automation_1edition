package ua.qa.edusson.pages.CustomerPages;
import org.openqa.selenium.WebElement;

import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.tests.TestBase.app;

public class MyOrdersCustomerPage {
	
	
	//public static String orderName = By.linkText("test for webdriver");
//	public static String editedOrder = By.linkText("Edited order");
	public static String make_order_button = "//button[@data-atest='atest_order_create_elem_new_order_btn']";
	
	public void makeNewOrder(){
		WebElement makeNewOrderButton = app.getHelper().cyclicElementSearchByXpath(make_order_button);
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