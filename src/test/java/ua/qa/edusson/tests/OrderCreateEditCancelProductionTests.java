package ua.qa.edusson.tests;

import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderCancelPopUp;
import ua.qa.edusson.pages.CommonPages.OrderCancelViewPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.AttentionBeforOrderEditingCustomerPopUp;
import ua.qa.edusson.pages.CustomerPages.MyOrdersCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderBiddingCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderCreateCustomerPage;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.utils.Config;

public class OrderCreateEditCancelProductionTests extends TestBase {
	

	public String orderUrlForEdition;

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
		app.getHelper().goToEdusson();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		app.getHelper().sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		app.getHelper().sleep(1);
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");

		app.getHelper().sleep(10);
		// assert bidding page
	  /*  assertEquals("Edusson.com - Place your Order", app.driver.getTitle());
		//assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		app.getHelper().sleep(1);
		//������� �������� ����� �������� ������
		app.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    String orderUrl = app.driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		app.getHelper().sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		app.getHelper().sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		app.getHelper().sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		app.getHelper().sleep(1);
		//app.driver.navigate().refresh();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ��������� ������� ����
		app.getHelper().sleep(1);
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		// ��������������� ��������
		headerMenu.userLogOut();
		app.getHelper().sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		app.getHelper().sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		//����������� ���
		orderBiddingWriterPage.changeBid();
		app.getHelper().sleep(1);
		orderBiddingWriterPage.createBid("8");
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		app.getHelper().sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		app.getHelper().sleep(1);
		//app.driver.navigate().refresh();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ��������� ������� ����
		app.getHelper().sleep(1);
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		//����� ���������, ��� ���������� ��������� ����
		
		// ��������������� ��������
		headerMenu.userLogOut();
		app.getHelper().sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		app.getHelper().sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		//������� ���
		orderBiddingWriterPage.removeBid();
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		app.getHelper().sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		app.getHelper().sleep(1);
		//app.driver.navigate().refresh();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ��������� ���������� ����
		app.getHelper().sleep(1);
		assertFalse(orderBiddingCustomerPage.isBidPresent());
		//������� ����� ���
		// ��������������� ��������
		headerMenu.userLogOut();
		app.getHelper().sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		app.getHelper().sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		app.getHelper().sleep(1);
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		app.getHelper().sleep(1);
		//app.driver.navigate().refresh();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ��������� ������� ����
		app.getHelper().sleep(1);
		assertTrue(orderBiddingCustomerPage.isBidPresent());
		// ������� ������ �������������� ������
		orderBiddingCustomerPage.clickEditOrder();
		 app.getHelper().sleep(1);
		// apply pop-up info
	    attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
	    app.getHelper().sleep(1);
	    // ����������� �����
	    orderCreateCustomerPage.editOrder(" edited order");
	    // ���������, ��� ��� �������� �������� ������������
	    assertFalse(orderBiddingCustomerPage.isBidPresent());
	    //cancel order
	    orderBiddingCustomerPage.clickEditOrder();
	    app.getHelper().sleep(1);
	    // apply pop-up info
	    attentionBeforOrderEditingCustomerPopUp.applyPopupBeforEditingOrder();
	    app.getHelper().sleep(1);
	    orderCreateCustomerPage.clickCancelOrderButton();
	    app.getHelper().sleep(1);
	    orderCancelPopUp.cancelOrder("testing");
	    app.getHelper().sleep(1);
	    assertTrue(orderCancelViewPage.isCancelTextPresent());
		// ��������������� ��������
	 	headerMenu.userLogOut();
	 	app.getHelper().sleep(1);
	 	// ��������� ���������
	 	userAuthorizationPage.changeUser(Config.writer1, Config.password);
	 	//��������� ���������� �����
	 	app.getHelper().sleep(2);
	 	myOrdersWriterPage.closePopup();
	 	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	 	app.driver.get(orderUrl);
	 	app.getHelper().sleep(1);
	    // ��������� ���������, ��� ����� ������ ��� ����������
	 	assertEquals("Edusson.com - Order is not available", app.driver.getTitle());
	*/}


	}
