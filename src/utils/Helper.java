package utils;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Helper {
	public static WebDriver driver;
	
	
public static void driverSetUp(){
	System.setProperty("webdriver.chrome.driver","D:\\AUTO_TESTING\\downloads\\ChromeDriver\\chromedriver.exe");	
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	driver.get("http://edusson.com");
	
}


public static void quit(){
	driver.quit();
	}
public static WebElement cyclicElementSearchByXpath(String target) {
      for (int i = 0; i < 600; i++){
            if (driver.findElements(By.xpath(target)).size() > 0) {
               break;            }
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
}

