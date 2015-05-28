package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PopUpAttention {
	FirefoxDriver driver;
	
	By applyButton = By.xpath("html/body/div[9]/div/div/div[3]/button[2]");
	public PopUpAttention(FirefoxDriver driver){
		this.driver = driver;
	}
public void clickApply(){
	driver.findElement(applyButton).click();
}
}