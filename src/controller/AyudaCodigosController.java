package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class AyudaCodigosController implements Initializable {

    @FXML
    private TableColumn<?, ?> colIdTipoReparacion;

    @FXML
    private TableColumn<?, ?> colTipoReparacion;

    @FXML
    private TableColumn<?, ?> colIdNivelUrgencia;

    @FXML
    private TableColumn<?, ?> colNivelUrgencia;

    @FXML
    private TableColumn<?, ?> colIdTipoComunicacion;

    @FXML
    private TableColumn<?, ?> colTipoComunicacion;

    @FXML
    private TableColumn<?, ?> colIdComunidades;

    @FXML
    private TableColumn<?, ?> colComunidades;

    @FXML
    private TableColumn<?, ?> colIdEmpleados;

    @FXML
    private TableColumn<?, ?> colEmpleados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
