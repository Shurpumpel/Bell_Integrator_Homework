package ru.citilink;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import pages.CitilinkCategoryPage;
import pages.CitilinkMainPage;
import ru.BaseTests;
import steps.Steps;
import utils.PageUtils;

public class CitilinkTests extends BaseTests {

    @Test
    public void videoCardsTest(){
        Steps.goPage("https://www.citilink.ru");
        CitilinkMainPage citilinkMainPage = new CitilinkMainPage();
        Steps.clickOnElement(citilinkMainPage, "Каталог товаров");
        Steps.hoverOnElement(citilinkMainPage, "Ноутбуки и компьютеры");
        Steps.hoverOnElement(citilinkMainPage, "Видеокарты");
        Steps.clickOnElement(citilinkMainPage, "Видеокарты");
        CitilinkCategoryPage citilinkCategoryPage = new CitilinkCategoryPage();
        Steps.sendNewKeysToElement(citilinkCategoryPage, "searchField", "gtx 1660ti"+ Keys.ENTER);
        Steps.clickOnElement(citilinkCategoryPage, "sortByPriceButton");
        Double price1 = citilinkCategoryPage.getProductPrice(citilinkCategoryPage.getElement("firstProductInList"));
        Steps.sendNewKeysToElement(citilinkCategoryPage, "searchField", "rtx 3060"+ Keys.ENTER);
        Steps.clickOnElement(citilinkCategoryPage, "sortByPriceButton");
        Double price2 = citilinkCategoryPage.getProductPrice(citilinkCategoryPage.getElement("firstProductInList"));
        Assertions.assertTrue(Steps.isFirstPriceLessSecondPrice(price1, price2));
    }
}
