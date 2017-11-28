import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GitHubAutoLogin {

    private String url = "http://www.google.com";
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void qaTest() {
        webDriver.navigate().to(url);
        WebElement serachBar = webDriver.findElement(By.cssSelector("#lst-ib"));
        serachBar.sendKeys("GitHub \n");
        WebElement ghLink = webDriver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > h3 > a"));
        ghLink.click();
        webDriver.manage().window().fullscreen();
        WebElement signInLink = webDriver.findElement(By.cssSelector("body > div.position-relative.js-header-wrapper > header > div > div.HeaderMenu.HeaderMenu--bright.d-lg-flex.flex-justify-between.flex-auto > div > span > div > a:nth-child(1)"));
        signInLink.click();
        WebElement logInBox = webDriver.findElement(By.cssSelector("#login_field"));
        logInBox.sendKeys("awoodcroft95");
        WebElement passBox = webDriver.findElement(By.cssSelector("#password"));
        passBox.sendKeys("patrick95");
        WebElement button = webDriver.findElement(By.cssSelector("#login > form > div.auth-form-body.mt-3 > input.btn.btn-primary.btn-block"));
        button.click();
        webDriver.findElement(By.cssSelector("#user-links > li:nth-child(3) > details > summary")).click();
        String value = webDriver.findElement(By.cssSelector("#user-links > li:nth-child(3) > details > ul > li.dropdown-header.header-nav-current-user.css-truncate > strong")).getText();
        String expected = "awoodcroft95";
        Assert.assertEquals(expected, value);
    }
}
