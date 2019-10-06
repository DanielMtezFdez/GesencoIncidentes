package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Incidente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXComboBox<String> cbFiltroCampos;

    @FXML
    private JFXTextField buscarPorCampo;

    @FXML
    private JFXButton btnAñadirIncidente, btnAñadirEmpleado, btnInciEditar, btnInciCompletar;

    @FXML
    private TableView<Incidente> TablaIncidente;

    @FXML
    private TableColumn<?, ?> colTItulo, colEmpleado, colTipoIncidente, colNivelUrgencia, colFechaInicio, colTipoComunicacion;

    @FXML
    private JFXTextArea inciDescripcion;

    @FXML
    private Label inciEmpleado, inciTitulo, inciTipoJunta, inciComunicadoVia, inciNivelUrgencia, inciCreacion, inciFechaInicioAccion, inciFechaFinalizacion;

    @FXML
    private ImageView inciShowEmpleado, btnSearch;


    ArrayList<String> camposDeBusqueda;

    ObservableList<Incidente> incidentes;
    private int posicionIncidenteTabla;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCamposBusqueda();
    }


    /**
     * Created 06/10/2019 by dmartinez
     * Relleana el ComboBox con los diferentes campos
     */
    public void fillCamposBusqueda() {
        ArrayList<String> campos = new ArrayList<>();
        campos.add("Titulo");
        campos.add("Empleado");
        campos.add("TipoIncidente");
        campos.add("NivelUrgencia");
        campos.add("FechaInicio");
        campos.add("TipoComunicacion");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        for(String campo : campos) {
            lista_campos.add(campo);
        }

        cbFiltroCampos.setItems(lista_campos);
        cbFiltroCampos.setPromptText("Selecciona campo");

    }








    @FXML
    void añadirEmpleado(ActionEvent event) {

    }

    @FXML
    void añadirIncidente(ActionEvent event) {

    }

    @FXML
    void completarIncidente(ActionEvent event) {

    }

    @FXML
    void editarIncidente(ActionEvent event) {

    }

    @FXML
    void filtrarBusqueda(MouseEvent event) {

    }

    @FXML
    void mostrarEmpleado(MouseEvent event) {

    }
}
