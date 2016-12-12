package ua.qa.edusson.pages.CommonPages;

import static ua.qa.edusson.tests.TestBase.app;

public class HeaderMenu {

    public static String logOutMain = "//a[@href='/logout']/span";
    public static String logOut2 = "//a[text()='Log out']";
    public static String userMenu = "//div[@class='block__user_controllers']";


    public void userLogOut() {
        String site = app.driver.getCurrentUrl();
       // System.out.println(site);
        boolean found = false;

        String[] sitesWithDropHeaderMenu = {
                "http://essayvikings.com/", "http://eduzaurus.com/", "http://studarea.com/", "http://essaybison.com/",
                "http://samedaypapers.com/", "http://studyfaq.com/", "http://australianwritings.com.au/", "http://papercp.com/"};
        for (String i : sitesWithDropHeaderMenu) {
           // System.out.println(i);
            if (i.startsWith(site)) {
                found = true;
                break;
            }
        }
        if (found){
                app.getHelper().cyclicElementSearchByXpath(userMenu).click();
                app.getHelper().waitElement(logOut2);
                app.getHelper().cyclicElementSearchByXpath(logOut2).click();
            } else {
                app.getHelper().cyclicElementSearchByXpath(logOutMain).click();
            }

        }
    }
