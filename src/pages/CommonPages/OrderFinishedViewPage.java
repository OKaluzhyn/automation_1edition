package pages.CommonPages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderFinishedViewPage extends BasePage {
	public OrderFinishedViewPage(FirefoxDriver driver) {
		this.driver = driver;
	}

	By finishedPageName = By.xpath("html/body/div[6]/div/div[1]/span[2]");
	 public By textOrderFinished = By.className("alert-success");

	// Get the Page name from Home Page
	public void checkOrderFinished(){
		assertTrue(driver.findElement(textOrderFinished).getText()
				.contains("The order has been finished"));

	}
}