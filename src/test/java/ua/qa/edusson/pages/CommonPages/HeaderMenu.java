package ua.qa.edusson.pages.CommonPages;

import static ua.qa.edusson.tests.TestBase.app;

public class HeaderMenu {

    public static String logOutMain = "//a[@href='/logout']/span";
    public static String logOut2 = "//a[text()='Log out']";
    public static String userMenu = "//div[@class='block__user_controllers']";

    public void userLogOut() {
        if (app.getHelper().isElementPresent("//div[@class='block__user_controllers']")) {
            app.getHelper().cyclicElementSearchByXpath(userMenu).click();

            app.getHelper().waitElement(logOut2);
            app.getHelper().cyclicElementSearchByXpath(logOut2).click();
        } else {
            app.getHelper().cyclicElementSearchByXpath(logOutMain).click();

        }
    }
}