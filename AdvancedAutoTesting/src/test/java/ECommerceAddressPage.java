import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECommerceAddressPage {
    @FindBy(css = "#center_column > form > p > button")
    private WebElement proceed;

    public void clickProceed(){
        proceed.click();
    }
}
