package edu.pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class OrderPage {
 
	FirefoxDriver driver;
	By orderPageName = By.xpath("//html/body/div[6]/span/span/div/div[1]/div/h1");
	By typeOfPaper = By.id("order_product_type");
	By topic = By.id("order_name");
	By subject = By.id("order_product_subject");
	By numberOfPages = By.id("order_product_pages");
	By deadline = By.id("order_deadline" );
	By nextButton1 = By.xpath("//*[@id='step-1']/div/div[6]/button");
	By typeOfService = By.xpath("//*[@id='step-2']/div/div[1]/label[1]/button");
	By writerLevel = By.xpath("//*[@id='step-2']/div/div[2]/label[3]/span");
	By numOfCitation = By.id("order_product_sources");
	By formatOfCitation = By.id("order_product_style");
	By nextButton2 = By.xpath("//*[@id='step-2']/div/div[5]/button");
	By paperInstruction = By.id("order_description");
	By uploadFiles = By.xpath("//*[@id='dropzone']/div/span[2]/a");
	By nextButton3 = By.xpath("//*[@id='step-3']/div/div[3]/button");
	By biddingButton = By.xpath("//*[@id='step-3']/div/div[4]/button");


	public OrderPage(FirefoxDriver driver){
		this.driver = driver;
			
	}
		//заполняем обязательные поля
	//select type
	public void selectType (){
		 new Select(driver.findElement(typeOfPaper)).selectByVisibleText("Essay (Any Type)");
	}
	//order topic
	public void setTopic(String strTopic){
		driver.findElement(topic).sendKeys(strTopic);
	
	}
	//select subject
	public void selectSubject(){
		new Select(driver.findElement(subject)).selectByVisibleText("Accounting");
	}
	//click next button
	public void clickNext1(){
		driver.findElement(nextButton1).click();
	}
	public void setnumOfCitation(){
		driver.findElement(numOfCitation).sendKeys("5");
	}
	
	
	public void selectformatOfCitation(){
		new Select(driver.findElement(formatOfCitation)).selectByVisibleText("Chicago/Turabian");
	}
	
	
	public void clickNext2(){
		(new WebDriverWait(driver, 30))
		                .until(ExpectedConditions.visibilityOfElementLocated(nextButton2 ));	
		driver.findElement(nextButton2).click();
	}
	
	//описание заказа
	public void orderDescription(String strDescription){
		driver.findElement(paperInstruction).sendKeys(strDescription);
	}
	
	public void upload(){
		driver.findElement(uploadFiles).click();
	}
	
	public void clickNext3(){
		driver.findElement(nextButton3).click();
	}
	public void proceedToBidding(){
		driver.findElement(biddingButton).click();
	}
	
	
	public void createOrder(String strTopic, String strDescription) {
		this.selectType();
		this.setTopic(strTopic);
		this.selectSubject();
		this.clickNext1();
		this.setnumOfCitation();
		this.selectformatOfCitation();
		this.clickNext2();
		this.orderDescription(strDescription);
		this.upload();
		this.clickNext3();
		this.proceedToBidding();
	
		
	}
	
	//Get the Page name from Home Page
	public String getOrderPageDashboardName(){
		 return	driver.findElement(orderPageName).getText();
		}
}