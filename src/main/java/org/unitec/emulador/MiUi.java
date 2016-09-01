package org.unitec.emulador;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by campitos on 30/08/16.
 */
@Theme("valo")
//@Theme("colored") // Este ya esta ok con gradle !!
@SpringUI
public class MiUi extends UI {

    String op1;
    String op2;
    String op3;
    String op4;
    TextArea label;
    int contadora=0;

    int pregunta=0;
    int contador;


    @Autowired RepositorioReactivos reactivos;
  // private Grid grid = new Grid();


    @Override
    protected void init(VaadinRequest request) {
        //ESte es para que se refresque toda la pantalla y se generen Threads normales en las componentes visuales
        UI.getCurrent().setPollInterval( 1000 );

        final VerticalLayout principal = new VerticalLayout();
        final VerticalLayout layout=new VerticalLayout();
        /*
           INICIAMOS ELÑ EXAMEN
         */



        final TextField name = new TextField();
        name.setCaption("Type your name here:");


        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
        System.out.println("Vergas!!");






        Button button = new Button("Checar respuesta");


        ArrayList<Reactivo> todos= (ArrayList<Reactivo>) reactivos.findByTema("A1");

/**********************************************************************************************************
 Checamos si las pregunats son más de 10 para hacer el acomodado al azar
 **********************************************************************************************************/
        if(reactivos.size()>5) {
            Random rng = new Random(); //
// El LinkedHashSet es para mantener el orden
            Set<Integer> generated = new LinkedHashSet<Integer>();
            //Aqui va el numero de reactivos a mostrarse o sea 10 preguntas
            while (generated.size() < 5) {
                // Aqui va el numero total de reactivos en los que debe hacerse la asignacion aleatorio
                Integer next = rng.nextInt(reactivos.size()) + 1;
                // Agregamos
                generated.add(next);
            }
            //Acomodamos los reactivos de acuerdo a los indices generados
            reactivosAleatorios=new ArrayList<>();
            for (int i : generated) {
                System.out.println( "Valor:" + i);
                reactivosAleatorios.add(reactivos.get(i-1));
            }

            //Reasignamos el arraylist de reactivos a sólo los 10.
            reactivos=reactivosAleatorios;


        }



///////////////////////////////////////////////////////////////
        op1= todos.get(0).getOpciones().get(0).getTitulo();
        op2=  todos.get(0).getOpciones().get(1).getTitulo();
        op3=  todos.get(0).getOpciones().get(2).getTitulo();
        op4=  todos.get(0).getOpciones().get(3).getTitulo();

        // A single-select radio button group
        label=new TextArea("");
        label.setWidth("100%");
        label.setHeight("250px");
        label.setEnabled(false);
        label.setStyleName(ValoTheme.TEXTAREA_LARGE);


        label.setValue(todos.get(0).getPregunta());


        OptionGroup single = new OptionGroup();
        single.addItems(op1, op2, op3,op4);
        single.setStyleName(ValoTheme.OPTIONGROUP_LARGE);


        // Java 8
        button.setStyleName(ValoTheme.BUTTON_PRIMARY);

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


        // grid.setContainerDataSource(new BeanItemContainer<>(Reactivo.class,reactivos.findByTema("A1")));


       // AQUI SE PONE EL LAYOUT PRINCIPAL
        principal.setMargin(true);
        Label bienvenido=new Label();

        bienvenido.setValue("Bienvenidos al emulador del CENEVAL, el examen consta de un total de "+reactivos.findAll().size()+" reactivos. Una vez " +
                "que inicies el examen no podrás pausarlo.");
        bienvenido.addStyleName(ValoTheme.LABEL_BOLD);
        Label label2= new Label();
        label2.setValue("Al final se te proporcionará una retroalimentación de como saliste  en cada área.");
        Label label4=new Label("");
        Label label3=new Label("El examen evalúa cada área y sub área adel EGEL  en los porcentajes oficiales");
        Label label5 =new Label("");
        principal.addComponents(bienvenido,label2,label4, label3, label5 );

        Button boton=new Button("INICIAR EXAMEN", FontAwesome.ANGELLIST);
        boton.addStyleName(ValoTheme.BUTTON_PRIMARY);


        boton.addClickListener(click -> {


                    Notification.show("Lo que seleccionaste es: " );
            setContent(layout);

                });
        principal.addComponent(boton);


        Label labello=new Label();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {


                    System.out.println("Hola "+contadora);
                    labello.setValue("Hola "+contadora);
                    contadora++;
                    try {
                        Thread.sleep(1000);




                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();


        principal.addComponent(labello);

        setContent(principal);
    }
}