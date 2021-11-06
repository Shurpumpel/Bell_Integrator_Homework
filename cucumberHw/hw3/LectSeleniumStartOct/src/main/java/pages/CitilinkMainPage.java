package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CitilinkMainPage implements Page{

    @FieldName("Каталог товаров")
    @FindBy(xpath = "//button//span[contains(.,'Каталог товаров')]")
    private WebElement catalogButton;

    @FieldName("Ноутбуки и компьютеры")
    @FindBy(xpath = "//a[@class='CatalogMenu__category-item js--CatalogMenu__category-item' and contains(.,'Ноутбуки и компьютеры')]")
    private WebElement notebooksAndComputersButton;

    @FieldName("Видеокарты")
    @FindBy(xpath = "//a[@class='CatalogMenu__subcategory-link' and contains(.,'Видеокарты')]")
    private WebElement videoCardsButton;

    public CitilinkMainPage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }
}
