package controller;

import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AyudaCodigosController implements Initializable {

    @FXML
    private TableView<TipoReparacion> tableTipoReparacion;

    @FXML
    private TableView<NivelUrgencia> tableNivelUrgencia;

    @FXML
    private TableView<TipoComunicado> tableTipoComunicacion;

    @FXML
    private TableView<Comunidad> tableComunidades;

    @FXML
    private TableView<Empleado> tableEmpleados;

    @FXML
    private TableColumn<TipoReparacion, String> colIdTipoReparacion, colTipoReparacion;

    @FXML
    private TableColumn<NivelUrgencia, String> colIdNivelUrgencia, colNivelUrgencia;

    @FXML
    private TableColumn<TipoComunicado, String> colIdTipoComunicacion, colTipoComunicacion;

    @FXML
    private TableColumn<Comunidad, String> colIdComunidades, colComunidades;

    @FXML
    private TableColumn<Empleado, String> colIdEmpleados, colEmpleados;

    private static Stage ayudaCodigosStage;

    public static int estadoStageAyudaCodigos = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarTablaTiposReparacion();
    }

    private void inicializarTablaTiposReparacion() {

        colIdTipoReparacion.setCellValueFactory(new PropertyValueFactory<TipoReparacion, String>("id"));
        colTipoReparacion.setCellValueFactory(new PropertyValueFactory<TipoReparacion, String>("tipoReparacion"));

        colIdNivelUrgencia.setCellValueFactory(new PropertyValueFactory<NivelUrgencia, String>("id"));
        colNivelUrgencia.setCellValueFactory(new PropertyValueFactory<NivelUrgencia, String>("nivelUrgencia"));

        colIdTipoComunicacion.setCellValueFactory(new PropertyValueFactory<TipoComunicado, String>("id"));
        colTipoComunicacion.setCellValueFactory(new PropertyValueFactory<TipoComunicado, String>("tipoComunicado"));

        colIdComunidades.setCellValueFactory(new PropertyValueFactory<Comunidad, String>("codigo"));
        colComunidades.setCellValueFactory(new PropertyValueFactory<Comunidad, String>("nombre"));

        colIdEmpleados.setCellValueFactory(new PropertyValueFactory<Empleado, String>("codigo"));
        colEmpleados.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombreCompleto"));

        ArrayList<TipoReparacion> tipoReparaciones;
        ArrayList<NivelUrgencia> nivelesUrgencia;
        ArrayList<TipoComunicado> tiposComunicados;
        ArrayList<Comunidad> comunidades;
        ArrayList<Empleado> empleados;

        tipoReparaciones = TipoReparacionDAO.getTiposReparaciones();
        nivelesUrgencia = NivelUrgenciaDAO.getNivelesUrgencia();
        tiposComunicados = TipoComunicadoDAO.getTiposComunicados();
        comunidades = ComunidadDAO.getComunidades();
        empleados = EmpleadoDAO.getEmpleados();


        ObservableList<TipoReparacion> listaTipoReparaciones = FXCollections.observableArrayList(tipoReparaciones);
        tableTipoReparacion.setItems(listaTipoReparaciones);

        ObservableList<NivelUrgencia> listaNivelesUrgencia = FXCollections.observableArrayList(nivelesUrgencia);
        tableNivelUrgencia.setItems(listaNivelesUrgencia);

        ObservableList<TipoComunicado> listaTiposComunicado = FXCollections.observableArrayList(tiposComunicados);
        tableTipoComunicacion.setItems(listaTiposComunicado);

        ObservableList<Comunidad> listaComunidades = FXCollections.observableArrayList(comunidades);
        tableComunidades.setItems(listaComunidades);

        ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList(empleados);
        tableEmpleados.setItems(listaEmpleados);

    }

    public static Stage getAyudaCodigosStage() {
        return ayudaCodigosStage;
    }

    public static void setAyudaCodigosStage(Stage ayudaCodigosStage) {
        AyudaCodigosController.ayudaCodigosStage = ayudaCodigosStage;
    }

}
