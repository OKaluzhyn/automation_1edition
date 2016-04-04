package pages.CommonPages;

import java.util.NoSuchElementException;

import utils.Helper;

public class OrderCancelViewPage {

	public static String cancelTxt = "//span[@data-atest='atest_order_view_canceled_elem_notify']";


public boolean isElementPresent() {
	  try {
	    Helper.cyclicElementSearchByXpath(cancelTxt);
	    return true;
	  } catch (NoSuchElementException e) {
	    return false;
	  }
	}

}

