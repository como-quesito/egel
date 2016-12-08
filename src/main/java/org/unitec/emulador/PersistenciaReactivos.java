package org.unitec.emulador;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by campitos on 19/10/16.
 */
public class PersistenciaReactivos {

ArrayList<Reactivo> reactivos;

    public PersistenciaReactivos() {
       reactivos=new ArrayList<>();
    }

    //Primero ponemos el metodo de buscar todos
    ArrayList<Reactivo> buscarTodos()throws Exception{
        //Paso numero 1:
        // Leemos el aarchvo donde esta guardado el arraylist
        File f=new File("reactivos.raton");
        //Leemos el contenido
        FileInputStream fis=new FileInputStream(f);
        //DEscomprimimos el contenido
        ObjectInputStream ois=new ObjectInputStream(fis);
        reactivos=(ArrayList<Reactivo>) ois.readObject();
        return reactivos;
    }

    public void guardar(ArrayList<Reactivo> reactivos)throws Exception{
        File f=new File("reactivos.raton");
        FileOutputStream fos=new FileOutputStream(f);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(reactivos);
    }

}
