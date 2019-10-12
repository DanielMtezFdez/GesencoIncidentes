package model;

import java.util.Date;

public class Incidente {
    private String codEmpleado;
    private String titulo, descripcion;
    private String fechaAlta, fechaJunta, fechaFin;
    private String nivelUrgencia, tipoComunicado, completo;

    public Incidente() {

    }

    public Incidente(String codEmpleado, String titulo, String descripcion, String fechaAlta, String fechaJunta, String fechaFin, String nivelUrgencia, String tipoComunicado, String completo) {
        this.codEmpleado = codEmpleado.toLowerCase();
        this.titulo = titulo.toLowerCase();
        this.descripcion = descripcion.toLowerCase();
        this.fechaAlta = fechaAlta.toLowerCase();
        this.fechaJunta = fechaJunta.toLowerCase();

        if(fechaFin.equals(" ")) {
            this.fechaFin = "NO FINALIZADO";
        } else {
            this.fechaFin = fechaFin.toLowerCase();
        }

        this.nivelUrgencia = nivelUrgencia.toLowerCase();
        this.tipoComunicado = tipoComunicado.toLowerCase().replace(" ", "_");
        this.completo = completo.toLowerCase();
    }


    

    @Override
    public String toString() {
        return "Incidente{" +
                "codEmpleado='" + codEmpleado + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaJunta=" + fechaJunta +
                ", fechaFin=" + fechaFin +
                ", tipoIncidente='" + nivelUrgencia + '\'' +
                ", tipoComunicado='" + tipoComunicado + '\'' +
                ", completo='" + completo + '\'' +
                '}';
    }

    //Getters y setters
    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaJunta() {
        return fechaJunta;
    }

    public void setFechaJunta(String fechaJunta) {
        this.fechaJunta = fechaJunta;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String tipoIncidente) {
        this.nivelUrgencia = tipoIncidente;
    }

    public String getTipoComunicado() {
        return tipoComunicado;
    }

    public void setTipoComunicado(String tipoComunicado) {
        this.tipoComunicado = tipoComunicado;
    }

    public String getCompleto() {
        return completo;
    }

    public void setCompleto(String completo) {
        this.completo = completo;
    }
}
