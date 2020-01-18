package model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incidente {
    private int idIncidente;
    private String codEmpleado;
    private int codComunidad;
    private String titulo, descripcion, completo, empresaReparadora;
    private Timestamp fechaAlta, fechaComunicado, fechaFin;
    private String nivelUrgencia, tipoComunicado, tipoReparacion;

    private String nombreEmpleado;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Incidente() {

    }

    public Incidente(String codEmpleado, String titulo, String descripcion, Timestamp fechaAlta, Timestamp fechaComunicado, Timestamp fechaFin,
                     String nivelUrgencia, String tipoComunicado, String completo, int codComunidad, String tipoReparacion, String empresaReparadora, String nombreEmpleado) {
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
        this.nombreEmpleado = nombreEmpleado;
    }

    public Incidente(int idIncidente, String codEmpleado, String titulo, String descripcion, Timestamp fechaAlta, Timestamp fechaComunicado, Timestamp fechaFin,
                     String nivelUrgencia, String tipoComunicado, String completo, int codComunidad, String tipoReparacion, String empresaReparadora, String nombreEmpleado) {
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
        this.nombreEmpleado = nombreEmpleado;
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

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
}
