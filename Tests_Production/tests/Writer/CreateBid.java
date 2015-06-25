package tests.Writer;


	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CommonPages.UserAuthorizationPage;
import pages.WriterPages.AllAvaliableOrdersWriterPage;
import pages.WriterPages.OrderBiddingWriterPage;
import pages.WriterPages.MyOrdersWriterPage;
import utils.Config;

	public class CreateBid {
	static FirefoxDriver driver;
	static pages.CommonPages.UserAuthorizationPage objLogin;
	static pages.WriterPages.MyOrdersWriterPage objMyOrdersWriter;
	static pages.WriterPages.OrderBiddingWriterPage objCreateBid;
	static pages.WriterPages.AllAvaliableOrdersWriterPage objChooseOrder;
	



	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}
	  


	@Test

	public void createBid()throws Exception{
		//использовать метод авторизация
		objLogin = new UserAuthorizationPage(driver);
		objLogin.login(Config.writer1, Config.password);
		objMyOrdersWriter = new MyOrdersWriterPage (driver);
		// открыть страницу доступных заказов
		driver.get("http://edusson.com/writer/orders/available");
		//выбрать заказ
		objChooseOrder = new AllAvaliableOrdersWriterPage(driver);
		objChooseOrder.chooseOrder();
		//сщздать бид на выбранный заказ
		objCreateBid = new OrderBiddingWriterPage(driver);
		objCreateBid.createBid("8");
		//проверить, что бид создан
		objCreateBid.asseertBid();
		
		
	}
		@After
		  public void tearDown() {
		    driver.quit();
		  }
		
		

	}




