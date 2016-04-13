package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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

public class Base {
	public String orderUrl;
	public String orderId;
	public String writerUrl;
	
	public String customerReleasedPercent;
	public String writerReleasedPercent;
	
	
	public static String login="//a[@data-atest='atest_login_elem_popup_open']";
	public static String typeOfPaper = "//div[@data-atest='atest_order_create_form_type']";
	public static String next_button = "//div[@id='step-1']//button[@data-atest='atest_order_create_elem_next_btn']";
	public static String nextButton2 = "//div[@id='step-2']//button";
	public static String number = "//input[@id='order_product_sources']";
	public static String paperInstruction = "//input[@data-atest='atest_order_create_form_description']";
	@Before
	public void setUp() throws Exception {
		Helper.driverSetUp("http://edusson.com.test8/");
	}
	
	@Test
	public void urlTest2(){
		Helper.goToEdubirdie();
		System.out.println(Helper.driver.getCurrentUrl());
		Helper.goToEdusson();
		System.out.println(Helper.driver.getCurrentUrl());
	}
	
	@Test
	public void urlTest(){
		UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
		MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
		OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
		MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
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
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    orderUrl = Helper.driver.getCurrentUrl();
	   	orderId = orderUrl.substring(35);
	   	System.out.println(orderId);
	   	Helper.goToEdusson();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		writerUrl = "http://edusson.com.test9/order/view"+orderId;
		System.out.println(writerUrl);
		Helper.driver.get(writerUrl);
	}
	@Test
	
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 10%+90%
	
	public void standartCheck_PAyPal_Production_Edusson() throws Exception {
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
		//assertEquals("Edusson.com - Place your Order", Helper.driver.getTitle());
		//assertTrue(driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//������� �������� ����� �������� ������
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    orderUrl = Helper.driver.getCurrentUrl();
	    orderId = orderUrl.substring(35);
	    writerUrl = "http://edusson.com.test9/order/view"+orderId;
		// ��������������� ��������
		//headerMenu.userLogOut();
	    Helper.sleep(1);
		Helper.goToEdusson();
		
		
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.logIn(Config.auto_writer_1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		writerUrl = "http://edusson.com.test9/order/view"+orderId;
		Helper.driver.get(writerUrl);
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
		// ��������������� ���������
	//	headerMenu.userLogOut();
		Helper.goToEdubirdie();
		Helper.sleep(1);
		// ��������� ��������
		//userAuthorizationPage.changeUser(Config.customer1, Config.password);
		//Helper.sleep(1);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
				
		Helper.sleep(2);
		// Helper.driver.get("http://edusson.com/order/view/74178"); //- ��� �������� ����� ������
	   // Helper.sleep(2);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		// ����������� ���, ��������� �� �������� ������
		orderPayCustomerPage.choosePayPal();
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
		Helper.sleep(2);
		// ��������������� ��������
		//headerMenu.userLogOut();
		Helper.goToEdusson();
		Helper.sleep(1);
		// ��������� ���������
		//userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//��������� ���������� �����
		//Helper.sleep(2);
		//myOrdersWriterPage.closePopup();
		//����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
	    //��������� �������
		
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		// ��������������� ���������
		//Helper.sleep(2);
		//headerMenu.userLogOut();
		Helper.goToEdubirdie();
		Helper.sleep(2);
		// ��������� ��������
		//userAuthorizationPage.changeUser(Config.customer1, Config.password);
		//Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
	    // ������� �������� 10%
	    orderInProgressPage.releaseMoney("10");
	    // �������� �������� % ���������� ����� �� �������� �������
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	    //��������������� ��������
		//headerMenu.userLogOut();
		Helper.goToEdusson();
		Helper.sleep(2);
		// ��������� ���������
		//userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//��������� ���������� �����
		//Helper.sleep(2);
		//myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		//Thread.sleep(5000);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		// ��������������� ���������
		//headerMenu.userLogOut();
		Helper.goToEdubirdie();
		Helper.sleep(2);
		// ��������� ��������
		//userAuthorizationPage.changeUser(Config.customer1, Config.password);
		//Helper.sleep(2);
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
		//headerMenu.userLogOut();
		Helper.goToEdusson();
		Helper.sleep(2);
		// ��������� ���������
		//userAuthorizationPage.changeUser(Config.auto_writer_1, Config.password);
		//��������� ���������� �����
		//Helper.sleep(2);
		//myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//��������� ������� ������ order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		//headerMenu.userLogOut();
		System.out.println("TEST PASSED");

	}

		@After
	public void theEnd(){
			Helper.quit();
				
		}
	}
