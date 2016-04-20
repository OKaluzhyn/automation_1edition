package pages.CustomerPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderPayCustomerPage  {
	
	public static String payPalButton = "//label[@data-atest='atest_order_pay_elem_pay_pall']"; 
	public static String creditCardButton = "//label[@data-atest='atest_order_pay_elem_credit_card']";
	public static String makeDepositButton = "//button[@data-atest='atest_order_pay_elem_make_deposit_btn']";
	 //order pay for easy bidding
	public static String reserveMoney = "//button[text()='Reserve money']";

     public void choosePayPal(){
    	 WebElement pay_pal_button = Helper.cyclicElementSearchByXpath(payPalButton);
    	 pay_pal_button.click();
	
}
	public void chooseCardPay(){
		WebElement credit_card_button = Helper.cyclicElementSearchByXpath(creditCardButton);
		credit_card_button.click();
	}
	public void clickReserveButton() {
		WebElement make_deposit_button = Helper.cyclicElementSearchByXpath(makeDepositButton);
		make_deposit_button.click();

	}
	//for easy bidding
	public void reserveMoney(){
		WebElement reserve_button = Helper.cyclicElementSearchByXpath(reserveMoney);
		reserve_button.click();
	}
}
