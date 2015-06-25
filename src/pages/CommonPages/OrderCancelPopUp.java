package pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderCancelPopUp {
FirefoxDriver driver;

By popUp = By.className("modal-content");
By reason = By.xpath(".//*[@id='cancel_order']/div[2]/ul/label[1]/button");
By otherReason = By.className("js_cancel_reason_other hidden");
By textfield = By.className("cancel_comment");
By apply = By.xpath(".//*[@id='cancel_order']/div[3]/span/input");
By cancel = By.xpath(".//*[@id='cancel_order']/div[3]/span/input");

public OrderCancelPopUp(FirefoxDriver driver){
	this.driver = driver;
}
public void chooseReason(){
	driver.findElement(reason).click();
}
public void inputYourReason(String strreason){
	driver.findElement(otherReason).click();
	driver.findElement(textfield).sendKeys(strreason);
	
}
public void clickApply(){
	driver.findElement(apply).click();
}
public void clickCancel(){
	driver.findElement(cancel).click();
	}
public void cancelOrder(){
	this.chooseReason();
	this.clickApply();
}
public void cancelWithOtherReason(String strreason){
	this.inputYourReason("test reason");
	this.clickApply();
}
}
