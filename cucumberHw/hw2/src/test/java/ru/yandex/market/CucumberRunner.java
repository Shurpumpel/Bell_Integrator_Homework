package ru.yandex.market;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty", "json:target/cucumber-report/report.json"},
        features = "src/test/java/ru/yandex/market/features",
        glue = "ru.yandex.market.step_defs",
        tags = {"@Run and not @Ignored"}
)
public class CucumberRunner {
}
