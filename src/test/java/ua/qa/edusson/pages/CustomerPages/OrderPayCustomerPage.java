package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.TestBase.app;


public class OrderPayCustomerPage  {
	
	public static String payPalButton = "//label[@data-atest='atest_order_pay_elem_pay_pall']"; 
	public static String creditCardButton = "//label[@data-atest='atest_order_pay_elem_credit_card']";
	public static String makeDepositButton = "//button[@data-atest='atest_order_pay_elem_make_deposit_btn']";
	 //order pay for easy bidding
	public static String reserveMoney = "//button[text()='Reserve money']";


     public void choosePayPal(){
    	 WebElement pay_pal_button = app.getHelper().cyclicElementSearchByXpath(payPalButton);
    	 pay_pal_button.click();
	
}
	public void chooseCardPay(){
		WebElement credit_card_button = app.getHelper().cyclicElementSearchByXpath(creditCardButton);
		credit_card_button.click();
	}
	public void clickReserveButton() {
		WebElement make_deposit_button = app.getHelper().cyclicElementSearchByXpath(makeDepositButton);
		make_deposit_button.click();

	}
	//for easy bidding
	public void reserveMoney(){
		WebElement reserve_button = app.getHelper().cyclicElementSearchByXpath(reserveMoney);
		reserve_button.click();
	}
	
	public void confirmPay()
	{
		if(app.getHelper().isElementPresent(makeDepositButton)){
			this.clickReserveButton();
		}
		else if (app.getHelper().isElementPresent(reserveMoney)){
			this.reserveMoney();
		}
	}
}
