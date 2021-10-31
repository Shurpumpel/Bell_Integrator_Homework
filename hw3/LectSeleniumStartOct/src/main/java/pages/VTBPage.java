package pages;

import drivers.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.PageUtils;

import java.util.HashMap;

public class VTBPage extends CurrencyExchangePage implements Page{

    private String bankName = "ВТБ";

    @FindBy(xpath = "//div[@class='tab-panel__accord-content']/div[1]//ul[@class='simple-tab__heads-list']//span[contains(.,'В офисе (наличные)')]")
    private WebElement worstCourseButton;

    @FindBy(xpath = "//div[@class='tab-panel__accord-content']/div[1]//ul[@class='simple-tab__heads-list']//span[contains(.,'В офисе (безналично)')]")
    private WebElement bestCourseButton;

    @FindBy(xpath = "//div[@class='tab-panel__accord-content']/div[1]//section[1]//div[@class='simple-tab__tab ng-scope'][1]//table//tbody/tr[2]//td[2]//span")
    private WebElement dollarBuy;

    @FindBy(xpath = "//div[@class='tab-panel__accord-content']/div[1]//section[1]//div[@class='simple-tab__tab ng-scope'][1]//table//tbody/tr[2]//td[3]//span")
    private WebElement dollarSell;

    @FindBy(xpath = "//div[@class='tab-panel__accord-content']/div[1]//section[1]//div[@class='simple-tab__tab ng-scope'][1]//table//tbody/tr[3]//td[2]//span")
    private WebElement euroBuy;

    @FindBy(xpath = "//div[@class='tab-panel__accord-content']/div[1]//section[1]//div[@class='simple-tab__tab ng-scope'][1]//table//tbody/tr[3]//td[3]//span")
    private WebElement euroSell;

    public VTBPage(){
        initPage();
        currencies = new HashMap<>();
        collectCurrencies();
    }

    private void collectCurrencies(){
        clickButton(bestCourseButton);
        currencies.put("Лучший.Доллар.Покупка", convertToNormalDouble(dollarBuy.getText()));
        currencies.put("Лучший.Доллар.Продажа", convertToNormalDouble(dollarSell.getText()));
        currencies.put("Лучший.Евро.Покупка", convertToNormalDouble(euroBuy.getText()));
        currencies.put("Лучший.Евро.Продажа", convertToNormalDouble(euroSell.getText()));
        clickButton(worstCourseButton);
        currencies.put("Худший.Доллар.Покупка", convertToNormalDouble(dollarBuy.getText()));
        currencies.put("Худший.Доллар.Продажа", convertToNormalDouble(dollarSell.getText()));
        currencies.put("Худший.Евро.Покупка", convertToNormalDouble(euroBuy.getText()));
        currencies.put("Худший.Евро.Продажа", convertToNormalDouble(euroSell.getText()));

    }

    private void clickButton(WebElement button){
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        actions.moveToElement(button);
        actions.click().perform();
    }

    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public void preActions() {
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    private Double convertToNormalDouble(String str){
        String str1 = str.replaceAll("[^\\d.,]", "");
        str1 = str1.replaceAll(",",".");
        return Double.parseDouble(str1);
    }

}

//*[@id="n9bktkipnb-0"]/a/span
//*[@id="77dk59isaso-0"]/a/span