package controller;

import com.jfoenix.controls.*;
import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.*;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class NewIncidentController implements Initializable {

    // mainPlayStage para tener acceso desde otras clases
    private static Stage crearIncidenteStage;

    @FXML
    private JFXTextArea taDescripcion;

    @FXML
    private JFXTextField tfTitulo, tfEmpresaReparadora;

    @FXML
    private JFXComboBox<String> cbEmpleado, cbTipoJunta, cbNivelUrgencia, cbComunidad, cbTipoReparacion;

    @FXML
    private JFXDatePicker dateFechaIncidencia;

    @FXML
    private JFXButton btnGuardar;

    private MainController mainController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rellenarCBEmpleados();
        rellenarCBTipoJunta();
        rellenarCBComunidades();
        rellenarCBNivelUrgencia();
        rellenarCBTipoReparacion();


        ParametrosController.limitTextField(tfTitulo, 100);
        ParametrosController.limitTextField(tfEmpresaReparadora, 100);
    }


    /**
     * Created 12/10/2019 by dmartinez
     *
     * Recoge el texto de los diferentes campos y crea un Incidente y lo almacena en la base de datos
     *
     * @param event
     */
    @FXML
    void crearIncidente(ActionEvent event) {

        Incidente incidente = checkFields();

        if(incidente != null) {
            // TODO Actualizamos lista

            boolean creationOK = IncidenteDAO.registerIncident(incidente);

            // obtenemos la ventana y la cerramos
            Stage stage = (Stage) ((JFXButton)event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    private Incidente checkFields() {

        String descripcion = "";
        String titulo = "";
        String empleado = "";
        int tipoJunta = 0;
        int nivelUrgencia = 0;
        Timestamp fechaJuntaCasted = null;
        String comunidad = "";
        int tipoReparacion = 0;
        String empresaReparadora = "";

        boolean descripcionOK = false;
        boolean tituloOK = false;
        boolean empleadoOK = false;
        boolean tipoJuntaOK = false;
        boolean nivelUrgenciaOK = false;
        boolean fechaJuntaOK = false;
        boolean comunidadOK = false;


        if (taDescripcion.getText().trim().equals("")) {
            // campo vacio
            taDescripcion.getStyleClass().add("error_field");
            taDescripcion.setPromptText("CAMPO VACÍO");
        } else {
            descripcion = taDescripcion.getText();
            descripcionOK = true;

            // Eliminamos filtros de error
            taDescripcion.getStyleClass().remove("error_field");
        }

        if (tfTitulo.getText().trim().equals("")) {
            // campo vacio
            tfTitulo.getStyleClass().add("error_field");
            tfTitulo.setPromptText("CAMPO VACÍO");
        } else {
            titulo = tfTitulo.getText();
            tituloOK = true;

            // Eliminamos filtros de error
            tfTitulo.getStyleClass().remove("error_field");
        }

        if (cbEmpleado.getSelectionModel().getSelectedItem() == null) {
            // campo vacio
            cbEmpleado.getStyleClass().add("error_field");
            cbEmpleado.setPromptText("CAMPO VACÍO");

            //TODO chequear si usuario está en BD

        } else if (!EmpleadoDAO.checkEmpleado(cbEmpleado.getSelectionModel().getSelectedItem())){
            // no está en bd
            cbEmpleado.getStyleClass().add("error_field");
            cbEmpleado.setPromptText("CAMPO ERRÓNEO");
        } else {
            empleado = cbEmpleado.getSelectionModel().getSelectedItem().split(" ")[0];
            empleadoOK = true;

            // Eliminamos filtros de error
            cbEmpleado.getStyleClass().remove("error_field");
        }

        if (cbTipoJunta.getSelectionModel().getSelectedItem() == null) {
            // campo vacio
            cbTipoJunta.getStyleClass().add("error_field");
            cbTipoJunta.setPromptText("CAMPO VACÍO");
        } else {
//            tipoJunta = cbTipoJunta.getSelectionModel().getSelectedItem();
            tipoJunta = TipoComunicadoDAO.getIdByTipo(cbTipoJunta.getSelectionModel().getSelectedItem()) + 1;
            tipoJuntaOK = true;

            // Eliminamos filtros de error
            cbTipoJunta.getStyleClass().remove("error_field");
        }

        if (cbNivelUrgencia.getSelectionModel().getSelectedItem() == null) {
            // campo vacio
            cbNivelUrgencia.getStyleClass().add("error_field");
            cbNivelUrgencia.setPromptText("CAMPO VACÍO");
        } else {
            nivelUrgencia = NivelUrgenciaDAO.getIdByNivelUrgencia(cbNivelUrgencia.getSelectionModel().getSelectedItem()) + 1;
            nivelUrgenciaOK = true;

            // Eliminamos filtros de error
            cbNivelUrgencia.getStyleClass().remove("error_field");
        }

        if (dateFechaIncidencia.getValue() == null) {
            // campo vacio
            dateFechaIncidencia.getStyleClass().add("error_field");
            dateFechaIncidencia.setPromptText("CAMPO VACÍO");
        } else {
            String fechaJunta = dateFechaIncidencia.getValue().toString();
            String fechaJuntaFormatted = fechaJunta.replace("/", "-");
            // Eliminamos filtros de error
            dateFechaIncidencia.getStyleClass().remove("error_field");
                DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
                Date date = null;
                try {
                    date = formatter.parse(fechaJuntaFormatted);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fechaJuntaCasted = new Timestamp(date.getTime());
                fechaJuntaOK = true;

                // Eliminamos filtros de error
                dateFechaIncidencia.getStyleClass().remove("error_field");
        }

        if(cbComunidad.getSelectionModel().getSelectedItem().equals("")){
            // campo vacio
            cbComunidad.getStyleClass().add("error_field");
            cbComunidad.setPromptText("CAMPO VACÍO");
            //TODO chequear si está en BD
        } else if (!ComunidadDAO.checkComunidad(cbComunidad.getSelectionModel().getSelectedItem())) {
            // no está en bd
            cbComunidad.getStyleClass().add("error_field");
            cbComunidad.setPromptText("CAMPO ERRÓNEO");
        } else {
            comunidad = cbComunidad.getSelectionModel().getSelectedItem().split(" ")[0];
            comunidadOK = true;
        }

        if(cbTipoReparacion.getSelectionModel().getSelectedItem() == null) {
            tipoReparacion = 1;
        } else {
            int tipoReparacionFormatted = Integer.parseInt(cbTipoReparacion.getSelectionModel().getSelectedItem().split(" ")[0]);
            tipoReparacion = tipoReparacionFormatted;
        }

        empresaReparadora = tfEmpresaReparadora.getText();


        if(descripcionOK && tituloOK && empleadoOK && tipoJuntaOK && nivelUrgenciaOK && fechaJuntaOK && comunidadOK) {
            Incidente incidente = new Incidente(empleado, titulo, descripcion, null, fechaJuntaCasted, null, nivelUrgencia, tipoJunta, "no", comunidad, tipoReparacion, empresaReparadora);
            return incidente;
        } else {
            return null;
        }
    }


    @FXML
    void comprobarTipoReparacion(MouseEvent event) {
        if(cbTipoReparacion.getSelectionModel().getSelectedItem() == null || cbTipoReparacion.getSelectionModel().getSelectedItem().split(" ")[0] == "1") {
            tfEmpresaReparadora.setDisable(true);
        } else {
            tfEmpresaReparadora.setDisable(false);
        }
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de niveles de urgencia con los diferentes campos
     */
    private void rellenarCBNivelUrgencia() {
        ArrayList<NivelUrgencia> nivelesUrgencia = NivelUrgenciaDAO.getNivelesUrgencia();

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(NivelUrgencia nivelUrgencia : nivelesUrgencia) {
            lista_campos.add(nivelUrgencia.getNivelUrgencia());
        }

        cbNivelUrgencia.setItems(lista_campos);
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de junta con los diferentes campos
     */
    private void rellenarCBTipoJunta() {
        ArrayList<TipoComunicado> tiposComunicados = TipoComunicadoDAO.getTiposComunicados();

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(TipoComunicado tipoComunicado : tiposComunicados) {
            lista_campos.add(tipoComunicado.getTipoComunicado());
        }

        cbTipoJunta.setItems(lista_campos);
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de empleados con los diferentes campos
     */
    private void rellenarCBEmpleados() {
        ArrayList<Empleado> empleados = EmpleadoDAO.getEmpleados();

        ObservableList<String> lista_empleados = FXCollections.observableArrayList();

        for(Empleado empleado : empleados) {
            lista_empleados.add(empleado.getCodigo() + " - " + empleado.getNombre() + " " + empleado.getApellidos());
        }

        cbEmpleado.setItems(lista_empleados);
    }


    private void rellenarCBTipoReparacion() {
        ArrayList<TipoReparacion> tiposReparaciones = TipoReparacionDAO.getTiposReparaciones();

        ObservableList<String> listaTiposReparaciones = FXCollections.observableArrayList();

        for(TipoReparacion tr : tiposReparaciones) {
            listaTiposReparaciones.add(tr.getId() + " - " + tr.gettipoReparacion());
        }

        cbTipoReparacion.setItems(listaTiposReparaciones);
    }

    private void rellenarCBComunidades() {
        ArrayList<Comunidad> comunidades = ComunidadDAO.getComunidades();

        ObservableList<String> listaComunidades = FXCollections.observableArrayList();

        for(Comunidad comunidad : comunidades) {
            listaComunidades.add(comunidad.getCodigo() + " - " + comunidad.getNombre());
        }

        cbComunidad.setItems(listaComunidades);

    }


    public static Stage getCrearIncidenteStage() {
        return crearIncidenteStage;
    }

    public static void setCrearIncidenteStage(Stage crearIncidenteStage) {
        NewIncidentController.crearIncidenteStage = crearIncidenteStage;
    }

}
