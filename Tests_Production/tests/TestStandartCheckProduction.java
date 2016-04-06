package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.OrderInProgressPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.CreditCardPayment;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;
import utils.Helper;

public class TestStandartCheckProduction {
	

	public String orderUrl;
	public String orderId;
	public String customerReleasedPercent;
	public String writerReleasedPercent;
	public String writerUrl;

	@Before
	public void setUp() throws Exception {
		 Helper.driverSetUp("");
		

	}

	@After
	public void tearDown() {
		Helper.quit();
	}

	@Test
	// у клиента на балансе 0, оплата заказа через PayPall, релиз пистаелю
	// 10%+90%
	
	public void standartCheck_PAyPal_Production() throws Exception {
		// инициализация страниц
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		PayPalPage payPalPage = new PayPalPage();
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		HeaderMenu headerMenu = new HeaderMenu();
		Helper.driver.get("http://edusson.com");
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// создаем заказ
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// ждем полной загрузки биддинг-страницы
		Thread.sleep(5000);
		// обновляем страницу заказа, чтобы получить правильный урл
		Helper.driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
		orderUrl = Helper.driver.getCurrentUrl();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// создаем бид
		orderBiddingWriterPage.createBid("8");
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// выбираем бид первого писателя
		orderBiddingCustomerPage.bid1();
		// подтвержаем бид, переходим на страницу оплаты
		orderPayCustomerPage.choosePayPal();
		orderPayCustomerPage.clickReserveButton();
		// оплачиваем заказа через PayPall
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		// разлогиниваемся клиентом
				headerMenu.userLogOut();
				// логинимся писателем
				userAuthorizationPage.logIn(Config.writer1, Config.password);
				// берем урл страницы заказа из переменной и переходим по нему
				Helper.driver.get(orderUrl);
		//загружаем ревизию
				orderInProgressPage.uploadRevision();
				Helper.sleep(2);
				// разлогиниваемся писателем
				headerMenu.userLogOut();
				// логинимся клиентом
				userAuthorizationPage.logIn(Config.customer1, Config.password);
				// берем урл страницы заказа из переменной и переходим по нему
				Helper.driver.get(orderUrl);
		// возвращаемся на страницу заказа
		//orderPayThankYouCustomerPage.returnOrderPage();
		//Helper.driver.get(orderUrl);
		// релизим писателю 10%
		orderInProgressPage.releaseMoney("10");
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// релизим писателю 90%
		orderInProgressPage.releaseMoney("90");
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// проверяем наличие текста order finished
		orderFinishedViewPage.checkOrderFinished();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// проверяем наличие текста order finished 
		orderFinishedViewPage.checkOrderFinished();
		headerMenu.userLogOut();
		System.out.println("TEST PASSED");
	}
	@Test
	// у клиента на балансе 0, оплата заказа через CreditCard, релиз пистаелю
	// 50%+50%
	public void standartCheck_CreditCard_Production() throws Exception {
		// инициализация страниц
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		HeaderMenu headerMenu = new HeaderMenu();
		CreditCardPayment сreditCardPayment = new CreditCardPayment();
//for (int i=0; i<=10; i++){
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
		// создаем заказ
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// ждем полной загрузки биддинг-страницы
		Thread.sleep(5000);
		// обновляем страницу заказа, чтобы получить правильный урл
		Helper.driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
		orderUrl = Helper.driver.getCurrentUrl();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// создаем бид
		orderBiddingWriterPage.createBid("5");
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// accept бид первого писателя
		orderBiddingCustomerPage.bid1();
		// выбираем оплату с помощью карты
		orderPayCustomerPage.chooseCardPay();
		orderPayCustomerPage.clickReserveButton();
		// заполняем и отправляем пеймент форму
		сreditCardPayment.setAllFields();
		// возвращаемся на страницу заказа
		orderPayThankYouCustomerPage.returnOrderPage();
		// релизим писателю 10%
		orderInProgressPage.releaseMoney("50");
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		// релизим писателю 90%
		orderInProgressPage.releaseMoney("50");
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// проверяем наличие текста order finished
		orderFinishedViewPage.checkOrderFinished();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// проверяем наличие текста order finished 
		orderFinishedViewPage.checkOrderFinished();
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		System.out.println("TEST PASSED");
	}
	}
	/*
@Test
//добавить новые локаторы для берди!!!!
public void standartCheck_Edubirdie_Production() throws Exception {
	// инициализация страниц
	UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
	OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
	OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
	OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
	OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
	PayPalPage payPalPage = new PayPalPage();
	OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
	OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
	OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
	HeaderMenu headerMenu = new HeaderMenu();
	.get("http://edubirdie.com");
	// логинимся клиентом
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// создаем заказ
	orderCreateCustomerPage.createOrder("test for webdriver", "test");
	// ждем полной загрузки биддинг-страницы
	Thread.sleep(5000);
	// обновляем страницу заказа, чтобы получить правильный урл
	.navigate().refresh();
	// сохраняем урл страницы текущего заказа в переменную
	orderUrl = .getCurrentUrl();
	orderId = orderUrl.substring(25);
	// разлогиниваемся клиентом
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// логинимся писателем
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	writerUrl = "edusson.com/order/view"+orderId;
	.get(writerUrl);
	// создаем бид
	orderBiddingWriterPage.createBid("8");
	// разлогиниваемся писателем
	headerMenu.userLogOut();
	.get("http://edubirdie.com");
	// логинимся клиентом
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(orderUrl);
	// выбираем бид первого писателя
	orderBiddingCustomerPage.bid1();
	// подтвержаем бид, переходим на страницу оплаты
	orderPayCustomerPage.clickReserveButton();
	// оплачиваем заказа через PayPall
	payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
	// возвращаемся на страницу заказа
	orderPayThankYouCustomerPage.returnOrderPage();
	// релизим писателю 10%
	orderInProgressPage.releaseMoney("10");
	// получаем занчение % релизнутых денег на странице клиента
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// разлогиниваемся клиентом
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// логинимся писателем
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(writerUrl);
	Thread.sleep(5000);
	// получаем занчение % релизнутых денег на странице писателя
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// сравниваем значения релизнутых денег у клиента и у писателя
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// разлогиниваемся писателем
	headerMenu.userLogOut();
	.get("http://edubirdie.com");
	// логинимся клиентом
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(orderUrl);
	// релизим писателю 90%
	orderInProgressPage.releaseMoney("90");
	// получаем занчение % релизнутых денег на странице клиента
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// проверяем наличие текста order finished
	orderFinishedViewPage.checkOrderFinished();
	// разлогиниваемся клиентом
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// логинимся писателем
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(writerUrl);
	Thread.sleep(5000);
	// получаем занчение % релизнутых денег на странице писателя
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// сравниваем значения релизнутых денег у клиента и у писателя
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// проверяем наличие текста order finished 
	orderFinishedViewPage.checkOrderFinished();
	headerMenu.userLogOut();
	System.out.println("TEST PASSED");


}
@Test
//добавить новые локаторы для StudyFaq!!!!
public void standartCheck_StudyFaq_Production() throws Exception {
	// инициализация страниц
	UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
	OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
	OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
	OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
	OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
	PayPalPage payPalPage = new PayPalPage();
	OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
	OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
	OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
	HeaderMenu headerMenu = new HeaderMenu();
	.get("http://studyfaq.com");
	// логинимся клиентом
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// создаем заказ
	orderCreateCustomerPage.createOrder("test for webdriver", "test");
	// ждем полной загрузки биддинг-страницы
	Thread.sleep(5000);
	// обновляем страницу заказа, чтобы получить правильный урл
	.navigate().refresh();
	// сохраняем урл страницы текущего заказа в переменную
	orderUrl = .getCurrentUrl();
	orderId = orderUrl.substring(25);
	// разлогиниваемся клиентом
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// логинимся писателем
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	writerUrl = "edusson.com/order/view"+orderId;
	.get(writerUrl);
	// создаем бид
	orderBiddingWriterPage.createBid("8");
	// разлогиниваемся писателем
	headerMenu.userLogOut();
	.get("http://studyfaq.com");
	// логинимся клиентом
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(orderUrl);
	// выбираем бид первого писателя
	orderBiddingCustomerPage.bid1();
	// подтвержаем бид, переходим на страницу оплаты
	orderPayCustomerPage.clickReserveButton();
	// оплачиваем заказа через PayPall
	payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
	// возвращаемся на страницу заказа
	orderPayThankYouCustomerPage.returnOrderPage();
	// релизим писателю 10%
	orderInProgressPage.releaseMoney("10");
	// получаем занчение % релизнутых денег на странице клиента
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// разлогиниваемся клиентом
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// логинимся писателем
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(writerUrl);
	Thread.sleep(5000);
	// получаем занчение % релизнутых денег на странице писателя
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// сравниваем значения релизнутых денег у клиента и у писателя
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// разлогиниваемся писателем
	headerMenu.userLogOut();
	.get("http://studyfaq.com");
	// логинимся клиентом
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(orderUrl);
	// релизим писателю 90%
	orderInProgressPage.releaseMoney("90");
	// получаем занчение % релизнутых денег на странице клиента
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// проверяем наличие текста order finished
	orderFinishedViewPage.checkOrderFinished();
	// разлогиниваемся клиентом
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// логинимся писателем
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// берем урл страницы заказа из переменной и переходим по нему
	.get(writerUrl);
	Thread.sleep(5000);
	// получаем занчение % релизнутых денег на странице писателя
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// сравниваем значения релизнутых денег у клиента и у писателя
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// проверяем наличие текста order finished 
	orderFinishedViewPage.checkOrderFinished();
	headerMenu.userLogOut();
	System.out.println("TEST PASSED");

}
}
	*/