package com.kataacademy.schoolportal.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

public class HorizontalMainTabPanel extends Div {

    public HorizontalMainTabPanel() {
        getElement().setAttribute("tab-theme","tan-appLayout");
    }

    public static Tabs tabPanelLayout(){
        Tab details = new Tab("Общая информация");
        Tab rating = new Tab("Рейтинг");
        Tab learning = new Tab("Хочу учиться");
        Tab contacts = new Tab("Контакты");

        Tabs tabs = new Tabs(details, rating, learning, contacts);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        return tabs;
    }
}

