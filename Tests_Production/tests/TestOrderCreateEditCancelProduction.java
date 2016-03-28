package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.CommonPages.HeaderMenu;
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
		Helper.driverSetUp("http://edusson.com.test18/");

	}

	@After
	public void tearDown() {
		Helper.quit();
	}

	@Test
	public void test() throws Exception {
		Helper.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Helper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		HeaderMenu headerMenu = new HeaderMenu();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
		AttentionBeforOrderEditingCustomerPopUp attentionBeforOrderEditingCustomerPopUp = new AttentionBeforOrderEditingCustomerPopUp();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();

		// ��������� ��������
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
		//������� �������� ����� �������� ������
		Helper.driver.navigate().refresh();
		
		// ��������� ��� �������� �������� ������ � ����������
	    String orderUrl = Helper.driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//��������� ���������� �����
		Helper.sleep(1);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); // ����������� ��� - ������, ������� ��� -
										// ������
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		Helper.sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		//Helper.driver.navigate().refresh();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		
		// ��������� ������� ����
		Helper.sleep(1);
		assertTrue(Helper.isElementPresent("//div[@data-atest='atest_order_bid_elem_bid_open']"));
		// ������� ������ �������������� ������
		orderBiddingCustomerPage.clickEditOrder();
		 Helper.sleep(1);
		// apply pop-up info
	    attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
	    Helper.sleep(1);
	    // ����������� �����
	    orderCreateCustomerPage.editOrder("edited order");
	    // ���������, ��� ��� �������� �������� ������������
	    assertFalse(Helper.isElementPresent("//div[@data-atest='atest_order_bid_elem_bid_open']"));
	    //cancel order
	    orderBiddingCustomerPage.clickEditOrder();
	    orderCreateCustomerPage.clickCancelOrderButton();
	    
		
		
		
		
		
		
		// ��������� ���������, ��� ����� ������ ��� ����������
		
	}

	
	}
