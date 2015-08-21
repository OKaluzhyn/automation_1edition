import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.CreditCardPayment;
import pages.CustomerPages.MyBalanceCustomerPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderInProgressPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.OrderPayThankYouCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;

public class TestStandartCheckStage1 {
	public FirefoxDriver driver;

	public String orderUrl;
	public String customerReleasedPercent;
	public String writerReleasedPercent;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.get("http://edusson.com.stage1");

	}

	@After
	public void tearDown() {
		driver.quit();
	}
	 public static void main(String[] args) {
	 }
	 
	@Test
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 10%+90%
	public void standartCheck_PAyPal_Stage1() throws Exception {
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
//for (int i=0; i<=10; i++){
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
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
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("5");
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		// ����������� ���, ��������� �� �������� ������
		orderPayCustomerPage.clickReserveButton();
		// ���������� ������ ����� PayPall
		payPalPage.confirmPayPal(Config.paypall_login,Config.paypall_pass);
		Thread.sleep(5000);
		// ������������ �� �������� ������
		orderPayThankYouCustomerPage.returnOrderPage();
		// ������� �������� 10%
		orderInProgressPage.releaseMoney("10");
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ��������������� ��������
		headerMenu.userLogOut();
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
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
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
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
		userAuthorizationPage.logIn(Config.auto_writer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
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
	//}

}
	@Test
	// � ������� �� ������� 0, ������ ������ ����� CreditCard, ����� ��������
	// 50%+50%
	public void standartCheck_CreditCard_Stage1() throws Exception {
		// ������������� �������
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage(driver);
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage(driver);
		OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage(driver);
		OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage(driver);
		OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage(driver);
		OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage(driver);
		OrderInProgressPage orderInProgressPage = new OrderInProgressPage(driver);
		OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage(driver);
		HeaderMenu headerMenu = new HeaderMenu(driver);
		CreditCardPayment �reditCardPayment = new CreditCardPayment(driver);
//for (int i=0; i<=10; i++){
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1, Config.password);
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
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("5");
		// ��������������� ���������
		headerMenu.userLogOut();
		// ��������� ��������
		userAuthorizationPage.logIn(Config.auto_customer_1,Config.password);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		driver.get(orderUrl);
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
		driver.get(orderUrl);
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
		driver.get(orderUrl);
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
		driver.get(orderUrl);
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
	//}
	}

			
			}