package tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderSummaryCustomerPopUp;
import pages.CustomerPages.AttentionBeforOrderEditingCustomerPopUp;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;
import utils.Helper;

public class TestOrderCreateEditCancelProduction {
	public FirefoxDriver driver;

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
	public void test() throws Exception {
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(driver);
		AttentionBeforOrderEditingCustomerPopUp attentionBeforOrderEditingCustomerPopUp = new AttentionBeforOrderEditingCustomerPopUp(driver);
		OrderSummaryCustomerPopUp orderSummaryCustomerPopUp = new OrderSummaryCustomerPopUp(driver);
		HeaderMenu headerMenu = new HeaderMenu();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(driver);

		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		//go to order form
		myOrdersCustomerPage.makeNewOrder();
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
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrlForEdition);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); // ����������� ��� - ������, ������� ��� -
										// ������
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������

		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrlForEdition);
		// ��������� ������� ����
		orderBiddingCustomerPage.isBidPresent();
		// ������� ������ �������������� ������
		orderBiddingCustomerPage.clickEdit();
		// apply pop-up info
		attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
		// ������������� �� �����
		WebElement iframe = driver.findElement(orderSummaryCustomerPopUp.PopUpEditOrder);
		driver.switchTo().frame(iframe);
		// ����������� �����
		orderSummaryCustomerPopUp.editOrder(null, null);
		// ���������, ��� ��� �������� �������� ������������
		// ��������� ���������, ��� ����� ������ ��� ����������

	}

	// cancel order

}