package ua.qa.edusson.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.qa.edusson.utils.ApplicationManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by tester on 12.08.2016.
 */
public class TestBase {
    public static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
        app.driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        app.driver.manage().timeouts().implicitlyWait(1200, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}
