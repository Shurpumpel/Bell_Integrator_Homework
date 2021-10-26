package ru.yandex.market;

/**
 * This class has tests for https://market.yandex.ru/
 */

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CategoryPage;
import pages.MainCategoryPage;
import pages.YandexMainPage;
import pages.YandexMarketMainPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
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
        open("https://yandex.ru", YandexMainPage.class)
                .goServiceByName("Маркет", YandexMarketMainPage.class)
                .openListOfCategories()
                .goToMainCategory("Электроника", MainCategoryPage.class)
                .goToCategory("Смартфоны")
                .setProducer(value)
                .waitUntilSearchOrFilterEnds()
                .setCountElemsToShow(12)
                .waitUntilSearchOrFilterEnds();

        SelenideElement buttonNextPage = $x("//a[@class='_2prNU _3OFYT']");

        boolean isAllNamesGood = true;
        while (isAllNamesGood) {
            isAllNamesGood = isAllNamesContainsValue(value);
            try {
                buttonNextPage.click();
            }catch (com.codeborne.selenide.ex.ElementNotFound e){
                break;
            }
        }
        isAllNamesGood = isAllNamesContainsValue(value);

        Assertions.assertTrue(isAllNamesGood,
                "Не все имена подобрались верно для смартфона марки " + value);
    }

    /**
     * This Method allows to check that all names of results on this page contains name of producer
     * @param value name of producer
     * @return false if anyone not contains
     */
    private boolean isAllNamesContainsValue(String value) {
        boolean result = true;
        ElementsCollection results = $$x("//article//h3//a");
        List<String> foundNames = results.stream()
                .map(x -> x.getAttribute("title"))
                .collect(Collectors.toList());
        for (String name : foundNames) {
            if (!name.contains(value)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
