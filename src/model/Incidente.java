package model;

import java.util.Date;

public class Incidente {
    private String codEmpleado, tipoJunta;
    private String titulo, descripcion;
    private Date fechaAlta, fechaJunta, fechaFin;
    private String tipoIncidente, tipoComunicado, completo;

    public Incidente() {

    }

    public Incidente(String codEmpleado, String tipoJunta, String titulo, String descripcion, Date fechaAlta, Date fechaJunta, Date fechaFin, String tipoIncidente, String tipoComunicado, String completo) {
        this.codEmpleado = codEmpleado;
        this.tipoJunta = tipoJunta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.fechaJunta = fechaJunta;
        this.fechaFin = fechaFin;
        this.tipoIncidente = tipoIncidente;
        this.tipoComunicado = tipoComunicado;
        this.completo = completo;
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaJunta() {
        return fechaJunta;
    }

    public void setFechaJunta(Date fechaJunta) {
        this.fechaJunta = fechaJunta;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
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
