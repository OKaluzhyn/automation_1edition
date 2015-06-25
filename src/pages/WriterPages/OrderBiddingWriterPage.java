package pages.WriterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderBiddingWriterPage extends BasePage {
	public OrderBiddingWriterPage(FirefoxDriver driver) {
		this.driver = driver;
	}
	By bidPrice = By.id("bidding_form_writer_value");
	By applyButton = By.name("makebid");
	By bidAmount = By.className("bid-amount");

	public void setPrice(String strBidPrice) {
		driver.findElement(bidPrice).sendKeys(strBidPrice);
	}

	public void clickApply() {
		driver.findElement(applyButton).click();
	}

	public void createBid(String strBidPrice) {
		this.setPrice(strBidPrice);
		this.clickApply();

	}

	public void asseertBid() {
		driver.findElement(bidAmount).isDisplayed();
	}
}
