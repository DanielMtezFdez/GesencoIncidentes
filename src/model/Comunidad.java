package model;

public class Comunidad {
    private String codigo, nombre;

    public Comunidad(){

    }

    public Comunidad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return "Comunidad{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // GETTERS AND SETTERS
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
