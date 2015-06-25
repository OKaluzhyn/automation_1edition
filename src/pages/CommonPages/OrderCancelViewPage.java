package pages.CommonPages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderCancelViewPage {
FirefoxDriver driver;
By cancelTxt = By.className("alert-content");


public OrderCancelViewPage(FirefoxDriver driver){
	this.driver = driver;
}	


public boolean isElementPresent() {
	  try {
	    driver.findElement(cancelTxt);
	    return true;
	  } catch (NoSuchElementException e) {
	    return false;
	  }
	}

}

