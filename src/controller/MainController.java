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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Filtro;
import model.Incidente;
import model.TipoComunicado;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private JFXComboBox<String> cbFiltroCampos, cbFiltro;

    @FXML
    private JFXTextField inputFiltro;

    @FXML
    private JFXDatePicker dateFiltro;

    @FXML
    private JFXButton btnInciEditar, btnInciCompletar, btnAyudaCodigos, btnRefresh, btnFiltros, btnBuscar;

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

    // Campos de filtro
    private int cantidadFiltros;
    private Map<String, String> listaFiltros = new HashMap<String, String>();;

    private Incidente incidenteSeleccionado;

    private Stage crearIncidenciaStage, showAyudaCodigo, showFiltrosAplicadosStage;

    private static Filtro filtro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Image searchIcon = new Image(new File("img/ic_search.png").toURI().toString());
//        btnFiltrar.setImage(searchIcon);
        fillCamposBusqueda();
        filtro = new Filtro();
//        cantidadFiltros = 0;
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
        campos.add("Fecha finalizacion antes de");
        campos.add("Fecha finalizacion despues de");

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

            Image icon = new Image(new File("img/logo_gesenco.jpg").toURI().toString());
            crearIncidenciaStage.getIcons().add(icon);
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

        if (cbFiltro.isVisible()){
            if(cbFiltro.getSelectionModel().getSelectedItem().equals("")) {
                return;
            }
        }
        if (dateFiltro.isVisible()){
            if(dateFiltro.getValue() == null){
                return;
            }
        }

        String filtroAplicado = cbFiltroCampos.getSelectionModel().getSelectedItem();

        switch(filtroAplicado){
            case "Comunidad":
                filtro.setCodComunidad(cbFiltro.getSelectionModel().getSelectedItem().split(" ")[0]);
                break;
            case "Empleado":
                filtro.setCodEmpleado(cbFiltro.getSelectionModel().getSelectedItem().split(" ")[0]);
                break;
            case "Nivel urgencia":
                filtro.setNivelUrgencia(cbFiltro.getSelectionModel().getSelectedItem().split(" ")[0]);
                break;
            case "Tipo comunicado":
                filtro.setTipoComunicado(cbFiltro.getSelectionModel().getSelectedItem().split(" ")[0]);
                break;
            case "Tipo reparación":
                filtro.setTipoReparacion(cbFiltro.getSelectionModel().getSelectedItem().split(" ")[0]);
                break;
            case "Empresa reparadora":
                filtro.setEmpresaReparadora(cbFiltro.getSelectionModel().getSelectedItem());
                break;
            case "Completo":
                if(cbFiltro.getSelectionModel().getSelectedItem().toLowerCase().equals("sí")){
                    cbFiltro.getSelectionModel().getSelectedItem().replace("í", "i");
                }
                filtro.setCompleto(cbFiltro.getSelectionModel().getSelectedItem().toLowerCase());
                break;
            case "Fecha de junta anterior a":
                filtro.setFechaJuntaAntesDe(Timestamp.valueOf(dateFiltro.getValue().atStartOfDay()));
                break;
            case "Fecha de junta posterior a":
                filtro.setFechaJuntaDespuesDe(Timestamp.valueOf(dateFiltro.getValue().atStartOfDay()));
                break;
            case "Fecha de alta anterior a":
                filtro.setFechaAltaAntesDe(Timestamp.valueOf(dateFiltro.getValue().atStartOfDay()));
                break;
            case "Fecha de alta posterior a":
                filtro.setFechaAltaDespuesDe(Timestamp.valueOf(dateFiltro.getValue().atStartOfDay()));
                break;
            case "Fecha finalizacion antes de":
                filtro.setFechaFinalizacionAntesDe(Timestamp.valueOf(dateFiltro.getValue().atStartOfDay()));
                break;
            case "Fecha finalizacion despues de":
                filtro.setFechaFinalizacionDespuesDe(Timestamp.valueOf(dateFiltro.getValue().atStartOfDay()));
                break;
        }

        inicializarTablaIncidentes();

    }

    private void inicializarTablaIncidentes() {

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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/filtrosAplicados.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Creación del Stage para el PlayLauncher
            showFiltrosAplicadosStage = new Stage();
            FiltrosAplicadosController.setShowFiltrosStage(showFiltrosAplicadosStage);

            Image icon = new Image(new File("img/logo_gesenco.jpg").toURI().toString());
            showFiltrosAplicadosStage.getIcons().add(icon);
            showFiltrosAplicadosStage.setTitle("Filtros aplicados");

            Scene scene = new Scene(page);

            showFiltrosAplicadosStage.setOnCloseRequest(e->FiltrosAplicadosController.setShowFiltrosStage(null));
            showFiltrosAplicadosStage.setScene(scene);
            showFiltrosAplicadosStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void recargarTipoBusqueda(ActionEvent event) {

        String filtroAplicado = cbFiltroCampos.getSelectionModel().getSelectedItem();

        if(filtroAplicado == null) {
            return;
        }

        if(filtroAplicado.equals("Comunidad") | filtroAplicado.equals("Empleado") | filtroAplicado.equals("Nivel urgencia") | filtroAplicado.equals("Tipo comunicado")
            | filtroAplicado.equals("Tipo reparación") |filtroAplicado.equals("Empresa reparadora") |filtroAplicado.equals("Completo")) {
            cbFiltro.setVisible(true);
            cbFiltro.setDisable(false);
            dateFiltro.setVisible(false);
            dateFiltro.setDisable(true);
        } else if (filtroAplicado.equals("Fecha de junta anterior a") | filtroAplicado.equals("Fecha de junta posterior a") |
                filtroAplicado.equals("Fecha de alta anterior a") | filtroAplicado.equals("Fecha de alta posterior a") |
                filtroAplicado.equals("Fecha finalizacion antes de") | filtroAplicado.equals("Fecha finalizacion despues de")){
            dateFiltro.setVisible(true);
            dateFiltro.setDisable(false);
            cbFiltro.setDisable(false);
            cbFiltro.setVisible(false);
        } else {
            cbFiltro.setVisible(true);
            cbFiltro.setDisable(true);
            dateFiltro.setVisible(false);
            dateFiltro.setDisable(true);
        }

        switch (filtroAplicado) {
            case "Comunidad":
                cbFiltro.setItems(RellenarComboBox.rellenarCBComunidades());
                break;
            case "Empleado":
                cbFiltro.setItems(RellenarComboBox.rellenarCBEmpleados());
                break;
            case "Nivel urgencia":
                cbFiltro.setItems(RellenarComboBox.rellenarCBNivelUrgencia());
                break;
            case "Tipo comunicado":
                cbFiltro.setItems(RellenarComboBox.rellenarCBTipoComunicado());
                break;
            case "Tipo reparación":
                cbFiltro.setItems(RellenarComboBox.rellenarCBTipoReparacion());
                break;
            case "Empresa reparadora":
                cbFiltro.setItems(RellenarComboBox.rellenarCBEmpresasReparadoras());
                break;
            case "Completo":
                cbFiltro.setItems(RellenarComboBox.rellenarCBCompleto());
                break;
        }
    }


    public static Filtro getFiltro() {
        return filtro;
    }

    public static void setFiltro(Filtro filtro) {
        filtro = filtro;
    }
}
