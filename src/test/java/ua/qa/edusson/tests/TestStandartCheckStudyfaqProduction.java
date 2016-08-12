package ua.qa.edusson.tests;

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
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

public class TestStandartCheckStudyfaqProduction {
	public String orderUrl;
	public String orderId;
	public String writerUrl;
	
	public String customerReleasedPercent;
	public String writerReleasedPercent;
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
			CreditCardPayment creditCardPayment = new CreditCardPayment();
	
	
	@Before
	public void setUp() throws Exception {
		Helper.driverSetUp();
	}
	
    @Test
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 20%+80%
	
	public void standartCheck_PAyPal_Production_Studyfaq() throws Exception {
    	Helper.goToStudyfaq();;
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		// create order
	    Helper.sleep(1);
		orderCreateCustomerPage.createOrderForStudyfaq("test for webdriver", "test");
		Helper.sleep(1);
		assertTrue(Helper.driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//������� �������� ����� �������� ������
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    orderUrl = Helper.driver.getCurrentUrl();
	    orderId = orderUrl.substring(31);
	    System.out.println(orderId);
	    writerUrl = "http://edusson.com/order/view/"+orderId;
		Helper.sleep(1);
		Helper.goToEdusson();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		Helper.sleep(2);
		System.out.println(Helper.driver.getCurrentUrl());
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
		Helper.sleep(2);
		Helper.goToStudyfaq();
		Helper.sleep(1);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Helper.sleep(2);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		// ����������� ���, ��������� �� �������� ������
		orderPayCustomerPage.chooseCardPay();
		Helper.sleep(2);
		orderPayCustomerPage.clickReserveButton();
		//������������� �� frame �� �������� �������
		//Helper.sleep(1);
		//Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		Helper.sleep(1);
		// ��������� � PayPall � ����������� ������
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		//payPalPage.clickContinue();
		// ���� ����������� �� ����
		//Helper.sleep(30);
		//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		Helper.sleep(2);
		Helper.goToEdusson();
		Helper.sleep(1);
		//����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
	    //��������� �������
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		Helper.goToStudyfaq();
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
	    // ������� �������� 10%
	    orderInProgressPage.releaseMoney("20");
	    // �������� �������� % ���������� ����� �� �������� �������
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	    Helper.goToEdusson();
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		Helper.goToStudyfaq();
		Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� �������� 90%
		orderInProgressPage.releaseMoney("80");
		Helper.sleep(2);
		//orderFinishedViewPage.closePopup();
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		Helper.goToStudyfaq();
		Helper.sleep(2);
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
		 @Test
			// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
			// 20%+80%
			
			public void standartCheck_CreditCard_Production_Studyfaq() throws Exception {
			    Helper.goToStudyfaq();;
				// ��������� ��������
				userAuthorizationPage.logIn(Config.customer1, Config.password);
				Helper.sleep(1);
				//go to order form
			    myOrdersCustomerPage.makeNewOrder();
				// create order
			    Helper.sleep(1);
				orderCreateCustomerPage.createOrderForStudyfaq("test for webdriver", "test");
				assertTrue(Helper.driver.getCurrentUrl().contains("order#redirect_url="));
				Helper.sleep(1);
				//������� �������� ����� �������� ������
				Helper.driver.navigate().refresh();
				// ��������� ��� �������� �������� ������ � ����������
			    orderUrl = Helper.driver.getCurrentUrl();
			    orderId = orderUrl.substring(31);
			    System.out.println(orderId);
			    writerUrl = "http://edusson.com/order/view/"+orderId;
				Helper.sleep(1);
				Helper.goToEdusson();
				Helper.sleep(1);
				// ��������� ���������
				userAuthorizationPage.logIn(Config.writer1, Config.password);
				//��������� ���������� �����
				Helper.sleep(2);
				myOrdersWriterPage.closePopup();
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
				Helper.sleep(2);
				System.out.println(Helper.driver.getCurrentUrl());
				// ������� ���
				orderBiddingWriterPage.createBid("6"); 
				Helper.sleep(2);
				Helper.goToStudyfaq();
				Helper.sleep(1);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
				Helper.sleep(2);
				// �������� ��� ������� ��������
				orderBiddingCustomerPage.bid1();
				// ����������� ���, ��������� �� �������� ������
				orderPayCustomerPage.choosePayPal();
				Helper.sleep(2);
				orderPayCustomerPage.clickReserveButton();
				creditCardPayment.setAllFields();
				Helper.sleep(60);
				Helper.goToEdusson();
				Helper.sleep(1);
				//����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
			    //��������� �������
				orderInProgressPage.uploadRevision();
				Helper.sleep(2);
				Helper.goToStudyfaq();
				Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
			    // ������� �������� 10%
			    orderInProgressPage.releaseMoney("20");
			    // �������� �������� % ���������� ����� �� �������� �������
			    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
			    Helper.goToEdusson();
				Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
				// �������� �������� % ���������� ����� �� �������� ��������
				writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
				// ���������� �������� ���������� ����� � ������� � � ��������
				assertEquals(customerReleasedPercent, writerReleasedPercent);
				Helper.goToStudyfaq();
				Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
				// ������� �������� 90%
				orderInProgressPage.releaseMoney("80");
				Helper.sleep(2);
				//orderFinishedViewPage.closePopup();
				// �������� �������� % ���������� ����� �� �������� �������
				customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
				assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
				Helper.sleep(2);
				Helper.goToStudyfaq();
				Helper.sleep(2);
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
}
