package org.unitec.emulador;

import org.joda.time.DateTime;

/**
 * Created by campitos on 9/08/16.
 */
public class Evaluacion {

    String tema;
    float calificacion;
    int intentos;

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    DateTime fecha;

    public Evaluacion(String tema, float calificacion, DateTime fecha) {
        this.tema = tema;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public Evaluacion() {
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }
}
