import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECommerceConfirm {
    @FindBy(css = "#cart_navigation > button")
    private WebElement confirmButton;

    public void clickConfirm(){
        confirmButton.click();
    }
}
