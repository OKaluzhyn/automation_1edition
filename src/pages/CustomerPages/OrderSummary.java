package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderSummary extends OrderPage{

public OrderSummary(FirefoxDriver driver) {
		super(driver);
}		

By discard = By.xpath(".//*[@id='order']/div[2]/div[2]/button[1]");
By save = By.xpath(".//*[@id='order']/div[2]/div[2]/button[2]");
By cancelLink = By.xpath(".//*[@id='order']/div[2]/div[1]/span/a");


public void openCancelPopUp(){
	driver.findElement(cancelLink).click();
}

@Override
public void selectType(){
	new Select(driver.findElement(typeOfPaper)).selectByVisibleText("Other");
	
	}
@Override
public void setTopic(String strTopic){
	driver.findElement(topic).clear();
	driver.findElement(topic).sendKeys("Edited order");
}
@Override
//select subject
public void selectSubject(){
	new Select(driver.findElement(subject)).selectByVisibleText("Art");
}
@Override
public void setnumOfCitation(){
	driver.findElement(numOfCitation).sendKeys("3");
}

@Override
public void selectformatOfCitation(){
	new Select(driver.findElement(formatOfCitation)).selectByVisibleText("MLA");
}

@Override
public void orderDescription(String strDescription){
	driver.findElement(paperInstruction).sendKeys("new description");
}



public void saveChanges(){
	driver.findElement(save).click();
}

public void editOrder(String strTopic, String strDescription){
	this.selectType();
	this.setTopic(strTopic);
	this.selectSubject();
	this.setnumOfCitation();
	this.selectformatOfCitation();
	this.orderDescription(strDescription);
	this.saveChanges();
	
}
}


