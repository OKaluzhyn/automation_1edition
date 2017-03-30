package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class MyBalanceCustomerPage {

    public static String loadMoneyButton = "//button[@data-popup-target='popup_replenish_balance']";
    //Pop up
    public static String refundButton = "btn-primary [2]";
    public static String amountField = "//input[@id='add_deposit_form_deposit_deposit_amount']";
    public static String load = "//*[@id='popup_replenish_balance']//button";
    public static String payPal = "//label[@for='add_deposit_form_payment_system_payment_system_0']";
    public static String creditCard = "//label[@for='add_deposit_form_payment_system_payment_system_1']";
    public static String balance = "//b[@class='balance']";
    public static String balance_thankyou = "//div[@class='thanks-box']//*[text() = 'Transaction has been made successfully']";
    public static String balanceLink = "//div[@class='thanks-box']//a[@href='/customer/balance']";


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
        this.setValue(strAmount);
        this.clickload();

    }

    public Double userBalance() {
        Double b = Double.parseDouble(app.getHelper().cyclicElementSearchByXpath(balance).getText().substring(1));
        return b;
    }

    public String thanksMessage(){
        return balance_thankyou;
    }
    public void returnBalancePage(){
        app.getHelper().cyclicElementSearchByXpath(balanceLink).click();
    }
}
