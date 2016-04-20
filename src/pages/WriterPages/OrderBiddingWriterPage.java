package pages.WriterPages;

import org.openqa.selenium.WebElement;

import utils.Helper;



public class OrderBiddingWriterPage  {
	
	
	
	
	public static String bidPrice = "//input[@data-atest='atest_order_view_writer_bidding_form_bid_value']";
	public static String applyButton = "//input[@data-atest='atest_order_view_writer_bidding_form_submit']";
	public static String removeButton = "//button[@data-atest='atest_order_view_writer_bidding_elem_remove_btn']";
	public static String changeButton = "//button[@data-atest='atest_order_view_writer_bidding_elem_change_btn']";
	
	//public static String bidAmount = "//input[@data-atest='atest_order_view_writer_bidding_form_bid_value']";
	
	//easy bidding
	public static String easyBiddingapplyButton = "//input[@value='Apply for this order']";
	
	public void easyBiddingApplyprice(){
		WebElement easy_bidding_apply_button = Helper.cyclicElementSearchByXpath(easyBiddingapplyButton);
		easy_bidding_apply_button.click();
	}

	public void setPrice(String strBidPrice) {
		WebElement bid_price = Helper.cyclicElementSearchByXpath(bidPrice);
		bid_price.clear();
		bid_price.sendKeys(strBidPrice);
	}

	public void clickApply() {
		WebElement apply_button = Helper.cyclicElementSearchByXpath(applyButton);
		apply_button.click();
	}

	public void createBid(String strBidPrice) {
		this.setPrice(strBidPrice);
		this.clickApply();

	}
	
	public void removeBid(){
		WebElement remove_button = Helper.cyclicElementSearchByXpath(removeButton);
		remove_button.click();
	}
    public void changeBid(){
	WebElement change_button = Helper.cyclicElementSearchByXpath(changeButton);
	change_button.click();
}
	
	
}
