package org.unitec.emulador;

/**
 * Created by campitos on 31/08/16.
 */

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/** A start view for navigating to the main view */
@SpringUI
public class StartView extends VerticalLayout implements View {

    Navigator navigator;
    protected static final String MAINVIEW = "main";

    public StartView() {
        setSizeFull();

        Button button = new Button("Go to Main View",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        navigator.navigateTo(MAINVIEW);
                    }
                });
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}