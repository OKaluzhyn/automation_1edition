package ua.qa.edusson.pages.CommonPages;

import static ua.qa.edusson.tests.TestBase.app;

public class HeaderMenu {

    public static String logOutMain = "//a[@href='/logout']/span";
    public static String logOut2 = "//a[text()='Log out']";
    public static String userMenu = "//div[@class='block__user_controllers']";


    public void userLogOut() {
        String site = app.driver.getCurrentUrl().substring(7, 17);
        // System.out.println(site);
        boolean found = false;

        String[] sitesWithDropHeaderMenu = {"customwriting.com/", "essays.studymoose.com/", "paperial.com/",
                "essayvikings.com/", "eduzaurus.com/", "studarea.com/", "essaybison.com/",
                "samedaypapers.com/", "studyfaq.com/", "australianwritings.com.au/", "papercp.com/"};
        for (String i : sitesWithDropHeaderMenu) {
            //  System.out.println((i).substring(0,10));

            if (i.substring(0, 10).equals(site)) {
                found = true;
                break;
            }
        }
        if (found) {
            app.getHelper().cyclicElementSearchByXpath(userMenu).click();
           // app.getHelper().waitElement(logOut2);
            app.getHelper().cyclicElementSearchByXpath(logOut2).click();
        } else {
            //app.getHelper().waitElement(logOutMain);
            app.getHelper().cyclicElementSearchByXpath(logOutMain).click();
        }

    }
}
