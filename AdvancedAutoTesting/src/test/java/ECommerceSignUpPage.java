import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ECommerceSignUpPage {
    @FindBy(css = "#account-creation_form > div:nth-child(1) > div.clearfix > div:nth-child(3) > label")
    private WebElement titleRadio;

    @FindBy(css = "#customer_firstname")
    private WebElement firstNameBox;

    @FindBy(css = "#customer_lastname")
    private WebElement lastNameBox;

    @FindBy(css = "#email")
    private WebElement emailBox;

    @FindBy(css = "#passwd")
    private WebElement passwordBox;

    @FindBy(css = "#firstname")
    private WebElement firstNameBox2;

    @FindBy(css = "#lastname")
    private WebElement lastNameBox2;

    @FindBy(css = "#address1")
    private WebElement addressLine1;

    @FindBy(css = "#city")
    private WebElement city;

    @FindBy(css = "#postcode")
    private WebElement postCode;

    @FindBy(css = "#phone_mobile")
    private WebElement phoneNo;

    @FindBy(css = "#alias")
    private WebElement addressAlias;

    @FindBy(css = "#submitAccount")
    private WebElement submit;

    @FindBy(css = "#id_state")
    private WebElement stateSelector;

    @FindBy(css = "#id_country")
    private WebElement countrySelector;

    public void clickTitle(){
        titleRadio.click();
    }

    public void fillForm(List<String> data){
        firstNameBox.sendKeys(data.get(1));
        lastNameBox.sendKeys(data.get(2));
        //emailBox.sendKeys(data.get(0));
        passwordBox.sendKeys(data.get(3));
        firstNameBox2.sendKeys(data.get(1));
        lastNameBox2.sendKeys(data.get(2));
        addressLine1.sendKeys(data.get(4));
        city.sendKeys(data.get(5));
        postCode.sendKeys(data.get(6).substring(0, data.get(6).length()-2));
        phoneNo.sendKeys(data.get(7));
        addressAlias.sendKeys(data.get(8));
    }

    public void selectFromDropDowns(){
        Select state = new Select(stateSelector);
        state.selectByVisibleText("California");
        Select country = new Select(countrySelector);
        country.selectByVisibleText("United States");
    }

    public void submitForm(){
        submit.click();
    }

}
