package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import ua.qa.edusson.utils.Helper;

import static ua.qa.edusson.tests.TestBase.app;

public class UserAuthorizationPage {


    //XPath

    public static String login_link = "//*[@data-atest='atest_login_elem_popup_open']";
    public static String user_name_field = "//input[@data-atest='atest_login_form_email']";
    public static String continue_button = "//button[@data-atest='atest_login_form_submit']";
    public static String user_password_field = "//input[@data-atest='atest_login_form_password']";
    public static String login_button = "//button[@data-atest='atest_login_form_submit']";
    public static String login_button2 = "(//button[@data-atest='atest_login_form_submit'])[1]";

    public static String forgot_password_link = "//a[@data-atest='atest_forgot_pass_elem_popup_open']";
    public static String email_for_change_password_field = "//input[@data-atest='atest_forgot_pass_form_email']";
    public static String submit_button = "//button[@data-atest='atest_forgot_pass_form_submit']";
    public static String change_user = "//a[@data-atest='atest_login_elem_change_user']";
    //for editing
    public static String success_pass_change = "//span[@class='js_forgot_password_content_2']";
    public static String error_pass_change = "//div[@class='errorText js_forgot_pass_error']";
    public static String submit_button_after_change_user = "//button[@data-atest='atest_login_form_submit' and text()='Continue']";
    public static String error_message = "//div[@class='errorText']";

    //public static String messageForDeactivateUser = "//div[@class='errorText']";

    private String login = "//*[@data-atest='atest_login_elem_popup_open']";

    public String getloginLink() {

        return this.login;
    }


    public void LogClick() {
        WebElement openAuthorizationPopUp = app.getHelper().cyclicElementSearchByXpath(login_link);
        openAuthorizationPopUp.click();
    }


    public void logIn(String strUserName, String strPassword) {

        this.LogClick();
        app.getHelper().WaitElement(user_name_field);
        this.setUserName(strUserName);
        this.continueClick();
        this.setPassword(strPassword);
        this.clickLoginButton();
    }

    public void changeUser(String strUserName, String strPassword) {
        this.LogClick();
        app.getHelper().WaitElement(change_user);
        this.changeUserClick();
        app.getHelper().WaitElement(user_name_field);
        this.setUserName(strUserName);
        app.getHelper().WaitElement(submit_button_after_change_user);
        this.submitClickAfterChangeUser();
        app.getHelper().WaitElement(user_password_field);
        this.setPassword(strPassword);
        app.getHelper().WaitElement(login_button2);
        this.clickLoginButton2();
    }


    public void setUserName(String strUserName) {
        WebElement userEmail = app.getHelper().cyclicElementSearchByXpath(user_name_field);
        userEmail.click();
        userEmail.sendKeys(strUserName);
    }

    public void continueClick() {
        WebElement continueButton = app.getHelper().cyclicElementSearchByXpath(continue_button);
        continueButton.click();
    }


    public void setPassword(String strPassword) {
        WebElement userPassword = app.getHelper().cyclicElementSearchByXpath(user_password_field);
        userPassword.click();
        userPassword.sendKeys(strPassword);
    }


    public void clickLoginButton() {
        WebElement submit = app.getHelper().cyclicElementSearchByXpath(login_button);
        submit.click();
    }


    //change user methods
    public void changeUserClick() {
        WebElement changeUserLink = app.getHelper().cyclicElementSearchByXpath(change_user);
        changeUserLink.click();
    }

    //login
    public void clickLoginButton2() {
        WebElement submit = app.getHelper().cyclicElementSearchByXpath(login_button);
        submit.click();
    }


    public void submitClickAfterChangeUser() {
        WebElement submitButton2 = app.getHelper().cyclicElementSearchByXpath(submit_button_after_change_user);

        submitButton2.click();
    }


    // Forgot Password methods

    public void clickForgotPasswordlink() {
        WebElement forgotPassword = app.getHelper().cyclicElementSearchByXpath(forgot_password_link);
        forgotPassword.click();
    }


    public void clickForgotPasswordSubmit() {
        WebElement submit = app.getHelper().cyclicElementSearchByXpath(submit_button);
        submit.click();

    }


    public void assertErrorForgotPassword() {
        WebElement errorText = app.getHelper().cyclicElementSearchByXpath(error_pass_change);
        errorText.getText()
                .contains("This is an obligatory field.");
    }


    public void setEmail(String strEmail) {
        WebElement email = app.getHelper().cyclicElementSearchByXpath(email_for_change_password_field);
        email.sendKeys(strEmail);
    }

    // forgot password
    public void assertSuccessPasswordChange() {
        WebElement successMessage = app.getHelper().cyclicElementSearchByXpath(success_pass_change);
        successMessage.getText()
                .contains("We have just sent temporary password to your email.Use these details to login.");
    }


    public void forgotPasswordSuccess(String strEmail) {
        this.LogClick();
        Helper.sleep(1);
        this.clickForgotPasswordlink();
        this.setEmail(strEmail);
        this.assertSuccessPasswordChange();

    }


    public void forgotPassNoEmail(String strEmail) {
        this.LogClick();
        Helper.sleep(1);
        this.clickForgotPasswordlink();
        this.setEmail(strEmail);
        this.clickForgotPasswordSubmit();
        this.assertErrorForgotPassword();
    }


    public boolean checkErrorMessagePresent() {

        try {
            app.getHelper().cyclicElementSearchByXpath(error_message);
            return true;
        } catch (ElementNotVisibleException ex) {
            return false;
        }
    }


}


