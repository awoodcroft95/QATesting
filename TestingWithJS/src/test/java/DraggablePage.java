import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraggablePage {

    @FindBy(css = "#ui-id-1")
    private WebElement defaultFuntionality;

    @FindBy(css = "#draggable")
    private WebElement dragBox;

    private WebDriver driver;
    private JavascriptExecutor js;

    public void clickOnDefault(){
        defaultFuntionality.click();
    }

    public DraggablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setupJS(){
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor)driver;
        }
    }

    public void dragAction(){
        Actions builder = new Actions(driver);
        builder.moveByOffset(dragBox.getLocation().getY()+5, dragBox.getLocation().getY()+5).perform();
        builder.clickAndHold().moveByOffset(50, 50).release().perform();
    }

    public int getLocationX(){
        return dragBox.getLocation().getX();
    }

    public int getLocationY(){
        return dragBox.getLocation().getY();
    }
}
