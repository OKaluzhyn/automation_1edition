package junit;
/*
* This is a test suite class that we will create to run all our tests
* Follow along to see how its done Pay special attention to annotations
*/
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.TestOrderCreateEditCancelProduction;
import tests.TestStandartCheckEdubirdieProduction;
import tests.TestStandartCheckStudyfaqProduction;
import tests.TestUserAutorizationProduction;
import tests.TestUserForgotPassProduction;
import tests.TestStandartCheckEdussonProduction;

	 
	/*
	* This is a test suite class that we will create to run all our tests
	* Follow along to see how its done Pay special attention to annotations
	*/
	 
	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		 TestUserAutorizationProduction.class,
         TestUserForgotPassProduction.class,
         TestStandartCheckEdussonProduction.class,
         TestOrderCreateEditCancelProduction.class,
         TestStandartCheckEdubirdieProduction.class,
         TestStandartCheckStudyfaqProduction.class,
         })
	    	
public class TestSuite1 {
}
