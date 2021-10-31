package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class OpenPage extends CurrencyExchangePage implements Page{

    @FindBy(xpath = "//div[@role = 'tab' and contains(., 'Стандартный курс')]")
    private WebElement defaultCurrencyButton;

    @FindBy(xpath = "//div[@role = 'tab' and contains(., 'Льготный курс')]")
    private WebElement preferentialCurrencyButton;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-defaultRate']//tr[3]/td[2]//span")
    private WebElement defaultEuroBuy;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-defaultRate']//tr[3]/td[4]//span")
    private WebElement defaultEuroSell;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-defaultRate']//tr[2]/td[2]//span")
    private WebElement defaultDollarBuy;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-defaultRate']//tr[2]/td[4]//span")
    private WebElement defaultDollarSell;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-preferentialRate']//tr[3]/td[2]//span")
    private WebElement preferentialEuroBuy;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-preferentialRate']//tr[3]/td[4]//span")
    private WebElement preferentialEuroSell;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-preferentialRate']//tr[2]/td[2]//span")
    private WebElement preferentialDollarBuy;

    @FindBy(xpath = "//div[@id ='rc-tabs-1-panel-preferentialRate']//tr[2]/td[4]//span")
    private WebElement preferentialDollarSell;

    private String bankName = "открытие";

    @Override
    public String getBankName() {
        return bankName;
    }

    public OpenPage() {
        initPage();
        currencies = new HashMap<>();
        collectCurrencies();
    }

    private void collectCurrencies() {
        defaultCurrencyButton.click();
        currencies.put("Худший.Доллар.Покупка", convertToNormalDouble(defaultDollarBuy.getText()));
        currencies.put("Худший.Доллар.Продажа", convertToNormalDouble(defaultDollarSell.getText()));
        currencies.put("Худший.Евро.Покупка", convertToNormalDouble(defaultEuroBuy.getText()));
        currencies.put("Худший.Евро.Продажа", convertToNormalDouble(defaultEuroSell.getText()));
        preferentialCurrencyButton.click();
        currencies.put("Лучший.Доллар.Покупка", convertToNormalDouble(preferentialDollarBuy.getText()));
        currencies.put("Лучший.Доллар.Продажа", convertToNormalDouble(preferentialDollarSell.getText()));
        currencies.put("Лучший.Евро.Покупка", convertToNormalDouble(preferentialEuroBuy.getText()));
        currencies.put("Лучший.Евро.Продажа", convertToNormalDouble(preferentialEuroSell.getText()));
    }

    @Override
    public void preActions() {
        collectCurrencies();
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    private Double convertToNormalDouble(String currencyString) {
        return Double.parseDouble(currencyString.replaceAll(",", ".").trim());
    }
}
