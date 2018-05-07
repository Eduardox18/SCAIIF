package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.dao.ActividadDAO;
import modelo.pojos.ActividadAsesor;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class ActividadesPorImpartirController implements Initializable {
    @FXML
    private JFXHamburger menuIcon;
    @FXML
    private JFXDrawer menuDrawer;
    @FXML 
    private TableView tablaActividades;
    @FXML
    private TableColumn colNombreAct;
    @FXML
    private TableColumn colFecha;
    @FXML
    private Label etiquetaNombreAsesor;
    @FXML
    private JFXButton botonDetalles;
   

    /**
     * Initializes the controller class.
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
        
        llenarTabla();
        botonDetalles.setDisable(true);
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
     * Recupera las actividades que esta por 
     * impartir el Asesor de la base de datos 
     * y las muestra en la tabla.
     *
     */
    private void llenarTabla() {
        ObservableList<ActividadAsesor> actividadesObservable;
        Integer noPersonal = 18109;
       
        try {
            actividadesObservable = FXCollections.observableArrayList(ActividadDAO.
                recuperarActividadesPorImpartir(noPersonal, Date.valueOf(LocalDate.now())));
            colNombreAct.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaActividades.setItems(actividadesObservable);
            botonDetalles.setDisable(false);
           
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    /**
     * 
     * Crea la ventana CrearComentario desde donde se añade el comentario al alumno.
     */
    @FXML
    private void lanzarDescripcionActividad() {
        ActividadAsesor actividadRecuperada = (ActividadAsesor) tablaActividades.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/DescripcionActividad.fxml"));
            AnchorPane paneLista = loader.load();

            DescripcionActividadController controller = loader.<DescripcionActividadController>getController();
            controller.infoVentana(actividadRecuperada.getNombre());

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneLista);
            
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, "No se ha seleccionado ninguna actividad", 
                    "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
}
