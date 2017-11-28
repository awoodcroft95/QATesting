import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverExample {

    private String url = "http://www.google.com";
    private WebDriver webDriver;

    @Before
    public void setUp(){
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

    @Test
    public void qaTest(){
        webDriver.navigate().to(url);
        WebElement serachBar = webDriver.findElement(By.cssSelector("#lst-ib"));
        serachBar.sendKeys("qa \n");
        WebElement qaLink = webDriver.findElement(By.cssSelector("#rso > div > div > div:nth-child(1) > div > div > h3 > a"));
        qaLink.click();
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.qa.com/";
        Assert.assertEquals(expectedUrl, currentUrl);
    }
}
