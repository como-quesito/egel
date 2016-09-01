package org.unitec.emulador;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by campitos on 31/08/16.
 */
@UIScope
@SpringUI
public class VistaExamen extends VerticalLayout{

    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is a UI scoped view. Greeter says: " ));
    }

}
