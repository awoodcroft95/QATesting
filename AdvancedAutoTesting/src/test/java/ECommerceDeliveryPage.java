import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECommerceDeliveryPage {
    @FindBy(css = "#uniform-cgv")
    private WebElement termsOfService;

    @FindBy(css = "#form > p > button")
    private WebElement proceed;

    public void clickTOS(){
        termsOfService.click();
    }

    public void clickProceed(){
        proceed.click();
    }
}
