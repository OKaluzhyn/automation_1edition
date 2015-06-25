import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.AttentionBeforOrderEditingCustomerPopUp;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderSummaryCustomerPopUp;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;


public class TestOrderCreateEditCancelStage1 {
	public FirefoxDriver driver;

	public String orderUrlForEdition;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.get("http://edusson.com.stage1");

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void test() throws Exception {
		// инициализация страниц
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage(driver);
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(driver);
		AttentionBeforOrderEditingCustomerPopUp attentionBeforOrderEditingCustomerPopUp = new AttentionBeforOrderEditingCustomerPopUp(driver);
		OrderSummaryCustomerPopUp orderSummaryCustomerPopUp = new OrderSummaryCustomerPopUp(driver);
		HeaderMenu headerMenu = new HeaderMenu(driver);
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(driver);

		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		Thread.sleep(5000);
		// assert bidding page
		assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
		orderUrlForEdition = driver.getCurrentUrl();
		// разлогиниваемся клиентом
		headerMenu.userLogOut();
		// логинимся писателем
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrlForEdition);
		// создаем бид
		orderBiddingWriterPage.createBid("6"); // редактируем бид - ассерт, удаляем бид -
										// ассерт
		// разлогиниваемся писателем
		headerMenu.userLogOut();
		// логинимся клиентом

		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// берем урл страницы заказа из переменной и переходим по нему
		driver.get(orderUrlForEdition);
		// проверяем наличие бида
		orderBiddingCustomerPage.isBidPresent();
		// кликаем кнопку редактирования заказа
		orderBiddingCustomerPage.clickEdit();
		// apply pop-up info
		attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
		// переключаемся на фрейм
		WebElement iframe = driver.findElement(orderSummaryCustomerPopUp.PopUpEditOrder);
		driver.switchTo().frame(iframe);
		// редактируем заказ
		orderSummaryCustomerPopUp.editOrder(null, null);
		// проверяем, что бид писателя перестал отображаться
		// проверяем писателем, что заказ больше ему недоступен

	}

	// cancel order

}

