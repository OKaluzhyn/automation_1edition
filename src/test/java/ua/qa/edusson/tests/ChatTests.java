package ua.qa.edusson.tests;

import com.sun.jmx.snmp.Timestamp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.utils.Config;

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
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        OrderFinishedViewPage myOrdersWriterPage = new OrderFinishedViewPage();
        /*  app.driver.get("http://edubirdie.com/");
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.driver.get("http://edubirdie.com/order/view/202761");
        openChat();
        chooseDialog();
        sendMessage();
        WebWindow ww = new WebWindow(app.driver, "http://edusson.com/");
       */
        userAuthorizationPage.userLogin(Config.writer1, Config.password);

        myOrdersWriterPage.closePopup();
        //app.driver.get("http://edusson.com/order/view/202761");
        openChat();
        chooseDialog();
        //app.getHelper().cyclicElementSearchByXpath("//p[text()='customer-77965']").click();
        //app.getHelper().waitElement("//div[@class='dialog-text']");
        dialog = "//*[@id='mCSB_3_container']/div[4]/div[2]/text()";
       /* List<WebElement> messages = app.driver.findElements(By.xpath(dialog));
        System.out.println(messages.size());
        for(int i=0;i<=messages.size();i++)
        {
            String str = messages.get(i).getAttribute("id")+" - "+messages.get(i).getText();
            System.out.println(str);

        }
              //  .listIterator(messages.size() - 1);
        //String mess = messages.get(messages.size() - 1).getAttribute("value");
       */ String mess = app.driver.findElement(By.xpath(dialog)).getText();
        //.getAttribute("value");
        // System.out.println(dialog);
        System.out.println(mess);
        //writerMessage = mess;
        //Assert.assertEquals(clientMessage, writerMessage);

    }


}
