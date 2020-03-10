package controller;

import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.util.ArrayList;

public class RellenarComboBox {

    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de empleados con los diferentes campos
     */
    public static ObservableList<String> rellenarCBEmpleados() {
        ArrayList<Empleado> empleados = EmpleadoDAO.getEmpleados();

        ObservableList<String> lista_empleados = FXCollections.observableArrayList();

        for(Empleado empleado : empleados) {
            if(empleado.getApellidos() == null) {
                empleado.setApellidos("");
            }
            lista_empleados.add(empleado.getCodigo() + " - " + empleado.getNombre() + " " + empleado.getApellidos());
        }

        return lista_empleados;
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de junta con los diferentes campos
     */
    public static ObservableList<String> rellenarCBTipoComunicado() {
        ArrayList<TipoComunicado> tiposComunicados = TipoComunicadoDAO.getTiposComunicados();

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(TipoComunicado tipoComunicado : tiposComunicados) {
            lista_campos.add(tipoComunicado.getId() + " - " + tipoComunicado.getTipoComunicado());
        }

        return lista_campos;
    }

    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de comunidades con los diferentes campos
     */
    public static ObservableList<String> rellenarCBComunidades() {
        ArrayList<Comunidad> comunidades = ComunidadDAO.getComunidades();

        ObservableList<String> listaComunidades = FXCollections.observableArrayList();

        for(Comunidad comunidad : comunidades) {
            listaComunidades.add(comunidad.getCodigo() + " - " + comunidad.getNombre());
        }

        return listaComunidades;
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de niveles de urgencia con los diferentes campos
     */
    public static ObservableList<String> rellenarCBNivelUrgencia() {
        ArrayList<NivelUrgencia> nivelesUrgencia = NivelUrgenciaDAO.getNivelesUrgencia();

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(NivelUrgencia nivelUrgencia : nivelesUrgencia) {
            lista_campos.add(nivelUrgencia.getId() + " - " + nivelUrgencia.getNivelUrgencia());
        }

        return lista_campos;
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de reparacion con los diferentes campos
     */
    public static ObservableList<String> rellenarCBTipoReparacion() {
        ArrayList<TipoReparacion> tiposReparaciones = TipoReparacionDAO.getTiposReparaciones();

        ObservableList<String> listaTiposReparaciones = FXCollections.observableArrayList();

        for(TipoReparacion tr : tiposReparaciones) {
            listaTiposReparaciones.add(tr.getId() + " - " + tr.gettipoReparacion());
        }

        return listaTiposReparaciones;
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de reparacion con los diferentes campos
     */
    public static ObservableList<String> rellenarCBCompleto() {
        ObservableList<String> listaTiposReparaciones = FXCollections.observableArrayList();
        listaTiposReparaciones.add("si");
        listaTiposReparaciones.add("no");
        return listaTiposReparaciones;
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de reparacion con los diferentes campos
     */
    public static ObservableList<String> rellenarCBEmpresasReparadoras() {
        ArrayList<String> empresasReparadoras = IncidenteDAO.getEmpresasReparadoras();
        ObservableList<String> listaEmpresas = FXCollections.observableArrayList();
        for(String empresa : empresasReparadoras) {
            listaEmpresas.add(empresa);
        }

        return listaEmpresas;
    }

}
