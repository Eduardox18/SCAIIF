/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ReservarActividadController implements Initializable {
    
    @FXML
    private JFXButton botonContinuar;
    
    @FXML
    private JFXTextField campoMatricula;

    @FXML
    private TableView tablaALumnos;

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colMatricula;

    @FXML
    private TableColumn colEmail;
    

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
        
        campoMatricula.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                llenarTabla();
            }
        });
        
        llenarTabla();
    }

    @FXML
    /**
     * *
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del menú
     */
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    private void llenarTabla() {
        ObservableList<Alumno> alumnosObservable = null;
        try {
            alumnosObservable = FXCollections.observableArrayList(AlumnoDAO.recuperarAlumnos(
                    campoMatricula.getText()));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("correo"));
            tablaALumnos.setItems(alumnosObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
}
