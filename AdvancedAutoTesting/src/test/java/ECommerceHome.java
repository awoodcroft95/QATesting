import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECommerceHome {
    @FindBy(css = "#header > div.nav > div > div > nav > div.header_user_info > a")
    private WebElement loginLink;

    @FindBy(css = "#header > div.nav > div > div > nav > div:nth-child(1) > a > span")
    private WebElement userLinkText;

    @FindBy(css = "#homefeatured > li:nth-child(2) > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    private WebElement addToCart;

    @FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")
    private WebElement proceedToCheckout;

    public void clickLogin(){
        loginLink.click();
    }

    public String getUserLinkText(){
        return userLinkText.getText();
    }

    public void clickAddToCart(){
        addToCart.click();
    }

    public void proceedToCheckoutClick(){
        proceedToCheckout.click();
    }
}
