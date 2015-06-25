package pages.WriterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

public class MyOrdersWriterPage extends BasePage {
	public MyOrdersWriterPage(FirefoxDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	By writerInformPopUp = By.id("popup_writer_terms_of_use");

	// проверяем появление попапа для райтера
	public boolean isWritersPopUpPresent() {
		return driver.findElements(writerInformPopUp).size() > 0;

	}
}
