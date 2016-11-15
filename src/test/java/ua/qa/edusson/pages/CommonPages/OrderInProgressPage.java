package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static ua.qa.edusson.tests.TestBase.app;


public class OrderInProgressPage  {
		
	
	public static String releaseMoneyfield = "//input[@data-atest='atest_order_view_in_progress_elem_release_money_percent']";
	public static String releaseButton = "//a[@data-atest='atest_order_view_in_progress_elem_release_money_btn']";
	public static String popUpButtonRelease = "//button[@data-atest='atest_order_view_in_progress_elem_popup_release_confirm']";
	public static String payCustomerPercent = "//span[@data-atest='atest_order_view_in_progress_elem_paid_progress']";//������� ���������� �����
	public static String reassignButton = "//a[@data-atest='atest_order_view_in_progress_elem_request_another_writer']";

	//for writer
	public static String workResults = "//li[@data-atest='atest_order_view_writer_in_progress_elem_tab_work_results']";
	public static String inputRevision = "//input[@type='file']";
	public static String payWriterPercent = "//span[@data-atest='atest_order_view_writer_in_progress_elem_paid_progress']";//������� ���������� �����
	
	public void uploadRevision(){
		WebElement work_results = app.getHelper().cyclicElementSearchByXpath(workResults);
		work_results.click();
		app.getHelper().attachFile2(By.xpath(inputRevision), app.getHelper().getRevision().getAbsolutePath());
		app.getHelper().WaitElement("//a[@class='file-name-link']");
		String fileName = app.getHelper().cyclicElementSearchByXpath("//a[@class='file-name-link']").getText();
		System.out.println(fileName);
		Assert.assertEquals(fileName, "testFile.pdf");
		
	}
    

  	public String checkReleasedMoneyCustomerPage(){
		return  app.getHelper().cyclicElementSearchByXpath(payCustomerPercent)
  		.getAttribute("value");

  	}
	

  	public String checkReleasedMoneyWriterPage(){
		return  app.getHelper().cyclicElementSearchByXpath(payWriterPercent)
  		.getAttribute("value");

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
		app.getHelper().WaitElement(popUpButtonRelease);
		WebElement popup_button_release = app.getHelper().cyclicElementSearchByXpath(popUpButtonRelease);
		popup_button_release.click();
			}
	
	public void releaseMoney(String strPercent){
		this.setCompensationPercent(strPercent);
		this.clickReleaseButton();
		this.clickConfirmButton();
	}

	public void clickReassignButton() {
		app.getHelper().cyclicElementSearchByXpath(reassignButton).click();
	}
}
