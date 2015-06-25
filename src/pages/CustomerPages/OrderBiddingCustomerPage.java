package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderBiddingCustomerPage extends BasePage {
	public OrderBiddingCustomerPage(FirefoxDriver driver){
		this.driver = driver;
	}
	By bid1 = By.className("user-card-row");
	By acceptBid1 = By.className("button-accept");
	By confirm = By.xpath("html/body/div[9]/div/div/div[3]/div[2]/button");
	By editButton = By
			.xpath("html/body/div[6]/div/div[1]/div/div/div/table/tbody/tr/td[1]/button");
	By bid = By.className("search-results");

	public void isBidPresent() {
		driver.findElement(bid).isDisplayed();
	}

	public void chooseBid1() {
		driver.findElement(bid1).click();

	}

	public void acceptBid1() {
		driver.findElement(acceptBid1).click();
	}

	public void confirmWriter1() {
		driver.findElement(confirm).click();
	}

	public void clickEdit() {
		driver.findElement(editButton).click();
	}

	// выбрать бид первого райтера
	public void bid1() {
		this.chooseBid1();
		this.acceptBid1();
		this.confirmWriter1();
	}

}