package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CitilinkCategoryPage implements Page{

    @FieldName("searchField")
    @FindBy(xpath = "//div[@class='Container Container_has-grid MainHeader__inner  MainHeader__inner_bottom js--MainHeader__inner_bottom']//input[contains(@placeholder,'Поиск по товарам')]")
    private WebElement searchField;

    @FieldName("sortByPriceButton")
    @FindBy(xpath = "//div[@class='SortingList__item js--SortingList__item Sorting__item_selected SortingList__item_active']")
    private WebElement sortBuPriceButton;

    @FieldName("firstProductInList")
    @FindBy(xpath = "//div[@class='product_data__gtm-js product_data__pageevents-js  ProductCardVertical js--ProductCardInListing ProductCardVertical_normal ProductCardVertical_shadow-hover ProductCardVertical_separated'][1]")
    private WebElement firstProductInList;

    public CitilinkCategoryPage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    public Double getProductPrice(WebElement product){
        String str = product
                .findElement(By.xpath("//div[@class='ProductCardVerticalLayout__wrapper-cart']//span[@class='ProductCardVerticalPrice__price-current_current-price js--ProductCardVerticalPrice__price-current_current-price ']"))
                .getText();
        return convertToNormalDouble(str);
    }

    private Double convertToNormalDouble(String str){
        String str1 = str.replaceAll("[^\\d,.]", "");
        return Double.parseDouble(str1);
    }
}
