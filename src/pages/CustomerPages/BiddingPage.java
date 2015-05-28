package pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BiddingPage {
	FirefoxDriver driver;
	By biddingPageName = By.partialLinkText("http://edusson.com/order#redirect_url=/order/view/");
	By bid1 = By.xpath("//*[@id='js_user_card_18413']/a/div[2]/h3");
	By acceptBid1 = By.xpath("//*[@id='js_user_card_18413']/div/div[2]/button");
	By confirm = By.xpath("html/body/div[9]/div/div/div[3]/div[2]/button");
	By editButton = By.xpath("html/body/div[6]/div/div[1]/div/div/div/table/tbody/tr/td[1]/button");
	By bid = By.className("search-results");
	
	public BiddingPage(FirefoxDriver driver){
		this.driver = driver;
	}
	public void isBidPresent(){
		driver.findElement(bid).isDisplayed();
		}

	//Get the Page name from biddingPage 12
	public String getBiddingPageDashboardName(){
		 return	driver.findElement(biddingPageName).getText();
		}
	
	public void chooseBid1(){
		driver.findElement(bid1).click();
		
	}
	//test 55
	public void acceptBid1(){
		driver.findElement(acceptBid1).click();
	}
	
	public void confirmWriter1(){
		driver.findElement(confirm).click();
	}
	
	public void clickEdit(){
		driver.findElement(editButton).click();
	}
	//������� ��� ������� �������
	public void bid1(){
		this.chooseBid1();
		this.acceptBid1();
		this.confirmWriter1();
	}
}