package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CustomerBalanceThankyou {
FirefoxDriver driver;

	By paySuccessMessage = By.className("sub-heading"); 

	public CustomerBalanceThankyou(FirefoxDriver driver){
		this.driver = driver;
	}

public void assertPaySuccess(){
driver.findElement(paySuccessMessage).getText().contains("Transaction has been made successfully!");
}
}