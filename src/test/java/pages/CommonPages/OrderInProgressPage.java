package pages.CommonPages;

import org.openqa.selenium.WebElement;

import pages.CustomerPages.OrderCreateCustomerPage;
import utils.Helper;


public class OrderInProgressPage  {
		
	
	public static String releaseMoneyfield = "//input[@data-atest='atest_order_view_in_progress_elem_release_money_percent']";
	public static String releaseButton = "//a[@data-atest='atest_order_view_in_progress_elem_release_money_btn']";
	public static String popUpButtonRelease = "//button[@data-atest='atest_order_view_in_progress_elem_popup_release_confirm']";
	public static String payCustomerPercent = "//span[@data-atest='atest_order_view_in_progress_elem_paid_progress']";//процент релизнутых денег
	
	//for writer
	public static String workResults = "//li[@data-atest='atest_order_view_writer_in_progress_elem_tab_work_results']";
	public static String uploadLink = "//a[@data-atest='atest_order_view_writer_in_progress_elem_upload_attached_files']";
	public static String payWriterPercent = "//span[@data-atest='atest_order_view_writer_in_progress_elem_paid_progress']";//процент релизнутых денег
	
	public void uploadRevision(){
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		WebElement work_results = Helper.cyclicElementSearchByXpath(workResults);
		work_results.click();
		WebElement upload_link = Helper.cyclicElementSearchByXpath(uploadLink);
		upload_link.click();
		orderCreateCustomerPage.attachFile("C:\\Users\\tester\\resources\\testFile.pdf");
		
		
	}
    
   //получаем занчение релизнутых денег %
  	public String checkReleasedMoneyCustomerPage(){
  	Helper.cyclicElementSearchByXpath(payCustomerPercent)
  		.getAttribute("value");
  		  		return ("value");
  	}
	
  //получаем занчение релизнутых денег %
  	public String checkReleasedMoneyWriterPage(){
  	Helper.cyclicElementSearchByXpath(payWriterPercent)
  		.getAttribute("value");
  		  		return ("value");
  	}
	
	public void setCompensationPercent(String strPercent){
		WebElement release_money_field = Helper.cyclicElementSearchByXpath(releaseMoneyfield);
		release_money_field.clear();
		release_money_field.sendKeys(strPercent);
			}
	
	public void clickReleaseButton(){
		WebElement release_button = Helper.cyclicElementSearchByXpath(releaseButton);
		release_button.click();
		
	}
	
	public void clickConfirmButton(){
		Helper.sleep(2);
		WebElement popup_button_release = Helper.cyclicElementSearchByXpath(popUpButtonRelease);
		popup_button_release.click();
			}
	
	public void releaseMoney(String strPercent){
		this.setCompensationPercent(strPercent);
		this.clickReleaseButton();
		this.clickConfirmButton();
	}
}
