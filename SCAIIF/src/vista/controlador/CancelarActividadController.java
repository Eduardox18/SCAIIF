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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import modelo.dao.ActividadDAO;
import modelo.pojos.ActividadAsesor;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class CancelarActividadController implements Initializable {
    
    @FXML
    private TableView<ActividadAsesor> tablaActividades;

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
        
        tablaActividades.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnCancelar.setDisable(false);
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
    
    @FXML
    public void lanzarConfirmación() {
        Dialogo dialogo = null;
        String motivo = null;
        TextInputDialog asuntoCan = new TextInputDialog();
        asuntoCan.setTitle("Confirmar cancelación");
        asuntoCan.setHeaderText(null);
        asuntoCan.setContentText("Ingresar motivo de la cancelación:");
        asuntoCan.initStyle(StageStyle.UNDECORATED);
        
        Optional<String> resultado = asuntoCan.showAndWait();
        if (resultado.isPresent()){
            motivo = resultado.get();
            Integer noActividad = 
                    tablaActividades.getSelectionModel().getSelectedItem().getNoActividad();
            try {
                if (ActividadDAO.cancelarActividad(noActividad)) {
                    dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                        "La actividad ha sido cancelada y los alumnos han sido avisados", 
                         "Éxito", ButtonType.OK);
                    dialogo.show();
                    llenarTabla();
                }
            } catch(Exception ex) {
                dialogo = new Dialogo(Alert.AlertType.ERROR, 
                        "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                dialogo.show();
            }
            
        }
    }
    
    private void llenarTabla() {
        ObservableList<ActividadAsesor> actividadesPendientes = null;
        try {
            actividadesPendientes = FXCollections.observableArrayList(
                    ActividadDAO.recuperarActividadesPendientes());
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
            colHoraFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
            colAsesor.setCellValueFactory(new PropertyValueFactory<>("nombreAsesor"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaActividades.setItems(actividadesPendientes);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}
