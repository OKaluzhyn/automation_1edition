package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderCancelPopUp;
import pages.CommonPages.OrderCancelViewPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.AttentionBeforOrderEditingCustomerPopUp;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.WriterPages.MyOrdersWriterPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;
import utils.Helper;

public class TestOrderCreateEditCancelProduction {
	

	public String orderUrlForEdition;

	@Before
	public void setUp(){
		Helper.driverSetUp();

	}

	@After
	public void tearDown() {
		
		Helper.quit();
	}

	@Test
	public  void orderCreateEditCancel_bidCreateEditCancel() throws Exception {
		
		// инициализация страниц
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderCancelPopUp orderCancelPopUp = new OrderCancelPopUp();
		HeaderMenu headerMenu = new HeaderMenu();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
		AttentionBeforOrderEditingCustomerPopUp attentionBeforOrderEditingCustomerPopUp = new AttentionBeforOrderEditingCustomerPopUp();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderCancelViewPage orderCancelViewPage = new OrderCancelViewPage();
		Helper.goToEdusson();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		Helper.sleep(1);
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// assert bidding page
	    assertEquals("Edusson.com - Place your Order", Helper.driver.getTitle());
		//assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//релодим страницу чтобы получить ссылку
		Helper.driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
	    String orderUrl = Helper.driver.getCurrentUrl();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		Helper.sleep(1);
		// логинимся писателем
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// создаем бид
		orderBiddingWriterPage.createBid("6"); 
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		Helper.sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		//Helper.driver.navigate().refresh();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// проверяем наличие бида
		Helper.sleep(1);
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		Helper.sleep(1);
		// логинимся писателем
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		//редактируем бид
		orderBiddingWriterPage.changeBid();
		Helper.sleep(1);
		orderBiddingWriterPage.createBid("8");
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		Helper.sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		//Helper.driver.navigate().refresh();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// проверяем наличие бида
		Helper.sleep(1);
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		//нужно проверить, что изменилась стоимость бида
		
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		Helper.sleep(1);
		// логинимся писателем
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		//удаляем бид
		orderBiddingWriterPage.removeBid();
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		Helper.sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		//Helper.driver.navigate().refresh();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// проверяем отсутствие бида
		Helper.sleep(1);
		assertFalse(orderBiddingCustomerPage.isBidPresent());
		//создаем новый бид
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		Helper.sleep(1);
		// логинимся писателем
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// создаем бид
		orderBiddingWriterPage.createBid("6"); 
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		Helper.sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		//Helper.driver.navigate().refresh();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// проверяем наличие бида
		Helper.sleep(1);
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		// кликаем кнопку редактирования заказа
		orderBiddingCustomerPage.clickEditOrder();
		 Helper.sleep(1);
		// apply pop-up info
	    attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
	    Helper.sleep(1);
	    // редактируем заказ
	    orderCreateCustomerPage.editOrder(" edited order");
	    // проверяем, что бид писателя перестал отображаться
	    assertFalse(orderBiddingCustomerPage.isBidPresent());
	    //cancel order
	    orderBiddingCustomerPage.clickEditOrder();
	    Helper.sleep(1);
	    // apply pop-up info
	    attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
	    Helper.sleep(1);
	    orderCreateCustomerPage.clickCancelOrderButton();
	    Helper.sleep(1);
	    orderCancelPopUp.cancelOrder("testing");
	    Helper.sleep(1);
	    assertTrue(orderCancelViewPage.isCancelTextPresent());
		// разлогиниваемся клиентом
	 	headerMenu.userLogOut();
	 	Helper.sleep(1);
	 	// логинимся писателем
	 	userAuthorizationPage.changeUser(Config.writer1, Config.password);
	 	//закрываем райтерский попап
	 	Helper.sleep(2);
	 	myOrdersWriterPage.closePopup();
	 	// берем урл страницы заказа из переменной и переходим по нему
	 	Helper.driver.get(orderUrl);
	 	Helper.sleep(1);
	    // проверяем писателем, что заказ больше ему недоступен
	 	assertEquals("Edusson.com - Order is not available", Helper.driver.getTitle());
	}

	
	}
