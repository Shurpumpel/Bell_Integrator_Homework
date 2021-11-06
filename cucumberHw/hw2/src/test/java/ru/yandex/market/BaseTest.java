package ru.yandex.market;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    /**
     * This method sets some options before each test
     */
    @BeforeEach
    public void options(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
    }

    /**
     * This method makes some actions after each test
     */
    @AfterEach
    public void after(){
        Selenide.close();
    }
}
