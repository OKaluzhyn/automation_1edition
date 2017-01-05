package ua.qa.edusson.pages.WriterPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class OrderBiddingWriterPage  {
	
	
	
	
	public static String bidPrice = "//input[@data-atest='atest_order_view_writer_bidding_form_bid_value']";
	public static String applyButton = "//input[@data-atest='atest_order_view_writer_bidding_form_submit']";
	public static String removeButton = "//button[@data-atest='atest_order_view_writer_bidding_elem_remove_btn']";
	public static String changeButton = "//button[@data-atest='atest_order_view_writer_bidding_elem_change_btn']";
	public static String viewOrderButton = "//button[text()='View order']";
	public static String wonBidPopUp = "//div[@class='modal-content']/div[contains(text(),' Your bid has won!')]";
	//public static String bidAmount = "//input[@data-atest='atest_order_view_writer_bidding_form_bid_value']";
	
	//easy bidding
	public static String easyBiddingapplyButton = "//input[@value='Apply for this order']";
	
	public void easyBiddingApplyprice(){
		app.getHelper().waitElement(easyBiddingapplyButton);
		WebElement easy_bidding_apply_button = app.getHelper().cyclicElementSearchByXpath(easyBiddingapplyButton);
		easy_bidding_apply_button.click();
	}

	public void setPrice(String strBidPrice) {
		WebElement bid_price =app.getHelper().cyclicElementSearchByXpath(bidPrice);
		bid_price.clear();
		bid_price.sendKeys(strBidPrice);
	}

	public void clickApply() {
		WebElement apply_button = app.getHelper().cyclicElementSearchByXpath(applyButton);
		apply_button.click();
	}

	public void createBid(String strBidPrice) {
		this.setPrice(strBidPrice);
		this.clickApply();

	}
	
	public void removeBid(){
		WebElement remove_button = app.getHelper().cyclicElementSearchByXpath(removeButton);
		remove_button.click();
	}
    public void changeBid(){
	WebElement change_button = app.getHelper().cyclicElementSearchByXpath(changeButton);
	change_button.click();
}


    public void goToOrder() {
    	app.getHelper().waitElement(wonBidPopUp);
    	WebElement view_order_button = app.getHelper().cyclicElementSearchByXpath(viewOrderButton);
		view_order_button.click();
    }
}
