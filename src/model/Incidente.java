package model;

import java.sql.Timestamp;
import java.util.Date;

public class Incidente {
    private int idIncidente;
    private String codEmpleado;
    private String codComunidad;
    private String titulo, descripcion;
    private Timestamp fechaAlta, fechaJunta, fechaFin;
    private String nivelUrgencia, tipoComunicado, completo;

    public Incidente() {

    }

    public Incidente(String codEmpleado, String titulo, String descripcion, Timestamp fechaAlta, Timestamp fechaJunta, Timestamp fechaFin, String nivelUrgencia, String tipoComunicado, String completo, String codComunidad) {
        this.codEmpleado = codEmpleado.toLowerCase();
        this.titulo = titulo.toLowerCase();
        this.descripcion = descripcion.toLowerCase();
        this.fechaAlta = fechaAlta;

        if(fechaJunta != null) {
            this.fechaJunta = fechaJunta;
        } else {
            this.fechaJunta = null;
        }

        if(fechaFin != null) {
            this.fechaFin = fechaFin;
        } else {
            this.fechaFin = null;
        }

        this.nivelUrgencia = nivelUrgencia.toLowerCase();
        this.tipoComunicado = tipoComunicado.toLowerCase().replace(" ", "_");
        this.completo = completo.toLowerCase();
        this.codComunidad = codComunidad;
    }

    public Incidente(int idIncidente, String codEmpleado, String titulo, String descripcion, Timestamp fechaAlta, Timestamp fechaJunta, Timestamp fechaFin, String nivelUrgencia, String tipoComunicado, String completo, String codComunidad) {
        this.idIncidente = idIncidente;
        this.codEmpleado = codEmpleado.toLowerCase();
        this.titulo = titulo.toLowerCase();
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.fechaJunta = fechaJunta;
        this.fechaFin = fechaFin;
        this.nivelUrgencia = nivelUrgencia.toLowerCase();
        this.tipoComunicado = tipoComunicado.toLowerCase().replace(" ", "_");
        this.completo = completo.toLowerCase();
        this.codComunidad = codComunidad;
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
                ", nivelUrgencia='" + nivelUrgencia + '\'' +
                ", tipoComunicado='" + tipoComunicado + '\'' +
                ", completo='" + completo + '\'' +
                ", codComunidad='" + codComunidad + '\'' +
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaJunta() {
        return fechaJunta;
    }

    public void setFechaJunta(Timestamp fechaJunta) {
        this.fechaJunta = fechaJunta;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
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

    public int getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(int idIncidente) {
        this.idIncidente = idIncidente;
    }


    public String getCodComunidad() {
        return codComunidad;
    }

    public void setCodComunidad(String codComunidad) {
        this.codComunidad = codComunidad;
    }
}
