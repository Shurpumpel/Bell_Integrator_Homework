package ru.citilink;

import pages.CitilinkCategoryPage;
import pages.CitilinkMainPage;

public class CitilinkPagesSingleTon {
    private CitilinkMainPage mainPage;
    private CitilinkCategoryPage categoryPage;

    public CitilinkMainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new CitilinkMainPage();
        }
        return mainPage;
    }

    public CitilinkCategoryPage getCategoryPage(){
        if(categoryPage==null){
            categoryPage = new CitilinkCategoryPage();
        }
        return categoryPage;
    }

}
