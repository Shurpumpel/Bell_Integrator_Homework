Feature: тестирование citilink

  @Run
  Scenario: тест с видеокартами
    Given открыть сайт 'https://www.citilink.ru'
    Then открыть категории товаров citilink
    Then навести на категорию 'Ноутбуки и компьютеры'
    Then навести на категорию 'Видеокарты'
    Then кликнуть по категории с именем 'Видеокарты'
    Then убедиться, что самая дешевая 'gtx 1660ti' дороже самой дешевой 'rtx 3060'
