import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderCancelPopUp;
import pages.CommonPages.OrderCancelViewPage;
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
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(
				driver);
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage(
				driver);
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(
				driver);
		AttentionBeforOrderEditingCustomerPopUp attentionBeforOrderEditingCustomerPopUp = new AttentionBeforOrderEditingCustomerPopUp(
				driver);
		OrderSummaryCustomerPopUp orderSummaryCustomerPopUp = new OrderSummaryCustomerPopUp(
				driver);
		HeaderMenu headerMenu = new HeaderMenu(driver);
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(
				driver);
		OrderCancelPopUp orderCancelPopUp = new OrderCancelPopUp(driver);
		OrderCancelViewPage orderCancelViewPage = new OrderCancelViewPage(
				driver);

		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		Thread.sleep(5000);
		// assert bidding page
		assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
		orderUrlForEdition = driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrlForEdition);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); // ����������� ��� - ������,
												// ������� ��� -
		// ������
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������

		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrlForEdition);
		// ��������� ������� ����
		orderBiddingCustomerPage.isBidPresent();
		// ������� ������ �������������� ������
		orderBiddingCustomerPage.clickEdit();
		// apply pop-up info
		attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
		// ������������� �� �����
		WebElement iframe = driver
				.findElement(orderSummaryCustomerPopUp.PopUpEditOrder);
		driver.switchTo().frame(iframe);
		// ����������� �����
		orderSummaryCustomerPopUp.editOrder(null, null);

		// ���������, ��� ��� �������� �������� ������������ - ��� ��������!!!!!
		//orderBiddingCustomerPage.isBidPresent();
		assertFalse(orderBiddingCustomerPage.isElementPresent());
	
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrlForEdition);
		// ��������� ���������, ��� ����� ������ ��� ����������

		// cancel order
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrlForEdition);
		// ������� ������ �������������� ������
		orderBiddingCustomerPage.clickEdit();
		// apply pop-up info
		attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
		// ������������� �� ����� OrderSummary
		driver.switchTo().frame(iframe);
		// ��������� ����� Cancel Order
		orderSummaryCustomerPopUp.openCancelPopUp();
		// ������������� �� ��������� ����
		driver.switchTo().defaultContent();
		// �������� �����
		orderCancelPopUp.cancelOrder();
		// ��������� ������� ������ Order Cancel �� �������� ������
		orderCancelViewPage.isElementPresent();
	}
}
