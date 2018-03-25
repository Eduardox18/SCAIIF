/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.ActividadDAO;
import modelo.pojos.Actividad;
import modelo.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class CancelarActividadController implements Initializable {
    
    @FXML
    private TableView tablaActividades;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colAsesor;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colHoraInicio;
    
    @FXML
    private TableColumn colHoraFin;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private JFXHamburger menuIcon;

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
        llenarTabla();
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
    
    private void llenarTabla() {
        ObservableList<Actividad> actividadesPendientes = null;
        try {
            actividadesPendientes = FXCollections.observableArrayList(
                    ActividadDAO.recuperarActividadesPendientes());
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
            colHoraFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
            colAsesor.setCellValueFactory(new PropertyValueFactory<>("asesor"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaActividades.setItems(actividadesPendientes);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}
