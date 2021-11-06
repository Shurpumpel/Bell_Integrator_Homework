Feature: Тестирование курсов валют в разных банках

  @Ignore
  Scenario Outline: Тест банка
    Given открыть сайт '<сайт>'
    Then проверить, что разница между курсами в '<название банка>' не превышает '1.0'
    Then проверить максимальный курс покупки доллара в '<название банка>' не превышает '72.1'
    Examples:
      | название банка | сайт                                                           |
      | Сбербанк       | https://www.sberbank.ru/ru/quotes/currencies?package=ERNP-8    |
      | Открытие       | https://www.open.ru/                                           |
      | Альфа-банк     | https://alfabank.ru/currency/                                  |
      | ВТБ            | https://www.vtb.ru/personal/platezhi-i-perevody/obmen-valjuty/ |
