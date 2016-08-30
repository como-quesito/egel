package org.unitec.emulador;

/**
 * Created by rapid on 21/07/2016.
 */
public class UsuarioMapa {
    String id;
    String usuarioid;
    double lati;
    double longi;

    public UsuarioMapa(String id) {
        this.id = id;
    }

    public UsuarioMapa(String usuarioid, double lati, double longi) {
        this.usuarioid = usuarioid;
        this.lati = lati;
        this.longi = longi;
    }

    public UsuarioMapa() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }
}
