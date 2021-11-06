package ru.yandex.market.step_defs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.CategoryPage;
import pages.MainCategoryPage;
import pages.YandexMainPage;
import pages.YandexMarketMainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class StepDefinition {

    @Given("открыть страницу {string}")
    public void openPage(String page){
        open(page);
    }

    @Then("перейти к сервису {string}")
    public void goService(String serviceName){
        YandexMainPage.goServiceByName(serviceName);
    }

    @Then("открыть список категрий")
    public void openCategoriesList(){
        YandexMarketMainPage.openCategories();
    }

    @Then("выбрать категорию {string}")
    public void chooseMainCategory(String mainCategoryName){
        YandexMarketMainPage.goToMainCategory(mainCategoryName);
    }

    @Then("выбрать подкатегорию {string}")
    public void chooseCategory(String categoryName){
        MainCategoryPage.goCategory(categoryName);
    }

    @Then("установить цену от {string} до {string}")
    public void setPrice(String from, String to){
        CategoryPage.setNewPrice(Float.parseFloat(from),
                Float.parseFloat(to));
    }

    @Then("выбрать несколько производителей:")
    public void chooseProducers(List<String> producers){
        for(String name:producers){
            CategoryPage.setNewProducer(name);
        }
    }

    @Then("дождаться загрузки результатов")
    public void waitLoading(){
        CategoryPage.waitUntilSearchEnds();
    }


    @Then("установить количество отображаемых элементов {string}")
    public void setCountElementsToShow(String count) {
        CategoryPage.setCountElementsShow(Integer.parseInt(count));
    }


    @Then("проверить, что отобразилось {string}")
    public void checkCountElementsVisible(String count) {
        ElementsCollection results = $$x("//article");
        results.shouldHave(size(12));
    }


    @Then("искать через поиск первый элемент и убедиться что он существует")
    public void checkSearch() {
        ElementsCollection results = $$x("//article");
        String nameOfFirst = results.get(0).$x(".//h3//a").getAttribute("title");
        CategoryPage.searchQuerry(nameOfFirst);
        CategoryPage.waitUntilSearchEnds();
        ElementsCollection resultSearchByName = $$x("//article//h3//a");
        List<String> foundNames = resultSearchByName.stream()
                .map(x -> x.getAttribute("title"))
                .collect(Collectors.toList());
        Assertions.assertTrue(foundNames.contains(nameOfFirst), "Результат поиска не соответствует введенному названию");
    }

    @Before
    public void before(){
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
    }

    @Then("выбрать одного производителя {string}")
    public void chooseOneProducer(String name) {
        CategoryPage.setNewProducer(name);
    }

    @Then("убедиться, что названия смартфонов содержат {string}")
    public void checkNames(String shouldHaveThis) {
        List<WebElement> buttonNextPage = WebDriverRunner.getWebDriver().findElements(By.xpath("//button[@class='tzQlI _1e9zv']"));
        boolean isAllNamesGood = true;
        while (!buttonNextPage.isEmpty()) {
            CategoryPage.waitUntilSearchEnds();
            Actions actions = new Actions(WebDriverRunner.getWebDriver());
            actions.moveToElement(buttonNextPage.get(0)).click().perform();
            CategoryPage.waitUntilSearchEnds();
            buttonNextPage = WebDriverRunner.getWebDriver().findElements(By.xpath("//button[@class='tzQlI _1e9zv']"));
        }
        isAllNamesGood = isAllNamesContainsValue(shouldHaveThis);

        Assertions.assertTrue(isAllNamesGood,
                "Не все имена подобрались верно для смартфона марки " + shouldHaveThis);
    }

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
