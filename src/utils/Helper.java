package utils;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Helper {
	public static WebDriver driver;
	
	
public static void driverSetUp(String siteUrl){
	System.setProperty("webdriver.chrome.driver","D:\\AUTO_TESTING\\downloads\\ChromeDriver\\chromedriver.exe");	
	driver = new ChromeDriver();
	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	driver.get(siteUrl);
	
}


public static void quit(){
	driver.quit();
	}
public static WebElement cyclicElementSearchByXpath(String target) {
      for (int i = 0; i < 600; i++){
            if (driver.findElements(By.xpath(target)).size() > 0) {
               break;   }
              
            sleep(1);
        }
        return driver.findElement(By.xpath(target));
   }
public static void sleep(long sec) {
	try {
        Thread.sleep(sec*1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
	
}
	public static WebElement randomChoiceFromDropdown(String arg, String xpath){
		WebElement openDropdown = Helper.cyclicElementSearchByXpath(arg);
		openDropdown.click();
		List<WebElement> listOfElements =  Helper.driver.findElements(By.xpath(xpath));
		// select a random one
		Random random = new Random();
		WebElement someRandomElement = listOfElements.get(random.nextInt(listOfElements.size()));
		
		someRandomElement.click();
		openDropdown.click();
		return someRandomElement;
		
	}
	
	 public static boolean isElementPresent(String xpath){
	        try {
	        	Helper.driver.findElement(By.xpath(xpath));
	            return true;
	        } catch (Exception e) {
	            return false;
	        } }
	
	
	//public WebDriverWait wait = new WebDriverWait(driver, 5).withMessage("Element was not found");
	//private final Wait <WebDriver> wait = new WebDriverWait(driver, 5).withMessage("Element was not found");
	
		
	}


