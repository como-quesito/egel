package org.unitec.emulador;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.calendar.CalendarClientRpc;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.annotation.WebServlet;
import javax.validation.Configuration;
import java.util.ArrayList;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@Theme("valo")
public class MyUI extends UI {
    String op1;
    String op2;
    String op3;
    String op4;
TextArea label;

    int pregunta=0;
    int contador;
    @Autowired
    RepositorioReactivos reactivos;
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        /*
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
        System.out.println("Vergas!!");
        */
        final VerticalLayout layout = new VerticalLayout();

        Button button = new Button("Checar respuesta");


        ArrayList<Reactivo> todos= (ArrayList<Reactivo>) reactivos.findByTema("A1");




         op1= todos.get(0).getOpciones().get(0).getTitulo();
      op2=  todos.get(0).getOpciones().get(1).getTitulo();
       op3=  todos.get(0).getOpciones().get(2).getTitulo();
       op4=  todos.get(0).getOpciones().get(3).getTitulo();

        // A single-select radio button group
         label=new TextArea("");
        label.setWidth("100%");
        label.setHeight("250px");
        label.setEnabled(false);
        label.setValue(todos.get(0).getPregunta());


        OptionGroup single = new OptionGroup();
        single.addItems(op1, op2, op3,op4);


        // Java 8
        button.addClickListener(click -> {

                    if (pregunta < todos.size()) {

                        pregunta++;

                        Notification.show("Lo que seleccionaste es: " + single.isSelected("Sola"));

                        op1 = todos.get(pregunta).getOpciones().get(0).getTitulo();
                        op2 = todos.get(pregunta).getOpciones().get(1).getTitulo();
                        op3 = todos.get(pregunta).getOpciones().get(2).getTitulo();
                        op4 = todos.get(pregunta).getOpciones().get(3).getTitulo();

                        label.setValue(todos.get(pregunta).getPregunta());


                        single.removeAllItems();
                        single.addItems(op1, op2, op3, op4);

                    }
                }
        );





        layout.setMargin(true);

        layout.addComponent(label);
        layout.addComponent(single);
        layout.addComponent(button);






        setContent(layout);
    }


}
