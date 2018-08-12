package org.example.proyectoisw1213;

public class Lugar {
    private int imagen;  //guarda el id de la imagen almacenada en la carpeta drawable
    private String nombre;
    private int visitas;
    public Lugar(int imagen, String nombre, int visitas) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.visitas = visitas;
    }
    public String getNombre() {
        return nombre;
    }
    public int getVisitas() {
        return visitas;
    }

    public int getImagen() {
        return imagen;
    }
}
