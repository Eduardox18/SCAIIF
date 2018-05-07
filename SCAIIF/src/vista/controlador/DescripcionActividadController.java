package vista.controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import modelo.pojos.Actividad;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class DescripcionActividadController implements Initializable {
    @FXML
    private TableView tablaDetalle;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colFecha;
    @FXML
    private TableColumn colInicio;
    @FXML
    private TableColumn colFin;
    @FXML
    private TableColumn colCupo;
    @FXML
    private JFXButton botonListo;
    
    private String nombreActividad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTablaDetalle();
    }    
    
/**
     * Funciona como un método setter para pasar parámetros de otra ventana a esta.
     *
     * @param nombreActividad La matrícula del alumno que se seleccionó en la ventana Comentario.
     */
    public void infoVentana(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    
    /**
     * Cierra la ventana actual cuando se dde clic sobre el botón listo
     * retorna a la ventana ActividadesPorImpartir
     */
    private void cerrarVentana() {
        try {
            URL actividadesPorImpartir = getClass().getResource("/vista/ActividadesPorImpartir.fxml");
            AnchorPane paneActividades = FXMLLoader.load(actividadesPorImpartir);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneActividades);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                "No se pudo cerrar la ventana actual.", "Error",
                ButtonType.OK);
        }
    }
    
    /**
     * *
     * Recupera el detalle de la actividad que seleccionó el Asesor 
     * de la base de datos y las muestra en la tabla.
     *
     */
    private void llenarTablaDetalle () {
        ObservableList<Actividad> actividadesObservable;
        try {
            //actividadesObservable = FXCollections.observableArrayList(ActividadDAO.recuperarAlumnos(
              //      campoMatricula.getText()));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            colInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
            colFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
            colCupo.setCellValueFactory(new PropertyValueFactory<>("cupo"));
           // tablaDetalle.setItems(actividadesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
}
