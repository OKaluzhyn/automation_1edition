package ua.qa.edusson.tests.standartCheck;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.Chat;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.OrderFinishedViewPage;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;
import ua.qa.edusson.utils.WebWindow;

import static ua.qa.edusson.pages.CommonPages.Chat.customerOrderUrl;
import static ua.qa.edusson.pages.CommonPages.Chat.writerOrderUrl;

/**
 * Created by tester on 12.12.2016.
 */
public class ChatTests extends TestBase {


    @Test
    public void chatTesting() {
        Chat chat = new Chat();
        HeaderMenu headerMenu = new HeaderMenu();
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        OrderFinishedViewPage myOrdersWriterPage = new OrderFinishedViewPage();
        //open edubirdie as customer
        app.getHelper().goToEdubirdie();
        userAuthorizationPage.userLogin(Config.customer2, Config.password);
        app.driver.get(customerOrderUrl);

        //open edusson as writer
        WebWindow ww = new WebWindow(app.driver, "https://edusson.com/");

        userAuthorizationPage.userLogin(Config.writer2, Config.password);
        Helper.sleep(1);
        myOrdersWriterPage.closePopup();
        app.driver.get(writerOrderUrl);
        //return to the customer tab
        ww.switchToParent();
        //customer send a message
        chat.openChat();
        chat.chooseDialog();
        chat.sendMessage();
        System.out.println(chat.clientMessage);
        //return to the writer tab
        ww.switchToWindow();
        //writer read a message
        chat.openChat();
        Helper.sleep(1);
        chat.chooseDialog();
        Helper.sleep(1);
        chat.writerMessage = chat.getMessageText();
        System.out.println(chat.writerMessage);
        //compare messages
        Assert.assertEquals(chat.clientMessage, chat.writerMessage);
        headerMenu.userLogOut();
        ww.close();
        headerMenu.userLogOut();
        Helper.sleep(1);
        System.out.println("TEST PASSED");

    }

}
