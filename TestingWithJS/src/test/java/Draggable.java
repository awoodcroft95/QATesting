import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class Draggable {

    private String url = "http://demoqa.com/draggable/";
    private WebDriver webDriver;
    private JavascriptExecutor js;
    private static ExtentReports report;
    private DraggablePage dragPage;


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
    public void defaultFunctionality(){
        ExtentTest test = report.createTest("Default Functionality Test");
        test.log(Status.INFO, "Test to check the default functionality for draggable");
        test.log(Status.DEBUG, "Load the draggable page, click on default functionality, drag the box around, check if it moved.");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        dragPage = new DraggablePage(webDriver);
        dragPage.clickOnDefault();
        dragPage.dragAction();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        report.flush();
    }
}
