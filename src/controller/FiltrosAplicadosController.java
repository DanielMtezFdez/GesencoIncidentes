package controller;

import com.jfoenix.controls.JFXButton;
import database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Filtro;

import java.net.URL;
import java.util.ResourceBundle;

public class FiltrosAplicadosController implements Initializable {

    // mainPlayStage para tener acceso desde otras clases
    private static Stage showFiltrosStage;

    @FXML
    private TextField lblEmpleado, lblComunidad, lblNivelUrgencia, lblTipoComunicado, lblTipoReparacion, lblEmpresaReparadora, lblCompleto;

    @FXML
    private TextField lblFechaJuntaAnteriorA, lblFechaJuntaPosteriorA, lblFechaAltaAnteriorA,  lblFechaAltaPosteriorA, lblFechaFinalizacionAnteriorA, lblFechaFinalizacionPosteriorA;

    @FXML
    private ImageView deleteEmpleado, deleteComunidad, deleteNivelUrgencia, deleteTipoComunicado, deleteTipoReparacion, deleteEmpresaReparadora, deleteCompleto;

    @FXML
    private ImageView deleteFechaJuntaAnterior, deleteFechaJuntaPosterior, deleteFechaAltaAnterior, deleteFechaAltaPosterior, deleteFechaFinalizacionAnterior, deleteFechaFinalizacionPosterior;

    @FXML
    private JFXButton btnCancelar, btnGuardarDatos;

    private Filtro filtro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rellenarDatosFiltro();
    }

    private void rellenarDatosFiltro() {
        filtro = MainController.getFiltro();

        lblEmpleado.setText(EmpleadoDAO.getEmpleadoById(filtro.getCodEmpleado()));
        lblComunidad.setText(ComunidadDAO.getComunidadById(filtro.getCodComunidad()));
        lblNivelUrgencia.setText(NivelUrgenciaDAO.getNivelUrgenciaByID(filtro.getNivelUrgencia()));
        lblTipoComunicado.setText(TipoComunicadoDAO.getTipoById(filtro.getTipoComunicado()));
        lblTipoReparacion.setText(TipoReparacionDAO.getTipoById(filtro.getTipoReparacion()));
        lblEmpresaReparadora.setText(filtro.getEmpresaReparadora());
        lblCompleto.setText(filtro.getCompleto());
        lblFechaJuntaAnteriorA.setText(filtro.getFechaJuntaAntesDe().toString());
        lblFechaJuntaPosteriorA.setText(filtro.getFechaJuntaDespuesDe().toString());
        lblFechaAltaAnteriorA.setText(filtro.getFechaAltaAntesDe().toString());
        lblFechaAltaPosteriorA.setText(filtro.getFechaAltaDespuesDe().toString());
        lblFechaFinalizacionAnteriorA.setText(filtro.getFechaFinalizacionAntesDe().toString());
        lblFechaFinalizacionPosteriorA.setText(filtro.getFechaFinalizacionDespuesDe().toString());
    }

    @FXML
    void guardarDatosFiltro(ActionEvent event) {

        filtro.setCodEmpleado();

        MainController.setFiltro(filtro);
    }

    @FXML
    void cancelar(ActionEvent event) {
        showFiltrosStage.close();
    }

    @FXML
    void deleteFiltroCompleto(MouseEvent event) {
        lblCompleto.setText("");
    }

    @FXML
    void deleteFiltroComunidad(MouseEvent event) {
        lblComunidad.setText("");
    }

    @FXML
    void deleteFiltroEmpleado(MouseEvent event) {
        lblEmpleado.setText("");
    }

    @FXML
    void deleteFiltroEmpresaReparadora(MouseEvent event) {
        lblEmpresaReparadora.setText("");
    }

    @FXML
    void deleteFiltroFechaAltaAnterior(MouseEvent event) {
        lblFechaAltaAnteriorA.setText("");
    }

    @FXML
    void deleteFiltroFechaAltaPosterior(MouseEvent event) {
        lblFechaAltaPosteriorA.setText("");
    }

    @FXML
    void deleteFiltroFechaFinalizacionAnterior(MouseEvent event) {
        lblFechaFinalizacionAnteriorA.setText("");
    }

    @FXML
    void deleteFiltroFechaFinalizacionPosterior(MouseEvent event) {
        lblFechaFinalizacionPosteriorA.setText("");
    }

    @FXML
    void deleteFiltroFechaJuntaAnterior(MouseEvent event) {
        lblFechaJuntaAnteriorA.setText("");
    }

    @FXML
    void deleteFiltroFechaJuntaPosterior(MouseEvent event) {
        lblFechaJuntaPosteriorA.setText("");
    }

    @FXML
    void deleteFiltroNivelUrgencia(MouseEvent event) {
        lblNivelUrgencia.setText("");
    }

    @FXML
    void deleteFiltroTipoComunicado(MouseEvent event) {
        lblTipoComunicado.setText("");
    }

    @FXML
    void deleteFiltroTipoReparacion(MouseEvent event) {
        lblTipoReparacion.setText("");
    }



    public static Stage getShowFiltrosStage() {
        return showFiltrosStage;
    }

    public static void setShowFiltrosStage(Stage showFiltrosStage) {
        FiltrosAplicadosController.showFiltrosStage = showFiltrosStage;
    }
}
