package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import modelo.dao.ActividadDAO;
import modelo.dao.ReservacionDAO;
import modelo.pojos.Actividad;
import modelo.pojos.ListaAsistencia;
import vista.Dialogo;

/**
 * Controlador de la Vista ListaDeAsistencia
 *
 * @author lalo
 */
public class ListasDeAsistenciaController implements Initializable {

    @FXML
    private JFXComboBox<Actividad> comboActividades;

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colMatricula;

    @FXML
    private TableColumn colAsistencia;

    @FXML
    private TableView tablaLista;

    @FXML
    private JFXButton botonImprimir;

    /**
     * Inicializador de la clase
     *
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

        llenarComboBox();
    }

    /**
     * Muestra el diálogo para imprimir una lista de asistencia
     */
    @FXML
    public void mostrarVentanaImprimir() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "Se está imprimiendo la lista...", "Imprimiendo",
                ButtonType.OK);
        dialogo.show();
    }

    /**
     * Muestra el ícono del menú en la ventana
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Llena el comboBox de Actividades con las Actividades pendientes del
     * asesor
     */
    private void llenarComboBox() {
        List<Actividad> actividadesProximas = new ArrayList<>();

        try {
            actividadesProximas = ActividadDAO.recuperarActividadesAsesor(
                    LoginController.returnNoPersonalLog(), Date.valueOf(LocalDate.now()));
            ObservableList<Actividad> actividadesObservable
                    = FXCollections.observableArrayList(actividadesProximas);
            comboActividades.setItems(actividadesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Llena la tabla con los alumnos inscritos a la actividad seleccionada.
     */
    @FXML
    public void llenarTabla() {
        List<ListaAsistencia> listaAlumnosReservacion = new ArrayList<>();

        try {
            listaAlumnosReservacion = ReservacionDAO.recuperarAlumnosDeActividad(
                    comboActividades.getSelectionModel().getSelectedItem().getNoActividad());

            if (!listaAlumnosReservacion.isEmpty()) {
                botonImprimir.setDisable(false);
            } else {
                botonImprimir.setDisable(true);
            }
            ObservableList<ListaAsistencia> listaObservable
                    = FXCollections.observableArrayList(listaAlumnosReservacion);
            colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            tablaLista.setItems(listaObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

}
