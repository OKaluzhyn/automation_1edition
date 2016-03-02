package pages.CustomerPages;

import java.awt.AWTException;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Helper;



public class OrderCreateCustomerPage  {
	
	// инициализация элементов страницы
	
	public static String orderPageName = "//html/body/div[6]/span/span/div/div[1]/div/h1";
	public static String typeOfPaper = "//select[@id='order_product_type']";
	public static String topic = "order_name";
	public static String subject = "order_product_subject";
	public static String numberOfPages = "order_product_pages";
	public static String deadline = "order_deadline";
	public static String nextButton1 = "//*[@id='step-1']/div/div[6]/button";
	public static String typeOfService = "//*[@id='step-2']/div/div[1]/label[1]/button";
	public static String writerLevel = "//*[@id='step-2']/div/div[2]/label[3]/span";
	public static String numOfCitation = "order_product_sources";
	public static String formatOfCitation = "order_product_style";
	public static String nextButton2 = "//*[@id='step-2']/div/div[5]/button";
	public static String paperInstruction = "order_description";
	public static String uploadFiles = "//*[@id='dropzone']/div/span[2]/a";
	public static String input = "order_additional_materials___name__";
	public static String nextButton3 = "//div[contains (@id, 'step-3')]//button";
	public static String biddingButton = "//*[@id='step-3']/div/div[4]/button";

	// заполняем обязательные поля ордер формы
	// select type
	/*public void selectType() {
		new Select(Helper.cyclicElementSearchByXpath(typeOfPaper));
		List <WebElement> options = select.getOptions();
		int count = options.size();
		int randomIndex = getRandom(1, count);
		options.get(randomIndex-1).select();
				//.selectByVisibleText("Essay (Any Type)");
	}

	// order topic
	public void setTopic(String strTopic) {
		driver.findElement(topic).sendKeys(strTopic);
	}

	// select subject
	public void selectSubject() {
		new Select(driver.findElement(subject))
				.selectByVisibleText("Accounting");
	}

	// click next button1
	public void clickNext1() {
		driver.findElement(nextButton1).click();
	}

	// number jf citation
	public void setnumOfCitation() {
		driver.findElement(numOfCitation).sendKeys("5");
	}

	// format of citation
	public void selectformatOfCitation() {
		new Select(driver.findElement(formatOfCitation))
				.selectByVisibleText("Chicago/Turabian");
	}

	// click next button2
	public void clickNext2() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions
				.visibilityOfElementLocated(nextButton2));
		driver.findElement(nextButton2).click();
	}

	// set paper description
	public void orderDescription(String strDescription) {
		driver.findElement(paperInstruction).sendKeys(strDescription);
	}

	// загрузка файлов

	// не работает попытка использовать js
	public void upload() {
		WebElement element = (WebElement) driver
				.executeScript("getElementById('order_additional_materials').style.display = 'inline'");
		element = driver.findElement(input);
		element.sendKeys("D:\\AUTO_TESTING\\testFiles\\upload_to_webdriver.txt");
	}

	// end

	// просто клик на загрузку без самой загрузки

	public void clicUpload() {
		driver.findElement(uploadFiles).click();
	}

	//

	// click next button2
	public void clickNext3() {
		driver.findElement(nextButton3).click();
	}

	// click start bidding button
	public void proceedToBidding() {
		driver.findElement(biddingButton).click();
	}

	// создание заказа полностью
	public void createOrder(String strTopic, String strDescription)
			throws InterruptedException, AWTException {
		this.selectType();
		this.setTopic(strTopic);
		this.selectSubject();
		this.clickNext1();
		this.setnumOfCitation();
		this.selectformatOfCitation();
		this.clickNext2();
		this.orderDescription(strDescription);
		Thread.sleep(1000);
		//*upload file
		this.clickNext3();
		this.proceedToBidding();
	}*/
}
