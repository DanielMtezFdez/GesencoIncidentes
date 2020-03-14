package controller;

import com.jfoenix.controls.JFXButton;
import database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Filtro;

import java.io.File;
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
        setImages();
    }

    private void setImages() {
        Image icon = new Image(new File("img/ic_delete.png").toURI().toString());
        deleteEmpleado.setImage(icon);
        deleteComunidad.setImage(icon);
        deleteNivelUrgencia.setImage(icon);
        deleteTipoComunicado.setImage(icon);
        deleteTipoReparacion.setImage(icon);
        deleteEmpresaReparadora.setImage(icon);
        deleteCompleto.setImage(icon);
        deleteFechaJuntaAnterior.setImage(icon);
        deleteFechaJuntaPosterior.setImage(icon);
        deleteFechaAltaAnterior.setImage(icon);
        deleteFechaAltaPosterior.setImage(icon);
        deleteFechaFinalizacionAnterior.setImage(icon);
        deleteFechaFinalizacionPosterior.setImage(icon);
    }

    private void rellenarDatosFiltro() {
        filtro = MainController.getFiltro();

        if(filtro.getCodEmpleado() == null) {
            lblEmpleado.setText("");
        }else{
            lblEmpleado.setText(EmpleadoDAO.getEmpleadoById(filtro.getCodEmpleado()));
        }

        if(filtro.getCodComunidad() == null) {
            lblComunidad.setText("");
        }else{
            lblComunidad.setText(ComunidadDAO.getComunidadById(filtro.getCodComunidad()));
        }

        if(filtro.getNivelUrgencia() == null) {
            lblNivelUrgencia.setText("");
        }else{
            lblNivelUrgencia.setText(NivelUrgenciaDAO.getNivelUrgenciaByID(filtro.getNivelUrgencia()));
        }

        if(filtro.getTipoComunicado() == null) {
            lblTipoComunicado.setText("");
        }else{
            lblTipoComunicado.setText(TipoComunicadoDAO.getTipoById(filtro.getTipoComunicado()));
        }

        if(filtro.getTipoReparacion() == null) {
            lblTipoReparacion.setText("");
        }else{
            lblTipoReparacion.setText(TipoReparacionDAO.getTipoById(filtro.getTipoReparacion()));
        }

        if(filtro.getEmpresaReparadora() == null) {
            lblEmpresaReparadora.setText("");
        }else{
            lblEmpresaReparadora.setText(filtro.getEmpresaReparadora());
        }

        if(filtro.getCompleto() == null) {
            lblCompleto.setText("");
        }else{
            lblCompleto.setText(filtro.getCompleto());
        }

        if(filtro.getFechaJuntaAntesDe() == null) {
            lblFechaJuntaAnteriorA.setText("");
        }else{
            lblFechaJuntaAnteriorA.setText(filtro.getFechaJuntaAntesDe().toString());
        }

        if(filtro.getFechaJuntaDespuesDe() == null) {
            lblFechaJuntaPosteriorA.setText("");
        }else{
            lblFechaJuntaPosteriorA.setText(filtro.getFechaJuntaDespuesDe().toString());
        }

        if(filtro.getFechaAltaAntesDe() == null) {
            lblFechaAltaAnteriorA.setText("");
        }else{
            lblFechaAltaAnteriorA.setText(filtro.getFechaAltaAntesDe().toString());
        }

        if(filtro.getFechaAltaDespuesDe() == null) {
            lblFechaAltaPosteriorA.setText("");
        }else{
            lblFechaAltaPosteriorA.setText(filtro.getFechaAltaDespuesDe().toString());
        }

        if(filtro.getFechaFinalizacionAntesDe() == null) {
            lblFechaFinalizacionAnteriorA.setText("");
        }else{
            lblFechaFinalizacionAnteriorA.setText(filtro.getFechaFinalizacionAntesDe().toString());
        }

        if(filtro.getFechaFinalizacionDespuesDe() == null) {
            lblFechaFinalizacionPosteriorA.setText("");
        }else{
            lblFechaFinalizacionPosteriorA.setText(filtro.getFechaFinalizacionDespuesDe().toString());
        }

    }

    @FXML
    void guardarDatosFiltro(ActionEvent event) {

        if (lblEmpleado.getText().equals("")){
            filtro.setCodEmpleado(null);
        }
        if (lblComunidad.getText().equals("")){
            filtro.setCodComunidad(null);
        }
        if (lblNivelUrgencia.getText().equals("")){
            filtro.setNivelUrgencia(null);
        }
        if (lblTipoComunicado.getText().equals("")){
            filtro.setTipoComunicado(null);
        }
        if (lblTipoReparacion.getText().equals("")){
            filtro.setTipoReparacion(null);
        }
        if (lblEmpresaReparadora.getText().equals("")){
            filtro.setEmpresaReparadora(null);
        }
        if (lblCompleto.getText().equals("")){
            filtro.setCompleto(null);
        }
        if (lblFechaJuntaAnteriorA.getText().equals("")){
            filtro.setFechaJuntaAntesDe(null);
        }
        if (lblFechaJuntaPosteriorA.getText().equals("")){
            filtro.setFechaJuntaDespuesDe(null);
        }
        if (lblFechaAltaAnteriorA.getText().equals("")){
            filtro.setFechaAltaAntesDe(null);
        }
        if (lblFechaAltaPosteriorA.getText().equals("")){
            filtro.setFechaAltaDespuesDe(null);
        }
        if (lblFechaFinalizacionAnteriorA.getText().equals("")){
            filtro.setFechaFinalizacionAntesDe(null);
        }
        if (lblFechaFinalizacionPosteriorA.getText().equals("")){
            filtro.setFechaFinalizacionDespuesDe(null);
        }

        MainController.setFiltro(filtro);
        showFiltrosStage.close();
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
