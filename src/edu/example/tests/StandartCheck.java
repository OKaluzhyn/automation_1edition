package edu.example.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.CustomerPages.BiddingPage;
import edu.pages.CustomerPages.InProgressCustPage;
import edu.pages.CustomerPages.MyOrdersCustomer;
import edu.pages.CustomerPages.OrderFinishedCustPage;
import edu.pages.CustomerPages.OrderPage;
import edu.pages.CustomerPages.OrderPayPage;
import edu.pages.CustomerPages.PayPalPage;
import edu.pages.CustomerPages.ThankYouPage;
import edu.pages.WriterPages.AllOrders;
import edu.pages.WriterPages.CreateBidPage;

public class StandartCheck {
	FirefoxDriver driver;
	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	  
	}
	//create order
	@Test
	public void check1() throws Exception{
		
		//логинимся клиентом
		AuthPage cust = new AuthPage(driver);
		  cust.loginAsCustomer1(null,null);
		  
		//создаем заказ
		  OrderPage order1 = new OrderPage(driver);
			order1.createOrder("test for webdriver","test");
	}
			//create bid
			@Test
			//логинимся райтером
		public void check2() throws Exception{
			
			AuthPage write = new AuthPage(driver);
						  write.loginAsWriter1(null,null);
			  
			  //открываем страницу доступных заказов
			  driver.get("http://edusson.com/writer/orders/available");
			  
			 	//выбираем заказ
			  AllOrders wOrders = new AllOrders(driver);
				wOrders.chooseOrder();
				
				//создаем бид на выбранный заказ
				CreateBidPage wBid = new CreateBidPage(driver);
				wBid.createBid("8");
			}
			//approve bid & pay order & release money
			@Test
				public void check3() throws Exception{				
				//логинимся клиентом
				
				AuthPage cust = new AuthPage(driver);
								  cust.loginAsCustomer1(null,null);
								  
								//переходим к нужному заказу
									driver.get("http://edusson.com/customer/orders");
									MyOrdersCustomer cOrders = new MyOrdersCustomer(driver);
									cOrders.goToOrder();
								  
								//выбираем бид
								  BiddingPage choose = new BiddingPage(driver);
									choose.bid1();
									
									//появилась страница оплаты
									OrderPayPage page = new OrderPayPage(driver);
									//
									page.clickReserveButton();
									
									//open paypall page
									PayPalPage pp = new PayPalPage(driver);
									pp.confirmPayPal("edussonpay2@ukr.net", "ghbdtndctv12");
									//assert ThankYouPage
									ThankYouPage successPay = new ThankYouPage(driver);
																		
									successPay.returnOrderPage();
									
									//release money
									InProgressCustPage myOrder = new InProgressCustPage(driver);
									myOrder.releaseMoney("10");
									myOrder.releaseMoney("90");
									//assert order is finished
									OrderFinishedCustPage finished = new OrderFinishedCustPage(driver);
									finished.getOrderFinishedCustPageDashboardName();
									
									
								  
									
								  
			
			
	}

	
	@After
	  public void tearDown() {
	    driver.quit();
	  }
}