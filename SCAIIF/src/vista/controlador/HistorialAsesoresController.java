package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import modelo.dao.InduccionDAO;
import modelo.dao.UsuarioDAO;
import modelo.pojos.Actividad;
import modelo.pojos.Induccion;
import modelo.pojos.Usuario;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Hernández González Esmeralda Yamileth
 */
public class HistorialAsesoresController implements Initializable {

    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();
    @FXML
    JFXTextField campoNombre;
    @FXML
    private JFXTextField campoNoPersonal;
    @FXML
    private JFXButton botonBuscar;
    @FXML
    private JFXButton botonImprimir;
    @FXML
    TableView tablaInduccion;
    @FXML
    TableColumn colMatricula;
    @FXML
    TableColumn colFecha;
    @FXML
    TableView tablaActividades;
    @FXML
    TableColumn colNombre;
    @FXML
    TableColumn colFechaAct;

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
        botonBuscar.setDisable(true);
        botonImprimir.setDisable(true);
    }

    /**
     * Método que hace visible e invisible el menú drawer.
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Comprueba el tamaño y contenido del noPersonal.
     */
    @FXML
    private void comprobarNoPersonal() {
        try {
            int noPersonal = Integer.parseInt(campoNoPersonal.getText());
            if (noPersonal != 0) {
                botonBuscar.setDisable(false);
            } else {
                botonBuscar.setDisable(true);
            }
        } catch (NumberFormatException ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Ingresa solo números.", "Error", ButtonType.OK);
            dialogo.show();
            campoNoPersonal.setText("");
        }
    }

    /**
     * Llamada al método consultar Actividades asignada al botón buscar, así como la recuperación
     * del nombre del asesor que coincide con el noPersonal ingresado por el Coordinador.
     */
    @FXML
    private void consultarHistorialAsesores() {
        recuperarNombreAsesor();
        consultarActividades();
        botonImprimir.setDisable(false);
    }

    /**
     * Recupera el nombre del Asesor del que se está consultado la información.
     */
    private void recuperarNombreAsesor() {
        Usuario nombreAsesor;
        try {
            nombreAsesor = UsuarioDAO.recuperarAsesor(Integer.parseInt(campoNoPersonal.getText()));
            campoNombre.setText(nombreAsesor.getNombre() + " " + nombreAsesor.getApPaterno() + " "
                + nombreAsesor.getApMaterno());
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "El noPersonal que ingresó no corresponde a un Usuario.", "Error",
                ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Método que realiza la búsqueda en la base de datos de las matrículas y fechas de las
     * asesorías introductorias impartidas por el asesor ingresado por el coordinador, así como de
     * las actividaes impartidas o que impartirá.
     */
    private void consultarActividades() {
        List<Induccion> historialAsesores = new ArrayList<>();
        List<Actividad> historialReservaciones = new ArrayList<>();
        try {
            historialAsesores = InduccionDAO.recuperarHistorialAsesores(Integer.parseInt(campoNoPersonal.getText()));
            historialReservaciones = ActividadDAO.recuperarHistorial(Integer.parseInt(campoNoPersonal.getText()));
            ObservableList<Induccion> actividadesIntro = FXCollections.observableArrayList(historialAsesores);
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaInduccion.setItems(actividadesIntro);

            ObservableList<Actividad> actividades = FXCollections.observableArrayList(historialReservaciones);
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colFechaAct.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaActividades.setItems(actividades);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Método que muestra el diálogo para imprimir una lista de asistencia
     */
    @FXML
    public void mostrarVentanaImprimir() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
            "Se está imprimiendo la lista...", "Imprimiendo",
            ButtonType.OK);
        dialogo.show();
        botonImprimir.setDisable(true);
    }
}
