package model;

public class Incidente {
    private String codEmpleado;
    private String nombre, apellido;

    public Incidente() {

    }

    public Incidente(String codEmpleado, String nombre, String apellido) {
        this.codEmpleado = codEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
    }




    @Override
    public String toString() {
        return "Incidente{" +
                "codEmpleado='" + codEmpleado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }


    //Getters y setters

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
