package steps;


import drivers.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.CurrencyExchangePage;
import pages.Page;
import utils.AllureEdit;

import java.util.Map;

public class Steps {

    @Step("Перейти на страницу {url}")
    public static void goPage(String url) {
        WebDriverManager.getCurrentDriver().get(url);
    }

    @Step("Нажать на элемент {elementName}")
    public static void clickOnElement(Page page, String elementName) {
        WebElement element = page.getElement(elementName);
        element.click();
    }

    @Step("Навести на элемент {elementName}")
    public static void hoverOnElement(Page page, String elementName) {
        WebElement element = page.getElement(elementName);
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        actions.moveToElement(element).build().perform();
    }

    @Step("Ввести в элемент {elementName} строку {keys}")
    public static void sendNewKeysToElement(Page page, String elementName, String keys) {
        WebElement element = page.getElement(elementName);
        element.clear();
        element.sendKeys(keys);
    }

    @Step("Получение курса {currencyType} со страницы банка")
    public static Double getBankCurrency(CurrencyExchangePage page, String currencyType) {
        page.preActions();
        return page.getAllCurrencies().get(currencyType);
    }

    @Step("Получение курсов валют по худшему тарифу из доступных на странице банка")
    public static Map<String, Double> getWorstCurrency(CurrencyExchangePage page) {
        AllureEdit.setLastStepName("Получение курсов валют по худшему тарифу из доступных на странице банка "
                + page.getBankName());
        page.preActions();
        return page.getWorstCurrencies();
    }

    @Step("Получение курсов валют по лучшему тарифу из доступных на странице банка")
    public static Map<String, Double> getBestCurrency(CurrencyExchangePage page) {
        AllureEdit.setLastStepName("Получение курсов валют по лучшему тарифу из доступных на странице банка"
                + page.getBankName());
        page.preActions();
        return page.getBestCurrencies();
    }

    @Step("Проверка, что разница между наилучшим и наихудшим курсом не превышает {countRub} руб/у.е.")
    public static boolean isDifferenceBetweenBestAndWorstNotExceedRub(CurrencyExchangePage page, Double countRub) {
        AllureEdit.setLastStepName("Проверка, что разница между наилучшим и наихудшим курсом в банке "
                + page.getBankName() + " не превышает 1 руб/у.е.");
        Map<String, Double> worst = getWorstCurrency(page);
        Map<String, Double> best = getBestCurrency(page);
        System.out.println(page.getBankName());
        System.out.println(worst);
        System.out.println(best);
        for (Map.Entry<String, Double> entry : best.entrySet()) {
            String worstKey = entry.getKey().replace("Лучший", "Худший");
            if (Math.abs(entry.getValue() - worst.get(worstKey)) <= countRub)
                return true;
        }
        AllureEdit.setLastStepStatusFailed();
        return false;
    }

    @Step("Курс покупки банком доллара не превышает {maxPrice}.")
    public static boolean isBankBuysDollarNotMoreExpensive(CurrencyExchangePage page, Double maxPrice) {
        Double bestCourse = Steps.getBankCurrency(page, "Лучший.Доллар.Покупка");
        Double worstCourse = Steps.getBankCurrency(page, "Худший.Доллар.Покупка");
        if (bestCourse <= maxPrice && worstCourse <= maxPrice)
            return true;
        AllureEdit.setLastStepStatusFailed();
        return false;
    }

    @Step("Сравнение цен на видеокарты")
    public static boolean isFirstPriceLessSecondPrice(Double firstPrice, Double secondPrice) {
        return firstPrice < secondPrice;
    }

}
