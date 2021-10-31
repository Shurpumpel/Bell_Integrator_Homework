package ru.banks;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import pages.AlfaBankPage;
import pages.OpenPage;
import pages.SberBankPage;
import pages.VTBPage;
import ru.BaseTests;
import steps.Steps;
public class bankTests extends BaseTests {

    @Feature("Проверка банков")
    @Test
    public void sberBankTest(){
        Steps.goPage("https://www.sberbank.ru/ru/quotes/currencies?package=ERNP-8");
        SberBankPage sberBankPage = new SberBankPage();
        Steps.isDifferenceBetweenBestAndWorstNotExceedRub(sberBankPage, 1.0);
        Steps.isBankBuysDollarNotMoreExpensive(sberBankPage, 72.1);
    }

    @Feature("Проверка банков")
    @Test
    public void openBankTest(){
        Steps.goPage("https://www.open.ru/");
        OpenPage openPage = new OpenPage();
        Steps.isDifferenceBetweenBestAndWorstNotExceedRub(openPage, 1.0);
        Steps.isBankBuysDollarNotMoreExpensive(openPage, 72.1);
    }

    @Feature("Проверка банков")
    @Test
    public void alfaBankTest(){
        Steps.goPage("https://alfabank.ru/currency/");
        AlfaBankPage alfaBankPage = new AlfaBankPage();
        Steps.isDifferenceBetweenBestAndWorstNotExceedRub(alfaBankPage, 1.0);
        Steps.isBankBuysDollarNotMoreExpensive(alfaBankPage, 72.1);
    }

    @Feature("Проверка банков")
    @Test
    public void vtbBankTest(){
        Steps.goPage("https://www.vtb.ru/personal/platezhi-i-perevody/obmen-valjuty/");
        VTBPage vtbPage = new VTBPage();
        Steps.isDifferenceBetweenBestAndWorstNotExceedRub(vtbPage, 1.0);
        Steps.isBankBuysDollarNotMoreExpensive(vtbPage, 72.1);
    }
}
