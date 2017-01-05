package ua.qa.edusson.pages.CommonPages;

import static ua.qa.edusson.tests.tools.TestBase.app;

public class OrderCancelViewPage {

	public static String cancelTxt = "//span[@data-atest='atest_order_view_canceled_elem_notify']";


public boolean isCancelTextPresent() {
	  
	    return app.getHelper().isElementPresent(cancelTxt);
	    

}

}