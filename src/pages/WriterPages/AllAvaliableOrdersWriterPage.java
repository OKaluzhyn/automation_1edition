package pages.WriterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class AllAvaliableOrdersWriterPage extends BasePage {
	public AllAvaliableOrdersWriterPage(FirefoxDriver driver) {
		this.driver = driver;
	}

	By orderName = By.linkText("test for webdriver");

	public void chooseOrder() {
		driver.findElement(orderName).click();
	}
}
