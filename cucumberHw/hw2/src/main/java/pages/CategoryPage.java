package pages;

/**
 * This is class with actions on lowest category
 */

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CategoryPage extends BasePage {

    /**
     * This method allows you to go to another visible category
     * @param nameCategory name of category
     * @return page object of other category page
     */
    @Step("Переходим в категорию {nameCategory}")
    public CategoryPage goToCategory(String nameCategory){
        $$x("//div[@class='uOosG']//a").find(text(nameCategory)).click();
        return page(CategoryPage.class);
    }

    /**
     * This method allows you to choose producer in filters
     * @param producerName name of the producer
     * @return page object with chosen producer
     */
    @Step("Выбираем производителя {producerName}")
    public CategoryPage setProducer(String producerName){
        $x("//legend[text()='Производитель']/parent::fieldset//footer//button").click();
        $x("//legend[text()='Производитель']/parent::fieldset//input").sendKeys(producerName);
        $$x("//legend[text()='Производитель']/parent::fieldset//span")
                .find(text(producerName)).click();
        return page(CategoryPage.class);
    }

    /**
     *
     * This method allows you to specify the number of search or filter results to display
     * @param countElemsToShow enter 12 or 48
     * @return page object with chosen count
     */
    @Step("Указываем количество показываемых результатов: {countElemsToShow}")
    public CategoryPage setCountElemsToShow(int countElemsToShow){
        String nameButton = "Показывать по "+ countElemsToShow;
        Assertions.assertTrue($x("//button[@class='vLDMf']").exists(),
                "Не найдена кнопка количества выводимых результатов");

        $x("//button[@class='vLDMf']").click();
        $$x("//div[@class='_3eEaG']//button")
                .find(text(nameButton)).click();
        $$x("//article//h3//a").shouldHave(size(countElemsToShow));
        return page(CategoryPage.class);
    }

    /**
     * This method allows you to search something by name
     * @param query enter the name of what you want to find
     * @return page object with found results
     */
    @Step("Вводим в строку поиска {query}")
    public CategoryPage search(String query){
        SelenideElement searchString = $x("//input[@id='header-search']");
        searchString.click();
        searchString.setValue(query);
        $x("//div[@class='_3dcus']//button//span[text()='Найти']").click();
        return page(CategoryPage.class);
    }

    /**
     * This method allows you to set some prices from and to
     * @param priceFrom float minimum price
     * @param priceTo float maximum price
     * @return page object with set price
     */
    @Step("Устанавливаем цену от {priceFrom} до {priceTo}")
    public CategoryPage setPrice(float priceFrom, float priceTo) {
        $x("//input[@id='glpricefrom']").sendKeys(String.valueOf(priceFrom));
        $x("//input[@id='glpriceto']").sendKeys(String.valueOf(priceTo));
        return page(CategoryPage.class);
    }

    /**
     * This method allows you to wait until the results are loaded
     * @return page object with loaded results
     */
    @Step("Ждем пока загрузятся результаты поиска")
    public CategoryPage waitUntilSearchOrFilterEnds(){
        new WebDriverWait(WebDriverRunner.getWebDriver(), 30)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//article"), 0));
        return page(CategoryPage.class);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method allows you to go to another visible category
     * @param nameCategory name of category
     */
    @Step("Переходим в категорию {nameCategory}")
    public static void goCategory(String nameCategory){
        $$x("//div[@class='uOosG']//a").find(text(nameCategory)).click();
    }

    /**
     * This method allows you to choose producer in filters
     * @param producerName name of the producer
     */
    @Step("Выбираем производителя {producerName}")
    public static void setNewProducer(String producerName){
        $x("//legend[text()='Производитель']/parent::fieldset//footer//button").click();
        $x("//legend[text()='Производитель']/parent::fieldset//input").sendKeys(producerName);
        $$x("//legend[text()='Производитель']/parent::fieldset//span")
                .find(text(producerName)).click();
    }

    /**
     *
     * This method allows you to specify the number of search or filter results to display
     * @param countElemsToShow enter 12 or 48
     */
    @Step("Указываем количество показываемых результатов: {countElemsToShow}")
    public static void setCountElementsShow(int countElemsToShow){
        String nameButton = "Показывать по "+ countElemsToShow;
        Assertions.assertTrue($x("//button[@class='vLDMf']").exists(),
                "Не найдена кнопка количества выводимых результатов");

        $x("//button[@class='vLDMf']").click();
        $$x("//div[@class='_3eEaG']//button")
                .find(text(nameButton)).click();
        $$x("//article//h3//a").shouldHave(size(countElemsToShow));
    }

    /**
     * This method allows you to search something by name
     * @param query enter the name of what you want to find
     */
    @Step("Вводим в строку поиска {query}")
    public static void searchQuerry(String query){
        SelenideElement searchString = $x("//input[@id='header-search']");
        searchString.click();
        searchString.setValue(query);
        $x("//div[@class='_3dcus']//button//span[text()='Найти']").click();
    }

    /**
     * This method allows you to set some prices from and to
     * @param priceFrom float minimum price
     * @param priceTo float maximum price
     */
    @Step("Устанавливаем цену от {priceFrom} до {priceTo}")
    public static void setNewPrice(float priceFrom, float priceTo) {
        $x("//input[@id='glpricefrom']").sendKeys(String.valueOf(priceFrom));
        $x("//input[@id='glpriceto']").sendKeys(String.valueOf(priceTo));
    }

    /**
     * This method allows you to wait until the results are loaded
     */
    @Step("Ждем пока загрузятся результаты поиска")
    public static void waitUntilSearchEnds(){
        new WebDriverWait(WebDriverRunner.getWebDriver(), 30)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//article"), 0));
    }
}
