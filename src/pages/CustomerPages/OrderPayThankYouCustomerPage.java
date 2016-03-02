package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderPayThankYouCustomerPage extends BasePage {
	public OrderPayThankYouCustomerPage(FirefoxDriver driver) {
		this.driver = driver;
	}

	By thankYouPageName = By
			.partialLinkText("http://edusson.com/order/pay/thankyou");
	By order = By
			.xpath("//a[text()='order page']");

	// Get the Page name
	public String getThankYouPageDashboardName() {
		return driver.findElement(thankYouPageName).getText();
	}

	public void returnOrderPage() {
		driver.findElement(order).click();
	}
}