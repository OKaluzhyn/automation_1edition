package edu.example.tests.Writer;


	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;
import edu.pages.WriterPages.AllOrders;
import edu.pages.WriterPages.CreateBidPage;
import edu.pages.WriterPages.MyOrdersWriter;

	public class CreateBid {
	static FirefoxDriver driver;
	static edu.pages.AuthPage objLogin;
	static edu.pages.WriterPages.MyOrdersWriter objMyOrdersWriter;
	static edu.pages.WriterPages.CreateBidPage objCreateBid;
	static edu.pages.WriterPages.AllOrders objChooseOrder;
	



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
		objLogin.loginAsWriter1(null, null);
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




