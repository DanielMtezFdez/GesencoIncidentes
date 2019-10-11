package model;

import java.util.Date;

public class Incidente {
    private String codEmpleado, tipoJunta;
    private String titulo, descripcion;
    private String fechaAlta, fechaJunta, fechaFin;
    private String tipoIncidente, tipoComunicado, completo;

    public Incidente() {

    }

    public Incidente(String codEmpleado, String tipoJunta, String titulo, String descripcion, String fechaAlta, String fechaJunta, String fechaFin, String tipoIncidente, String tipoComunicado, String completo) {
        this.codEmpleado = codEmpleado.toLowerCase();
        this.tipoJunta = tipoJunta.toLowerCase();
        this.titulo = titulo.toLowerCase();
        this.descripcion = descripcion.toLowerCase();
        this.fechaAlta = fechaAlta.toLowerCase();
        this.fechaJunta = fechaJunta.toLowerCase();

        if(fechaFin.equals("")) {
            this.fechaFin = "NO FINALIZADO";
        } else {
            this.fechaFin = fechaFin.toLowerCase();
        }

        this.tipoIncidente = tipoIncidente.toLowerCase();
        this.tipoComunicado = tipoComunicado.toLowerCase();
        this.completo = completo.toLowerCase();
    }


    

    @Override
    public String toString() {
        return "Incidente{" +
                "codEmpleado='" + codEmpleado + '\'' +
                ", tipoJunta='" + tipoJunta + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaJunta=" + fechaJunta +
                ", fechaFin=" + fechaFin +
                ", tipoIncidente='" + tipoIncidente + '\'' +
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

    public String getTipoJunta() {
        return tipoJunta;
    }

    public void setTipoJunta(String tipoJunta) {
        this.tipoJunta = tipoJunta;
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

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
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
