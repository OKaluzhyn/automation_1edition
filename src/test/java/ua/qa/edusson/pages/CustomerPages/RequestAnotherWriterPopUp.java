package ua.qa.edusson.pages.CustomerPages;

import static ua.qa.edusson.tests.TestBase.app;

/**
 * Created by tester on 27.09.2016.
 */
public class RequestAnotherWriterPopUp {
    public static String textFieldReason = "//textarea[@data-atest='atest_order_popup_reassign_writer_elem_reason']";
    public static String submitButton = "//button[@data-atest = 'atest_order_popup_reassign_writer_elem_reassign_btn']";


    public void typeReason(){
        app.getHelper().cyclicElementSearchByXpath(textFieldReason).sendKeys("test reassign");
    }

    public void submitReassign(){
        app.getHelper().cyclicElementSearchByXpath(submitButton).click();
    }
}
