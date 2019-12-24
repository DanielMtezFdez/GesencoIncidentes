package model;

import java.sql.Timestamp;
import java.util.Date;

public class Incidente {
    private int idIncidente;
    private String codEmpleado;
    private int codComunidad;
    private String titulo, descripcion, completo, empresaReparadora;
    private Timestamp fechaAlta, fechaComunicado, fechaFin;
    private int nivelUrgencia, tipoComunicado, tipoReparacion;

    public Incidente() {

    }

    public Incidente(String codEmpleado, String titulo, String descripcion, Timestamp fechaAlta, Timestamp fechaComunicado, Timestamp fechaFin,
                     int nivelUrgencia, int tipoComunicado, String completo, int codComunidad, int tipoReparacion, String empresaReparadora) {
        this.codEmpleado = codEmpleado.toLowerCase();
        this.titulo = titulo.toLowerCase();
        this.descripcion = descripcion.toLowerCase();
        this.fechaAlta = fechaAlta;

        if(fechaComunicado != null) {
            this.fechaComunicado = fechaComunicado;
        } else {
            this.fechaComunicado = null;
        }

        if(fechaFin != null) {
            this.fechaFin = fechaFin;
        } else {
            this.fechaFin = null;
        }

        this.nivelUrgencia = nivelUrgencia;
        this.tipoComunicado = tipoComunicado;
        this.completo = completo.toLowerCase();
        this.codComunidad = codComunidad;
        this.tipoReparacion = tipoReparacion;
        this.empresaReparadora = empresaReparadora.toUpperCase();
    }

    public Incidente(int idIncidente, String codEmpleado, String titulo, String descripcion, Timestamp fechaAlta, Timestamp fechaComunicado, Timestamp fechaFin,
                     int nivelUrgencia, int tipoComunicado, String completo, int codComunidad, int tipoReparacion, String empresaReparadora) {
        this.idIncidente = idIncidente;
        this.codEmpleado = codEmpleado.toLowerCase();
        this.titulo = titulo.toLowerCase();
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.fechaComunicado = fechaComunicado;
        this.fechaFin = fechaFin;
        this.nivelUrgencia = nivelUrgencia;
        this.tipoComunicado = tipoComunicado;
        this.completo = completo.toLowerCase();
        this.codComunidad = codComunidad;
        this.tipoReparacion = tipoReparacion;
        this.empresaReparadora = empresaReparadora.toUpperCase();
    }



    @Override
    public String toString() {
        return "Incidente{" +
                "codEmpleado='" + codEmpleado + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaComunicado=" + fechaComunicado +
                ", fechaFin=" + fechaFin +
                ", nivelUrgencia='" + nivelUrgencia + '\'' +
                ", tipoComunicado='" + tipoComunicado + '\'' +
                ", completo='" + completo + '\'' +
                ", codComunidad='" + codComunidad + '\'' +
                ", tipoReparacion='" + tipoReparacion + '\'' +
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

    public Timestamp getFechaComunicado() {
        return fechaComunicado;
    }

    public void setFechaComunicado(Timestamp fechaComunicado) {
        this.fechaComunicado = fechaComunicado;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(int tipoIncidente) {
        this.nivelUrgencia = tipoIncidente;
    }

    public int getTipoComunicado() {
        return tipoComunicado;
    }

    public void setTipoComunicado(int tipoComunicado) {
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


    public int getCodComunidad() {
        return codComunidad;
    }

    public void setCodComunidad(int codComunidad) {
        this.codComunidad = codComunidad;
    }

    public String getEmpresaReparadora() {
        return empresaReparadora;
    }

    public void setEmpresaReparadora(String empresaReparadora) {
        this.empresaReparadora = empresaReparadora;
    }

    public int getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(int tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }
}
