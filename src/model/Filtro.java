package model;

import java.sql.Timestamp;

public class Filtro {

    private String codComunidad;
    private String codEmpleado;
    private Timestamp fechaJuntaAntesDe;
    private Timestamp fechaJuntaDespuesDe;
    private Timestamp fechaAltaAntesDe;
    private Timestamp fechaAltaDespuesDe;
    private Timestamp fechaFinalizacionAntesDe;
    private Timestamp fechaFinalizacionDespuesDe;
    private String nivelUrgencia;
    private String tipoComunicado;
    private String completo;
    private String tipoReparacion;
    private String empresaReparadora;

    public Filtro() {
        codComunidad = null;
        codEmpleado = null;
        fechaJuntaAntesDe = null;
        fechaJuntaDespuesDe = null;
        fechaAltaAntesDe = null;
        fechaAltaDespuesDe = null;
        fechaFinalizacionAntesDe = null;
        fechaFinalizacionDespuesDe = null;
        nivelUrgencia = null;
        tipoComunicado = null;
        completo = null;
        tipoReparacion = null;
        empresaReparadora = null;
    }

    public String getCodComunidad() {
        return codComunidad;
    }

    public void setCodComunidad(String codComunidad) {
        this.codComunidad = codComunidad;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public Timestamp getFechaJuntaAntesDe() {
        return fechaJuntaAntesDe;
    }

    public void setFechaJuntaAntesDe(Timestamp fechaJuntaAntesDe) {
        this.fechaJuntaAntesDe = fechaJuntaAntesDe;
    }

    public Timestamp getFechaJuntaDespuesDe() {
        return fechaJuntaDespuesDe;
    }

    public void setFechaJuntaDespuesDe(Timestamp fechaJuntaDespuesDe) {
        this.fechaJuntaDespuesDe = fechaJuntaDespuesDe;
    }

    public Timestamp getFechaAltaAntesDe() {
        return fechaAltaAntesDe;
    }

    public void setFechaAltaAntesDe(Timestamp fechaAltaAntesDe) {
        this.fechaAltaAntesDe = fechaAltaAntesDe;
    }

    public Timestamp getFechaAltaDespuesDe() {
        return fechaAltaDespuesDe;
    }

    public void setFechaAltaDespuesDe(Timestamp fechaAltaDespuesDe) {
        this.fechaAltaDespuesDe = fechaAltaDespuesDe;
    }

    public Timestamp getFechaFinalizacionAntesDe() {
        return fechaFinalizacionAntesDe;
    }

    public void setFechaFinalizacionAntesDe(Timestamp fechaFinalizacionAntesDe) {
        this.fechaFinalizacionAntesDe = fechaFinalizacionAntesDe;
    }

    public Timestamp getFechaFinalizacionDespuesDe() {
        return fechaFinalizacionDespuesDe;
    }

    public void setFechaFinalizacionDespuesDe(Timestamp fechaFinalizacionDespuesDe) {
        this.fechaFinalizacionDespuesDe = fechaFinalizacionDespuesDe;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
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

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    public String getEmpresaReparadora() {
        return empresaReparadora;
    }

    public void setEmpresaReparadora(String empresaReparadora) {
        this.empresaReparadora = empresaReparadora;
    }
}
