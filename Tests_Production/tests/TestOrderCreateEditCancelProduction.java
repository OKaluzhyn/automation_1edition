package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import utils.Config;
import utils.Helper;

public class TestOrderCreateEditCancelProduction {
	

	public String orderUrlForEdition;

	@Before
	public void setUp(){
		Helper.driverSetUp("http://edusson.com.test18/");

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
		//OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(driver);
		//AttentionBeforOrderEditingCustomerPopUp attentionBeforOrderEditingCustomerPopUp = new AttentionBeforOrderEditingCustomerPopUp(driver);
		//OrderSummaryCustomerPopUp orderSummaryCustomerPopUp = new OrderSummaryCustomerPopUp(driver);
		//HeaderMenu headerMenu = new HeaderMenu();
		//OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(driver);

		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		//go to order form
		myOrdersCustomerPage.makeNewOrder();
		// create order
		//orderCreateCustomerPage.createOrder("test for webdriver", "test");
		
		orderCreateCustomerPage.selectTypeOfPaper();
		orderCreateCustomerPage.setTopic("test");
		orderCreateCustomerPage.selectSubject();
		orderCreateCustomerPage.clickNext1();
		orderCreateCustomerPage.setnumOfCitation();
		orderCreateCustomerPage.selectformatOfCitation();
		Helper.sleep(1);
		orderCreateCustomerPage.clickNext2();
		orderCreateCustomerPage.orderDescription("test for webdriver");
		Helper.sleep(1);
		orderCreateCustomerPage.proceedToBidding();
		Thread.sleep(5000);
		
		
		// assert bidding page
		/*
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

	}*/

	// cancel order
	}
}