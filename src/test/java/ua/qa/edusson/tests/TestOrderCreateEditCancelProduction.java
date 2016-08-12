package ua.qa.edusson.tests;

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
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

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
		
		// ������������� �������
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
		// ��������� ��������
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
		//������� �������� ����� �������� ������
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    String orderUrl = Helper.driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
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
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		//����������� ���
		orderBiddingWriterPage.changeBid();
		Helper.sleep(1);
		orderBiddingWriterPage.createBid("8");
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
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		//����� ���������, ��� ���������� ��������� ����
		
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		//������� ���
		orderBiddingWriterPage.removeBid();
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		Helper.sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		//Helper.driver.navigate().refresh();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ��������� ���������� ����
		Helper.sleep(1);
		assertFalse(orderBiddingCustomerPage.isBidPresent());
		//������� ����� ���
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
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
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		// ������� ������ �������������� ������
		orderBiddingCustomerPage.clickEditOrder();
		 Helper.sleep(1);
		// apply pop-up info
	    attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
	    Helper.sleep(1);
	    // ����������� �����
	    orderCreateCustomerPage.editOrder(" edited order");
	    // ���������, ��� ��� �������� �������� ������������
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
		// ��������������� ��������
	 	headerMenu.userLogOut();
	 	Helper.sleep(1);
	 	// ��������� ���������
	 	userAuthorizationPage.changeUser(Config.writer1, Config.password);
	 	//��������� ���������� �����
	 	Helper.sleep(2);
	 	myOrdersWriterPage.closePopup();
	 	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	 	Helper.driver.get(orderUrl);
	 	Helper.sleep(1);
	    // ��������� ���������, ��� ����� ������ ��� ����������
	 	assertEquals("Edusson.com - Order is not available", Helper.driver.getTitle());
	}

	
	}
