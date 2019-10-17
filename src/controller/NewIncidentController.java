package controller;

import com.jfoenix.controls.*;
import database.EmpleadoDAO;
import database.IncidenteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Empleado;
import model.Incidente;

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
    private JFXTextField tfTitulo, tfComunidad;

    @FXML
    private JFXComboBox<String> cbEmpleado, cbTipoJunta, cbNivelUrgencia;

    @FXML
    private JFXDatePicker dateFechaIncidencia;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTimePicker timeFechaIncidencia;

    private MainController mainController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rellenarCBEmpleados();
        rellenarCBTipoJunta();
        rellenarCBNivelUrgencia();
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
        String tipoJunta = "";
        String nivelUrgencia = "";
        Timestamp fechaJuntaCasted = null;
        String comunidad = "";

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

        } else {
            empleado = cbEmpleado.getSelectionModel().getSelectedItem();
            empleadoOK = true;

            // Eliminamos filtros de error
            cbEmpleado.getStyleClass().remove("error_field");
        }

        if (cbTipoJunta.getSelectionModel().getSelectedItem() == null) {
            // campo vacio
            cbTipoJunta.getStyleClass().add("error_field");
            cbTipoJunta.setPromptText("CAMPO VACÍO");
        } else {
            tipoJunta = cbTipoJunta.getSelectionModel().getSelectedItem();
            tipoJuntaOK = true;

            // Eliminamos filtros de error
            cbTipoJunta.getStyleClass().remove("error_field");
        }

        if (cbNivelUrgencia.getSelectionModel().getSelectedItem() == null) {
            // campo vacio
            cbNivelUrgencia.getStyleClass().add("error_field");
            cbNivelUrgencia.setPromptText("CAMPO VACÍO");
        } else {
            nivelUrgencia = cbNivelUrgencia.getSelectionModel().getSelectedItem();
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

            if(timeFechaIncidencia.getValue() == null){
                // campo vacio
                timeFechaIncidencia.getStyleClass().add("error_field");
                timeFechaIncidencia.setPromptText("CAMPO VACÍO");
            } else {
                DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD hh:mm");
                Date date = null;
                try {
                    date = formatter.parse(fechaJuntaFormatted + " " + timeFechaIncidencia.getValue());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fechaJuntaCasted = new Timestamp(date.getTime());
                fechaJuntaOK = true;

                // Eliminamos filtros de error
                dateFechaIncidencia.getStyleClass().remove("error_field");
            }

            if(tfComunidad.getText().equals("")){
                // campo vacio
                tfComunidad.getStyleClass().add("error_field");
                tfComunidad.setPromptText("CAMPO VACÍO");
                //TODO chequear si está en BD
            } else {
                comunidad = tfComunidad.getText();
                comunidadOK = true;
            }


        }

        if(descripcionOK && tituloOK && empleadoOK && tipoJuntaOK && nivelUrgenciaOK && fechaJuntaOK) {
            Incidente incidente = new Incidente(empleado, titulo, descripcion, null, fechaJuntaCasted, null, nivelUrgencia, tipoJunta, "no", comunidad);
            return incidente;
        } else {
            return null;
        }
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de niveles de urgencia con los diferentes campos
     */
    private void rellenarCBNivelUrgencia() {
        ArrayList<String> campos = new ArrayList<>();
        campos.add("Superurgente");
        campos.add("Urgente");
        campos.add("Normal");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        lista_campos.addAll(campos);

        cbNivelUrgencia.setItems(lista_campos);
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de junta con los diferentes campos
     */
    private void rellenarCBTipoJunta() {
        ArrayList<String> campos = new ArrayList<>();
        campos.add("Junta ordinaria");
        campos.add("Junta extraordinaria");
        campos.add("Oficina");
        campos.add("Telefono");
        campos.add("Administracion");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        lista_campos.addAll(campos);

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
            lista_empleados.add(empleado.getCodigo());
        }

        cbEmpleado.setItems(lista_empleados);
    }



    public static Stage getCrearIncidenteStage() {
        return crearIncidenteStage;
    }

    public static void setCrearIncidenteStage(Stage crearIncidenteStage) {
        NewIncidentController.crearIncidenteStage = crearIncidenteStage;
    }

}
