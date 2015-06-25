package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderInProgressPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;

public class TestStandartCheckProduction {
	public FirefoxDriver driver;

	public String orderUrl;
	public String customerReleasedPercent;
	public String writerReleasedPercent;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.get("http://edusson.com");

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 10%+90%
	
	public void standartCheckProduction() throws Exception {
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage(driver);
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(driver);
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(driver);
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage(driver);
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage(driver);
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage(driver);
		HeaderMenu headerMenu = new HeaderMenu(driver);

		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// ������� �����
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		// ���� ������ �������� �������-��������
		Thread.sleep(5000);
		// ��������� �������� ������, ����� �������� ���������� ���
		driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
		orderUrl = driver.getCurrentUrl();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("8");
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
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
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
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
		driver.get(orderUrl);
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
		driver.get(orderUrl);
		Thread.sleep(5000);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������� ������� ������ order finished 
		orderFinishedViewPage.checkOrderFinished();
	}

}