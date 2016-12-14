package ua.qa.edusson.tests;

import com.sun.jmx.snmp.Timestamp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;
import ua.qa.edusson.utils.WebWindow;

import java.text.SimpleDateFormat;

/**
 * Created by tester on 12.12.2016.
 */
public class ChatTests extends TestBase {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    public static String dialog;
    public static String clientMessage;
    public static String writerMessage;


    public void openChat() {
        app.getHelper().waitElement("//p[text()=' Chat']");
        app.getHelper().cyclicElementSearchByXpath("//p[text()=' Chat']").click();
    }

    public void chooseDialog() {
        app.getHelper().waitElement("//span[text()='chat testing']");
        app.getHelper().cyclicElementSearchByXpath("//span[text()='chat testing']").click();
    }

    public void sendMessage() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        clientMessage = String.valueOf(timestamp);
        System.out.println(clientMessage);
        app.getHelper().cyclicElementSearchByXpath("//div[@class='write-message']/textarea").sendKeys(clientMessage);
        app.getHelper().cyclicElementSearchByXpath("//div[@class='write-message']/textarea").sendKeys(Keys.ENTER);
    }


    @Test
    public void chatTesting() {

        HeaderMenu headerMenu = new HeaderMenu();
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        OrderFinishedViewPage myOrdersWriterPage = new OrderFinishedViewPage();
        //open edubirdie as customer
        app.driver.get("http://edubirdie.com/");
        userAuthorizationPage.userLogin(Config.customer2, Config.password);
        app.driver.get("http://edubirdie.com/order/view/203585");
        //open edusson as writer
        WebWindow ww = new WebWindow(app.driver, "http://edusson.com/");
        userAuthorizationPage.userLogin(Config.writer2, Config.password);
        Helper.sleep(1);
        myOrdersWriterPage.closePopup();
        app.driver.get("http://edusson.com/order/view/203585");
        //return to the customer tab
        ww.switchToParent();
        //customer send a message
        openChat();
        chooseDialog();
        sendMessage();
        //return to the writer tab
        ww.switchToWindow();
        //writer read a message
        openChat();
        Helper.sleep(1);
        chooseDialog();
        Helper.sleep(1);
        dialog = "//div[contains(@class, 'interlocutor-message')][last()]/div[2]";
        writerMessage = app.driver.findElement(By.xpath(dialog)).getText();
        System.out.println(writerMessage);
        //compare messages
        Assert.assertEquals(clientMessage, writerMessage);
        headerMenu.userLogOut();
        ww.switchToParent();
        headerMenu.userLogOut();
        Helper.sleep(1);
        System.out.println("TEST PASSED");

    }


}
