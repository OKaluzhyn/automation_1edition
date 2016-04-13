package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.OrderInProgressPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.WriterPages.MyOrdersWriterPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;
import utils.Helper;

public class Base {
	public String orderUrl;
	public String orderId;
	public String writerUrl;
	
	public String customerReleasedPercent;
	public String writerReleasedPercent;
	
	
	public static String login="//a[@data-atest='atest_login_elem_popup_open']";
	public static String typeOfPaper = "//div[@data-atest='atest_order_create_form_type']";
	public static String next_button = "//div[@id='step-1']//button[@data-atest='atest_order_create_elem_next_btn']";
	public static String nextButton2 = "//div[@id='step-2']//button";
	public static String number = "//input[@id='order_product_sources']";
	public static String paperInstruction = "//input[@data-atest='atest_order_create_form_description']";
	@Before
	public void setUp() throws Exception {
		Helper.driverSetUp("http://edusson.com.test8/");
	}
	
	@Test
	public void urlTest2(){
		Helper.goToEdubirdie();
		System.out.println(Helper.driver.getCurrentUrl());
		Helper.goToEdusson();
		System.out.println(Helper.driver.getCurrentUrl());
	}
	
	@Test
	public void urlTest(){
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// assert bidding page
		//assertEquals("Edusson.com - Place your Order", Helper.driver.getTitle());
		//assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//релодим страницу чтобы получить ссылку
		Helper.driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
	    orderUrl = Helper.driver.getCurrentUrl();
	   	orderId = orderUrl.substring(35);
	   	System.out.println(orderId);
	   	Helper.goToEdusson();
		Helper.sleep(1);
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		writerUrl = "http://edusson.com.test9/order/view"+orderId;
		System.out.println(writerUrl);
		Helper.driver.get(writerUrl);
	}
	@Test
	
	// у клиента на балансе 0, оплата заказа через PayPall, релиз пистаелю
	// 10%+90%
	
	public void standartCheck_PAyPal_Production_Edusson() throws Exception {
		// инициализация страниц
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		HeaderMenu headerMenu = new HeaderMenu();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		PayPalPage payPalPage = new PayPalPage();
		//OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// assert bidding page
		//assertEquals("Edusson.com - Place your Order", Helper.driver.getTitle());
		//assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//релодим страницу чтобы получить ссылку
		Helper.driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
	    orderUrl = Helper.driver.getCurrentUrl();
	    orderId = orderUrl.substring(35);
	    writerUrl = "http://edusson.com.test9/order/view"+orderId;
		// разлогиниваемся клиентом
		//headerMenu.userLogOut();
	    Helper.sleep(1);
		Helper.goToEdusson();
		
		
		Helper.sleep(1);
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		writerUrl = "http://edusson.com.test9/order/view"+orderId;
		Helper.driver.get(writerUrl);
		// создаем бид
		orderBiddingWriterPage.createBid("6"); 
		// разлогиниваемся писателем
	//	headerMenu.userLogOut();
		Helper.goToEdubirdie();
		Helper.sleep(1);
		// логинимся клиентом
		//userAuthorizationPage.changeUser(Config.customer1, Config.password);
		//Helper.sleep(1);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
				
		Helper.sleep(2);
		// Helper.driver.get("http://edusson.com/order/view/74178"); //- для быстрого теста оплаты
	   // Helper.sleep(2);
		// выбираем бид первого писателя
		orderBiddingCustomerPage.bid1();
		// подтвержаем бид, переходим на страницу оплаты
		orderPayCustomerPage.choosePayPal();
		Helper.sleep(2);
		orderPayCustomerPage.clickReserveButton();
		//переключаемся на frame на странице пейпала
		//Helper.sleep(1);
		//	Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		Helper.sleep(1);
		// логинимся в PayPall и подтвержаем оплату
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		//payPalPage.clickContinue();
		// ждем возвращения на сайт
		//Helper.sleep(30);
		//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		Helper.sleep(2);
		// разлогиниваемся клиентом
		//headerMenu.userLogOut();
		Helper.goToEdusson();
		Helper.sleep(1);
		// логинимся писателем
		//userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//закрываем райтерский попап
		//Helper.sleep(2);
		//myOrdersWriterPage.closePopup();
		//берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(writerUrl);
	    //загружаем ревизию
		
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		// разлогиниваемся писателем
		//Helper.sleep(2);
		//headerMenu.userLogOut();
		Helper.goToEdubirdie();
		Helper.sleep(2);
		// логинимся клиентом
		//userAuthorizationPage.changeUser(Config.customer1, Config.password);
		//Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
	    // релизим писателю 10%
	    orderInProgressPage.releaseMoney("10");
	    // получаем занчение % релизнутых денег на странице клиента
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	    //разлогиниваемся клиентом
		//headerMenu.userLogOut();
		Helper.goToEdusson();
		Helper.sleep(2);
		// логинимся писателем
		//userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//закрываем райтерский попап
		//Helper.sleep(2);
		//myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(writerUrl);
		//Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// разлогиниваемся писателем
		//headerMenu.userLogOut();
		Helper.goToEdubirdie();
		Helper.sleep(2);
		// логинимся клиентом
		//userAuthorizationPage.changeUser(Config.customer1, Config.password);
		//Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// релизим писателю 90%
		orderInProgressPage.releaseMoney("90");
		Helper.sleep(2);
		orderFinishedViewPage.closePopup();
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		//headerMenu.userLogOut();
		Helper.goToEdusson();
		Helper.sleep(2);
		// логинимся писателем
		//userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//закрываем райтерский попап
		//Helper.sleep(2);
		//myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(writerUrl);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//проверяем наличие текста order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		//headerMenu.userLogOut();
		System.out.println("TEST PASSED");

	}

		@After
	public void theEnd(){
			Helper.quit();
				
		}
	}
