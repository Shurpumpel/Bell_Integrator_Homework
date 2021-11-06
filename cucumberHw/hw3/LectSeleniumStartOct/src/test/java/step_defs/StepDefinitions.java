package step_defs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import drivers.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import pages.*;
import ru.citilink.CitilinkPagesSingleTon;
import steps.Steps;

import java.util.NoSuchElementException;

public class StepDefinitions {

    private CitilinkPagesSingleTon citilinkPagesSingleTon = new CitilinkPagesSingleTon();

    @Before
    public void before(){
        WebDriverManager.initChrome();
    }

    @Given("открыть сайт {string}")
    public void openPage(String url){
        Steps.goPage(url);
    }

    @Then("проверить, что разница между курсами в {string} не превышает {string}")
    public void checkDifferenceBetweenCourses(String bankName, String maxDiff) {
        Steps.isDifferenceBetweenBestAndWorstNotExceedRub(
                createBankPageObject(bankName),
                Double.parseDouble(maxDiff)
        );
    }

    @Then("проверить максимальный курс покупки доллара в {string} не превышает {string}")
    public void checkMaxBankBuyCourse(String bankName, String maxPrice) {
        Steps.isBankBuysDollarNotMoreExpensive(
                createBankPageObject(bankName),
                Double.parseDouble(maxPrice)
        );
    }

    private CurrencyExchangePage createBankPageObject(String bankName){
        switch(bankName){
            case "Сбербанк":
                return new SberBankPage();
            case "Открытие":
                return new OpenPage();
            case "Альфа-банк":
                return new AlfaBankPage();
            case "ВТБ":
                return new VTBPage();
            default:
                throw new NoSuchElementException();
        }
    }



    @Then("открыть категории товаров citilink")
    public void openCitilinkCategories() {
        CitilinkMainPage mainPage = citilinkPagesSingleTon.getMainPage();
        Steps.clickOnElement(mainPage, "Каталог товаров");
    }

    @Then("навести на категорию {string}")
    public void hoverOnCategory(String categoryName) {
        CitilinkMainPage mainPage = citilinkPagesSingleTon.getMainPage();
        Steps.hoverOnElement(mainPage, categoryName);
    }

    @Then("кликнуть по категории с именем {string}")
    public void clickOnCategoryWithName(String name) {
        CitilinkMainPage mainPage = citilinkPagesSingleTon.getMainPage();
        Steps.clickOnElement(mainPage, name);
    }

    @Then("убедиться, что самая дешевая {string} дороже самой дешевой {string}")
    public void checkFirstVideoCardMoreExpensive(String name1, String name2) {
        CitilinkCategoryPage citilinkCategoryPage = citilinkPagesSingleTon.getCategoryPage();
        Steps.sendNewKeysToElement(citilinkCategoryPage, "searchField", "gtx 1660ti"+ Keys.ENTER);
        Steps.clickOnElement(citilinkCategoryPage, "sortByPriceButton");
        Double price1 = citilinkCategoryPage.getProductPrice(citilinkCategoryPage.getElement("firstProductInList"));
        Steps.sendNewKeysToElement(citilinkCategoryPage, "searchField", "rtx 3060"+ Keys.ENTER);
        Steps.clickOnElement(citilinkCategoryPage, "sortByPriceButton");
        Double price2 = citilinkCategoryPage.getProductPrice(citilinkCategoryPage.getElement("firstProductInList"));
        Assertions.assertTrue(Steps.isFirstPriceLessSecondPrice(price1, price2));
    }

    @After
    public void closeBellTest(){
        WebDriverManager.killCurrentDriver();
    }

}
