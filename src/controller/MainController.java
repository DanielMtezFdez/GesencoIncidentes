package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.IncidenteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Incidente;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXComboBox<String> cbFiltroCampos;

    @FXML
    private JFXTextField buscarPorCampo;

    @FXML
    private JFXButton btnInciEditar, btnInciCompletar;

    @FXML
    private TableView<Incidente> tablaIncidente;

    @FXML
    private TableColumn<Incidente, String> colTitulo, colEmpleado, colTipoIncidente, colNivelUrgencia, colComunicacionVia, colCompleto;

    @FXML
    private JFXTextArea inciDescripcion;

    @FXML
    private Label inciEmpleado, inciTitulo, inciCreacion, inciComunicadoVia, inciNivelUrgencia,  inciFechaJunta, inciFechaFinalizacion;

    @FXML
    private ImageView btnSearch, btnRefresh;


    ArrayList<String> camposDeBusqueda;

    ObservableList<Incidente> incidentes;
    private int posicionIncidenteTabla;

    private Stage crearIncidenciaStage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCamposBusqueda();

        inicializarTablaIncidentes();
    }


    /**
     * Created 06/10/2019 by dmartinez
     * Relleana el ComboBox con los diferentes campos
     */
    private void fillCamposBusqueda() {
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

    /**
     * Created 12/10/2019 by dmartinez
     *
     * Enseña la ventana de creación de incidentes
     *
     * @param event
     */
    @FXML
    void añadirIncidente(ActionEvent event) {
        try {
            if(crearIncidenciaStage == null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/crearIncidente.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Creación del Stage para el PlayLauncher
                crearIncidenciaStage = new Stage();
                NewIncidentController.setCrearIncidenteStage(crearIncidenciaStage);

                Scene scene = new Scene(page);

                crearIncidenciaStage.setScene(scene);
                crearIncidenciaStage.show();
            } else {
                crearIncidenciaStage.toFront();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void completarIncidente(ActionEvent event) {
        // TODO
    }

    @FXML
    void editarIncidente(ActionEvent event) {
        Incidente incidente = new Incidente();

        incidente.setCodEmpleado(inciEmpleado.getText());
        incidente.setTitulo(inciTitulo.getText());
        incidente.setDescripcion(inciDescripcion.getText());
        incidente.setFechaAlta(inciCreacion.getText());
        incidente.setFechaJunta(inciFechaJunta.getText());
        incidente.setFechaFin(inciFechaFinalizacion.getText());
        incidente.setNivelUrgencia(inciNivelUrgencia.getText());
        incidente.setTipoComunicado(inciComunicadoVia.getText());
        incidente.setCompleto(inciEmpleado.getText());

        incidentes.set(posicionIncidenteTabla, incidente);
        // TODO GUARDAR EN BD

    }

    @FXML
    void filtrarBusqueda(MouseEvent event) {
        // TODO
    }


    private void inicializarTablaIncidentes() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<Incidente, String>("titulo"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Incidente, String>("codEmpleado"));
        colTipoIncidente.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoIncidente"));
        colNivelUrgencia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("nivelUrgencia"));
        colComunicacionVia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoComunicado"));
        colCompleto.setCellValueFactory(new PropertyValueFactory<Incidente, String>("completo"));

        ArrayList<Incidente> incidentes = IncidenteDAO.getIncidentes();
        ObservableList<Incidente> listaIncidentes = FXCollections.observableArrayList(incidentes);
        tablaIncidente.setItems(listaIncidentes);
    }

    @FXML
    void mostrarIncidente(MouseEvent event) {
        List<Incidente> tabla = tablaIncidente.getSelectionModel().getSelectedItems();

        Incidente incidente = tablaIncidente.getSelectionModel().getSelectedItem();
        System.out.println(incidente.getNivelUrgencia());

        // Se establecen los labels con los datos correspondientes
        inciEmpleado.setText(incidente.getCodEmpleado());
        inciTitulo.setText(incidente.getTitulo());
        inciComunicadoVia.setText(incidente.getTipoComunicado());
        inciNivelUrgencia.setText(incidente.getNivelUrgencia());
        inciCreacion.setText(incidente.getFechaAlta());
        inciFechaJunta.setText(incidente.getFechaJunta());
        inciFechaFinalizacion.setText(incidente.getFechaFin());
        inciDescripcion.setText(incidente.getDescripcion());

    }

    @FXML
    void recargarTabla(MouseEvent event) {
        inicializarTablaIncidentes();
    }
}
