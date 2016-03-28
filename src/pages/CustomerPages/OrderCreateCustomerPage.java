package pages.CustomerPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;




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
	public static String paperInstruction = "//textarea[@data-atest='atest_order_create_form_description']";
	public static String uploadFiles = "//*[@id='dropzone']/div/span[2]/a";
	
	public static String vas1 = "//label[@data-atest='atest_order_create_elem_vas_1']";
	public static String startBiddingButton = "//button[@data-atest='atest_order_create_form_submit']";
	
	//order edit
	public static String saveChangesButton = "//button[@data-atest='atest_order_create_form_submit']";
	public static String discardChangesButton = "//a[@data-atest='atest_order_edit_elem_discard_changes_btn']";
	public static String cancelOrderButton = "//a[@data-popup-target='popup_customer_order_cancel']";

	// заполняем обязательные поля ордер формы
	// select type
	public void selectTypeOfPaper() {
		Helper.randomChoiceFromDropdown(typeOfPaper, "//select[@id='order_product_type']/option[@value]");
		
	}

	// order topic
	public void setTopic(String strTopic) {
		WebElement order_topic = Helper.cyclicElementSearchByXpath(topic);
		order_topic.sendKeys(strTopic);
	}

	// select subject
	public void selectSubject() {
		Helper.randomChoiceFromDropdown(subject, "//select[@data-atest='atest_order_create_form_subject']/option[@value]");
		
	}

	// click next button1
	public void clickNext1() {
		WebElement next_button_1 = Helper.cyclicElementSearchByXpath(nextButton1);
		next_button_1.click();
	}

	// number jf citation
	public void setnumOfCitation() {
		WebElement citation_number = Helper.cyclicElementSearchByXpath(numOfCitation);
				citation_number.sendKeys("3");
	}

	// format of citation
	public void selectformatOfCitation() {
		Helper.randomChoiceFromDropdown(formatOfCitation, "//select[@data-atest='atest_order_create_form_style']/option[@value]");
				
	}

	// click next button2
	public void clickNext2() {
		WebElement next_button_2 = Helper.cyclicElementSearchByXpath(nextButton2);
		next_button_2.click();
	}

	// set paper description
	public void orderDescription(String strDescription) {
		WebElement paper_description = 	Helper.cyclicElementSearchByXpath(paperInstruction);
		Helper.sleep(1);
		
				paper_description.sendKeys(strDescription);
	}

	
	
	// загрузка файлов
	  public  void setClipboardData(String path) {
        StringSelection stringSelection = new StringSelection("C:\\Users\\s.mankut\\Google Диск\\files for download testing\\.....pdf");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void attachFile(String path) {
        setClipboardData("path");
        try {
        	Robot robot = new Robot();
    		robot.delay(1000);
    		robot.keyPress(KeyEvent.VK_CONTROL);
    		robot.delay(300);
    		robot.keyPress(KeyEvent.VK_V);
    		robot.delay(300);
    		robot.keyRelease(KeyEvent.VK_V);
    		robot.delay(300);
    		robot.keyRelease(KeyEvent.VK_CONTROL);
    		robot.delay(300);
    		robot.keyPress(KeyEvent.VK_ENTER);
    		robot.delay(300);
    		robot.keyRelease(KeyEvent.VK_ENTER);
    		robot.delay(300);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    

	public void clicUpload() {
		Helper.cyclicElementSearchByXpath(uploadFiles).click();
		//this.setClipboardData("D:\\AUTO_TESTING\\testFiles\\upload_to_webdriver.txt");
		this.attachFile("C:\\Users\\s.mankut\\Google Диск\\files for download testing\\.....pdf");
	}

	

	// click start bidding button
	public void proceedToBidding() {
		WebElement bidding_button = Helper.cyclicElementSearchByXpath(startBiddingButton);
				bidding_button.click();
	}

	// создание заказа полностью
	public void createOrder(String strTopic, String strDescription)
			 {
		this.selectTypeOfPaper();
		this.setTopic(strTopic);
		this.selectSubject();
		this.clickNext1();
		this.setnumOfCitation();
		this.selectformatOfCitation();
		Helper.sleep(1);
		this.clickNext2();
		Helper.sleep(1);
		this.orderDescription(strDescription);
		Helper.sleep(1);
		this.clicUpload();
		Helper.sleep(7);
		this.proceedToBidding();
	}
	public void saveChanges(){
		WebElement save_changes_button = Helper.cyclicElementSearchByXpath(saveChangesButton);
		save_changes_button.click();
	}
	
	public void discardChanges(){
		WebElement discard_changes_button = Helper.cyclicElementSearchByXpath(discardChangesButton);
		discard_changes_button.click();
	}
	
	public void clickCancelOrderButton(){
		WebElement cancel_order_button = Helper.cyclicElementSearchByXpath(cancelOrderButton);
		cancel_order_button.click();
	}
	
	
	public void editOrder (String strNewTopic){
		this.setTopic(strNewTopic);
		this.saveChanges();
		
	}
	
	
	
}
