package pages;

import drivers.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageUtils;

import java.util.HashMap;

public class AlfaBankPage extends CurrencyExchangePage implements Page{

    private String bankName = "альфа-банк";

    @FindBy(xpath = "//div[@class='a2TNHD']//div[@class='b2TNHD c2TNHD'][1]//button[2]")
    private WebElement dollarButton;

    @FindBy(xpath = "//div[@class='a2TNHD']//div[@class='b2TNHD c2TNHD'][1]//button[1]")
    private WebElement euroButton;

    @FindBy(xpath = "//div[@class='a2TNHD']//div[@class='b2TNHD c2TNHD'][2]//button[1]//p")
    private WebElement worstCourseButton;

    @FindBy(xpath = "//div[@class='a2TNHD']//div[@class='b2TNHD c2TNHD'][2]//button[3]//p")
    private WebElement bestCourseButton;

    @FieldName("currencyBuy")
    @FindBy(xpath = "//table[@class='a2Yxgp']//tbody//tr[1]//td[3]//p")
    private WebElement currencyBuy;

    @FindBy(xpath = "//table[@class='a2Yxgp']//tbody//tr[1]//td[4]//p")
    private WebElement currencySell;


    public AlfaBankPage() {
        initPage();
        currencies = new HashMap<>();
        PageUtils.waitUntilElementBeVisible(currencyBuy);
        collectCurrencies();
    }

    private void collectCurrencies(){
        clickButton(bestCourseButton);
        clickButton(dollarButton);
//        bestCourseButton.click();
//        dollarButton.click();
        currencies.put("Лучший.Доллар.Покупка", convertToNormalDouble(currencyBuy.getText()));
        currencies.put("Лучший.Доллар.Продажа", convertToNormalDouble(currencySell.getText()));
//        euroButton.click();
        clickButton(euroButton);
        currencies.put("Лучший.Евро.Покупка", convertToNormalDouble(currencyBuy.getText()));
        currencies.put("Лучший.Евро.Продажа", convertToNormalDouble(currencySell.getText()));
//        worstCourseButton.click();
//        dollarButton.click();
        clickButton(worstCourseButton);
        clickButton(dollarButton);
        currencies.put("Худший.Доллар.Покупка", convertToNormalDouble(currencyBuy.getText()));
        currencies.put("Худший.Доллар.Продажа", convertToNormalDouble(currencySell.getText()));
//        euroButton.click();
        clickButton(euroButton);
        currencies.put("Худший.Евро.Покупка", convertToNormalDouble(currencyBuy.getText()));
        currencies.put("Худший.Евро.Продажа", convertToNormalDouble(currencySell.getText()));

    }

    private void clickButton(WebElement button){
        JavascriptExecutor jse = (JavascriptExecutor)WebDriverManager.getCurrentDriver();
        jse.executeScript("arguments[0].click()", button);
    }

    private Double convertToNormalDouble(String str){
        String str1 = str.replaceAll("[^\\d.,]", "");
        str1 = str1.replaceAll(",",".");
        return Double.parseDouble(str1);
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
}
