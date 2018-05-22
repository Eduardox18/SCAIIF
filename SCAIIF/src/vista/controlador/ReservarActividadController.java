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
import javafx.scene.control.Label;
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

public class ReservarActividadController implements Initializable {
    
    @FXML
    private JFXButton botonContinuar;
    
    @FXML
    private JFXTextField campoMatricula;

    @FXML
    private TableView<Alumno> tablaALumnos;

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
        
        botonContinuar.setDisable(true);
        
        tablaALumnos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                botonContinuar.setDisable(false);
            }
        });
        
        tablaALumnos.setPlaceholder(new Label("No se encontraron coincidencias"));
        
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
    
    /**
     * Puebla la tabla con los alumnos registrados en el sistema
     */
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
    
    
    @FXML
    /**
     * Carga la siguiente ventana donde se reserva la actividad para un alumno específico
     */
    private void continuarAccion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ReservarParaAlumno.fxml"));
            AnchorPane pane = loader.load();

            ReservarParaAlumnoController controller = loader.<ReservarParaAlumnoController>getController();
            controller.infoVentana(tablaALumnos.getSelectionModel().getSelectedItem());

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(pane);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
}
