package junit;
/*
* This is a test suite class that we will create to run all our tests
* Follow along to see how its done Pay special attention to annotations
*/
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.TestUserAutorizationProduction;
import tests.TestUserForgotPassProduction;
import tests.TestStandartCheckEdussonProduction;
import tests.Customer.CancelOrder;
import tests.Customer.CreateOrder;
import tests.Customer.EditOrder;
import tests.Customer.LoadMoneyToBalance;
	 
	/*
	* This is a test suite class that we will create to run all our tests
	* Follow along to see how its done Pay special attention to annotations
	*/
	 
	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		 TestUserAutorizationProduction.class,
         TestUserForgotPassProduction.class,
         TestStandartCheckEdussonProduction.class,
         //Customers tests
        // CreateOrder.class,
        // EditOrder.class,
        // CancelOrder.class,
       //  LoadMoneyToBalance.class
         })
	    	
public class TestSuite1 {
}
