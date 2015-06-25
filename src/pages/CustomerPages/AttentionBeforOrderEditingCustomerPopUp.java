package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class AttentionBeforOrderEditingCustomerPopUp extends BasePage {
	public AttentionBeforOrderEditingCustomerPopUp(FirefoxDriver driver){
		this.driver = driver;
	}
	By applyButton = By.xpath("html/body/div[9]/div/div/div[3]/button[2]");

	public void applyPopupBeforEditingOrder() {
		driver.findElement(applyButton).click();
	}
}