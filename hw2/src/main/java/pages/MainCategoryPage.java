package pages;

/**
 * This class of page object main category like Electronics or Computers
 */

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.page;

public class MainCategoryPage extends BasePage {

    /**
     * This method allows you to go to low-level category
     * @param categoryName enter name of low-level category
     * @return page object og low-level category
     */
    @Step("Переходим на категорию {categoryName}")
    public CategoryPage goToCategory(String categoryName){
        $$x("//div[@class='_2et7a egKyN n1VbV _2oLyz _9qbcy gmQcK']")
                        .forEach(SelenideElement::click);

        $$x("//div[@class='section _2MUss _3jiXh OM29h NRIlq']//a[@class='egKyN _2oLyz _9qbcy']")
                .find(text(categoryName)).click();
        return page(CategoryPage.class);
    }

}
