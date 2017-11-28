import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginTestPO {

    private String url = "http://thedemosite.co.uk/addauser.php";
    private WebDriver webDriver;
    private AddUserPage addUser;
    private LoginPage login;

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
