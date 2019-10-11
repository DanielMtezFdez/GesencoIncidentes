package controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewIncidentController implements Initializable {

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






}
