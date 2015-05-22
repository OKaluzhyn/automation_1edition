package edu.example.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.pages.AuthPage;

public class ForgotPass {
	
	FirefoxDriver driver;
	 AuthPage objForgotForm;
	 

	@Before
  public void setUp() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://edusson.com");
  }
  
@Test
public void forgotPass(){
	objForgotForm = new AuthPage(driver);
	objForgotForm.forgotPass(null);
}

@Test
public void assertError(){
	objForgotForm = new AuthPage(driver);
	objForgotForm.assertError();
}


@After
public void tearDown() {
  driver.quit();
}
}