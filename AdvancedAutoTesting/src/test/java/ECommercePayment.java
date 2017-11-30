import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECommercePayment {
    @FindBy(css = "#HOOK_PAYMENT > div:nth-child(1) > div > p > a")
    private WebElement payButton;

    public void clickPayButton(){
        payButton.click();
    }
}
