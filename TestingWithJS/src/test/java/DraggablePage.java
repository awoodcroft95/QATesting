import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraggablePage {

    @FindBy(css = "#ui-id-1")
    private WebElement defaultFuntionality;

    @FindBy(css = "#ui-id-2")
    private WebElement constrainMovement;

    @FindBy(css = "#draggabl")
    private WebElement boxInY;

    @FindBy(css = "#draggabl2")
    private WebElement boxInX;

    @FindBy(css = "#draggable")
    private WebElement dragBox;

    @FindBy(css = "#draggabl3")
    private WebElement constrainedBox;

    @FindBy(css = "#draggabl5")
    private WebElement parentBox;

    @FindBy(css = "#ui-id-3")
    private WebElement cursorStyle;

    @FindBy(css = "#drag2")
    private WebElement topLeftDragBox;

    private WebDriver driver;
    Actions builder;

    public void clickOnDefault(){
        defaultFuntionality.click();
    }

    public void clickConstrain(){
        constrainMovement.click();
    }

    public void clickOnCursorStyle(){cursorStyle.click();}

    public DraggablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    public void dragAction(){
        builder.moveToElement(dragBox).clickAndHold().moveByOffset(50, 50).release().perform();
    }

    public void dragYBox(){ ;
        builder.moveToElement(boxInY).clickAndHold().moveByOffset(50, 50).release().perform();
    }

    public void dragXBox(){
        builder.moveToElement(boxInX).clickAndHold().moveByOffset(50, 50).release().perform();
    }

    public void dragConstrainedBox(){
        builder.moveToElement(constrainedBox).clickAndHold().moveByOffset(550, 30).release().perform();
    }

    public void dragTopLeftBox(){
        builder.moveToElement(topLeftDragBox).clickAndHold().moveByOffset(50, 250).release().perform();
    }

    public int getYLockedX(){
        return boxInY.getLocation().getX();
    }

    public int getYLockedY(){
        return boxInY.getLocation().getY();
    }

    public int getLocationX(){
        return dragBox.getLocation().getX();
    }

    public int getLocationY(){
        return dragBox.getLocation().getY();
    }

    public int getXLockedX(){ return boxInX.getLocation().getX(); }

    public int getXLockedY(){ return boxInX.getLocation().getY(); }

    public int getConX(){
        return constrainedBox.getLocation().getX();
    }

    public int getConY(){
        return constrainedBox.getLocation().getY();
    }

    public int getStyleX(){
        return topLeftDragBox.getLocation().getX();
    }

    public int getStyleY(){
        return topLeftDragBox.getLocation().getY();
    }
}
