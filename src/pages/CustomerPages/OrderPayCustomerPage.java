package pages.CustomerPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderPayCustomerPage  {
	
	public static String payPalButton = "//label[@data-atest='atest_order_pay_elem_pay_pall']"; 
	public static String creditCardButton = "//label[@data-atest='atest_order_pay_elem_credit_card']";
	public static String makeDepositButton = "//button[text()='Make a deposit']";
	 

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
}
