package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ObservacionController implements Initializable {

    @FXML
    JFXButton botonRegistrar;

    @FXML
    JFXTextField campoMatricula;

    @FXML
    TableView tablaALumnos;

    @FXML
    JFXHamburger menuIcon;

    @FXML
    JFXDrawer menuDrawer;

    @FXML
    TableColumn colNombre;

    @FXML
    TableColumn colMatricula;

    @FXML
    TableColumn colEmail;

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
            ex.printStackTrace();
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });

        campoMatricula.textProperty().addListener((observable, oldValue, newValue) -> {
            llenarTabla(newValue);
        });

        botonRegistrar.setDisable(true);
        tablaALumnos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            botonRegistrar.setDisable(false);
        });

        llenarTabla("");
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
     * *
     * Recupera los alumnos de la base de datos y mostrarlos en la tabla.
     *
     * @param nombreAlumno El nombre o matrícula del alumno que se busca, en caso de estar vacio, el
     * método buscará a todos los alumnos.
     */
    private void llenarTabla(String nombreAlumno) {
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

    @FXML
    /**
     * 
     * Crea la ventana CrearComentario desde donde se añade el comentario al alumno.
     */
    private void lanzarEditorComentario() {
        Alumno alumnoRecuperado = (Alumno) tablaALumnos.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CrearObservacion.fxml"));
            AnchorPane paneLista = loader.load();

            CrearObservacionController controller = loader.<CrearObservacionController>getController();
            controller.infoVentana(alumnoRecuperado.getMatricula());

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneLista);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, "No se ha seleccionado a ningún alumno", 
                    "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}
