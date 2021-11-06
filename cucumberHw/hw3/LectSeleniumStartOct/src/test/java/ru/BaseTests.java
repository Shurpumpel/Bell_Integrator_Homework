package ru;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import drivers.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTests {

    protected WebDriver chromeDriver;

    @Before
    @BeforeEach
    public void before(){
        WebDriverManager.initChrome();
        chromeDriver = WebDriverManager.getCurrentDriver();
    }

    @After
    @AfterEach
    public void closeBellTest(){
        WebDriverManager.killCurrentDriver();
    }

}
