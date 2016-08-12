package ua.qa.edusson.tests;


import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.OrderInProgressPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestStandartCheckEdussonProduction extends TestBase{
	

	public String orderUrl;
	public String orderId;
	public String customerReleasedPercent;
	public String writerReleasedPercent;
	public String writerUrl;
	
	// ������������� �������
			UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
			MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
			OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
			OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
			OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
			HeaderMenu headerMenu = new HeaderMenu();
			MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
			OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
			PayPalPage payPalPage = new PayPalPage();
			//OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
			OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
			OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();


	@Test
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 10%+90%
	
	public void standartCheck_PAyPal_Production_Edusson() throws Exception {
		app.getHelper().goToEdusson();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// assert bidding page
		//assertEquals("Edusson.com - Place your Order", Helper.driver.getTitle());
		//assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//������� �������� ����� �������� ������
		app.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    String orderUrl = app.driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
		// ��������������� ���������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
				
		Helper.sleep(2);
		// Helper.driver.get("http://edusson.com/order/view/74178"); //- ��� �������� ����� ������
	   // Helper.sleep(2);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		// ����������� ���, ��������� �� �������� ������
		//orderPayCustomerPage.choosePayPal();
		Helper.sleep(2);
		orderPayCustomerPage.clickReserveButton();
		//������������� �� frame �� �������� �������
		//Helper.sleep(1);
		//	Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		Helper.sleep(1);
		// ��������� � PayPall � ����������� ������
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		//payPalPage.clickContinue();
		// ���� ����������� �� ����
		//Helper.sleep(30);
		//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		//Helper.sleep(2);
		// ��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		//����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
	    //��������� �������
		
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		// ��������������� ���������
		Helper.sleep(2);
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
	    // ������� �������� 10%
	    orderInProgressPage.releaseMoney("10");
	    // �������� �������� % ���������� ����� �� �������� �������
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	    //��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		//Thread.sleep(5000);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������������� ���������
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// ������� �������� 90%
		orderInProgressPage.releaseMoney("90");
		Helper.sleep(2);
		orderFinishedViewPage.closePopup();
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		app.driver.get(orderUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//��������� ������� ������ order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		headerMenu.userLogOut();
		System.out.println("TEST PASSED");

	}
/*
	// � ������� �� ������� 0, ������ ������ ����� CreditCard, ����� ��������
	// 50%+50%
	public void standartCheck_CreditCard_Production_Edusson() throws Exception {
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		HeaderMenu headerMenu = new HeaderMenu();
		CreditCardPayment creditCardPayment = new CreditCardPayment();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
//for (int i=0; i<=10; i++){
		Helper.goToEdusson();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
	    Helper.sleep(2);
		// create order
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// assert bidding page
		//assertEquals("Edusson.com - Place your Order", Helper.driver.getTitle());
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
		Helper.sleep(1);
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(1);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		//Helper.sleep(2);
		// Helper.driver.get("http://edusson.com/order/view/74178"); //- ��� �������� ����� ������
		Helper.sleep(2);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		Helper.sleep(2);
		// �������� ������ � ������� �����
		orderPayCustomerPage.chooseCardPay();
		Helper.sleep(20);
		orderPayCustomerPage.clickReserveButton();
		Helper.sleep(2);
		// ��������� � ���������� ������� �����
		�reditCardPayment.setAllFields();
		Helper.sleep(60);
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
	    //��������� �������
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		// ��������������� ���������
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
	    // ������� �������� 10%
	    orderInProgressPage.releaseMoney("50");
	    // �������� �������� % ���������� ����� �� �������� �������
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	    //��������������� ��������
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������������� ���������
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� �������� 90%
		orderInProgressPage.releaseMoney("50");
		Helper.sleep(2);
		orderFinishedViewPage.closePopup();
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		headerMenu.userLogOut();
		Helper.sleep(2);
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//��������� ������� ������ order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		headerMenu.userLogOut();
		System.out.println("TEST PASSED");

	}
	*/
	}

	