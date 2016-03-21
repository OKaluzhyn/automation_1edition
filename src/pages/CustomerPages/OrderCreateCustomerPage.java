package pages.CustomerPages;

import java.awt.AWTException;

import utils.Helper;



public class OrderCreateCustomerPage  {
	
	// инициализация элементов страницы
	
	public static String orderPageName = "//html/body/div[6]/span/span/div/div[1]/div/h1";
	public static String typeOfPaper = "//div[@data-atest='atest_order_create_form_type']";
	public static String topic = "//input[@data-atest='atest_order_create_form_name']";
	public static String subject = "//div[@data-atest='atest_order_create_form_subject']";
	public static String numberOfPages = "//input[@data-atest='atest_order_create_form_pages']";
	public static String deadline = "//input[@data-atest='atest_order_create_form_deadline']";
	public static String nextButton1 = "//div[@id='step-1']//button[@data-atest='atest_order_create_elem_next_btn']";
	
	//public static String typeOfService = "//*[@id='step-2']/div/div[1]/label[1]/button";
	//public static String writerLevel = "//*[@id='step-2']/div/div[2]/label[3]/span";
	public static String numOfCitation = "//input[@data-atest='atest_order_create_form_sources']";
	public static String formatOfCitation = "//div[@data-atest='atest_order_create_form_style']";
	public static String nextButton2 = "//div[@id='step-2']//button[@data-atest='atest_order_create_elem_next_btn']";
	public static String paperInstruction = "//input[@data-atest='atest_order_create_form_description']";
	public static String uploadFiles = "//*[@id='dropzone']/div/span[2]/a";
	
	public static String vas1 = "//label[@data-atest='atest_order_create_elem_vas_1']";
	public static String startBiddingButton = "//button[@data-atest='atest_order_create_form_submit']";

	// заполняем обязательные поля ордер формы
	// select type
	public void selectTypeOfPaper() {
		Helper.randomChoiceFromDropdown(typeOfPaper, "//select[@id='order_product_type']/option[@value]");
		
	}

	// order topic
	public void setTopic(String strTopic) {
		Helper.cyclicElementSearchByXpath(topic).sendKeys(strTopic);
	}

	// select subject
	public void selectSubject() {
		Helper.randomChoiceFromDropdown(subject, "//select[@data-atest='atest_order_create_form_subject']/option[@value]");
		
	}

	// click next button1
	public void clickNext1() {
		Helper.cyclicElementSearchByXpath(nextButton1).click();
	}

	// number jf citation
	public void setnumOfCitation() {
		Helper.cyclicElementSearchByXpath(numOfCitation).sendKeys("5");
	}

	// format of citation
	public void selectformatOfCitation() {
		Helper.randomChoiceFromDropdown(formatOfCitation, "//select[@data-atest='atest_order_create_form_style']/option[@value]");
				
	}

	// click next button2
	public void clickNext2() {
		Helper.cyclicElementSearchByXpath(nextButton2).click();
	}

	// set paper description
	public void orderDescription(String strDescription) {
		Helper.cyclicElementSearchByXpath(paperInstruction).sendKeys(strDescription);
	}

	/*// загрузка файлов

	// не работает попытка использовать js
	public void upload() {
		WebElement element = (WebElement) driver
				.executeScript("getElementById('order_additional_materials').style.display = 'inline'");
		element = driver.findElement(input);
		element.sendKeys("D:\\AUTO_TESTING\\testFiles\\upload_to_webdriver.txt");
	}

	// end
*/
	// просто клик на загрузку без самой загрузки

	public void clicUpload() {
		Helper.cyclicElementSearchByXpath(uploadFiles).click();
	}

	

	// click start bidding button
	public void proceedToBidding() {
		Helper.cyclicElementSearchByXpath(startBiddingButton).click();
	}

	// создание заказа полностью
	public void createOrder(String strTopic, String strDescription)
			throws InterruptedException, AWTException {
		this.selectTypeOfPaper();
		this.setTopic(strTopic);
		this.selectSubject();
		this.clickNext1();
		this.setnumOfCitation();
		this.selectformatOfCitation();
		this.clickNext2();
		this.orderDescription(strDescription);
		Thread.sleep(1000);
		//*upload file
		this.proceedToBidding();
	}
}
