package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;
import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class OrderBiddingCustomerPage {


    public static String bid1 = "//div[@data-atest='atest_order_bid_elem_bid_open']";
    public static String acceptBidButton = "//button[@data-atest='atest_order_bid_elem_accept_btn']";
    public static String regectBidButton = "//button[@data-atest='atest_order_bid_elem_reject_btn']";
    public static String confirmPopupButton = "//input[@data-atest='atest_order_popup_bid_elem_accept_ok_btn']";
    public static String editOrderButton = "//button[@data-atest='atest_order_view_bidding_elem_edit_btn']";
    public static String bid = "//div[@data-atest='atest_order_bid_elem_bid_open']";

    public String seopopup(){
        return "//div[@id='popup_exit_bidding']";
    }




    public void chooseBid1() {
        WebElement bid_1 = app.getHelper().cyclicElementSearchByXpath(bid1);
        bid_1.click();

    }

    public void acceptBid1() {
        WebElement accept_button = app.getHelper().cyclicElementSearchByXpath(acceptBidButton);
        accept_button.click();
    }

    public void confirmWriter1() {
        WebElement confirm_popup_button = app.getHelper().cyclicElementSearchByXpath(confirmPopupButton);
        confirm_popup_button.click();
    }

    public void clickEditOrder() {
        WebElement edit_order_button = app.getHelper().cyclicElementSearchByXpath(editOrderButton);
        edit_order_button.click();
    }

    public void bid1() {
        this.acceptBid1();
        Helper.sleep(2);
        this.confirmWriter1();
    }

}