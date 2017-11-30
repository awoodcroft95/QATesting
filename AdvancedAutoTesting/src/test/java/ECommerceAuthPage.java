import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ECommerceAuthPage {
    @FindBy(css = "#email_create")
    private WebElement emailInput;

    @FindBy(css = "#SubmitCreate")
    private WebElement submit;

    @FindBy(css = "#create_account_error")
    private WebElement emailAlert;

    @FindBy(css = "#email")
    private WebElement emailSignIn;

    @FindBy(css = "#passwd")
    private WebElement passSignIn;

    @FindBy(css = "#SubmitLogin")
    private WebElement submitLogin;

    public void inputEmail(String input){
        emailInput.sendKeys(input);
    }

    public void clickCreate(){
        submit.click();
    }

    public boolean hasFailed(){
        return emailAlert.isDisplayed();
    }

    public void login(List<String> data){
        emailSignIn.sendKeys(data.get(0));
        passSignIn.sendKeys(data.get(3));
        submitLogin.click();
    }
}
