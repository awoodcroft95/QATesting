import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECommerceShoppingCart {
    @FindBy(css = "#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")
    private WebElement proceed;

    public void clickProceed(){
        proceed.click();
    }
}
