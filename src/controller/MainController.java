package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.IncidenteDAO;
import database.NivelUrgenciaDAO;
import database.TipoComunicadoDAO;
import database.TipoReparacionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
    private JFXButton btnInciEditar, btnInciCompletar, btnAyudaCodigos;

    @FXML
    private TableView<Incidente> tablaIncidente;

    @FXML
    private TableColumn<Incidente, String> colComunidad;
    @FXML
    private TableColumn<Incidente, String> colEmpleado;
    @FXML
    private TableColumn<Incidente, Timestamp> colFechaJunta, colFechaCreacion, colFechaFinalizacion;
    @FXML
    private TableColumn<Incidente, String> colNivelUrgencia;
    @FXML
    private TableColumn<Incidente, String> colComunicacionVia;
    @FXML
    private TableColumn<Incidente, String> colTipoReparacion;
    @FXML
    private TableColumn<Incidente, String> colCompleto;

    @FXML
    private JFXTextArea inciDescripcion;

    @FXML
    private Label inciEmpleado, inciTitulo, inciCreacion, inciComunicadoVia, inciNivelUrgencia,  inciFechaJunta, inciFechaFinalizacion, inciComunidad, inciTipoReparacion;

    @FXML
    private ImageView btnSearch, btnRefresh;

    @FXML
    private Label lblFiltroComunidad, lblFiltroEmpleado, lblFiltroFechaJunta, lblFiltroNivelUrgencia, lblFiltroComunicacionVia, lblFiltroCompleto;

    @FXML
    private ImageView btnDeleteFiltroComunidad, btnDeleteFiltroEmpleado, btnDeleteFiltroFechaJunta, btnDeleteFiltroNivelUrgencia, btnDeleteFiltroComunicacionVia, btnDeleteFiltroCompleto;

    // Campos de filtro
    private int cantidadFiltros;
    private Map<String, String> listaFiltros = new HashMap<String, String>();;

    private Incidente incidenteSeleccionado;

    private Stage crearIncidenciaStage, showAyudaCodigo;


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
        campos.add("Comunidad");
        campos.add("Empleado");
        campos.add("Nivel urgencia");
        campos.add("Fecha junta antes de");
        campos.add("Fecha junta despues de");
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

                Image icon = new Image("img/logo_gesenco.jpg");
                crearIncidenciaStage.getIcons().add(icon);
                crearIncidenciaStage.setTitle("Creación incidencia");

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

        switch (filtroAplicado) {
            case "comunidad":
                lblFiltroComunidad.setText(filtroCampoAplicado);
                break;

            case "empleado":
                lblFiltroEmpleado.setText(filtroCampoAplicado);
                break;

            case "nivelurgencia":
                lblFiltroNivelUrgencia.setText(filtroCampoAplicado);
                break;

            case "fechajuntaantesde":
                lblFiltroFechaJunta.setText("Antes de " + filtroCampoAplicado);
                break;

            case "fechajuntadespuesde":
                lblFiltroFechaJunta.setText("Despues de " + filtroCampoAplicado);
                break;

            case "tipocomunicado":
                lblFiltroComunicacionVia.setText(filtroCampoAplicado);
                break;

            case "completo":
                lblFiltroCompleto.setText(filtroCampoAplicado);
                break;

            default:
                break;
        }


        inicializarTablaIncidentes();

    }


    private void inicializarTablaIncidentes() {

        colComunidad.setCellValueFactory(new PropertyValueFactory<Incidente, String>("codComunidad"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Incidente, String>("codEmpleado"));
        colFechaJunta.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaJunta"));
        colNivelUrgencia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("nivelUrgencia"));
        colComunicacionVia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoComunicado"));
        colCompleto.setCellValueFactory(new PropertyValueFactory<Incidente, String>("completo"));
        colFechaCreacion.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaAlta"));
        colFechaFinalizacion.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaFin"));
        colTipoReparacion.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoReparacion"));

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

        if(incidenteSeleccionado == null) {
            return;
        }
        // Se establecen los labels con los datos correspondientes
        inciEmpleado.setText(incidenteSeleccionado.getCodEmpleado().toUpperCase());
        inciTitulo.setText(incidenteSeleccionado.getTitulo());
        inciComunicadoVia.setText(TipoComunicadoDAO.getTipoById(incidenteSeleccionado.getTipoComunicado()));
        inciNivelUrgencia.setText(NivelUrgenciaDAO.getNivelUrgenciaByID(incidenteSeleccionado.getNivelUrgencia()));
        inciCreacion.setText(incidenteSeleccionado.getFechaAlta().toString());
        inciFechaJunta.setText(incidenteSeleccionado.getFechaJunta().toString());
        inciComunidad.setText(incidenteSeleccionado.getCodComunidad().toUpperCase());
        inciTipoReparacion.setText(TipoReparacionDAO.getTipoById(incidenteSeleccionado.getTipoReparacion()));

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
    void showAyudaCodigos(ActionEvent event) {
        try {
            if(showAyudaCodigo == null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ayudaCodigos.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Creación del Stage para el PlayLauncher
                showAyudaCodigo = new Stage();
                NewIncidentController.setCrearIncidenteStage(showAyudaCodigo);

                Image icon = new Image("img/logo_gesenco.jpg");
                showAyudaCodigo.getIcons().add(icon);
                showAyudaCodigo.setTitle("Ayuda códigos");

                Scene scene = new Scene(page);
                showAyudaCodigo.setResizable(false);

                showAyudaCodigo.setScene(scene);
                showAyudaCodigo.show();
            } else {
                showAyudaCodigo.toFront();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void deleteFiltroCompleto(MouseEvent event) {
        lblFiltroCompleto.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("completo");

        // Recarga de la tabla
        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroComunicacionVia(MouseEvent event) {
        lblFiltroComunicacionVia.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("tipocomunicado");

        // Recarga de la tabla
        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroEmpleado(MouseEvent event) {
        lblFiltroEmpleado.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("empleado");

        // Recarga de la tabla
        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroNivelUrgencia(MouseEvent event) {
        lblFiltroNivelUrgencia.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("nivelurgencia");

        // Recarga de la tabla
        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroFechaJunta(MouseEvent event) {
        lblFiltroFechaJunta.setText("");
        cantidadFiltros -= 1;
        if(listaFiltros.containsKey("fechajuntaantesde")) {
            listaFiltros.remove("fechajuntaantesde");
        }

        if(listaFiltros.containsKey("fechajuntadespuesde")) {
            listaFiltros.remove("fechajuntadespuesde");
        }


        // Recarga de la tabla
        inicializarTablaIncidentes();
    }


    @FXML
    void deleteFiltroComunidad(MouseEvent event) {
        lblFiltroComunidad.setText("");
        cantidadFiltros -= 1;
        listaFiltros.remove("comunidad");

        // Recarga de la tabla
        inicializarTablaIncidentes();
    }
}
