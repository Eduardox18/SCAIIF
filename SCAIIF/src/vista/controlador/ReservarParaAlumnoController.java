/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import modelo.dao.ActividadDAO;
import modelo.pojos.Actividad;
import modelo.pojos.ActividadAsesor;
import modelo.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ReservarParaAlumnoController implements Initializable {
    
    @FXML
    private Label labelMatricula;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelMail;
    
    @FXML
    private TableView<ActividadAsesor> tablaActividades;
    
    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colinicio;

    @FXML
    private TableColumn colFin;

    @FXML
    private TableColumn colAsesor;

    @FXML
    private JFXButton botonCancelar;

    @FXML
    private JFXButton botonReservar;
    
    private Alumno infoAlumno;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void accionRegresar() {
        try {
            URL reservarAct = getClass().getResource("/vista/ReservarActividad.fxml");
            AnchorPane paneReservar = FXMLLoader.load(reservarAct);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneReservar);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    public void infoVentana(Alumno infoAlumno) {
        this.infoAlumno = infoAlumno;
        labelMatricula.setText(infoAlumno.getMatricula());
        labelNombre.setText(infoAlumno.getApPaterno() + " " + infoAlumno.getApMaterno() + " " + 
                infoAlumno.getNombre());
        labelMail.setText(infoAlumno.getCorreo());
        llenarTabla();
    }
    
    private void llenarTabla(){
        ObservableList<ActividadAsesor> actividadesObservable;
        try {
            actividadesObservable = FXCollections.observableArrayList(ActividadDAO.recuperarActividadesDisponibles(infoAlumno.getMatricula()));
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
            colFin.setCellValueFactory(new PropertyValueFactory("horaFin"));
            colinicio.setCellValueFactory(new PropertyValueFactory("horaInicio"));
            colAsesor.setCellValueFactory(new PropertyValueFactory("nombreAsesor"));
            tablaActividades.setItems(actividadesObservable);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        
    }
    
}
