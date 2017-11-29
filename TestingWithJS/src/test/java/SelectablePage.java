import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectablePage {
    @FindBy(css = "#ui-id-1")
    private WebElement defaultSelectable;

    @FindBy(css = "#selectable > li:nth-child(5)")
    private WebElement item5;

    private WebDriver driver;
    Actions builder;

    public SelectablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    public void clickOnDefault(){
        defaultSelectable.click();
    }

    public void clickItem5(){
        item5.click();
    }

    public boolean isSelected(){
        System.out.println(item5.getCssValue("background"));
        if (item5.getCssValue("background") == "#F39814"){
            return true;
        } else {
            return false;
        }
    }
}
