package pages.CustomerPages;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
public class MyOrdersCustomer {

	FirefoxDriver driver;
	By orderName = By.linkText("test for webdriver");
	
	public MyOrdersCustomer(FirefoxDriver driver){
		this.driver = driver;
	}
	
	public void goToOrder(){
		driver.findElement(orderName).click();
	}
}