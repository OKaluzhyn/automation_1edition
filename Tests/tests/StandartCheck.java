package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.CustomerPages.BiddingPage;
import pages.CustomerPages.InProgressCustPage;
import pages.CustomerPages.MyOrdersCustomer;
import pages.CustomerPages.OrderFinishedCustPage;
import pages.CustomerPages.OrderPage;
import pages.CustomerPages.OrderPayPage;
import pages.CustomerPages.PayPalPage;
import pages.CustomerPages.ThankYouPage;
import pages.WriterPages.AllOrders;
import pages.WriterPages.CreateBidPage;
import utils.Users;

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
		  cust.login(Users.customer1, Users.password);
		  
		//создаем заказ
		  OrderPage order1 = new OrderPage(driver);
			order1.createOrder("test for webdriver","test");
	}
			//create bid
			@Test
			//логинимся райтером
		public void check2() throws Exception{
			
			AuthPage write = new AuthPage(driver);
						  write.login(Users.writer1, Users.password);
			  
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
				cust.login(Users.customer1, Users.password);
								  
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