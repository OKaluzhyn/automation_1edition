package ua.qa.edusson.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ua.qa.edusson.utils.ApplicationManager;


/**
 * Created by tester on 12.08.2016.
 */
@Listeners(MyTestListener.class)
public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeMethod
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);

    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}
