package org.example.proyectoisw1213;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Lugar {


    private String user;
    private String lugar;
    private String tipo;
    private String ubicacion;
    private String gps;
    private Bitmap foto;
    private String comentario;
    private Float rating;



    public Lugar(String user, String lugar, String tipo, String ubicacion, String  gps, Bitmap foto, String comentario, Float rating) {
        this.user = user;
        this.lugar = lugar;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.gps = gps;
        this.foto = foto;
        this.comentario = comentario;
        this.rating = rating;

    }

    public String getuser() {
        return user;
    }
    public String getlugar() {
        return lugar;
    }
    public String gettipo() {
        return tipo;
    }
    public String getubicacion() {
        return ubicacion;
    }
    public String getgps() {
        return gps;
    }
    public Bitmap getfoto() {
        return foto;
    }
    public String getcomentario() {
        return comentario;
    }
    public Float getrating(){ return rating;}


}
