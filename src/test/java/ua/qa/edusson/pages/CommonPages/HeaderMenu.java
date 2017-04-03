package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;

public class HeaderMenu {

    public static String logOutMain = "//a[@href='/logout']/span";
    public static String logOut2 = "//a[text()='Log out']";
    public static String userMenu = "//div[@class='user_logged js_user_logged']";
    public static String userMenuStudyFaq = "//span[@class='menu_title']";
    public static String myBalance = "//ul[@class='user_menu js_user_menu']//a[@href='/customer/balance']";
    public static String orderButton = "//div[@class='uk-button js_order_now_header_btn_action']";

    public void orderNow() {
        WebElement orderNow = app.getHelper().cyclicElementSearchByXpath(orderButton);
        orderNow.click();
    }

    public void userLogOut() {
        String site = app.driver.getCurrentUrl().substring(8, 18);
        if (site.equals("studyfaq.c")) {
            app.getHelper().cyclicElementSearchByXpath(userMenuStudyFaq).click();
            app.getHelper().cyclicElementSearchByXpath(logOut2).click();
        } else {
            boolean found = false;

            String[] sitesWithDropHeaderMenu = {"edusson.com/", "customwriting.com/", "paperial.com/",
                    "essayvikings.com/", "eduzaurus.com/", "studarea.com/", "essaybison.com/",
                    "samedaypapers.com/", "australianwritings.com.au/", "papercp.com/"};
            for (String i : sitesWithDropHeaderMenu) {
                if (i.substring(0, 10).equals(site)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                app.getHelper().cyclicElementSearchByXpath(userMenu).click();
                app.getHelper().cyclicElementSearchByXpath(logOut2).click();

            } else {
                app.getHelper().cyclicElementSearchByXpath(logOutMain).click();
            }

        }
    }

    public void goTOMyBalanceCustomerPage() {
        String site = app.driver.getCurrentUrl().substring(8, 18);
        //System.out.println(site);
        if (site.equals("studyfaq.c")) {
            app.getHelper().cyclicElementSearchByXpath(userMenuStudyFaq).click();
            app.getHelper().cyclicElementSearchByXpath(myBalance).click();
        } else {
            boolean found = false;
            String[] sitesWithDropHeaderMenu = {"edusson.com/", "customwriting.com/", "paperial.com/",
                    "essayvikings.com/", "eduzaurus.com/", "studarea.com/", "essaybison.com/",
                    "samedaypapers.com/", "australianwritings.com.au/", "papercp.com/"};
            for (String i : sitesWithDropHeaderMenu) {
                if (i.substring(0, 10).equals(site)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                app.getHelper().cyclicElementSearchByXpath(userMenu).click();
                app.getHelper().cyclicElementSearchByXpath(myBalance).click();
            } else {
                app.getHelper().cyclicElementSearchByXpath(myBalance).click();
            }

        }
    }

}
