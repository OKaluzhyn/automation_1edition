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
	public static String typeOfPaper = "//div[@id='order_product_type-styler']";
	public static String next_button = "//div[@id='step-1']//button";
	public static String number = "//input[@id='order_product_sources']";
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
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
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
				//рандомно выбрать элемент из дропдауна - метод рабочий
				WebElement openDropdownTypes = Helper.cyclicElementSearchByXpath(typeOfPaper);
				openDropdownTypes.click();
				List<WebElement> types =  Helper.driver.findElements(By.xpath("//select[@id='order_product_type']/option[@value]"));
				// select a random one
				Random random = new Random();
				WebElement someType = types.get(random.nextInt(types.size()));
				someType.click();
				openDropdownTypes.click();
				
				WebElement num = Helper.cyclicElementSearchByXpath(number);
						num.sendKeys("5");
Helper.sleep(5);
				//link.select();
	}
	
		@After
	public void theEnd(){
			Helper.quit();
				
		}
	}
