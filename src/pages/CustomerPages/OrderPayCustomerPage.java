package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderPayCustomerPage extends BasePage {
	public OrderPayCustomerPage(FirefoxDriver driver) {
		this.driver = driver;
	}

	By reserveMoneyButton = By
			.xpath("//*[@id='form_payment_type']/div[1]/div[1]/div[2]/div[2]/button");

	// Get the Page name from Home Page
	public void clickReserveButton() {
		driver.findElement(reserveMoneyButton).click();

	}
}
