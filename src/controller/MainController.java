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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private TableColumn<Incidente, String> colTitulo;
    @FXML
    private TableColumn<Incidente, String> colEmpleado;
    @FXML
    private TableColumn<Incidente, Timestamp> colFechaJunta;
    @FXML
    private TableColumn<Incidente, String> colNivelUrgencia;
    @FXML
    private TableColumn<Incidente, String> colComunicacionVia;
    @FXML
    private TableColumn<Incidente, String> colCompleto;

    @FXML
    private JFXTextArea inciDescripcion;

    @FXML
    private Label inciEmpleado, inciTitulo, inciCreacion, inciComunicadoVia, inciNivelUrgencia,  inciFechaJunta, inciFechaFinalizacion;

    @FXML
    private ImageView btnSearch, btnRefresh;

    @FXML
    private Label lblFiltroTitulo, lblFiltroEmpleado, lblFiltroFechaJunta, lblFiltroNivelUrgencia, lblFiltroComunicacionVia, lblFiltroCompleto;

    @FXML
    private ImageView btnDeleteFiltroTitulo, btnDeleteFiltroEmpleado, btnDeleteFiltroFechaJunta, btnDeleteFiltroNivelUrgencia, btnDeleteFiltroComunicacionVia, btnDeleteFiltroCompleto;

    // Campos de filtro
    private int cantidadFiltros;
    private Map<String, String> listaFiltros = new HashMap<String, String>();;

    private Incidente incidenteSeleccionado;

    private Stage crearIncidenciaStage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCamposBusqueda();

        cantidadFiltros = 0;

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
        campos.add("Nivel urgencia");
        campos.add("Fecha junta");
        campos.add("Tipo comunicado");
        campos.add("Completo");

        ObservableList<String> lista_campos = FXCollections.observableArrayList();

        lista_campos.addAll(campos);

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

    /**
     * Created 16/10/2019 by dmartinez
     *
     * Funcion llamada al pulsar la tecla "Completar". Llama al método de IncidenteDAO que marca como completo en la base de datos el incidente en cuestión
     * Al acabar la actualización, actualiza la tabla de datos
     *
     * @param event
     */
    @FXML
    void completarIncidente(ActionEvent event) {

        IncidenteDAO.completarIncidente(incidenteSeleccionado.getIdIncidente());

        inicializarTablaIncidentes();
    }

    /**
     * Created 15/10/2019 by dmartinez
     *
     * Recoge la descripcion editada y hace un Update en la base de datos con el ID del incidente seleccionado
     *
     * @param event
     */
    @FXML
    void editarIncidente(ActionEvent event) {

        String descripcionModificada = inciDescripcion.getText();

        IncidenteDAO.editarIncidente(incidenteSeleccionado.getIdIncidente(), descripcionModificada);
        inicializarTablaIncidentes();
    }

    @FXML
    void filtrarBusqueda(MouseEvent event) {

        String filtroAplicado = cbFiltroCampos.getSelectionModel().getSelectedItem().replace(" ", "").toLowerCase();
        String filtroCampoAplicado = buscarPorCampo.getText();

        listaFiltros.put(filtroAplicado, filtroCampoAplicado);

        cantidadFiltros += 1;

        inicializarTablaIncidentes();

    }


    private void inicializarTablaIncidentes() {

        colTitulo.setCellValueFactory(new PropertyValueFactory<Incidente, String>("titulo"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Incidente, String>("codEmpleado"));
        colFechaJunta.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaJunta"));
        colNivelUrgencia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("nivelUrgencia"));
        colComunicacionVia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoComunicado"));
        colCompleto.setCellValueFactory(new PropertyValueFactory<Incidente, String>("completo"));

        ArrayList<Incidente> incidentes;

        if(cantidadFiltros == 0) {
            incidentes = IncidenteDAO.getIncidentes();
        } else {
            incidentes = IncidenteDAO.getIncidentesConFiltro(listaFiltros);
        }

        ObservableList<Incidente> listaIncidentes = FXCollections.observableArrayList(incidentes);
        tablaIncidente.setItems(listaIncidentes);
    }

    @FXML
    void mostrarIncidente(MouseEvent event) {

        incidenteSeleccionado = tablaIncidente.getSelectionModel().getSelectedItem();

        // Se establecen los labels con los datos correspondientes
        inciEmpleado.setText(incidenteSeleccionado.getCodEmpleado());
        inciTitulo.setText(incidenteSeleccionado.getTitulo());
        inciComunicadoVia.setText(incidenteSeleccionado.getTipoComunicado());
        inciNivelUrgencia.setText(incidenteSeleccionado.getNivelUrgencia());
        inciCreacion.setText(incidenteSeleccionado.getFechaAlta().toString());
        inciFechaJunta.setText(incidenteSeleccionado.getFechaJunta().toString());

        if(incidenteSeleccionado.getCompleto().equals("si")){
            inciFechaFinalizacion.setText(incidenteSeleccionado.getFechaFin().toString());
        } else {
            inciFechaFinalizacion.setText("NO FINALIZADO");
        }

        inciDescripcion.setText(incidenteSeleccionado.getDescripcion());

    }


    @FXML
    void recargarTabla(MouseEvent event) {
        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroCompleto(MouseEvent event) {
        lblFiltroCompleto.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("completo");

        // Recarga de la tabla
//        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroComunicacionVia(MouseEvent event) {
        lblFiltroComunicacionVia.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("tipocomunicado");

        // Recarga de la tabla
//        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroEmpleado(MouseEvent event) {
        lblFiltroEmpleado.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("empleado");

        // Recarga de la tabla
//        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroNivelUrgencia(MouseEvent event) {
        lblFiltroNivelUrgencia.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("nivelurgencia");

        // Recarga de la tabla
//        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroFechaJunta(MouseEvent event) {
        lblFiltroFechaJunta.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("fechajunta");

        // Recarga de la tabla
//        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroTitulo(MouseEvent event) {
        lblFiltroTitulo.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("titulo");

        // Recarga de la tabla
//        inicializarTablaIncidentes();
    }
}
