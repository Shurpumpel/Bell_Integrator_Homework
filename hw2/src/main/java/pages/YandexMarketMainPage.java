package pages;

/**
 * This is page object class of https://market.yandex.ru/
 */

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketMainPage extends BasePage{

    /**
     * This method allows you to open list of categories
     * @return market page object with opened categories
     */
    @Step("Открываем список категорий маркета")
    public YandexMarketMainPage openListOfCategories(){
        $x("//button[@class='_2AMPZ _3CFK9 _2VvV8 _3WgR5']").click();
        return page(YandexMarketMainPage.class);
    }

    /**
     * This method allows you to go to high-level category by name
     * @param nameCategory name og main category
     * @param typeNextPage class of category page object
     * @param <T>
     * @return category page object
     */
    @Step("Переходим на основную категорию {nameCategory}")
    public <T extends BasePage> T goToMainCategory(String nameCategory, Class<T> typeNextPage){
        $$x("//li[@data-zone-name='category-link']//span")
                .find(text(nameCategory)).click();
        return typeNextPage.cast(page(typeNextPage));
    }



}
