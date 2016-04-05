package pages.CustomerPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderBiddingCustomerPage {
		
	
	public static String bid1 = "//div[@data-atest='atest_order_bid_elem_bid_open']";
	public static String acceptBidButton ="//button[@data-atest='atest_order_bid_elem_accept_btn']";
	public static String regectBidButton = "//button[@data-atest='atest_order_bid_elem_reject_btn']";
	public static String confirmPopupButton = "//input[@data-atest='atest_order_popup_bid_elem_accept_ok_btn']";
	public static String editOrderButton = "//button[@data-atest='atest_order_view_bidding_elem_edit_btn']";
	public static String bid = "//div[@data-atest='atest_order_bid_elem_bid_open']";

	public boolean isBidPresent() {
		return Helper.isElementPresent(bid);
		
	}
	
	
	
	

	public void chooseBid1() {
		WebElement bid_1 = Helper.cyclicElementSearchByXpath(bid1);
		bid_1.click();

	}

	public void acceptBid1() {
		WebElement accept_button = Helper.cyclicElementSearchByXpath(acceptBidButton);
		accept_button.click();
	}

	public void confirmWriter1() {
		WebElement confirm_popup_button = Helper.cyclicElementSearchByXpath(confirmPopupButton);
		confirm_popup_button.click();
			}

	public void clickEditOrder() {
		WebElement edit_order_button = Helper.cyclicElementSearchByXpath(editOrderButton);
		edit_order_button.click();
	}

	// выбрать бид первого райтера
	public void bid1() {
		this.chooseBid1();
		this.acceptBid1();
		this.confirmWriter1();
	}

}