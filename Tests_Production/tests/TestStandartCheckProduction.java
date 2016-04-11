package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import pages.CustomerPages.PayPalPage;
import pages.WriterPages.MyOrdersWriterPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;
import utils.Helper;

public class TestStandartCheckProduction {
	

	public String orderUrl;
	public String orderId;
	public String customerReleasedPercent;
	public String writerReleasedPercent;
	public String writerUrl;

	@Before
	public void setUp() throws Exception {
		 Helper.driverSetUp("");
		

	}

	@After
	public void tearDown() {
		Helper.quit();
	}

	@Test
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 10%+90%
	
	public void standartCheck_PAyPal_Production() throws Exception {
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
		// ����������� ���, ��������� �� �������� ������
		orderPayCustomerPage.choosePayPal();
		Helper.sleep(2);
		orderPayCustomerPage.clickReserveButton();
		//������������� �� frame �� �������� �������
		Helper.sleep(1);
		//	Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		//	Helper.sleep(1);
		// ��������� � PayPall � ����������� ������
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		//payPalPage.clickContinue();
		// ���� ����������� �� ����
		Helper.sleep(30);
		//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		Helper.sleep(2);
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
		// ��������� ��������
		userAuthorizationPage.changeUser(Config.customer1, Config.password);
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
	    // ������� �������� 10%
	    orderInProgressPage.releaseMoney("10");
	    // �������� �������� % ���������� ����� �� �������� �������
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	    //��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
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
		orderInProgressPage.releaseMoney("90");
		Helper.sleep(2);
		orderFinishedViewPage.closePopup();
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		headerMenu.userLogOut();
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

	@Test
	// � ������� �� ������� 0, ������ ������ ����� CreditCard, ����� ��������
	// 50%+50%
	public void standartCheck_CreditCard_Production() throws Exception {
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		HeaderMenu headerMenu = new HeaderMenu();
		CreditCardPayment �reditCardPayment = new CreditCardPayment();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
//for (int i=0; i<=10; i++){
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
		
		// �������� ������ � ������� �����
		orderPayCustomerPage.chooseCardPay();
		orderPayCustomerPage.clickReserveButton();
		// ��������� � ���������� ������� �����
		�reditCardPayment.setAllFields();
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
		// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
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

	}}

	/*
@Test
//�������� ����� �������� ��� �����!!!!
public void standartCheck_Edubirdie_Production() throws Exception {
	// ������������� �������
	UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
	OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
	OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
	OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
	OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
	PayPalPage payPalPage = new PayPalPage();
	OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
	OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
	OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
	HeaderMenu headerMenu = new HeaderMenu();
	.get("http://edubirdie.com");
	// ��������� ��������
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// ������� �����
	orderCreateCustomerPage.createOrder("test for webdriver", "test");
	// ���� ������ �������� �������-��������
	Thread.sleep(5000);
	// ��������� �������� ������, ����� �������� ���������� ���
	.navigate().refresh();
	// ��������� ��� �������� �������� ������ � ����������
	orderUrl = .getCurrentUrl();
	orderId = orderUrl.substring(25);
	// ��������������� ��������
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// ��������� ���������
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	writerUrl = "edusson.com/order/view"+orderId;
	.get(writerUrl);
	// ������� ���
	orderBiddingWriterPage.createBid("8");
	// ��������������� ���������
	headerMenu.userLogOut();
	.get("http://edubirdie.com");
	// ��������� ��������
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(orderUrl);
	// �������� ��� ������� ��������
	orderBiddingCustomerPage.bid1();
	// ����������� ���, ��������� �� �������� ������
	orderPayCustomerPage.clickReserveButton();
	// ���������� ������ ����� PayPall
	payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
	// ������������ �� �������� ������
	orderPayThankYouCustomerPage.returnOrderPage();
	// ������� �������� 10%
	orderInProgressPage.releaseMoney("10");
	// �������� �������� % ���������� ����� �� �������� �������
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ��������������� ��������
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// ��������� ���������
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(writerUrl);
	Thread.sleep(5000);
	// �������� �������� % ���������� ����� �� �������� ��������
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ���������� �������� ���������� ����� � ������� � � ��������
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// ��������������� ���������
	headerMenu.userLogOut();
	.get("http://edubirdie.com");
	// ��������� ��������
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(orderUrl);
	// ������� �������� 90%
	orderInProgressPage.releaseMoney("90");
	// �������� �������� % ���������� ����� �� �������� �������
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ��������� ������� ������ order finished
	orderFinishedViewPage.checkOrderFinished();
	// ��������������� ��������
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// ��������� ���������
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(writerUrl);
	Thread.sleep(5000);
	// �������� �������� % ���������� ����� �� �������� ��������
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ���������� �������� ���������� ����� � ������� � � ��������
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// ��������� ������� ������ order finished 
	orderFinishedViewPage.checkOrderFinished();
	headerMenu.userLogOut();
	System.out.println("TEST PASSED");


}
@Test
//�������� ����� �������� ��� StudyFaq!!!!
public void standartCheck_StudyFaq_Production() throws Exception {
	// ������������� �������
	UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
	OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
	OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
	OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
	OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
	PayPalPage payPalPage = new PayPalPage();
	OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
	OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
	OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
	HeaderMenu headerMenu = new HeaderMenu();
	.get("http://studyfaq.com");
	// ��������� ��������
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// ������� �����
	orderCreateCustomerPage.createOrder("test for webdriver", "test");
	// ���� ������ �������� �������-��������
	Thread.sleep(5000);
	// ��������� �������� ������, ����� �������� ���������� ���
	.navigate().refresh();
	// ��������� ��� �������� �������� ������ � ����������
	orderUrl = .getCurrentUrl();
	orderId = orderUrl.substring(25);
	// ��������������� ��������
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// ��������� ���������
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	writerUrl = "edusson.com/order/view"+orderId;
	.get(writerUrl);
	// ������� ���
	orderBiddingWriterPage.createBid("8");
	// ��������������� ���������
	headerMenu.userLogOut();
	.get("http://studyfaq.com");
	// ��������� ��������
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(orderUrl);
	// �������� ��� ������� ��������
	orderBiddingCustomerPage.bid1();
	// ����������� ���, ��������� �� �������� ������
	orderPayCustomerPage.clickReserveButton();
	// ���������� ������ ����� PayPall
	payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
	// ������������ �� �������� ������
	orderPayThankYouCustomerPage.returnOrderPage();
	// ������� �������� 10%
	orderInProgressPage.releaseMoney("10");
	// �������� �������� % ���������� ����� �� �������� �������
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ��������������� ��������
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// ��������� ���������
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(writerUrl);
	Thread.sleep(5000);
	// �������� �������� % ���������� ����� �� �������� ��������
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ���������� �������� ���������� ����� � ������� � � ��������
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// ��������������� ���������
	headerMenu.userLogOut();
	.get("http://studyfaq.com");
	// ��������� ��������
	userAuthorizationPage.logIn(Config.auto_birdie_customer, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(orderUrl);
	// ������� �������� 90%
	orderInProgressPage.releaseMoney("90");
	// �������� �������� % ���������� ����� �� �������� �������
	customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ��������� ������� ������ order finished
	orderFinishedViewPage.checkOrderFinished();
	// ��������������� ��������
	headerMenu.userLogOut();
	.get("http://edusson.com");
	// ��������� ���������
	userAuthorizationPage.logIn(Config.writer1, Config.password);
	// ����� ��� �������� ������ �� ���������� � ��������� �� ����
	.get(writerUrl);
	Thread.sleep(5000);
	// �������� �������� % ���������� ����� �� �������� ��������
	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
	// ���������� �������� ���������� ����� � ������� � � ��������
	assertEquals(customerReleasedPercent, writerReleasedPercent);
	// ��������� ������� ������ order finished 
	orderFinishedViewPage.checkOrderFinished();
	headerMenu.userLogOut();
	System.out.println("TEST PASSED");

}
}
	*/