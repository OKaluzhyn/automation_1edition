package tests.Writer;


	import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.AuthPage;
import pages.WriterPages.AllOrders;
import pages.WriterPages.CreateBidPage;
import pages.WriterPages.MyOrdersWriter;
import utils.Users;

	public class CreateBid {
	static FirefoxDriver driver;
	static pages.AuthPage objLogin;
	static pages.WriterPages.MyOrdersWriter objMyOrdersWriter;
	static pages.WriterPages.CreateBidPage objCreateBid;
	static pages.WriterPages.AllOrders objChooseOrder;
	



	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://edusson.com");
	}
	  


	@Test

	public void createBid()throws Exception{
		//������������ ����� �����������
		objLogin = new AuthPage(driver);
		objLogin.login(Users.writer1, Users.password);
		objMyOrdersWriter = new MyOrdersWriter (driver);
		// ������� �������� ��������� �������
		driver.get("http://edusson.com/writer/orders/available");
		//������� �����
		objChooseOrder = new AllOrders(driver);
		objChooseOrder.chooseOrder();
		//������� ��� �� ��������� �����
		objCreateBid = new CreateBidPage(driver);
		objCreateBid.createBid("8");
		//���������, ��� ��� ������
		objCreateBid.asseertBid();
		
		
	}
		@After
		  public void tearDown() {
		    driver.quit();
		  }
		
		

	}




