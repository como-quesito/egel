package org.unitec.emulador;

import com.vaadin.annotations.Theme;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
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
    int segundos=0;
    int minutos=0;

    int pregunta=0;
    int contador;
    Thread t1;

    //calificaciones
    float califa1;
    int buenasa1;

    float calia2;
    int buenasa2;

    float califb1;
    int buenasb1;

    float califb2;
    int buenasb2;

    float califb3;
    int buenasb3;

    float califb4;
    int buenasb4;

    float califc1;
    int buenasc1;

    float califc2;
    int buienasc2;

    float califd1;
    int buenasd1;

    float califd2;
    int buenasd2;
    float califd3;
    int buenasd3;

    float califtotal;



    @Autowired RepositorioReactivos reactivos;
  // private Grid grid = new Grid();


    @Override
    protected void init(VaadinRequest request) {
        //ESte es para que se refresque toda la pantalla y se generen Threads normales en las componentes visuales
        UI.getCurrent().setPollInterval( 1000 );

        final VerticalLayout principal = new VerticalLayout();



        final VerticalLayout layout=new VerticalLayout();


        //Ponemos la imagen inicial
        //Imagen de inicio

        ThemeResource resource = new ThemeResource("../egel.png");
        Image imagen = new Image(null, resource);
        principal.addComponent(imagen);
        /*
           INICIAMOS ELÑ EXAMEN
         */



        final TextField name = new TextField();
        name.setCaption("Type your name here:");


        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
        System.out.println("Vergas!!");

//ESte sera el reloj
        Label labello=new Label("RELOJ");
        labello.addStyleName(ValoTheme.LABEL_COLORED);




        Button button = new Button("Checar respuesta",FontAwesome.CHECK_CIRCLE_O);


        ArrayList<Reactivo> todos=obtenerReactivos("B4");



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


//ASIGNAMOS EL RELOJ
        layout.addComponent(labello);


        // ASIGNAMOS LA PREGUNTA

        label.setValue(todos.get(0).getPregunta());


        OptionGroup single = new OptionGroup();
        single.addItems(op1, op2, op3,op4);
        single.setStyleName(ValoTheme.OPTIONGROUP_LARGE);


        // Java 8
        button.setStyleName(ValoTheme.BUTTON_PRIMARY);

        button.addClickListener(click -> {
//Antes checamos la respuesta!!!! si no , nos movera hacia adelante a la siguiente pregunta
                    //Checamos la pregunta

            if(checarRespuesta(todos.get(pregunta), todos.get(pregunta).getTema(),single)){
                Notification.show("CORRECTO" );
            }else{
                Notification.show("MAL!!! IN-CO-RREC-TO" );
            }

            pregunta++;


                    if (pregunta < todos.size()) {











                        op1 = todos.get(pregunta).getOpciones().get(0).getTitulo();
                        op2 = todos.get(pregunta).getOpciones().get(1).getTitulo();
                        op3 = todos.get(pregunta).getOpciones().get(2).getTitulo();
                        op4 = todos.get(pregunta).getOpciones().get(3).getTitulo();

                        label.setValue(todos.get(pregunta).getPregunta());


                        single.removeAllItems();
                        single.addItems(op1, op2, op3, op4);




                    }
                    else{
                        layout.removeAllComponents();

                        Label resultadoA1=new Label("Resultado");
                        Label resultadoA2=new Label("Resltado");
                        resultadoA1.setStyleName(ValoTheme.LABEL_SUCCESS);
                        resultadoA2.setStyleName(ValoTheme.LABEL_FAILURE);
                        layout.addComponent(resultadoA1);
                        layout.addComponent(resultadoA2);
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
        Label label3=new Label("El examen evalúa cada área y sub-área del EGEL  en los porcentajes oficiales");
        Label label5 =new Label("");
        principal.addComponents(bienvenido,label2,label4, label3, label5 );
/*
BOTON PARA INICIAR EXAMEN, INICIAMOS EL THREAD.
 */
        Button boton=new Button("INICIAR EXAMEN", FontAwesome.ANGELLIST);
        boton.addStyleName(ValoTheme.BUTTON_PRIMARY);


        boton.addClickListener(click -> {

            t1.start();

                    Notification.show("INICIA TU EMULACIÓN DE EXAMEN! " );
            setContent(layout);

                });
        principal.addComponent(boton);




         t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {

                  if(segundos>=60){
                      segundos=0;
                      minutos++;
                  }
                    System.out.println("TIEMPO TRANSCURRIDO: "+minutos+":"+segundos+" minutos");
                    labello.setValue("TIEMPO TRANSCURRIDO: "+minutos+":"+segundos+" minutos");
                    segundos++;
                    try {
                        Thread.sleep(1000);




                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();




//PROBEMOS UN LINK
      // Link link=new Link(null, new ExternalResource("/hola-mundo/"));
     //  link.setIcon(resource);


        setContent(principal);
    }


    public ArrayList<Reactivo> obtenerReactivos(String tema) {


        ArrayList<Reactivo> reactivosAleatorios = new ArrayList<>();

        ArrayList<Reactivo> todos = (ArrayList<Reactivo>) reactivos.findByTema(tema);


/**********************************************************************************************************
 Checamos si las pregunats son más de 10 para hacer el acomodado al azar
 **********************************************************************************************************/
        if (todos.size() > 5) {
            Random rng = new Random(); //
// El LinkedHashSet es para mantener el orden
            Set<Integer> generated = new LinkedHashSet<Integer>();
            //Aqui va el numero de reactivos a mostrarse o sea 10 preguntas
            while (generated.size() < 5) {
                // Aqui va el numero total de reactivos en los que debe hacerse la asignacion aleatorio
                Integer next = rng.nextInt(todos.size()) + 1;
                // Agregamos
                generated.add(next);
            }
            //Acomodamos los reactivos de acuerdo a los indices generados
            reactivosAleatorios = new ArrayList<>();
            for (int i : generated) {
                System.out.println("Valor:" + i);
                reactivosAleatorios.add(todos.get(i - 1));
            }


        }
        //REgresamos los 5 aleatorios
        return reactivosAleatorios;

    }

//metodo para checar la respuesta de x pregunta
    public boolean checarRespuesta(Reactivo pregunta, String tema, OptionGroup single){
        boolean isCorrecto=false;
        String laCorrecta="nada";

        //Chwecamos de la pregunta actual cual es la correcta
        for(Opcion o: pregunta.getOpciones()){
            if(o.isAcierto()){
                laCorrecta=    o.getTitulo();
                break;
            }
        }
        //Checamos la que el usduario selecciono y la probamos con la correcta
        if(single.isSelected(laCorrecta)){



            //AQUI VIENE LO BUENOOOOOO LA EVALUACIOOOOOOOOON



            isCorrecto= true;
        }
        else{
            isCorrecto=false;
        }

        return isCorrecto;
    }






}