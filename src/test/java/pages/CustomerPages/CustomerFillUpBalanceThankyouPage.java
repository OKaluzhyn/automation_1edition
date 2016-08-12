package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CustomerFillUpBalanceThankyouPage {
FirefoxDriver driver;

	By paySuccessMessage = By.className("sub-heading"); 

	public CustomerFillUpBalanceThankyouPage(FirefoxDriver driver){
		this.driver = driver;
	}

public void assertPaySuccess(){
driver.findElement(paySuccessMessage).getText().contains("Transaction has been made successfully!");
}
}