package org.unitec.emulador;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by campitos on 30/08/16.
 */
@Theme("valo")
 //   @Theme("colored")  Este ya esta ok con gradle !!
@SpringUI
public class MiUi extends UI {

    @Autowired RepositorioReactivos reactivos;
   private Grid grid = new Grid();


    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();

        layout.addComponents(grid);
        layout.addComponent(new Label("Hola mundo  "+reactivos.findByTema("A1").size()));


        setContent(layout);
    }
}