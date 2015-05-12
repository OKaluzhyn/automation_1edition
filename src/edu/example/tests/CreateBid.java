package edu.example.tests;


	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AllOrders;
import edu.pages.AuthPage;
import edu.pages.CreateBidPage;
import edu.pages.MyOrdersWriter;

	public class CreateBid {
	static FirefoxDriver driver;
	static edu.pages.AuthPage objLogin;
	static edu.pages.MyOrdersWriter objMyOrdersWriter;
	static edu.pages.CreateBidPage objCreateBid;
	static edu.pages.AllOrders objChooseOrder;
	



	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}
	  


	@Test

	public void createBid()throws Exception{
		//использовать метод авторизация
		objLogin = new AuthPage(driver);
		objLogin.loginToAuthPage("Write.23.02@i.ua", "402438");
		objMyOrdersWriter = new MyOrdersWriter (driver);
		// открыть страницу доступных заказов
		driver.get("http://edusson.com/writer/orders/available");
		//выбрать заказ
		objChooseOrder = new AllOrders(driver);
		objChooseOrder.chooseOrder();
		//сщздать бид на выбранный заказ
		objCreateBid = new CreateBidPage(driver);
		objCreateBid.createBid("8");
		//проверить, что бид создан
		objCreateBid.asseertBid();
		
		
	}
		@After
		  public void tearDown() {
		    driver.quit();
		  }
		
		

	}




