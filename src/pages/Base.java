package pages;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import utils.Config;
import utils.Helper;

public class Base {
	
	
	public static String login="//a[@data-atest='atest_login_elem_popup_open']";
	public static String typeOfPaper = "//div[@data-atest='atest_order_create_form_type']";
	public static String next_button = "//div[@id='step-1']//button[@data-atest='atest_order_create_elem_next_btn']";
	public static String nextButton2 = "//div[@id='step-2']//button";
	public static String number = "//input[@id='order_product_sources']";
	public static String paperInstruction = "//input[@data-atest='atest_order_create_form_description']";
	@Before
	public void setUp() throws Exception {
		Helper.driverSetUp("http://edusson.com.test18/");
	}
	@Test
	public void auth(){
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		if (Helper.driver.getTitle().equals("Edusson.com - My Orders")){
		
			System.out.println("Test passed");
		}
	else {
		System.out.println("Title does not match");
	}
	}

	@Test
	public  void someVoid(){
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
	//	OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		// логинимся клиентом
				userAuthorizationPage.logIn(Config.customer1, Config.password);
				//go to order form
				myOrdersCustomerPage.makeNewOrder();
				//new Select(Helper.cyclicElementSearchByXpath(typeOfPaper));
			//	List <WebElement> options = select.getOptions();
				//int count = options.size();
			//	int randomIndex = getRandom(1, count);
				//options.get(randomIndex-1).select();
				// get all links by xpath
				WebElement buttonNext = Helper.cyclicElementSearchByXpath(next_button);
				buttonNext.click();
				/*рандомно выбрать элемент из дропдауна - метод рабочий
				WebElement openDropdownTypes = Helper.cyclicElementSearchByXpath(typeOfPaper);
				openDropdownTypes.click();
				List<WebElement> types =  Helper.driver.findElements(By.xpath("//select[@id='order_product_type']/option[@value]"));
				// select a random one
				Random random = new Random();
				WebElement someType = types.get(random.nextInt(types.size()));
				someType.click();
				openDropdownTypes.click();
				*/
				Helper.randomChoiceFromDropdown(typeOfPaper, "//select[@id='order_product_type']/option[@value]");
				WebElement num = Helper.cyclicElementSearchByXpath(number);
						num.sendKeys("5");
						Helper.sleep(1);
                WebElement button2 = Helper.cyclicElementSearchByXpath(nextButton2);
                        button2.click();
                       
                       
                        
                             
                             WebElement input = Helper.cyclicElementSearchByXpath(paperInstruction);
                             input.click();//link.select();
	}
	
	public void randomChoice(String arg, String xpath){
		WebElement openDropdownTypes = Helper.cyclicElementSearchByXpath(arg);
		openDropdownTypes.click();
		List<WebElement> types =  Helper.driver.findElements(By.xpath(xpath));
		// select a random one
		Random random = new Random();
		WebElement someType = types.get(random.nextInt(types.size()));
		someType.click();
		openDropdownTypes.click();
	}
	
		@After
	public void theEnd(){
			Helper.quit();
				
		}
	}
