package tests.Customer;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pages.CommonPages.HeaderMenu;
import pages.CommonPages.OrderFinishedViewPage;
import pages.CommonPages.OrderInProgressPage;
import pages.CommonPages.UserAuthorizationPage;
import pages.CustomerPages.MyOrdersCustomerPage;
import pages.CustomerPages.OrderBiddingCustomerPage;
import pages.CustomerPages.OrderCreateCustomerPage;
import pages.CustomerPages.OrderPayCustomerPage;
import pages.CustomerPages.PayPalPage;
import pages.WriterPages.MyOrdersWriterPage;
import pages.WriterPages.OrderBiddingWriterPage;
import utils.Config;
import utils.Helper;


public class CreateOrder {

	public String customerReleasedPercent;
	public String writerReleasedPercent;

@Before
public void setUp(){
	Helper.driverSetUp("http://edusson.com");

}

@After
public void tearDown() {
	
	Helper.quit();
}

@Test
public  void orderCreate() throws Exception {
	Helper.driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	Helper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
// ������������ �� �������� ������
//orderPayThankYouCustomerPage.returnOrderPage();
//Helper.driver.get(orderUrl);
    // ������� �������� 10%
    orderInProgressPage.releaseMoney("10");
 // �������� �������� % ���������� ����� �� �������� �������
 //customerReleasedPercent = orderInProgressPage.checkReleasedMoney();
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
		//writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
		// ���������� �������� ���������� ����� � ������� � � ��������
		//assertEquals(customerReleasedPercent, writerReleasedPercent);
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
	//	customerReleasedPercent = ((OrderInProgressPage) orderInProgressPage).checkReleasedMoney();
		assertTrue(orderFinishedViewPage.check());
		Helper.sleep(2);
		headerMenu.userLogOut();
	// ��������� ���������
		userAuthorizationPage.changeUser(Config.writer1, Config.password);
		//��������� ���������� �����
			Helper.sleep(2);
			myOrdersWriterPage.closePopup();
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
					Helper.driver.get(orderUrl);
					/*userAuthorizationPage.logIn(Config.customer1, Config.password);
			Helper.sleep(2);
			Helper.driver.get("http://edusson.com/order/view/74593");
				// �������� �������� % ���������� ����� �� �������� ��������
			//	writerReleasedPercent = orderInProgressPage.checkReleasedMoney();
				// ���������� �������� ���������� ����� � ������� � � ��������
			//	assertEquals(customerReleasedPercent, writerReleasedPercent);
				// ��������� ������� ������ order finished 
			*/	
					System.out.println(Helper.driver.getTitle());
					assertTrue(orderFinishedViewPage.check());
					//orderFinishedViewPage.finished();
				Helper.sleep(2);
				
				
				headerMenu.userLogOut();
				System.out.println(Helper.driver.getTitle());
				System.out.println("TEST PASSED");

}}






	


