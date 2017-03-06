package ua.qa.edusson.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ua.qa.edusson.pages.CommonPages.HeaderMenu;
import ua.qa.edusson.pages.CommonPages.UserAuthorizationPage;
import ua.qa.edusson.pages.CustomerPages.*;
import ua.qa.edusson.pages.WriterPages.MyOrdersWriterPage;
import ua.qa.edusson.pages.WriterPages.OrderBiddingWriterPage;
import ua.qa.edusson.tests.tools.TestBase;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;
import ua.qa.edusson.utils.WebWindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popPendingPayPal;
import static ua.qa.edusson.pages.CustomerPages.OrderPayThankYouCustomerPage.popUpFailPayPal;

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

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
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

        userAuthorizationPage.userLogin(Config.writer1, Config.password);
        app.getHelper().sleep(2);
        myOrdersWriterPage.closePopup();
        app.driver.get("http://edusson.com/order/view/169636#order-tabs3");
        attachFile(app.driver, By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(10);
    }

    @Test(enabled = false)
    public void customerUploadingTest() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        //app.getHelper().sleep(1);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.clickNext1();
        app.getHelper().sleep(1);
        orderCreateCustomerPage.clickNext2();
        app.getHelper().sleep(1);
        attachFile(app.driver, By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
        app.getHelper().sleep(2);
        orderCreateCustomerPage.proceedToBidding();
        app.getHelper().waitLoading("order#redirect_url=");
    }


    @Test(enabled = false)
    public void payPal() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        PayPalPage payPalPage = new PayPalPage();
        OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        app.getHelper().goTo("http://edusson.com/order/pay/174899");
        orderPayCustomerPage.clickReserveButton();
        payPalPage.payPayPal("edussonpay4@ukr.net", "123456789");
        // payPalPage.logInToPayPalMain("edussonpay4@ukr.net", "123456789");
        app.getHelper().waitLoading("thankyou");
        Helper.sleep(10);
    }

    @Test(enabled = false)
    public void login() {
        //app.driver.get("http://essayvikings.com/");
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        HeaderMenu header = new HeaderMenu();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);

        app.getHelper().waitLoading("orders");
        header.userLogOut();
        Helper.sleep(3);
       /* app.driver.get("http://edubirdie.com/");
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        header.userLogOut();
        Helper.sleep(3);
        app.driver.get("http://edusson.com/");
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        header.userLogOut();
        Helper.sleep(3);
*/

    }

    @Test(enabled = false)
    public void paypal() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
        OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
        MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
        OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
        PayPalPage payPalPage = new PayPalPage();
        OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
        HeaderMenu headerMenu = new HeaderMenu();

        app.driver.get("http://customwriting.com/");
        String siteUrl = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl + " Reason: Payment is being reviewed by PayPal");
        // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        //app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();

        app.driver.get("http://essays.studymoose.com/");
        String siteUrl1 = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl1);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl1 + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl1 + " Reason: Payment is being reviewed by PayPal");
        // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl1+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        // app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();

        app.driver.get("http://paperial.com/");
        String siteUrl2 = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl2);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl2 + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl2 + " Reason: Payment is being reviewed by PayPal");
        // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl2+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        // app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();

        app.driver.get("http://customwriting.com/");

        String siteUrl3 = app.driver.getCurrentUrl();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        orderCreateCustomerPage.createOrder(siteUrl, "test for webdriver", "test");
        app.getHelper().waitLoading("/order/pay/");
        orderPayCustomerPage.confirmPay();
        payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
        Helper.sleep(1);
        app.getHelper().waitLoading(siteUrl3);
        Assert.assertFalse(app.getHelper().isElementPresent(popUpFailPayPal), "Test Failed " + siteUrl3 + " Reason: Payment didn't go through");
        Assert.assertFalse(app.getHelper().isElementPresent(popPendingPayPal), "Test Failed " + siteUrl3 + " Reason: Payment is being reviewed by PayPal");
        // Assert.assertFalse(app.getHelper().isElementPresent(error), "Test Failed " + siteUrl3+ " Reason: "+ orderPayThankYouCustomerPage.getErrorText());
        //app.getHelper().waitLoading("thankyou");
        headerMenu.userLogOut();
    }

    @Test(enabled = false)
    public void checkUrls() {
        System.out.println(("http://edusson.com/" + "order/pay/12345").substring(29));
        System.out.println(("http://customwriting.com/" + "order/pay/12345").substring(35));
        System.out.println(("http://essays.studymoose.com/" + "order/pay/12345").substring(39));
        System.out.println(("http://essayvikings.com/" + "order/pay/12345").substring(34));
        System.out.println(("http://edubirdie.com/" + "order/pay/12345").substring(31));
        System.out.println(("http://eduzaurus.com/" + "order/pay/12345").substring(32));
        System.out.println(("http://paperdon.com/" + "order/pay/12345").substring(31));
        System.out.println(("http://papersowl.com/" + "order/pay/12345").substring(32));
        System.out.println(("http://studarea.com/" + "order/pay/12345").substring(31));
        System.out.println(("http://essaybison.com/" + "order/pay/12345").substring(33));
        System.out.println(("http://samedaypapers.com/" + "order/pay/12345").substring(35));
        System.out.println(("http://paperell.com/" + "order/pay/12345").substring(30));
        System.out.println(("http://essaytornado.com/" + "order/pay/12345").substring(34));
        System.out.println(("http://studyfaq.com/" + "order/pay/12345").substring(30));
        System.out.println(("http://ca.edubirdie.com/" + "order/pay/12345").substring(33));
        System.out.println(("http://au.edubirdie.com/" + "order/pay/12345").substring(33));
        System.out.println(("http://uk.edubirdie.com/" + "order/pay/12345").substring(35));
        System.out.println(("http://gpaessay.com/" + "order/pay/12345").substring(31));
        System.out.println(("http://australianwritings.com.au/" + "order/pay/12345").substring(42));
        System.out.println(("http://papercp.com/" + "order/pay/12345").substring(28));
        System.out.println(("http://typemyessays.com/" + "order/pay/12345").substring(33));
        System.out.println(("http://typemyessays.com/" + "order/pay/12345").substring(34));
        System.out.println(("http://paperial.com/" + "order/pay/12345").substring(30));
        System.out.println(("http://essayontime.com.au/" + "order/pay/12345").substring(36));
        System.out.println(("http://phdify.com/" + "order/pay/12345").substring(28));
        System.out.println(("http://essays.blablawriting.com/" + "order/pay/12345").substring(36));
    }

    public void checkSiteType() {
        String siteType;
        // System.out.println(site);
        boolean found = false;

        String[] sitesWithEasyBidding = {"customwriting.com/", "essays.studymoose.com/", "paperial.com/",
                "phdfy.com/", "essayontime.com/", "essaylab.com/", "essayblablawriting.com/"
        };

        String[] sitesNotEasyBidding = {"http://edusson.com/", "http://essayvikings.com/", "http://edubirdie.com/",
                "http://eduzaurus.com/", "http://paperdon.com/", "http://papersowl.com/", "http://studarea.com/",
                "http://essaybison.com/", "http://samedaypapers.com/", "http://paperell.com/", "http://essaytornado.com/",
                "http://studyfaq.com/", "http://ca.edubirdie.com/", "http://au.edubirdie.com/", "http://uk.edubirdie.com/",
                "http://gpaessay.com/", "http://typemyessay.com/", "http://papercp.com/", "http://australianwritings.com.ua/"
        };

    }


    @Test(enabled = false)
    public void checkSites() {
        String site = app.driver.getCurrentUrl();
        System.out.println(app.getHelper().isSiteEasybidding(site));
        if (app.getHelper().isSiteEasybidding(site).equals("easy")) {
            UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
            HeaderMenu header = new HeaderMenu();
            userAuthorizationPage.userLogin(Config.customer1, Config.password);
            app.getHelper().waitLoading("orders");
            header.userLogOut();
            Helper.sleep(3);
        } else {
            System.out.println("site is not easy");
        }

    }

    @Test(enabled = false)
    public void st1() {
        app.getHelper().goToEdubirdie();
        WebWindow ww = null;
        try {
            ww = new WebWindow(app.driver, "http://edusson.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ww.switchToParent();
        System.out.println("Test 1 passed");

    }


    @Test(enabled = false)
    public void st2() {
        app.getHelper().goToEdubirdie();
        WebWindow ww = null;
        try {
            ww = new WebWindow(app.driver, "http://edusson.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ww.switchToParent();
        System.out.println("Test 2 passed");
        saveImageAttach("Image attach");
    }

    @Test(enabled = false)
    public void st3() {
        String siteUrl = app.driver.getCurrentUrl();
        OrderCreateCustomerPage p = new OrderCreateCustomerPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        p.createOrder(siteUrl, "", "");

    }

    @Test(enabled = false)
    public void st4() {
        String siteUrl = app.driver.getCurrentUrl();
        OrderCreateCustomerPage p = new OrderCreateCustomerPage();
        MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();

        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        myOrdersCustomerPage.makeNewOrder();
        p.createOrder(siteUrl, "", "");

    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] saveImageAttach(String attachPath) {
        try {
            URL defaultImage = Base.class.getResource("/images.png");
            //URL defaultImage = attachPath;
            File imageFile = new File(defaultImage.toURI());
            return toByteArray(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }

    @Test (enabled = false)
    public void st6() {
        WebWindow ww = new WebWindow(app.driver, "https://edusson.com/");
        WebWindow ww2 = new WebWindow(app.driver, "https://edusson.com/");
        // WebWindow ww3  = new WebWindow(app.driver, "https://edusson.com/");

    }

    @Test //(enabled = false)
    public void st7() {
        UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
        HeaderMenu headerMenu = new HeaderMenu();
        userAuthorizationPage.userLogin(Config.customer1, Config.password);
        app.getHelper().waitLoading("orders");
        headerMenu.userLogOut();
    }
    @Test //(enabled = false)
    public void yoda() {
        app.driver.get("http://contentyoda.com");
        app.getHelper().cyclicElementSearchByXpath("//div[@class='dropdown header_pop header_pop_enter_drop log']/button").click();
        app.getHelper().cyclicElementSearchByXpath("//input[@name='user_login']").sendKeys("kli_kli2008@mail.ru");
        app.getHelper().cyclicElementSearchByXpath("//input[@name='user_pass']").sendKeys("Olaneg152");
        app.getHelper().cyclicElementSearchByXpath("//span[@class='popup_enter_button']").click();
        app.getHelper().waitLoading("/newtask");
       // app.getHelper().cyclicElementSearchByXpath("//input[@type='text']").sendKeys("title");
       // app.getHelper().cyclicElementSearchByXpath("//textarea").sendKeys("Мой текст больше 10 символов");
       // app.getHelper().cyclicElementSearchByXpath("//button[text()='Go']").click();
       // app.getHelper().waitLoading("task_id");
        app.getHelper().cyclicElementSearchByXpath("//button[@onclick='uploadFromFile()']").click();
        attachFile(app.driver, By.xpath("//input[@type='file']"), "C:\\Users\\tester\\resources\\..txt.txt");
        app.getHelper().cyclicElementSearchByXpath("//button[@id='send_button']").click();
        app.getHelper().sleep(10);


    }
}






