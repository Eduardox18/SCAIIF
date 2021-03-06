package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import modelo.CorreoElectronico;
import modelo.dao.ActividadDAO;
import modelo.dao.AlumnoDAO;
import modelo.pojos.ActividadAsesor;
import vista.Dialogo;

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
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
        
        tablaActividades.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnCancelar.setDisable(false);
        });
        
        tablaActividades.setPlaceholder(new Label("No hay actividades para cancelar"));
        
        llenarTabla();
    }
    

    /**
     * *
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del mnú
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    } 
    
    @FXML
    public void lanzarConfirmación() {
        TextInputDialog asuntoCan = new TextInputDialog();
        asuntoCan.setTitle("Confirmar cancelación");
        asuntoCan.setHeaderText(null);
        asuntoCan.setContentText("Ingresar motivo de la cancelación:");
        asuntoCan.initStyle(StageStyle.UNDECORATED);
        
        Optional<String> resultado = asuntoCan.showAndWait();
        if (resultado.isPresent()){
            cancelarActividad(tablaActividades.getSelectionModel().getSelectedItem().getNombre(), 
                    resultado.get(), 
                    tablaActividades.getSelectionModel().getSelectedItem().getNoActividad());
        }
    }
    
    private void cancelarActividad(String nombre, String motivo, Integer noActividad) {
        Dialogo dialogo = null;
        motivo = "La actividad " + nombre + " ha sido cancelada por el siguiente motivo: " + motivo;
        String asunto = "Cancelación de actividad " + nombre;
            
        try {
            if (ActividadDAO.actualizarEstado(noActividad, 3)) {
                dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                    "La actividad ha sido cancelada y los alumnos han sido avisados", 
                     "Éxito", ButtonType.OK);
                List<String> correos  = AlumnoDAO.recuperarCorreos(noActividad);
                CorreoElectronico.enviarCorreo(correos, asunto, motivo);
                dialogo.show();
                llenarTabla();
            }
        } catch(IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        } catch (MessagingException ex) {
            try {
                ActividadDAO.actualizarEstado(noActividad, 1);
            } catch (IOException ex1) {
            }
            ex.printStackTrace();
            dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Ha ocurrido un error al enviar los correos electrónicos, "
                            + "la actividad no se ha cancelado", "Error al notificar", ButtonType.OK);
            dialogo.show();
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
