package vista.controlador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import modelo.dao.ReservacionDAO;
import servicios.pojos.Actividad;
import servicios.pojos.ListaAsistencia;
import vista.Dialogo;

/**
 * FXML Controller class
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

        llenarComboBox();
    }

    @FXML
    public void mostrarVentanaImprimir() {
        Dialogo dialogo = null;
        dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "Se está imprimiendo la lista...", "Imprimiendo",
                ButtonType.OK);
        dialogo.show();
    }

    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    private void llenarComboBox() {
        List<Actividad> actividadesProximas = new ArrayList<>();

        try {
            actividadesProximas = ActividadDAO.recuperarActividadesAsesor(
                    LoginController.returnNoPersonalLog());
            ObservableList<Actividad> actividadesObservable = FXCollections.observableArrayList();
            actividadesObservable = FXCollections.observableArrayList(actividadesProximas);
            comboActividades.setItems(actividadesObservable);
        } catch (Exception ex) {
            //Diálogo Error
        }
    }

    @FXML
    public void llenarTabla() {
        List<ListaAsistencia> listaAlumnosReservacion = new ArrayList<>();

        try {
            listaAlumnosReservacion = ReservacionDAO.recuperarAlumnosDeActividad(
                    comboActividades.getSelectionModel().getSelectedItem().getNoActividad());
            ObservableList<ListaAsistencia> listaObservable = FXCollections.observableArrayList();
            listaObservable = FXCollections.observableArrayList(listaAlumnosReservacion);
            colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            tablaLista.setItems(listaObservable);
        } catch (Exception ex) {
            Logger.getLogger(ListasDeAsistenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
