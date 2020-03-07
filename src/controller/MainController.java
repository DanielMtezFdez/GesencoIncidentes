package controller;

import com.jfoenix.controls.*;
import database.IncidenteDAO;
import database.TipoComunicadoDAO;
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
import model.Filtro;
import model.Incidente;
import model.TipoComunicado;

import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private JFXComboBox<String> cbFiltroCampos;

    @FXML
    private JFXTextField inputFiltro;

    @FXML
    private JFXDatePicker dateFiltro;

    @FXML
    private JFXButton btnInciEditar, btnInciCompletar, btnAyudaCodigos, btnRefresh, btnFiltros;

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
    private TableColumn<Incidente, String> colEmpresaReparadora;

    @FXML
    private JFXTextArea inciDescripcion;

    @FXML
    private Label inciEmpleado, inciTitulo, inciCreacion, inciComunicadoVia, inciNivelUrgencia;

    @FXML
    private Label inciFechaJunta, inciFechaFinalizacion, inciComunidad, inciTipoReparacion, inciEmpresaReparadora;

    @FXML
    private Label lblFiltroComunidad, lblFiltroEmpleado, lblFiltroFechaJunta, lblFiltroNivelUrgencia, lblFiltroComunicacionVia, lblFiltroCompleto;

    @FXML
    private ImageView btnDeleteFiltroComunidad, btnDeleteFiltroEmpleado, btnDeleteFiltroFechaJunta, btnDeleteFiltroNivelUrgencia, btnDeleteFiltroComunicacionVia, btnDeleteFiltroCompleto, btnFiltrar;

    // Campos de filtro
    private int cantidadFiltros;
    private Map<String, String> listaFiltros = new HashMap<String, String>();;

    private Incidente incidenteSeleccionado;

    private Stage crearIncidenciaStage, showAyudaCodigo;

    private Filtro filtro;


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
        campos.add("Tipo comunicado");
        campos.add("Tipo reparación");
        campos.add("Empresa reparadora");
        campos.add("Completo");
        campos.add("Fecha de junta anterior a");
        campos.add("Fecha de junta posterior a");
        campos.add("Fecha de alta anterior a");
        campos.add("Fecha de alta posterior a");
        campos.add("Fecha junta antes de");
        campos.add("Fecha junta despues de");

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/crearIncidente.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Creación del Stage para el PlayLauncher
            crearIncidenciaStage = new Stage();
            NewIncidentController.setCrearIncidenteStage(crearIncidenciaStage);

            //Image icon = new Image("img/logo_gesenco.jpg");
            //crearIncidenciaStage.getIcons().add(icon);
            crearIncidenciaStage.setTitle("Creación incidencia");

            Scene scene = new Scene(page);

            crearIncidenciaStage.setOnCloseRequest(e->NewIncidentController.setCrearIncidenteStage(null));
            crearIncidenciaStage.setScene(scene);
            crearIncidenciaStage.show();

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
    void filtrarBusqueda(ActionEvent event) {

        String filtroAplicado = cbFiltroCampos.getSelectionModel().getSelectedItem().replace(" ", "").toLowerCase();
        String filtroCampoAplicado = null;

//        campos.add("Comunidad");
//        campos.add("Empleado");
//        campos.add("Nivel urgencia");
//        campos.add("Tipo comunicado");
//        campos.add("Tipo reparación");
//        campos.add("Empresa reparadora");
//        campos.add("Completo");
//        campos.add("Fecha de junta anterior a");
//        campos.add("Fecha de junta posterior a");
//        campos.add("Fecha de alta anterior a");
//        campos.add("Fecha de alta posterior a");
//        campos.add("Fecha junta antes de");
//        campos.add("Fecha junta despues de");

        switch(filtroAplicado){
            case "Comunidad":
            case "Empleado":
            case "Nivel urgencia":
            case "Tipo comunicado":
            case "Tipo reparación":
            case "Empresa reparadora":
            case "Completo":
        }


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

        filtro = new Filtro();

        //TODO obtener filtros




        List<TipoComunicado> tiposDeComunicado = TipoComunicadoDAO.getTiposComunicados();
        HashMap<Integer, String> mapTiposComunicados = new HashMap<>();

        for(int i = 0; i<tiposDeComunicado.size(); i++) {
            mapTiposComunicados.put(tiposDeComunicado.get(i).getId(), tiposDeComunicado.get(i).getTipoComunicado());
        }

        colComunidad.setCellValueFactory(new PropertyValueFactory<Incidente, String>("codComunidad"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Incidente, String>("nombreEmpleado"));
        colFechaJunta.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaComunicado"));
        colNivelUrgencia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("nivelUrgencia"));
        colComunicacionVia.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoComunicado"));
        colCompleto.setCellValueFactory(new PropertyValueFactory<Incidente, String>("completo"));
        colFechaCreacion.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaAlta"));
        colFechaFinalizacion.setCellValueFactory(new PropertyValueFactory<Incidente, Timestamp>("fechaFin"));
        colTipoReparacion.setCellValueFactory(new PropertyValueFactory<Incidente, String>("tipoReparacion"));
        colEmpresaReparadora.setCellValueFactory(new PropertyValueFactory<Incidente, String>("empresaReparadora"));

        ArrayList<Incidente> incidentes;

        incidentes = IncidenteDAO.getIncidentes(filtro);

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
//        inciEmpleado.setText(incidenteSeleccionado.getCodEmpleado().toUpperCase());
        inciEmpleado.setText(incidenteSeleccionado.getNombreEmpleado());
        inciEmpleado.setText(incidenteSeleccionado.getNombreEmpleado());
        inciTitulo.setText(incidenteSeleccionado.getTitulo());
//        inciComunicadoVia.setText(TipoComunicadoDAO.getTipoById(incidenteSeleccionado.getTipoComunicado()));
        inciComunicadoVia.setText(incidenteSeleccionado.getTipoComunicado());
        inciNivelUrgencia.setText(incidenteSeleccionado.getNivelUrgencia());
        inciCreacion.setText(incidenteSeleccionado.getFechaAlta().toString());
        inciFechaJunta.setText(incidenteSeleccionado.getFechaComunicado().toString());
        inciComunidad.setText(String.valueOf(incidenteSeleccionado.getCodComunidad()));
        inciTipoReparacion.setText(incidenteSeleccionado.getTipoReparacion());
        inciEmpresaReparadora.setText(incidenteSeleccionado.getEmpresaReparadora());

        if(incidenteSeleccionado.getCompleto().equals("si")){
            inciFechaFinalizacion.setText(incidenteSeleccionado.getFechaFin().toString());
        } else {
            inciFechaFinalizacion.setText("NO FINALIZADO");
        }

        inciDescripcion.setText(incidenteSeleccionado.getDescripcion());

    }


    @FXML
    void recargarTabla(ActionEvent event) {
        inicializarTablaIncidentes();
    }

    @FXML
    void showFiltros(ActionEvent event) {
    }

    @FXML
    void showAyudaCodigos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ayudaCodigos.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Creación del Stage para el PlayLauncher
            showAyudaCodigo = new Stage();
            AyudaCodigosController.setAyudaCodigosStage(showAyudaCodigo);

            //Image icon = new Image("img/logo_gesenco.jpg");
            //showAyudaCodigo.getIcons().add(icon);
            showAyudaCodigo.setTitle("Ayuda códigos");

            Scene scene = new Scene(page);
            showAyudaCodigo.setResizable(false);

            showAyudaCodigo.setScene(scene);
            showAyudaCodigo.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void recargarTipoBusqueda(MouseEvent event) {

        String filtroAplicado = cbFiltroCampos.getSelectionModel().getSelectedItem().replace(" ", "").toLowerCase();

        if(filtroAplicado.equals("Comunidad") | filtroAplicado.equals("Empleado") | filtroAplicado.equals("Nivel urgencia") | filtroAplicado.equals("Tipo comunicado")
            | filtroAplicado.equals("Tipo reparación") |filtroAplicado.equals("Empresa reparadora") |filtroAplicado.equals("Completo")) {
            inputFiltro.setVisible(true);
            dateFiltro.setVisible(false);
        } else {
            inputFiltro.setVisible(false);
            dateFiltro.setVisible(true);
        }
    }

}
