package ua.qa.edusson.tests.tools;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ua.qa.edusson.utils.ApplicationManager;


/**
 * Created by tester on 12.08.2016.
 */
@Listeners(MyTestListener.class)
public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    public String handleHost;


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @Parameters("site")
    @BeforeTest
    public void chooseSite(@Optional("http://essays.studymoose.com/") String siteName, ITestContext context) throws Exception {
        context.setAttribute("app", app);
        app.driver.get(siteName);
        handleHost = app.driver.getWindowHandle(); //handle first Window
        //closeUnusedTabs();
    }

    @AfterTest
    public void closeUnusedTabs() throws Exception{
        try {
            int handlesCount = app.driver.getWindowHandles().size();
            System.out.println(handlesCount);
            if (handlesCount > 1) {
                app.driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


