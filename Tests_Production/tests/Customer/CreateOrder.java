package tests.Customer;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

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


public class CreateOrder {

	public String customerReleasedPercent;
	public String writerReleasedPercent;

@Before
public void setUp(){
	Helper.driverSetUp("http://edusson.com");

}

@After
public void tearDown() {
	
	Helper.quit();
}

@Test
public  void orderCreate() throws Exception {
	Helper.driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	Helper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	Helper.sleep(1);
	// логинимся клиентом
			userAuthorizationPage.changeUser(Config.customer1, Config.password);
			Helper.sleep(1);
			// берем урл страницы заказа из переменной и переходим по нему
			Helper.driver.get(orderUrl);
			
	        //Helper.sleep(2);
	        // Helper.driver.get("http://edusson.com/order/view/74178"); //- для быстрого теста оплаты
			Helper.sleep(2);
			// выбираем бид первого писателя
			orderBiddingCustomerPage.bid1();
			// подтвержаем бид, переходим на страницу оплаты
			orderPayCustomerPage.choosePayPal();
			Helper.sleep(2);
			orderPayCustomerPage.clickReserveButton();
			//переключаемся на frame на странице пейпала
			Helper.sleep(1);
		//	Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		//	Helper.sleep(1);
			// логинимся в PayPall и подтвержаем оплату
			payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
			//payPalPage.clickContinue();
			// ждем возвращения на сайт
			Helper.sleep(30);
			//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		    Helper.sleep(2);
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
//загружаем ревизию
	orderInProgressPage.uploadRevision();
	Helper.sleep(2);
	// разлогиниваемся писателем
	headerMenu.userLogOut();
	// логинимся клиентом
	userAuthorizationPage.changeUser(Config.customer1, Config.password);
	Helper.sleep(2);
	// берем урл страницы заказа из переменной и переходим по нему
	Helper.driver.get(orderUrl);
// возвращаемся на страницу заказа
//orderPayThankYouCustomerPage.returnOrderPage();
//Helper.driver.get(orderUrl);
    // релизим писателю 10%
    orderInProgressPage.releaseMoney("10");
 // получаем занчение % релизнутых денег на странице клиента
 //customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
//разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//закрываем райтерский попап
			Helper.sleep(2);
			myOrdersWriterPage.closePopup();
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		//writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// сравниваем значения релизнутых денег у клиента и у писателя
		//assertEquals(customerReleasedPercent, writerReleasedPercent);
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		Helper.sleep(2);
		// логинимся клиентом
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// релизим писателю 90%
		orderInProgressPage.releaseMoney("90");
		Helper.sleep(2);
		orderFinishedViewPage.closePopup();
		// получаем занчение % релизнутых денег на странице клиента
	//	customerReleasedPercent = ((OrderInProgressPage) orderInProgressPage).checkReleasedMoney();
		assertTrue(orderFinishedViewPage.check());
		Helper.sleep(2);
		headerMenu.userLogOut();
	// логинимся писателем
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//закрываем райтерский попап
			Helper.sleep(2);
			myOrdersWriterPage.closePopup();
				// берем урл страницы заказа из переменной и переходим по нему
					Helper.driver.get(orderUrl);
					/*userAuthorizationPage.logIn(Config.customer1, Config.password);
			Helper.sleep(2);
			Helper.driver.get("http://edusson.com/order/view/74593");
				// получаем занчение % релизнутых денег на странице писателя
			//	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
				// сравниваем значения релизнутых денег у клиента и у писателя
			//	assertEquals(customerReleasedPercent, writerReleasedPercent);
				// проверяем наличие текста order finished 
			*/	
					System.out.println(Helper.driver.getTitle());
					assertTrue(orderFinishedViewPage.check());
					//orderFinishedViewPage.finished();
				Helper.sleep(2);
				
				
				headerMenu.userLogOut();
				System.out.println(Helper.driver.getTitle());
				System.out.println("TEST PASSED");

}}






	


