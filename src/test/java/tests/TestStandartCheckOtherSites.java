package tests;

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
import utils.Config;
import utils.Helper;

public class TestStandartCheckOtherSites {
	String orderUrl; 
	String siteUrl; 
	String orderId; 
	String writerUrl; 
    String customerUrl; 
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
	
	    	
    	public void standartCheck_Production_Other_All() throws Exception {
        	
        	String [] sites = {"http://eduzaurus.com/", 
        			"http://paperdon.com/", 
        			"http://papersowl.com/", 
        			"http://studarea.com/", 
        			"http://essaybison.com/",
        			"http://samedaypapers.com/",
        			"http://paperell.com/",
        			"http://essaystorm.com/",
        			"http://essayvikings.com/",
        			//"http://customwriting.com/",
        			};
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
		Helper.sleep(3);
		assertTrue(Helper.driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//релодим страницу чтобы получить ссылку
		Helper.driver.navigate().refresh();
		// сохраняем урл страницы текущего заказа в переменную
	    customerUrl = Helper.driver.getCurrentUrl();
	    if (siteUrl == "http://eduzaurus.com/")
	    {
	    orderId = Helper.driver.getCurrentUrl().substring(32);
	    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://paperdon.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(31);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://papersowl.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(32);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://studarea.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(31);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://essaybison.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(33);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://samedaypapers.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(36);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://paperell.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(31);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://essaystorm.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(33);
		    System.out.println(orderId);
	    }
	    else if (siteUrl == "http://essayvikings.com/")
	    {
	     	orderId = Helper.driver.getCurrentUrl().substring(35);
		    System.out.println(orderId);
	    }
	    
	    
	    writerUrl = "http://edusson.com/order/view/"+orderId;
		Helper.sleep(1);
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
		Helper.sleep(2);
		System.out.println(Helper.driver.getCurrentUrl());
		// создаем бид
		orderBiddingWriterPage.createBid("6"); 
		Helper.sleep(2);
		//Helper.goToEdubirdie();
		//Helper.sleep(1);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(customerUrl);
		Helper.sleep(2);
		// выбираем бид первого писателя
		orderBiddingCustomerPage.bid1();
		Helper.sleep(2);
		// подтвержаем бид, переходим на страницу оплаты
		orderPayCustomerPage.confirmPay();
		Helper.sleep(2);
		//orderPayCustomerPage.clickReserveButton();
		//переключаемся на frame на странице пейпала
		//Helper.sleep(1);
		//Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		Helper.sleep(1);
		// логинимся в PayPall и подтвержаем оплату
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		//payPalPage.clickContinue();
		// ждем возвращения на сайт
		//Helper.sleep(30);
		//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		Helper.sleep(2);
		//Helper.goToEdusson();
		//Helper.sleep(1);
		//берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(writerUrl);
	    //загружаем ревизию
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		//Helper.goToEdubirdie();
		//Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(customerUrl);
	    // релизим писателю 10%
	    orderInProgressPage.releaseMoney("20");
	    // получаем занчение % релизнутых денег на странице клиента
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	   // Helper.goToEdusson();
		//Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(writerUrl);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//Helper.goToEdubirdie();
		//Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(customerUrl);
		// релизим писателю 90%
		orderInProgressPage.releaseMoney("80");
		Helper.sleep(2);
		//orderFinishedViewPage.closePopup();
		// получаем занчение % релизнутых денег на странице клиента
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		//Helper.goToEdusson();
		//Helper.sleep(2);
		// берем урл страницы заказа из переменной и переходим по нему
		Helper.driver.get(writerUrl);
		// получаем занчение % релизнутых денег на странице писателя
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// сравниваем значения релизнутых денег у клиента и у писателя
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//проверяем наличие текста order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		//headerMenu.userLogOut();
		System.out.println("TEST PASSED");
        	}
	}

		@After
	public void theEnd(){
			Helper.quit();
				
		}
}
