package pages.WriterPages;

import utils.Helper;

public class WriterProfile {
	
	public static String hireButton = "";
	public String popUpHireWriter = "";
public String createButton = "";
public String applyRequestButton = "";
	public void clickHireButton() {
		Helper.cyclicElementSearchByXpath(hireButton).click();
	}

	public void clickCreateOrder() {
		Helper.cyclicElementSearchByXpath(createButton).click();
		
	}

	public  void applyRequest() {
		Helper.cyclicElementSearchByXpath(applyRequestButton ).click();
	}
	
}