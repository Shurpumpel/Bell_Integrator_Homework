package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;

public class SberBankPage extends CurrencyExchangePage implements Page {

    @FindBy(xpath = "//div[@class='rates-main-widget__wrapper-dropdown']//div[@data-test-id='SelectControl_Button-service']")
    private WebElement chooseCurrencyButton;

    @FindBy(xpath = "//ul[@class='kitt-dropdown-list__list']//span[contains(.,'Без пакета услуг')]")
    private WebElement worstCurrency;

    @FindBy(xpath = "//ul[@class='kitt-dropdown-list__list']//span[contains(.,'СберБанк Первый')]")
    private WebElement bestCurrency;

    @FieldName("Покупка евро")
    @FindBy(xpath = "//div[@data-id='sbol']//table[@aria-labelledby='rates-actual-table']//tr[7]//td[4]//div[@aria-hidden]")
    private WebElement euroBuy;

    @FieldName("Продажа евро")
    @FindBy(xpath = "//div[@data-id='sbol']//table[@aria-labelledby='rates-actual-table']//tr[7]//td[3]//div[@aria-hidden]")
    private WebElement euroSell;

    @FieldName("Покупка доллара")
    @FindBy(xpath = "//div[@data-id='sbol']//table[@aria-labelledby='rates-actual-table']//tr[2]//td[4]//div[@aria-hidden]")
    private WebElement dollarBuy;

    @FieldName("Продажа доллара")
    @FindBy(xpath = "//div[@data-id='sbol']//table[@aria-labelledby='rates-actual-table']//tr[2]//td[3]//div[@aria-hidden]")
    private WebElement dollarSell;

    private String bankName = "сбербанк";

    @Override
    public String getBankName() {
        return bankName;
    }

    public SberBankPage() {
        initPage();
        currencies = new HashMap<>();
    }

    private void collectCurrencies() {
        chooseCurrency("worst");
        currencies.put("Худший.Доллар.Покупка", convertToNormalDouble(dollarBuy.getText()));
        currencies.put("Худший.Доллар.Продажа", convertToNormalDouble(dollarSell.getText()));
        currencies.put("Худший.Евро.Покупка", convertToNormalDouble(euroBuy.getText()));
        currencies.put("Худший.Евро.Продажа", convertToNormalDouble(euroSell.getText()));
        chooseCurrency("best");
        currencies.put("Лучший.Доллар.Покупка", convertToNormalDouble(dollarBuy.getText()));
        currencies.put("Лучший.Доллар.Продажа", convertToNormalDouble(dollarSell.getText()));
        currencies.put("Лучший.Евро.Покупка", convertToNormalDouble(euroBuy.getText()));
        currencies.put("Лучший.Евро.Продажа", convertToNormalDouble(euroSell.getText()));
    }



    private void chooseCurrency(String bestOrWorst){
        chooseCurrencyButton.click();
        switch (bestOrWorst){
            case "best":
                bestCurrency.click();
                break;
            case "worst":
                worstCurrency.click();
                break;
            default:
        }
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
