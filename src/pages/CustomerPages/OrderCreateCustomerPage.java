package pages.CustomerPages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class OrderCreateCustomerPage extends BasePage {
	public  OrderCreateCustomerPage(FirefoxDriver driver) {
		this.driver = driver;
	}
	// инициализация элементов страницы
	By orderPageName = By
			.xpath("//html/body/div[6]/span/span/div/div[1]/div/h1");
	By typeOfPaper = By.xpath(".//*[@id='order_product_type']");
	By topic = By.id("order_name");
	By subject = By.id("order_product_subject");
	By numberOfPages = By.id("order_product_pages");
	By deadline = By.id("order_deadline");
	By nextButton1 = By.xpath("//*[@id='step-1']/div/div[6]/button");
	By typeOfService = By.xpath("//*[@id='step-2']/div/div[1]/label[1]/button");
	By writerLevel = By.xpath("//*[@id='step-2']/div/div[2]/label[3]/span");
	By numOfCitation = By.id("order_product_sources");
	By formatOfCitation = By.id("order_product_style");
	By nextButton2 = By.xpath("//*[@id='step-2']/div/div[5]/button");
	By paperInstruction = By.id("order_description");
	By uploadFiles = By.xpath("//*[@id='dropzone']/div/span[2]/a");
	By input = By.id("order_additional_materials___name__");
	By nextButton3 = By.xpath(".//*[@id='step-3']/div/div[3]/button");
	By biddingButton = By.xpath("//*[@id='step-3']/div/div[4]/button");

	// заполняем обязательные поля ордер формы
	// select type
	public void selectType() {
		new Select(driver.findElement(typeOfPaper))
				.selectByVisibleText("Essay (Any Type)");
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
		this.clicUpload();
		this.clickNext3();
		this.proceedToBidding();
	}
}
