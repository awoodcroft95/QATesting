import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class LoginTestPOReports {

    private String url = "http://thedemosite.co.uk/addauser.php";
    private WebDriver webDriver;
    private AddUserPage addUser;
    private LoginPage login;
    private static ExtentReports report;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        String fileName = "MyReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        report.flush();
    }

    @Test
    public void qaTest() {
        ExtentTest test = report.createTest("MyFirstTest");
        test.log(Status.INFO, "My First Test is Starting ");
        test.log(Status.DEBUG, "Navigating to URL");
        webDriver.navigate().to(url);
        addUser = PageFactory.initElements(webDriver, AddUserPage.class);
        String userName = "superUser";
        String passWord = "uberPass";
        addUser.setUserAndPass(userName, passWord);
        addUser.clickLink();
        login = PageFactory.initElements(webDriver, LoginPage.class);
        login.loginUser(userName, passWord);
        String confirmation = login.getConfirmation();
        Assert.assertEquals("**Successful Login**", confirmation);
    }
}
