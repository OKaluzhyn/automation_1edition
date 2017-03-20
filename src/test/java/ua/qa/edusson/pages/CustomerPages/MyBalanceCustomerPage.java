package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class MyBalanceCustomerPage {

    public static String loadMoneyButton = "//button[@data-popup-target='popup_replenish_balance']";
    //Pop up
    public static String refundButton = "btn-primary [2]";
    public static String amountField = ".//*[@id='add_deposit_form_deposit_value']";
    public static String load = ".//*[@id='add_deposit_form']/div[3]/button";
    public static String payPal = "//label[@for='add_deposit_form_payment_system_payment_system_0']";
    public static String creditCard = "//label[@for='add_deposit_form_payment_system_payment_system_1']";


    //WebElement refund_Button = app.getHelper().cyclicElementSearchByXpath(refundButton);


    public void choosePayPalSystem() {
        WebElement payPalRadioButton = app.getHelper().cyclicElementSearchByXpath(payPal);
        payPalRadioButton.click();
    }

    public void chooseCreditCardPay() {
        WebElement creditCardRadioButton = app.getHelper().cyclicElementSearchByXpath(creditCard);
        creditCardRadioButton.click();

    }

    public void clickLloadbutMain() {
        WebElement load_Money_Button = app.getHelper().cyclicElementSearchByXpath(loadMoneyButton);
        load_Money_Button.click();
    }

    public void setValue(String strAmount) {

        WebElement amount_Field = app.getHelper().cyclicElementSearchByXpath(amountField);
        amount_Field.sendKeys(strAmount);
    }

    public void clickload() {

        WebElement load_button = app.getHelper().cyclicElementSearchByXpath(load);
        load_button.click();
    }


    public void loadMoneyToBalancePayPal(String strAmount) {
        this.clickLloadbutMain();
        this.choosePayPalSystem();
        this.setValue(strAmount);
        this.clickload();
    }


    public void loadMoneyToBalanceCreditCard(String strAmount) {
        this.clickLloadbutMain();
        this.chooseCreditCardPay();
        this.setValue("10");
        this.clickload();

    }
}
