package ua.qa.edusson.pages.CustomerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;


public class OrderCreateCustomerPage {


    public static String orderPageName = "//html/body/div[6]/span/span/div/div[1]/div/h1";
    public static String typeOfPaper = "//div//div[@data-atest='atest_order_create_form_type']";
    public static String topic = "//input[@data-atest='atest_order_create_form_name']";
    public static String subject = "//div[@data-atest='atest_order_create_form_subject']";
    public static String numberOfPages = "//input[@data-atest='atest_order_create_form_pages']";
    public static String deadline = "//input[@data-atest='atest_order_create_form_deadline']";
    public static String nextButton1 = "//div[@id='step-1']//button[@data-atest='atest_order_create_elem_next_btn']";

    //public static String typeOfService = "//*[@id='step-2']/div/div[1]/label[1]/button";
    //public static String writerLevel = "//*[@id='step-2']/div/div[2]/label[3]/span";
    public static String numOfCitation = "//input[@data-atest='atest_order_create_form_sources']";
    public static String formatOfCitation = "//div[@data-atest='atest_order_create_form_style']";
    public static String nextButton2 = "//div[@id='step-2']//button[@data-atest='atest_order_create_elem_next_btn']";
    public static String paperInstruction = "//textarea[@data-atest='atest_order_create_form_description']";
    public static String uploadFiles = "//a[@data-atest='atest_order_create_elem_file_download']";
    public static String nextButton3 = "//div[@id='step-3']//button[@data-atest='atest_order_create_elem_next_btn']";

    public static String vas1 = "//label[@data-atest='atest_order_create_elem_vas_1']";
    public static String startBiddingButton = "//button[@data-atest='atest_order_create_form_submit']";

    //order edit
    public static String saveChangesButton = "//button[@data-atest='atest_order_create_form_submit']";
    public static String discardChangesButton = "//a[@data-atest='atest_order_edit_elem_discard_changes_btn']";
    public static String cancelOrderButton = "//a[@data-atest='atest_order_view_in_progress_elem_cancel_order']";

    // requested writer
    public static String writerName = "//p[@class='writer_name']";

    public static String vasBestWriter = "//label[@data-atest='atest_order_create_elem_vas_1']";

    // select type
    public void selectTypeOfPaper() {
        app.getHelper().randomChoiceFromDropdown("//select[@data-atest='atest_order_create_form_type']/option[@value]");

    }

    // order topic
    public void setTopic(String strTopic) {
        WebElement order_topic = app.getHelper().cyclicElementSearchByXpath(topic);
        order_topic.sendKeys(strTopic);
    }

    // select subject
    public void selectSubject() {
        app.getHelper().randomChoiceFromDropdown("//select[@data-atest='atest_order_create_form_subject']/option[@value]");

    }

    // click next button1
    public void clickNext1() {
        //app.getHelper().waitElement(nextButton1);
        WebElement next_button_1 = app.getHelper().cyclicElementSearchByXpath(nextButton1);
        next_button_1.click();
    }

    // number jf citation
    public void setnumOfCitation() {
        WebElement citation_number = app.getHelper().cyclicElementSearchByXpath(numOfCitation);
        citation_number.clear();
        citation_number.sendKeys("3");
    }

    // format of citation
    public void selectformatOfCitation() {
        app.getHelper().randomChoiceFromDropdown("//select[@data-atest='atest_order_create_form_style']/option[@value]");

    }

    // click next button2
    public void clickNext2() {
        //app.getHelper().waitElement(nextButton2);
        WebElement next_button_2 = app.getHelper().cyclicElementSearchByXpath(nextButton2);
        next_button_2.click();

    }

    // set paper description
    public void orderDescription(String strDescription) {
        //app.getHelper().waitElement(paperInstruction);
        WebElement paper_description = app.getHelper().cyclicElementSearchByXpath(paperInstruction);
        paper_description.sendKeys(strDescription);
    }


    // click start bidding button
    public void proceedToBidding() {
        //app.getHelper().waitElement(startBiddingButton);
        WebElement bidding_button = app.getHelper().cyclicElementSearchByXpath(startBiddingButton);
        bidding_button.click();
    }


    public void createOrder(String siteUrl, String strTopic, String strDescription) {
        if (!siteUrl.equals("http://studyfaq.com/")) {
            this.setTopic(strTopic);
            this.clickNext1();
           // this.selectformatOfCitation();
            //this.setnumOfCitation();
           // app.getHelper().sleep(1);
            this.clickNext2();
            this.orderDescription(strDescription);
            app.getHelper().attachFile(By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
            //app.getHelper().waitElement("//*[text()='testFile.pdf']");
            //app.getHelper().waitElement(startBiddingButton);
            this.proceedToBidding();
        } else {
            this.selectTypeOfPaper();
            this.setTopic(strTopic);
            this.orderDescription(strDescription);
            app.getHelper().attachFile(By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
            this.proceedToBidding();
        }
    }
    public void createOrderWithVasChooseBestWriter(String siteUrl, String strTopic, String strDescription) {
        if (!siteUrl.equals("http://studyfaq.com/")) {
            this.setTopic(strTopic);
            this.clickNext1();
           // this.selectformatOfCitation();
          //  this.setnumOfCitation();
            app.getHelper().sleep(1);
            this.clickNext2();
            this.orderDescription(strDescription);
            app.getHelper().attachFile(By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
            this.choseBestWriter();
            this.proceedToBidding();
        } else {
            this.selectTypeOfPaper();
            this.setTopic(strTopic);
            this.orderDescription(strDescription);
            app.getHelper().attachFile(By.xpath("//input[@class='dz-hidden-input']"), app.getHelper().getRevision().getAbsolutePath());
            this.choseBestWriter();
            this.proceedToBidding();
        }
    }

    private void choseBestWriter() {
        WebElement checkBoxVasBestWriter = app.getHelper().cyclicElementSearchByXpath(vasBestWriter);
        checkBoxVasBestWriter.click();
    }

    public void saveChanges() {
        WebElement save_changes_button = app.getHelper().cyclicElementSearchByXpath(saveChangesButton);
        save_changes_button.click();
    }

    public void discardChanges() {
        WebElement discard_changes_button = app.getHelper().cyclicElementSearchByXpath(discardChangesButton);
        discard_changes_button.click();
    }

    public void clickCancelOrderButton() {
        WebElement cancel_order_button = app.getHelper().cyclicElementSearchByXpath(cancelOrderButton);
        cancel_order_button.click();
    }


    public void editOrder(String strNewTopic) {
        this.setTopic(strNewTopic);
        this.saveChanges();
    }

    private void clickNext3() {
        WebElement next_button_3 = app.getHelper().cyclicElementSearchByXpath(nextButton3);
        next_button_3.click();
    }

    public String requestedWriter() {
        return app.getHelper().cyclicElementSearchByXpath(writerName).getText();
    }


}


