package ua.qa.edusson.pages.WriterPages;

import static ua.qa.edusson.tests.tools.TestBase.app;

public class WriterProfile {

    public static String hireButton = "//button[@data-atest='atest_customer_popup_hire_writer_btn_open_popup']";
    public String popUpRequest = "//div[@data-atest='atest_writer_popup_customer_request_elem_text']";
    public String createOrderButton = "//a[@data-atest='atest_customer_popup_hire_writer_elem_order_create_link']";
    public String viewOrderButton = "//button[@data-atest='atest_writer_popup_customer_request_elem_view_order_btn']";
    public String userName = "//h3[@class='user-name']";

    public void clickHireButton() {
        app.getHelper().cyclicElementSearchByXpath(hireButton).click();
    }

    public void clickCreateOrder() {
        app.getHelper().cyclicElementSearchByXpath(createOrderButton).click();

    }

    public void viewRequestingOrder() {
        app.getHelper().cyclicElementSearchByXpath(viewOrderButton).click();
    }

    public String writerName(){
        return app.getHelper().cyclicElementSearchByXpath(userName).getText();

    }

}