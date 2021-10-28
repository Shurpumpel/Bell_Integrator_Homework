package ru.yandex.market;

/**
 * This class has tests for https://market.yandex.ru/
 */

import com.codeborne.selenide.*;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class Tests extends BaseTest {

    /**
     * set to display only 12 items
     * check that only 12 items are displayed
     * enter the name of the first element in the search bar
     * check that such name is in the results
     */
    @Feature("Проверка компьютеров")
    @DisplayName("Проверка категории Компьютеры")
    @Test
    public void testComputersCategory() {
        CategoryPage myPage = open("https://yandex.ru/", YandexMainPage.class)
                .goServiceByName("Маркет", YandexMarketMainPage.class)
                .openListOfCategories()
                .goToMainCategory("Компьютеры", CategoryPage.class)
                .goToCategory("Ноутбуки и планшеты")
                .goToCategory("Ноутбуки")
                .setPrice(10000, 30000)
                .setProducer("HP")
                .setProducer("Lenovo")
                .waitUntilSearchOrFilterEnds()
                .setCountElemsToShow(12)
                .waitUntilSearchOrFilterEnds();
        ElementsCollection results = $$x("//article");
        results.shouldHave(size(12));
        String nameOfFirst = results.get(0).$x(".//h3//a").getAttribute("title");
        myPage.search(nameOfFirst).waitUntilSearchOrFilterEnds();
        ElementsCollection resultSearchByName = $$x("//article//h3//a");
        List<String> foundNames = resultSearchByName.stream()
                .map(x -> x.getAttribute("title"))
                .collect(Collectors.toList());
        Assertions.assertTrue(foundNames.contains(nameOfFirst), "Результат поиска не соответствует введенному названию");
    }

    /**
     * set smartphone producer
     * check that all results has name of producer
     *
     * @param value name of producer
     */
    @Feature("Проверка смартфонов")
    @DisplayName("Проверка смартфонов")
    @ParameterizedTest
    @ValueSource(strings = {
            "Apple",
            "Google",
            "HONOR",
            "HUAWEI",
            "Nokia",
            "OnePlus",
            "OPPO",
            "realme",
            "Samsung",
            "vivo",
            "Xiaomi",
            "ZTE"
    })
    public void testSmartphones(String value) {
        CategoryPage page = open("https://yandex.ru", YandexMainPage.class)
                .goServiceByName("Маркет", YandexMarketMainPage.class)
                .openListOfCategories()
                .goToMainCategory("Электроника", MainCategoryPage.class)
                .goToCategory("Смартфоны")
                .setProducer(value)
                .waitUntilSearchOrFilterEnds()
                .setCountElemsToShow(12)
                .waitUntilSearchOrFilterEnds();

        List<WebElement> buttonNextPage = WebDriverRunner.getWebDriver().findElements(By.xpath("//button[@class='tzQlI _1e9zv']"));
        boolean isAllNamesGood = true;
        while (!buttonNextPage.isEmpty()) {
            page.waitUntilSearchOrFilterEnds();
            Actions actions = new Actions(WebDriverRunner.getWebDriver());
            actions.moveToElement(buttonNextPage.get(0)).click().perform();
            page.waitUntilSearchOrFilterEnds();
            buttonNextPage = WebDriverRunner.getWebDriver().findElements(By.xpath("//button[@class='tzQlI _1e9zv']"));
        }
        isAllNamesGood = isAllNamesContainsValue(value);

        Assertions.assertTrue(isAllNamesGood,
                "Не все имена подобрались верно для смартфона марки " + value);
    }

    /**
     * This Method allows to check that all names of results on this page contains name of producer
     *
     * @param value name of producer
     * @return false if anyone not contains
     */
    private boolean isAllNamesContainsValue(String value) {
        boolean result = true;
        List<WebElement> results = new ArrayList<>($$x("//article//h3//a//span"));

        List<String> foundNames = new ArrayList<>();
        for(WebElement element : results){
            foundNames.add(element.getText());
        }

        for (String name : foundNames) {
            if (!name.contains(value)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
