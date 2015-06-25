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
			.xpath("html/body/div[6]/div/div/div[1]/table/tbody/tr[4]/td/div/a");

	// Get the Page name
	public String getThankYouPageDashboardName() {
		return driver.findElement(thankYouPageName).getText();
	}

	public void returnOrderPage() {
		driver.findElement(order).click();
	}
}