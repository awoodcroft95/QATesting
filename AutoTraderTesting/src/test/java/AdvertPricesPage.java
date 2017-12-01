import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvertPricesPage {

    @FindBy(css = "body > div.rate-card > div.content-container > section.rate-card__container.js-container.is-gb > section.rate-card__region.rate-card__gb.js-region-container > section > ul > li.tabs-item.js-tab.is-active")
    private WebElement up1K;

    @FindBy(css = "body > div.rate-card > div.content-container > section.rate-card__container.js-container.is-gb > section.rate-card__region.rate-card__gb.js-region-container > div > div.rate-card__package.is-bargain.Premium.js-package > p.rate-card--tagline > span:nth-child(1)")
    private WebElement fourTimes;

    public void clickTab(){
        up1K.click();
    }

    public String getPrice(){
        System.out.println(fourTimes.getText());
        return fourTimes.getText();
    }
}
