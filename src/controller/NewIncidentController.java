package controller;

import com.jfoenix.controls.*;
import database.EmpleadoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Empleado;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewIncidentController implements Initializable {

    // mainPlayStage para tener acceso desde otras clases
    private static Stage crearIncidenteStage;

    @FXML
    private JFXTextArea taDescripcion;

    @FXML
    private JFXTextField tfTitulo;

    @FXML
    private JFXComboBox<String> cbEmpleado, cbTipoJunta, cbNivelUrgencia;

    @FXML
    private JFXDatePicker dateFechaIncidencia;

    @FXML
    private JFXButton btnGuardar;


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
        String descripcion = taDescripcion.getText();
        String titulo = tfTitulo.getText();
        String empleado = cbEmpleado.getSelectionModel().getSelectedItem();
        String tipoJunta = cbTipoJunta.getSelectionModel().getSelectedItem();
        String nivelUrgencia = cbNivelUrgencia.getSelectionModel().getSelectedItem();
        String fechaJunta = dateFechaIncidencia.getValue().toString();

        String fechaJuntaFormatted = fechaJunta.replace("/", "-");

//        System.out.println(descripcion);
//        System.out.println(titulo);
//        System.out.println(empleado);
//        System.out.println(tipoJunta);
//        System.out.println(nivelUrgencia);
//        System.out.println(fechaJunta);

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


//    countries = CountryDAO.getCountries();
//    ObservableList<String> country_list = FXCollections.observableArrayList();
//        for(Country country : countries){
//        country_list.add(country.getName());
//    }
//        cbCountryRegister.setItems(country_list);
//        cbCountryRegister.setPromptText("Select country");



    public static Stage getCrearIncidenteStage() {
        return crearIncidenteStage;
    }

    public static void setCrearIncidenteStage(Stage crearIncidenteStage) {
        NewIncidentController.crearIncidenteStage = crearIncidenteStage;
    }

}
