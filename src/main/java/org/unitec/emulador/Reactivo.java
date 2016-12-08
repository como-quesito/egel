package org.unitec.emulador;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by campitos on 21/08/15.
 */
public class Reactivo implements Serializable {
    @Id
    String id;

    boolean validado;

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String tema;
    Integer claveProfesor;

    public Integer getClaveProfesor() {
        return claveProfesor;
    }

    public void setClaveProfesor(Integer claveProfesor) {
        this.claveProfesor = claveProfesor;
    }

    String pregunta;
    String retroalimentacion;
    String urlimagen;
    ArrayList<Opcion> opciones;

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }





    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Reactivo() {
    }
}
