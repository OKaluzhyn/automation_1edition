package tests.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.OrderInProgressPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.CreditCardPayment;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
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
	Helper.driverSetUp("http://edubirdie.com");

}

@After
public void tearDown() {
	
	Helper.quit();
}

@Test
public  void orderCreate() throws Exception {
	
	// инициализация страниц
	UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
	MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
	OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
	OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
	OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
	HeaderMenu headerMenu = new HeaderMenu();
	MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
	OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
	//PayPalPage payPalPage = new PayPalPage();
	//OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
	OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
	OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
	CreditCardPayment creditCardPayment = new CreditCardPayment();
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
	Helper.sleep(2);
	// выбираем оплату с помощью карты
	orderPayCustomerPage.chooseCardPay();
	orderPayCustomerPage.clickReserveButton();
	Helper.sleep(2);
	// заполняем и отправляем пеймент форму
	creditCardPayment.setAllFields();
	
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
    // релизим писателю 10%
    orderInProgressPage.releaseMoney("10");
    // получаем занчение % релизнутых денег на странице клиента
    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
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
	writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
	// сравниваем значения релизнутых денег у клиента и у писателя
	assertEquals(customerReleasedPercent, writerReleasedPercent);
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
	customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
	Helper.sleep(2);
	headerMenu.userLogOut();
	// логинимся писателем
	userAuthorizationPage.changeUser(Config.writer1, Config.password);
	//закрываем райтерский попап
	Helper.sleep(2);
	myOrdersWriterPage.closePopup();
	// берем урл страницы заказа из переменной и переходим по нему
	Helper.driver.get(orderUrl);
	// получаем занчение % релизнутых денег на странице писателя
	writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
	// сравниваем значения релизнутых денег у клиента и у писателя
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	//проверяем наличие текста order finished 
	assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
	Helper.sleep(2);
	headerMenu.userLogOut();
	System.out.println("TEST PASSED");

}}






	


