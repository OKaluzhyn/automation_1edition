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
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;


public class testDeposit {
	public FirefoxDriver driver;

	public String orderUrl;
	public String customerReleasedPercent;
	public String writerReleasedPercent;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.get("http://edusson.com.test2");

	}

	@After
	public void tearDown() {
		driver.quit();
	}
	 public static void main(String[] args) {
	 }
	 
	@Test
	// у клиента на балансе 0, оплата заказа через PayPall, релиз пистаелю
	// 10%+90%
	public void standartCheck_PAyPal_Stage1() throws Exception {
		// инициализация страниц
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage(driver);
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(driver);
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(driver);
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage(driver);
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage(driver);
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage(driver);
		HeaderMenu headerMenu = new HeaderMenu(driver);
//for (int i=0; i<=10; i++){
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.c, Config.password);
		// создаем заказ
		orderCreateCustomerPage.createOrder("test", "test");
		// ждем полной загрузки биддинг-страницы
		Thread.sleep(5000);
		// обновляем страницу заказа, чтобы получить правильный урл
		driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
		orderUrl = driver.getCurrentUrl();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrl);
		// создаем бид
		orderBiddingWriterPage.createBid("10");
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.c,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrl);
		// выбираем бид первого писателя
		orderBiddingCustomerPage.bid1();
		// подтвержаем бид, переходим на страницу оплаты
		orderPayCustomerPage.clickReserveButton();
		// оплачиваем заказа через PayPall
		payPalPage.confirmPayPal(Config.paypall_login,Config.paypall_pass);
		Thread.sleep(5000);
		// возвращаемся на страницу заказа
		orderPayThankYouCustomerPage.returnOrderPage();
		// релизим писателю 10%
		orderInProgressPage.releaseMoney("10");
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrl);
		Thread.sleep(5000);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.c,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrl);
		// релизим писателю 90%
		orderInProgressPage.releaseMoney("90");
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// проверяем наличие текста order finished
		orderFinishedViewPage.checkOrderFinished();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrl);
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
	//}
}}
