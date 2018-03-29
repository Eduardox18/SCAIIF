/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AvisoDAO;
import modelo.pojos.Aviso;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class AvisoAsesorController implements Initializable {
    
    @FXML
    private JFXTextField campoAsunto;
    
    @FXML
    private JFXTextArea areaAviso;
    
    @FXML
    private JFXDatePicker fechaLimite;
    
    @FXML
    private JFXButton botonPublicar;
    
    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/vista/DrawerPrincipal.fxml"));
            menuDrawer.setSidePane(box);
            menuDrawer.setDisable(true);
        } catch (IOException ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
    }    
    
    @FXML
    /**
     * Comprueba que los campos no estén vacios antes de activar el botón "Publicar"
     */
    private void comprobarCampos() {
        String asunto = campoAsunto.getText();
        String aviso = areaAviso.getText();
        LocalDate fecha = fechaLimite.getValue();
        if (asunto != null && aviso != null && !asunto.isEmpty() && !aviso.isEmpty() && 
                fecha != null) {
            botonPublicar.setDisable(false);
        } else {
            botonPublicar.setDisable(true);
        }
    }
    
    @FXML
    /**
     * *
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del mnú
     */
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    /**
     * Crea un objeto aviso con los datos ingresados por el usuario
     * 
     */
    private void guardarAviso() {
        Date fCreacion = Date.valueOf(LocalDate.now());
        Date fLim = Date.valueOf(fechaLimite.getValue());
        Aviso aviso = new Aviso();
        aviso.setAsunto(campoAsunto.getText());
        aviso.setMensaje(areaAviso.getText());
        aviso.setFechaCreacion(fCreacion);
        aviso.setFechaLimite(fLim);
        
        try {
            AvisoDAO.guardarAviso(aviso);
            campoAsunto.setText("");
            areaAviso.setText("");
            fechaLimite.setValue(null);
            botonPublicar.setDisable(true);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    @FXML
    /**
     * Muestra un diálogo de confirmación antes de guardar el aviso
     */
    private void botonAccion() {
        Dialogo dialogoConf = new Dialogo(Alert.AlertType.CONFIRMATION, 
                "¿Está seguro de que desea publicar el aviso?", "Confirmar publicación", 
                ButtonType.OK, ButtonType.CANCEL);
       
        if(validarFecha(fechaLimite.getValue())){
            dialogoConf.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    guardarAviso();
                }
            });
        } else {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING, 
                    "Ingrese una fecha posterior a la actual, para poder continuar", 
                    "Fecha incorrecta", ButtonType.OK);
            dialogo.show();
        }
        
    }
    
    /**
     * Comprueba que la fecha ingresada sea posterior a la fecha actual
     * @param fecha La fecha que se desea comprobar
     * @return True en caso de que sea posterior, false en caso de que sea la misma fehca o anteiror
     */
    private boolean validarFecha(LocalDate fecha) {
        boolean resul = false;
        if(fecha.isAfter(LocalDate.now())){
            resul = true;
        }
        return resul;
    }
    
}
