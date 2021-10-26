package pages;

/**
 * This is page object of https://yandex.ru
 */

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexMainPage extends BasePage {

    /**
     * This method allows you to go to some service
     * @param serviceName name of service
     * @param servicePage class of service page object
     * @param <T>
     * @return page object of service
     */
    @Step("Переходим на сервис {serviceName}")
    public <T extends BasePage> T goServiceByName(String serviceName, Class<T> servicePage){
        $x("//div[@class='services-new__item-title']")
                .shouldHave(text(serviceName)).click();
        switchTo().window(1);
        return servicePage.cast(page(servicePage));
    }
}
