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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
            ex.printStackTrace();
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
    }    
    
    @FXML
    private void comprobarCampos() {
        String asunto = campoAsunto.getText();
        String aviso = areaAviso.getText();
        LocalDate fecha = fechaLimite.getValue();
        if (asunto != null && aviso != null && !asunto.isEmpty() && !aviso.isEmpty() && 
                fecha != null) {
            botonPublicar.setDisable(false);
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
    
}
