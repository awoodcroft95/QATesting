import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoansPage {
    @FindBy(css = "body > div:nth-child(11) > section.top-section > div > form > div.finance-form__inputs > section.finance-form__credit-record.js-finance-credit-score.multi-radio > label.multi-radio-label.info-trigger.js-finance-hover-e.is-selected")
    private WebElement excellent;

    @FindBy(css = "body > div:nth-child(11) > section.top-section > div > form > div.finance-form__inputs > section.finance-form__sliders > div:nth-child(2) > div > div > div.js-range-slider-single-handle.pips-range.single-handle.gui-test-slider-amount.noUi-target.noUi-ltr.noUi-horizontal.noUi-connect > div > div > div.noUi-handle.noUi-handle-lower")
    private WebElement draggableBar1;

    @FindBy(css = "body > div:nth-child(11) > section.top-section > div > form > div.finance-form__inputs > section.finance-form__sliders > div:nth-child(4) > div > div > div.js-range-slider-single-handle.pips-range.single-handle.gui-test-slider-term.noUi-target.noUi-ltr.noUi-horizontal.noUi-connect > div > div > div.noUi-handle.noUi-handle-lower")
    private WebElement draggableBar2;

    @FindBy(css = "body > div:nth-child(11) > section.top-section > div > form > div.finance-form__summary.js-finance-summary > p.finance-form__summary-text > span.js-finance-summary-cost")
    private WebElement cost;

    private WebDriver driver;
    private Actions builder;

    public LoansPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    public void dragBar1(){
        builder.moveToElement(draggableBar1).clickAndHold().moveByOffset(-1000, 0).release().perform();
    }
    public void dragBar2(){
        builder.moveToElement(draggableBar2).clickAndHold().moveByOffset(1000, 0).release().perform();
    }

    public String getCost(){
        return cost.getText();
    }
}
