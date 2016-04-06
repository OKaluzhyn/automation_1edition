package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.OrderInProgressPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.CreditCardPayment;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
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
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		PayPalPage payPalPage = new PayPalPage();
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		HeaderMenu headerMenu = new HeaderMenu();
		Helper.driver.get("http://edusson.com");
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// ������� �����
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// ���� ������ �������� �������-��������
		Thread.sleep(5000);
		// ��������� �������� ������, ����� �������� ���������� ���
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
		orderUrl = Helper.driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("8");
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		// ����������� ���, ��������� �� �������� ������
		orderPayCustomerPage.choosePayPal();
		orderPayCustomerPage.clickReserveButton();
		// ���������� ������ ����� PayPall
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		// ��������������� ��������
				headerMenu.userLogOut();
				// ��������� ���������
				userAuthorizationPage.logIn(Config.writer1, Config.password);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
		//��������� �������
				orderInProgressPage.uploadRevision();
				Helper.sleep(2);
				// ��������������� ���������
				headerMenu.userLogOut();
				// ��������� ��������
				userAuthorizationPage.logIn(Config.customer1, Config.password);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
		// ������������ �� �������� ������
		//orderPayThankYouCustomerPage.returnOrderPage();
		//Helper.driver.get(orderUrl);
		// ������� �������� 10%
		orderInProgressPage.releaseMoney("10");
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� �������� 90%
		orderInProgressPage.releaseMoney("90");
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ��������� ������� ������ order finished
		orderFinishedViewPage.checkOrderFinished();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
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
	// � ������� �� ������� 0, ������ ������ ����� CreditCard, ����� ��������
	// 50%+50%
	public void standartCheck_CreditCard_Production() throws Exception {
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
		HeaderMenu headerMenu = new HeaderMenu();
		CreditCardPayment �reditCardPayment = new CreditCardPayment();
//for (int i=0; i<=10; i++){
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
		// ������� �����
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// ���� ������ �������� �������-��������
		Thread.sleep(5000);
		// ��������� �������� ������, ����� �������� ���������� ���
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
		orderUrl = Helper.driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("5");
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// accept ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		// �������� ������ � ������� �����
		orderPayCustomerPage.chooseCardPay();
		orderPayCustomerPage.clickReserveButton();
		// ��������� � ���������� ������� �����
		�reditCardPayment.setAllFields();
		// ������������ �� �������� ������
		orderPayThankYouCustomerPage.returnOrderPage();
		// ������� �������� 10%
		orderInProgressPage.releaseMoney("50");
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� �������� 90%
		orderInProgressPage.releaseMoney("50");
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ��������� ������� ������ order finished
		orderFinishedViewPage.checkOrderFinished();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Thread.sleep(5000);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������� ������� ������ order finished 
		orderFinishedViewPage.checkOrderFinished();
		// ��������������� ���������
		headerMenu.userLogOut();
		System.out.println("TEST PASSED");
	}
	}
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