import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Draggable {

    private String url = "http://demoqa.com/draggable/";
    private WebDriver webDriver;
    private JavascriptExecutor js;
    private static ExtentReports report;
    private DraggablePage dragPage;
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
        js = (JavascriptExecutor)webDriver;
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
        int x = dragPage.getLocationX();
        int y = dragPage.getLocationY();
        dragPage.dragAction();
        int endX = dragPage.getLocationX();
        int endY = dragPage.getLocationY();
        Assert.assertEquals(x + 50, endX);
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot" + screenShotCount), "Screenshot of end of test" + screenShotCount);
            screenShotCount ++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void constrainTest1() {
        ExtentTest test = report.createTest("Constrain Movement Test 1");
        test.log(Status.INFO, "Test to check each of the 2 axis controlled draggable boxes do the correct thing.");
        test.log(Status.DEBUG, "Check if the vertical only box can only be moved in the y axis, do this by attempting to move the box in both the X and Y axis.");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        dragPage = new DraggablePage(webDriver);
        dragPage.clickConstrain();
        int x = dragPage.getYLockedX();
        int y = dragPage.getYLockedY();
        dragPage.dragYBox();
        int endY = dragPage.getYLockedY();
        int endX = dragPage.getYLockedX();
        boolean vertResult;
        if (x == endX && endY == y + 50){
            vertResult = true;
        } else { vertResult = false; }

        test.log(Status.DEBUG, "Check if the horizontal only box can only be moved in the x axis, do this by attempting to move it in both the X and Y axis.");
        int xOfX = dragPage.getXLockedX();
        int yOfX = dragPage.getXLockedY();
        dragPage.dragXBox();
        int xOfXEnd = dragPage.getXLockedX();
        int yOfXEnd = dragPage.getXLockedY();
        boolean hozResult;
        if (xOfXEnd == xOfX + 50 && yOfX == yOfXEnd){
            hozResult = true;
        } else { hozResult = false;}

        boolean completeResult;
        if(vertResult && hozResult){
            completeResult = true;
        } else { completeResult = false; }

        Assert.assertEquals(true, completeResult);
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot" + screenShotCount), "Screenshot of end of test" + screenShotCount);
            screenShotCount ++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void constrainTest2(){
        ExtentTest test = report.createTest("Constrain Movement Test 2");
        test.log(Status.INFO, "Test to check each of the 2 dom element controlled draggable boxes do the correct thing.");
        test.log(Status.DEBUG, "Test to check if the contained box can move all the way to the bottom right corner.");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        dragPage = new DraggablePage(webDriver);
        dragPage.clickConstrain();
        dragPage.dragConstrainedBox();
        boolean result;
        if (dragPage.getConX() == 1090 && dragPage.getConY() == 579){
            result = true;
        } else { result = false; }
        Assert.assertEquals(true, result);
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot" + screenShotCount), "Screenshot of end of test" + screenShotCount);
            screenShotCount ++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cursorTest(){
        ExtentTest test = report.createTest("Cursor Style Test");
        test.log(Status.INFO, "Test to check if the box moves properly do the correct thing.");
        test.log(Status.DEBUG, "Test to check if the element that moves the mouse moves correctly when moved.");
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        dragPage = new DraggablePage(webDriver);
        dragPage.clickOnCursorStyle();
        int x, y;
        x = dragPage.getStyleX();
        y = dragPage.getStyleY();
        dragPage.dragTopLeftBox();
        boolean result;
        if (x+105 == dragPage.getStyleX() && y+305 == dragPage.getStyleY()){
            result = true;
        } else {
            result = false;
        }
        Assert.assertEquals(true, result);
        try {
            test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "screenShot" + screenShotCount), "Screenshot of end of test" + screenShotCount);
            screenShotCount ++;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
