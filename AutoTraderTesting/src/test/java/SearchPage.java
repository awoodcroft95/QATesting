import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {
    @FindBy(css = "#js-known-search-advanced > div.known-search-container__quick-search-section > div.quick-search-section__dual-container.quick-search-section__location-container > input")
    private WebElement postcode;

    @FindBy(css = "#js-known-search-advanced > div.known-search-container__quick-search-section > button")
    private WebElement submit;

    @FindBy(css = "#js-known-search-advanced > div.known-search-container__quick-search-section > div.quick-search-section__invalid-postcode-section.js-invalid-postcode > div.northern-ireland-section__checkbox > label > p")
    private WebElement message;

    //Please enter a valid UK postcode

    public void enterPostCode(String pc){
        postcode.sendKeys(pc);
    }

    public void clickSearch(){
        submit.click();
    }

    public Boolean getMessage(){
        return message.isDisplayed();
    }
}
