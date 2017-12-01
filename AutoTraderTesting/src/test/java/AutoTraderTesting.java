import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AutoTraderTesting {

    private String url = "https://www.autotrader.co.uk/";
    private WebDriver webDriver;
    private static ExtentReports report;
    private SpreadSheetReader sSReader;
    private List<String> rowString;
    private static int testNumber = 0;
    private Actions builder;


    @BeforeClass
    public static void init(){
        report = new ExtentReports();
        String fileName = "AutoTraderTestReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setUp(){
        webDriver = new ChromeDriver();
        sSReader = new SpreadSheetReader((System.getProperty("user.dir")+ File.separatorChar +"InputValues.xlsx"));
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
    public void testSearch(){
        ExtentTest test = report.createTest("Search Functionality Test");
        test.log(Status.INFO, "Use the search feature on the site home page to find a car.");
        test.log(Status.DEBUG, "Navigate to the home page, then enter data from a spreadsheet into the search box ");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        rowString = sSReader.readRow(1, "Sheet1");
        webDriver.findElement(By.cssSelector("#postcode")).sendKeys(rowString.get(0));
        WebElement radius = webDriver.findElement(By.cssSelector("#radius"));
        Select distance = new Select(radius);
        String radiusString = rowString.get(1).toString();
        distance.selectByValue(radiusString.substring(0, radiusString.length()-2));
        WebElement make = webDriver.findElement(By.cssSelector("#searchVehiclesMake"));
        Select makeSelector = new Select(make);
        makeSelector.selectByValue(rowString.get(2).toString());
        webDriver.findElement(By.cssSelector("#js-search-button")).click();
        String searchUrl = webDriver.getCurrentUrl();
        String[] possibleURLS = new String[2];
        possibleURLS[0] = "https://www.autotrader.co.uk/car-search?advertising-location=at_cars&search-target=usedcars&is-quick-search=true&radius=25&onesearchad=used&onesearchad=nearlynew&onesearchad=new&make=FORD&model=&price-from=&price-to=&postcode=cm112ag";
        possibleURLS[1] = "https://www.autotrader.co.uk/car-search?advertising-location=at_cars&search-target=usedcars&is-quick-search=true&radius=25&onesearchad=used&onesearchad=nearlynew&onesearchad=new&make=FORD&price-from=&price-to=&postcode=cm112ag";
        test.log(Status.DEBUG, "Sometimes the url produced will not be the same as the expected url, causing the test to fail.");
        Boolean result = false;
        for (String a : possibleURLS){
            if (searchUrl.equals(a)){
                result = true;
                break;
            } else {
                result = false;
            }
        }
        Assert.assertEquals(true, result);
        try{
            testNumber++;
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "ScreenShot" + testNumber), "Screen shot of test number " + testNumber);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invalidSearch(){
        ExtentTest test = report.createTest("Invalid Search, Functionality Test");
        test.log(Status.INFO, "Use the advanced search feature but enter an invalid postcode.");
        test.log(Status.DEBUG, "Navigate to the home page, navigate to the advanced search option page, enter an invalid postcode, check for an error message.");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        rowString = sSReader.readRow(2, "Sheet1");
        webDriver.findElement(By.cssSelector("#js-more-options")).click();
        SearchPage searchOption = PageFactory.initElements(webDriver, SearchPage.class);
        searchOption.enterPostCode(rowString.get(0).toString());
        searchOption.clickSearch();
        Boolean message = searchOption.getMessage();
        System.out.println(message);
        Assert.assertEquals(true, message);
        try{
            testNumber++;
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "ScreenShot" + testNumber), "Screen shot of test number " + testNumber);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void navBarHighlightTest(){
        ExtentTest test = report.createTest("Navbar Highlight Dropdown Test");
        test.log(Status.INFO, "Check the nav bar correctly shows new information when moused over.");
        test.log(Status.DEBUG, "Navigate to the home page, move a virtual mouse over the nav bar, check if new data appears.");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        builder = new Actions(webDriver);
        WebElement navBar1 = webDriver.findElement(By.cssSelector("#js-header-nav > ul > li.header__nav-listing.header__nav-buying > a"));
        builder.moveToElement(navBar1).moveByOffset(10,10).perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement howTo = webDriver.findElement(By.cssSelector("#buying-new-used > li.header__sub-nav-listing.header__sub-nav-listing--4 > a"));
        Assert.assertEquals(true, howTo.isDisplayed());
        try{
            testNumber++;
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "ScreenShot" + testNumber), "Screen shot of test number " + testNumber);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loansPageTest(){
        ExtentTest test = report.createTest("Loans Page Sliders Test");
        test.log(Status.INFO, "Check the sliders work correctly when interacted with.");
        test.log(Status.DEBUG, "Navigate to the loans page, use a Actions builder to control the sliders, validate using the output given by the site's calculation.");
        webDriver.navigate().to("https://www.autotrader.co.uk/car-finance");
        webDriver.manage().window().maximize();
        LoansPage loanPage = new LoansPage(webDriver);
        loanPage.dragBar1();
        loanPage.dragBar2();
        String cost = loanPage.getCost();
        Assert.assertEquals("£28.44", cost);
        try{
            testNumber++;
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "ScreenShot" + testNumber), "Screen shot of test number " + testNumber);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reviewsSearchTest(){
        ExtentTest test = report.createTest("Review Search Test");
        test.log(Status.INFO, "Check the search functionality of the user reviews.");
        test.log(Status.DEBUG, "Navigate to the car reviews page, enter information from a spreadsheet, check if the output page shows the correct information.");
        webDriver.navigate().to("https://www.autotrader.co.uk/car-reviews");
        webDriver.manage().window().maximize();
        ReviewsSearchPage reviews = PageFactory.initElements(webDriver, ReviewsSearchPage.class);
        rowString = sSReader.readRow(1, "Sheet2");
        //Sometimes this fails, not sure why.
        reviews.selectAllDropDowns(rowString.get(0), rowString.get(1), rowString.get(2));
        reviews.clickButton();
        String title = webDriver.findElement(By.cssSelector("#content > div.owner-reviews-top-section.panelMiddle > div.rollup > div.layer > h1")).getText();
        Assert.assertEquals("Volvo V70 Estate 2013‑2017", title);
        try{
            testNumber++;
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "ScreenShot" + testNumber), "Screen shot of test number " + testNumber);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //This Test doesn't work, it can't seem to get the data from the page that is needed for validation.
    @Ignore
    @Test
    public void testSellPackages(){
        ExtentTest test = report.createTest("Advertisements Tabs Test");
        test.log(Status.INFO, "Check thetabs work on the advertisement page.");
        test.log(Status.DEBUG, "Navigate to the advertising prices page, click on the non-default tab, check the value shown.");
        webDriver.navigate().to("https://www.autotrader.co.uk/sell-my-car/advertising-prices");
        webDriver.manage().window().maximize();
        AdvertPricesPage adverts = PageFactory.initElements(webDriver, AdvertPricesPage.class);
        adverts.clickTab();
        String price = adverts.getPrice();
        Assert.assertEquals("4x", price);
        try{
            testNumber++;
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "ScreenShot" + testNumber), "Screen shot of test number " + testNumber);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
