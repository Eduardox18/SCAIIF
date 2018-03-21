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
import servicios.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ObservacionController implements Initializable {

    @FXML
    JFXButton botonRegistrar = new JFXButton();

    @FXML
    JFXTextField campoMatricula = new JFXTextField();

    @FXML
    TableView tablaALumnos = new TableView();

    @FXML
    JFXHamburger menuIcon = new JFXHamburger();

    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();

    @FXML
    TableColumn colNombre = new TableColumn();

    @FXML
    TableColumn colMatricula = new TableColumn();

    @FXML
    TableColumn colEmail = new TableColumn();

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
        List<Alumno> alumnos = null;
        try {
            alumnos = AlumnoDAO.recuperarAlumnos(campoMatricula.getText());
            ObservableList<Alumno> alumnosObservable = FXCollections.observableArrayList();
            alumnosObservable = FXCollections.observableArrayList(alumnos);

            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("correo"));
            tablaALumnos.setItems(alumnosObservable);
        } catch (Exception ex) {
            //Diálogo error
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
