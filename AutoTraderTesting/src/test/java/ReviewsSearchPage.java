import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ReviewsSearchPage {
    @FindBy(css = "#reviewTypeOwnerMake")
    private WebElement make;

    @FindBy(css = "#reviewTypeOwnerModel")
    private WebElement model;

    @FindBy(css = "#reviewTypeOwnerBodytype")
    private WebElement type;

    @FindBy(css = "#findReviews")
    private WebElement button;

    public void selectAllDropDowns(String carMake, String carModel, String carType){
        Select makeSelector = new Select(make);
        makeSelector.selectByValue(carMake);
        Select modelSelector = new Select(model);
        modelSelector.selectByValue(carModel);
        Select typeSelector = new Select(type);
        typeSelector.selectByValue(carType);
    }

    public void clickButton(){
        button.click();
    }
}
