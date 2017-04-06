package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.qa.edusson.utils.Config;
import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class OrderPayCustomerPage {

    PayPalPage payPalPage = new PayPalPage();
    CreditCardPayment creditCardPayment = new CreditCardPayment();

    public static String payPalButton = "//label[@for='payment_system_type_payment_system_0']/i";
    public static String creditCardButton = "//label[@for='payment_system_type_payment_system_1']/i";
    public static String makeDepositButton = "//button[@data-atest='atest_order_pay_elem_make_deposit_btn']";
    //order pay for easy bidding
    public static String reserveMoney = "//button[text()='Reserve money']";

    public String exitPopUp() {
        return "//div[@class='modal uk-modal fade modal-allow-user js_popup_all in']";
    }

    public void choosePaymentSystem(String ps) {
        app.getHelper().remooveExitPopUp();
        if (ps.equals("paypal")) {
            app.getHelper().cyclicElementSearchByXpath(payPalButton).click();
        }
        if (ps.equals("card")) {
            app.getHelper().cyclicElementSearchByXpath(creditCardButton).click();
        }

    }

    public void choosePayPal() {
        app.getHelper().cyclicElementSearchByXpath(payPalButton).click();
    }

    public void chooseCardPay() {
        app.getHelper().cyclicElementSearchByXpath(creditCardButton).click();
    }

    public void clickReserveButton() {
        app.getHelper().cyclicElementSearchByXpath(makeDepositButton).click();

    }

    //for easy bidding
    public void reserveMoney() {
        app.getHelper().cyclicElementSearchByXpath(reserveMoney).click();
    }

    public void confirmPay() {
        //app.getHelper().closePopUp();
        app.getHelper().remooveExitPopUp();
        if (app.getHelper().isElementPresent(makeDepositButton)) {
            this.clickReserveButton();
        } else if (app.getHelper().isElementPresent(reserveMoney)) {
            this.reserveMoney();
        }
    }


    public void payOrder(String siteUrl) {
        app.getHelper().remooveExitPopUp();
        WebElement pp = app.driver.findElement(By.xpath("//input[@id='payment_system_type_payment_system_0']"));
        app.getHelper().unhide(pp);
        Boolean checkPayPal = pp.isSelected();
        if (checkPayPal == true) {
            System.out.println("PayPal");
            confirmPay();
            app.getHelper().waitLoading("sandbox");
            payPalPage.payPayPal(Config.paypall_login, Config.paypall_pass);
            Helper.sleep(1);
            payPalPage.checkForError();
            app.getHelper().waitLoading(siteUrl);
        }
        if (app.getHelper().hasSiteCardPay(siteUrl)) {
            WebElement cc = app.driver.findElement(By.xpath("//input[@id='payment_system_type_payment_system_1']"));
            app.getHelper().unhide(cc);
            Boolean checkCard = cc.isSelected();
            if (checkCard == true) {
                System.out.println("Card");
                confirmPay();
                app.getHelper().waitLoading("/pay/");
                creditCardPayment.setAllFields(siteUrl);
                app.getHelper().waitLoading(siteUrl);
            }
        }
    }

    public String getOrderTotal() {
        String orderTotal = app.getHelper()
                .cyclicElementSearchByXpath("//span[@data-atest='atest_order_pay_elem_total_price']").getText().substring(1);
        return orderTotal;
    }

    public String getDepositAmount() {
        String depositAmount = app.getHelper()
                .cyclicElementSearchByXpath("//span[@data-atest='atest_order_pay_elem_deposit_amount']").getText().substring(1);
        return depositAmount;
    }
}