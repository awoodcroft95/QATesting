import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ECommerceTest {

    private String url = "http://automationpractice.com/index.php";
    private WebDriver webDriver;
    private static ExtentReports report;
    private SpreadSheetReader sSReader;
    private List<String> rowString;
    private ECommerceHome homePage;
    private ECommerceAuthPage loginPage;
    private ECommerceSignUpPage signUpPage;
    private ECommerceShoppingCart cartPage;
    private ECommerceAddressPage addressPage;
    private ECommerceDeliveryPage deliveryPage;
    private ECommercePayment paymentPage;
    private ECommerceConfirm confirmPage;
    private static int testNumber = 0;

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
        sSReader = new SpreadSheetReader((System.getProperty("user.dir")+ File.separatorChar +"IOValues.xlsx"));
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
    public void signUpTest() throws InterruptedException {
        ExtentTest test = report.createTest("Sign Up Test");
        test.log(Status.INFO, "Create an Account");
        test.log(Status.DEBUG, "Create an account taking data from a spreadsheet.");
        rowString = sSReader.readRow(1, "Sheet1");
        webDriver.navigate().to(url);
        homePage = PageFactory.initElements(webDriver, ECommerceHome.class);
        homePage.clickLogin();
        loginPage = PageFactory.initElements(webDriver, ECommerceAuthPage.class);
        loginPage.inputEmail(rowString.get(0));
        loginPage.clickCreate();
        Thread.sleep(3000);
        if (webDriver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=authentication&back=my-account")){
            loginPage.login(rowString);
            test.log(Status.DEBUG, "Account already created with these credentials, logged in instead");
        }else {
            signUpPage = PageFactory.initElements(webDriver, ECommerceSignUpPage.class);
            signUpPage.clickTitle();
            signUpPage.fillForm(rowString);
            signUpPage.selectFromDropDowns();
            signUpPage.submitForm();
            test.log(Status.DEBUG, "Created a new account");
        }
        String expectedResults = rowString.get(1) + " " + rowString.get(2);
        webDriver.navigate().to(url);
        Assert.assertEquals(expectedResults, homePage.getUserLinkText());
        testNumber++;
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot" + testNumber), "Screenshot of end of test" + testNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginToSite(){
        ExtentTest test = report.createTest("Sign In Test");
        test.log(Status.INFO, "Log in to existing account");
        test.log(Status.DEBUG, "Use data from a spreadsheet to log in the the site.");
        rowString = sSReader.readRow(1, "Sheet1");
        webDriver.navigate().to(url);
        homePage = PageFactory.initElements(webDriver, ECommerceHome.class);
        homePage.clickLogin();
        loginPage = PageFactory.initElements(webDriver, ECommerceAuthPage.class);
        loginPage.login(rowString);
        String expectedResults = rowString.get(1) + " " + rowString.get(2);
        webDriver.navigate().to(url);
        Assert.assertEquals(expectedResults, homePage.getUserLinkText());
        testNumber++;
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot"+testNumber), "Screenshot of end of test" + testNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void orderItem() throws InterruptedException {
        ExtentTest test = report.createTest("Select and Order and Item");
        test.log(Status.INFO, "Select an Item from the store, proceed through checkout system.");
        test.log(Status.DEBUG, "Select an item");
        rowString = sSReader.readRow(1, "Sheet1");
        webDriver.navigate().to(url);
        homePage = PageFactory.initElements(webDriver, ECommerceHome.class);
        homePage.clickLogin();
        loginPage = PageFactory.initElements(webDriver, ECommerceAuthPage.class);
        loginPage.login(rowString);
        String expectedResults = rowString.get(1) + " " + rowString.get(2);
        webDriver.navigate().to(url);
        homePage.clickAddToCart();
        Thread.sleep(1000);
        homePage.proceedToCheckoutClick();
        cartPage = PageFactory.initElements(webDriver, ECommerceShoppingCart.class);
        cartPage.clickProceed();
        addressPage = PageFactory.initElements(webDriver, ECommerceAddressPage.class);
        addressPage.clickProceed();
        deliveryPage = PageFactory.initElements(webDriver, ECommerceDeliveryPage.class);
        deliveryPage.clickTOS();
        deliveryPage.clickProceed();
        paymentPage = PageFactory.initElements(webDriver, ECommercePayment.class);
        paymentPage.clickPayButton();
        confirmPage = PageFactory.initElements(webDriver, ECommerceConfirm.class);
        confirmPage.clickConfirm();
        String conformationTet = webDriver.findElement(By.cssSelector("#center_column > div > p > strong")).getText();
        Assert.assertEquals("Your order on My Store is complete.", conformationTet);
        testNumber++;
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot"+testNumber), "Screenshot of end of test" + testNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}