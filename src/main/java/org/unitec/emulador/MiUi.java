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

    float califa2;
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
    int buenasc2;

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

/* ******************************************************************************************************************************************************************************************************
GENERAR EXAMEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN
 ********************************************************************************************************************************************************************************************************/
        ArrayList<Reactivo> todos=generadorEXAMEN();



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
//***********************************************************************************************************************************************************************************************************
        button.addClickListener(click -> {
//Antes checamos la respuesta!!!! si no , nos movera hacia adelante a la siguiente pregunta********************************************************************************************************************
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
                         //RESPUestas

                        float porcientoa1=(7.2f*buenasa1)/5;
                        califa1=(buenasa1/5.0f)*10;

                        float porcientoa2=(6.1f*buenasa2)/5;
                        califa2=(buenasa2/5.0f)*10;


                        float porcientob1=(9.4f*buenasb1)/5;
                        califb1=(buenasb1/5.0f)*10;

                        float porcientob2=(22.4f*buenasb2)/5;
                        califb2=(buenasb2/5.0f)*10;

                        float porcientob3=(3.9f*buenasb3)/5;
                        califb3=(buenasb3/5.0f)*10;

                        float porcientob4=(5.0f*buenasb4)/5;
                        califb4=(buenasb4/5.0f)*10;

                        float porcientoc1=(5.5f*buenasc1)/5;
                        califc1=(buenasc1/5.0f)*10;

                        float porcientoc2=(8.8f*buenasc2)/5;
                        califc2=(buenasc2/5.0f)*10;

                        float porcientod1=(8.8f*buenasd1)/5;
                        califd1=(buenasd1/5.0f)*10;

                        float porcientod2=(12.2f*buenasd2)/5;
                        califd2=(buenasd2/5.0f)*10;

                        float porcientod3=(10.5f*buenasd3)/5;
                        califd3=(buenasd3/5.0f)*10;

                        Label resultadoA1=new Label("TEMA A1. Porcentaje CENEVAL: 7.2. Porcentaje A1 obtenido: "+porcientoa1+" Calificacion obtenida: "+califa1);
                        Label resultadoA2=new Label("TEMA A2. Porcentaje CENEVAL: 6.1. Porcentaje A2 obtenido: "+porcientoa2+" Calificacion obtenida: "+califa2);
                        Label resultadoB1=new Label("TEMA B1. Porcentaje CENEVAL: 9.4. Porcentaje B1 obtenido: "+porcientob1+" Calificacion obtenida: "+califb1);
                        Label resultadoB2=new Label("TEMA B2. Porcentaje CENEVAL: 22.4.Porcentaje B2 obtenido: "+porcientob2+" Calificacion obtenida: "+califb2);
                        Label resultadoB3=new Label("TEMA B3. Porcentaje CENEVAL: 3.9. Porcentaje B3 obtenido: "+porcientob3+" Calificacion obtenida: "+califb3);
                        Label resultadoB4=new Label("TEMA B4. Porcentaje CENEVAL: 5.0. Porcentaje B4 obtenido: "+porcientob4+" Calificacion obtenida: "+califb4);
                        Label resultadoC1=new Label("TEMA C1. Porcentaje CENEVAL: 5.5. Porcentaje C1 obtenido: "+porcientoc1+" Calificacion obtenida: "+califc1);
                        Label resultadoC2=new Label("TEMA C2. Porcentaje CENEVAL: 8.8. Porcentaje C2 obtenido: "+porcientoc2+" Calificacion obtenida: "+califc2);
                        Label resultadoD1=new Label("TEMA D1. Porcentaje CENEVAL: 8.8. Porcentaje D1 obtenido: "+porcientod1+" Calificacion obtenida: "+califd1);
                        Label resultadoD2=new Label("TEMA D2. Porcentaje CENEVAL: 12.2. Porcentaje D2 obtenido: "+porcientod2+" Calificacion obtenida: "+califd2);
                        Label resultadoD3=new Label("TEMA D3. Porcentaje CENEVAL: 10.5. Porcentaje D3 obtenido: "+porcientod3+" Calificacion obtenida: "+califd3);

                         if(califa1>=6){
                             resultadoA1.setStyleName(ValoTheme.LABEL_SUCCESS);
                         }else{
                             resultadoA1.setStyleName(ValoTheme.LABEL_FAILURE);
                         }

                        if(califa2>=6){
                            resultadoA2.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoA2.setStyleName(ValoTheme.LABEL_FAILURE);
                        }

                        if(califb1>=6){
                            resultadoB1.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoB1.setStyleName(ValoTheme.LABEL_FAILURE);
                        }
                        if(califb2>=6){
                            resultadoB2.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoB2.setStyleName(ValoTheme.LABEL_FAILURE);
                        }
                        if(califb3>=6){
                            resultadoB3.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoB3.setStyleName(ValoTheme.LABEL_FAILURE);
                        }
                        if(califb4>=6){
                            resultadoB4.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoB4.setStyleName(ValoTheme.LABEL_FAILURE);
                        }
                        if(califc1>=6){
                            resultadoC1.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoC1.setStyleName(ValoTheme.LABEL_FAILURE);
                        }
                        if(califc2>=6){
                            resultadoC2.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoC2.setStyleName(ValoTheme.LABEL_FAILURE);
                        }

                        if(califd1>=6){
                            resultadoD1.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoD1.setStyleName(ValoTheme.LABEL_FAILURE);
                        }

                        if(califd2>=6){
                            resultadoD2.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoD2.setStyleName(ValoTheme.LABEL_FAILURE);
                        }
                        if(califd3>=6){
                            resultadoD3.setStyleName(ValoTheme.LABEL_SUCCESS);
                        }else{
                            resultadoD3.setStyleName(ValoTheme.LABEL_FAILURE);
                        }


                      //  resultadoA2.setStyleName(ValoTheme.LABEL_FAILURE);
                        Label resultadoTiempo=new Label("TIEMPO ESTIMADO PARA EL EXAMEN: 110 MINUTOS, TIEMPO QUE TARDASTE: "+minutos+":"+segundos+" minutos");
                        resultadoTiempo.setStyleName(ValoTheme.LABEL_LARGE);
                        layout.addComponent(resultadoTiempo);
                        layout.addComponent(resultadoA1);
                        layout.addComponent(resultadoA2);
                        layout.addComponent(resultadoB1);
                        layout.addComponent(resultadoB2);
                        layout.addComponent(resultadoB3);
                        layout.addComponent(resultadoB4);
                        layout.addComponent(resultadoC1);
                        layout.addComponent(resultadoC2);
                        layout.addComponent(resultadoD1);
                        layout.addComponent(resultadoD2);
                        layout.addComponent(resultadoD3);
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

        bienvenido.setValue("Bienvenidos al emulador del CENEVAL, el examen consta de un total de "+todos.size()+"  REACTIVOS. El tiempo total del examen es de 110 MINUTOS. Una vez " +
                "que inicies el examen no podrás pausarlo.");
        bienvenido.addStyleName(ValoTheme.LABEL_BOLD);
        Label label2= new Label();
        label2.setValue(" Al final se te mostrará retroalimentación de tu evaluación, asi como del tiempo que tardaste en tu examen.");
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




    ArrayList<Reactivo> generadorEXAMEN(){
        ArrayList<Reactivo> bancoReactivos=new ArrayList<>();

        bancoReactivos.addAll(obtenerReactivos("A1"));
        bancoReactivos.addAll(obtenerReactivos("A2"));
        bancoReactivos.addAll(obtenerReactivos("B1"));
        bancoReactivos.addAll(obtenerReactivos("B2"));
        bancoReactivos.addAll(obtenerReactivos("B3"));
        bancoReactivos.addAll(obtenerReactivos("B4"));
        bancoReactivos.addAll(obtenerReactivos("C1"));
        bancoReactivos.addAll(obtenerReactivos("C2"));
        bancoReactivos.addAll(obtenerReactivos("D1"));
        bancoReactivos.addAll(obtenerReactivos("D2"));
        bancoReactivos.addAll(obtenerReactivos("D3"));


  return  bancoReactivos;

    }


    /************************************************************************************************************************
     *
     * @param tema
     * @return
     ****************************************************************************************************/
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
              switch (tema) {
                  case "A1": {
                      buenasa1++;
                      break;
                  }
                  case "A2": {
                      buenasa2++;
                      break;
                  }
                  case "B1": {
                      buenasb1++;
                      break;
                  }
                  case "B2": {
                    buenasb2++;
                      break;
                  }
                  case "B3":{
                      buenasb3++;
                      break;
                  }
                  case "B4":{
                      buenasb4++;
                      break;
                  }
                  case "C1":{
                      buenasc1++;
                      break;
                  }
                  case "C2":{
                      buenasc2++;
                      break;
                  }
                  case "D1":{
                      buenasd1++;
                      break;
                  }
                  case "D2":{
                      buenasd2++;
                      break;
                  }
                  case "D3":{
                      buenasd3++;
                      break;
                  }
              }// termina el buen switch



            isCorrecto= true;
        }
        else{
            isCorrecto=false;
        }

        return isCorrecto;
    }






}