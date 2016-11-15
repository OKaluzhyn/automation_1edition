package ua.qa.edusson.pages;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.MyOrdersCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderCreateCustomerPage;
import ua.qa.edusson.pages.CustomerPages.OrderPayCustomerPage;
import ua.qa.edusson.pages.CustomerPages.PayPalPage;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.tests.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

public class Base extends TestBase {
    public String orderUrl;
    public String orderId;
    public String writerUrl;

    public String customerReleasedPercent;
    public String writerReleasedPercent;


    public void unhide(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['visibility']='visible';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(WebDriver driver, By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(driver, input);
        input.sendKeys(file);

    }


    @Test(enabled = false)
    public void uploadTest1() {

        app.driver.get("http://blueimp.github.io/jQuery-File-Upload/basic.html");
        attachFile(app.driver, By.id("fileupload"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(10);
        /*app.driver.get("http://imgup.net/");
        attachFile(app.driver, By.id("image_image"), app.getHelper().getRevision().getAbsolutePath());
		app.getHelper().sleep(10);
		app.driver.get("http://www.2imgs.com/");
		attachFile(app.driver, By.id("f_file"), app.getHelper().getRevision().getAbsolutePath());
*/
    }

    @Test(enabled = false)
    public void uploadTest2() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();

        userAuthorizationPage.logIn(Config.customer1, Config.password);
        app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.clickNext1();
        app.getHelper().sleep(1);
        orderCreateCustomerPage.clickNext2();
        app.getHelper().sleep(1);
        attachFile(app.driver, By.id("attach_file"), "C:\\Users\\tester\\resources\\testFile.pdf");
        app.getHelper().sleep(10);
        //orderCreateCustomerPage.proceedToBidding();
    }

    @Test(enabled = false)
    public void uploadTest3() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();

        userAuthorizationPage.logIn(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get("http://edusson.com/order/view/169636#order-tabs3");
        attachFile(app.driver, By.xpath("//input[@type='file']"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(10);
    }

    @Test(enabled = false)
    public void payPal() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        PayPalPage payPalPage = new PayPalPage();
        OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();

        userAuthorizationPage.logIn(Config.customer1, Config.password);
        app.getHelper().WaitLoading("orders");
        app.getHelper().goTo("http://edusson.com/order/pay/174899");
        orderPayCustomerPage.clickReserveButton();
        payPalPage.confirmPayPal("edussonpay4@ukr.net", "123456789");
       // payPalPage.logInToPayPalMain("edussonpay4@ukr.net", "123456789");
        app.getHelper().WaitLoading("thankyou");
        Helper.sleep(10);
    }

    @Test
    public void login() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();

        userAuthorizationPage.logIn(Config.customer1, Config.password);
        app.getHelper().WaitLoading("orders");
    }
    public void popup(){
        app.driver.switchTo().alert().getText();
    }

}
