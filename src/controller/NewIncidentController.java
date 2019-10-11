package controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

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
        campos.add("");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(String campo : campos) {
            lista_campos.add(campo);
        }

        cbNivelUrgencia.setItems(lista_campos);
        cbNivelUrgencia.setPromptText("Selecciona campo");
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de tipos de junta con los diferentes campos
     */
    private void rellenarCBTipoJunta() {
        ArrayList<String> campos = new ArrayList<>();
        campos.add("");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(String campo : campos) {
            lista_campos.add(campo);
        }

        cbTipoJunta.setItems(lista_campos);
        cbTipoJunta.setPromptText("Selecciona campo");
    }


    /**
     * Created 11/10/2019 by dmartinez
     * Rellena el ComboBox de empleados con los diferentes campos
     */
    private void rellenarCBEmpleados() {
        ArrayList<String> campos = new ArrayList<>();
        campos.add("");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        lista_campos.addAll(campos);

        cbEmpleado.setItems(lista_campos);
        cbEmpleado.setPromptText("Selecciona campo");
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
