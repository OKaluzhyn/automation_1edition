package tests.Customer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderSummaryCustomerPopUp;
import pages.CustomerPages.AttentionBeforOrderEditingCustomerPopUp;
import utils.Config;
import utils.DriverFireFox;

public class EditOrder implements DriverFireFox, UserAuthorizationPage, OrderCreateCustomerPage, OrderBiddingCustomerPage, AttentionBeforOrderEditingCustomerPopUp,
OrderSummaryCustomerPopUp{
	public String urlEdit;
	@Before
	public	void beforeTest() throws Exception{
		setUp();
	}

	@Test

	public void edit()throws NullPointerException{
		// авторизация
		login(Config.customer1,Config.password);
		
		
		//ет перехода к заказу
		
		
		clickEdit();
		
		//apply pop-up info
		applyPopupBeforEditingOrder();
				
		//переключаемся на фрейм
		WebElement iframe = driver.findElement(PopUpEditOrder);
		driver.switchTo().frame(iframe);
		//editing
		editOrder(null, null);		
}
	@After
	  public void tearDown() {
	    driver.quit();
	  }
}
