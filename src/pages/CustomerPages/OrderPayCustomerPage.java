package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class OrderPayCustomerPage extends BasePage {
	public OrderPayCustomerPage(FirefoxDriver driver) {
		this.driver = driver;
	}
	 public static void main(String[] args) {
	 }
	 By creditCard = By.xpath("//span[text()='Visa / Master Card']");
	 By reserveMoneyButton = By.xpath("//div[contains(@class, 'reserve_money_butt_div')]/button");
	 
//WebElement creditCard = driver.findElement(By.xpath("//span[text()='Visa / Master Card']"));
//WebElement reserveMoneyButton = driver.findElement(By.xpath("//div[contains(@class, 'reserve_money_butt_div')]"));
	

	public void chooseCardPay(){
		driver.findElement(creditCard).click();
	}
	public void clickReserveButton() {
		driver.findElement(reserveMoneyButton).click();

	}
}
