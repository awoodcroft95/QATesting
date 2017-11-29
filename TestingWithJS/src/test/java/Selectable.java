import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Selectable {
    private String url = "http://demoqa.com/selectable/";
    private WebDriver webDriver;
    private static ExtentReports report;
    private SelectablePage selectPage;
    private static int screenShotCount = 1;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        String fileName = "MyReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setUp(){
        webDriver = new ChromeDriver();
    }

    @Test
    public void testSelectable(){
        webDriver.navigate().to(url);
        selectPage = new SelectablePage(webDriver);
        webDriver.manage().window().maximize();
        selectPage.clickOnDefault();
        selectPage.clickItem5();
        Assert.assertEquals(true, selectPage.isSelected());
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

    @AfterClass
    public static void cleanUp(){
        report.flush();
    }
}
