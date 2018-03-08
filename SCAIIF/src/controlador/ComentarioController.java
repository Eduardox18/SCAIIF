package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ComentarioController implements Initializable {
    
    @FXML
    JFXButton botonRegistrar = new JFXButton();
    
    @FXML
    JFXTextField campoMatricula = new JFXTextField();
    
    @FXML
    TableView tablaALumnos = new TableView();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    private void llenarTabla(String nombreAlumno) {
        
        
    }
}
