package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.WebElement;
import ua.qa.edusson.pages.CustomerPages.OrderCreateCustomerPage;
import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.tests.TestBase.app;


public class OrderInProgressPage  {
		
	
	public static String releaseMoneyfield = "//input[@data-atest='atest_order_view_in_progress_elem_release_money_percent']";
	public static String releaseButton = "//a[@data-atest='atest_order_view_in_progress_elem_release_money_btn']";
	public static String popUpButtonRelease = "//button[@data-atest='atest_order_view_in_progress_elem_popup_release_confirm']";
	public static String payCustomerPercent = "//span[@data-atest='atest_order_view_in_progress_elem_paid_progress']";//������� ���������� �����
	
	//for writer
	public static String workResults = "//li[@data-atest='atest_order_view_writer_in_progress_elem_tab_work_results']";
	public static String uploadLink = "//a[@data-atest='atest_order_view_writer_in_progress_elem_upload_attached_files']";
	public static String payWriterPercent = "//span[@data-atest='atest_order_view_writer_in_progress_elem_paid_progress']";//������� ���������� �����
	
	public void uploadRevision(){
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		WebElement work_results = app.getHelper().cyclicElementSearchByXpath(workResults);
		work_results.click();
		WebElement upload_link = app.getHelper().cyclicElementSearchByXpath(uploadLink);
		upload_link.click();
		app.getHelper().attachFile();
		
		
	}
    
   //�������� �������� ���������� ����� %
  	public String checkReleasedMoneyCustomerPage(){
		app.getHelper().cyclicElementSearchByXpath(payCustomerPercent)
  		.getAttribute("value");
  		  		return ("value");
  	}
	
  //�������� �������� ���������� ����� %
  	public String checkReleasedMoneyWriterPage(){
		app.getHelper().cyclicElementSearchByXpath(payWriterPercent)
  		.getAttribute("value");
  		  		return ("value");
  	}
	
	public void setCompensationPercent(String strPercent){
		WebElement release_money_field = app.getHelper().cyclicElementSearchByXpath(releaseMoneyfield);
		release_money_field.clear();
		release_money_field.sendKeys(strPercent);
			}
	
	public void clickReleaseButton(){
		WebElement release_button = app.getHelper().cyclicElementSearchByXpath(releaseButton);
		release_button.click();
		
	}
	
	public void clickConfirmButton(){
		Helper.sleep(2);
		WebElement popup_button_release = app.getHelper().cyclicElementSearchByXpath(popUpButtonRelease);
		popup_button_release.click();
			}
	
	public void releaseMoney(String strPercent){
		this.setCompensationPercent(strPercent);
		this.clickReleaseButton();
		this.clickConfirmButton();
	}
}
