package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.Spring;

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
import utils.Config;
import utils.Helper;

public class TestEasyBiddingStandart {
	
	String orderUrl; //= Helper.driver.getCurrentUrl();
	String siteUrl; //= "http://paperial.com/";
	String orderId; //= Helper.driver.getCurrentUrl().substring(30);
	String writerUrl; //= "http://edusson.com/order/view/"+orderId;
    String customerUrl; //= siteUrl+"order/view/"+orderId;
    String customerReleasedPercent;
	String writerReleasedPercent;
	// инициализация страниц
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
	// у клиента на балансе 0, оплата заказа через PayPall, релиз пистаелю
	// 20%+80%
	
	public void standartCheck_EasyBidding_Production_All() throws Exception {
    	
    	String [] sites = {"http://paperial.com/", 
    			"http://essayontime.com.au/", 
    			"http://phdify.com/", 
    			"http://customwriting.com/", 
    			"http://typemyessays.com/"};
    	for (int i = 0; i<sites.length; i++){
    	    	
    	siteUrl = sites[i];
    	Helper.driver.get(siteUrl);
    	
    	
		// логинимся клиентом
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		// create order
	    Helper.sleep(1);
		orderCreateCustomerPage.createOrderForOtherSites("test for webdriver", "test");
		Helper.sleep(2);
		assertTrue(Helper.driver.getCurrentUrl().contains("/order/pay/"));
		Helper.sleep(1);
		 if (siteUrl == "http://paperial.com/")
		    {
		    orderId = Helper.driver.getCurrentUrl().substring(30);
		    System.out.println(orderId);
		    }
		    else if (siteUrl == "http://essayontime.com.au/")
		    {
		     	orderId = Helper.driver.getCurrentUrl().substring(36);
			    System.out.println(orderId);
		    }
		    else if (siteUrl == "http://phdify.com/")
		    {
		     	orderId = Helper.driver.getCurrentUrl().substring(28);
			    System.out.println(orderId);
		    }
		    else if (siteUrl == "http://customwriting.com/")
		    {
		     	orderId = Helper.driver.getCurrentUrl().substring(35);
			    System.out.println(orderId);
		    }
		    else if (siteUrl == "http://typemyessays.com/")
		    {
		     	orderId = Helper.driver.getCurrentUrl().substring(34);
			    System.out.println(orderId);
		    }
		    
		    writerUrl = "http://edusson.com/order/view/"+orderId;
		    customerUrl = siteUrl+"order/view/"+orderId;
			Helper.sleep(1);
			
		//reserve money
		orderPayCustomerPage.reserveMoney();
		Helper.sleep(1);
		// логинимся в PayPall и подтвержаем оплату
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		Helper.sleep(2);
		//assert thankyou page
		assertTrue(Helper.driver.getCurrentUrl().contains("thankyou"));
		Helper.driver.get(customerUrl);
		//assert true - text is present
		assertTrue(Helper.isElementPresent("//*[text()='Your payment was successful, we are searching for the best writer now. Please wait a few minutes.']"));
		
		// логинимся писателем
		Helper.goToEdusson();
		Helper.sleep(1);
		if(Helper.isElementPresent(userAuthorizationPage.getloginLink()) == true)
		{
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		//закрываем райтерский попап
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		Helper.driver.get(writerUrl);
		}
		else
		{
			Helper.driver.get(writerUrl);
		}
		
		// берем урл страницы заказа из переменной и переходим по нему
		
		Helper.sleep(2);
		System.out.println(Helper.driver.getCurrentUrl());
		// создаем бид
		orderBiddingWriterPage.easyBiddingApplyprice();
		Helper.sleep(2);
		//загружаем ревизию
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		Helper.driver.get(customerUrl);
	    // релизим писателю 30%
	    orderInProgressPage.releaseMoney("30");
	    // получаем занчение % релизнутых денег на странице клиента
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	   	Helper.sleep(2);
		Helper.driver.get(writerUrl);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		Helper.sleep(2);
		Helper.driver.get(customerUrl);
		// релизим писателю 70%
		orderInProgressPage.releaseMoney("70");
		Helper.sleep(2);
		//orderFinishedViewPage.closePopup();
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		Helper.driver.get(writerUrl);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//проверяем наличие текста order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		//headerMenu.userLogOut();
		//Helper.sleep(2);
		System.out.println("TEST PASSED");
    	}
	}
    

		@After
	public void theEnd(){
			Helper.quit();
				
		}

}
