package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.dao.ReservacionDAO;
import modelo.pojos.ActividadProxima;
import modelo.pojos.Alumno;
import vista.Dialogo;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ActividadesProximasController implements Initializable {

    @FXML
    private JFXTextField matriculaTF;

    @FXML
    private JFXButton botonBuscar;

    @FXML
    private Label nombreLabel;

    @FXML
    private TableView tablaReservaciones;

    @FXML
    private TableColumn fechaActividadCol;

    @FXML
    private TableColumn actividadCol;

    @FXML
    private TableColumn fechaReservacionCol;

    @FXML
    private TableColumn cursoCol;

    @FXML
    private TableColumn estadoCol;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private JFXHamburger menuIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        tablaReservaciones.setPlaceholder(new Label(""));
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
     * Habilita el botón de buscar matrícula si es una cadena de tamaño válido
     */
    @FXML
    public void habilitarBuscar() {
        if (matriculaTF.getText().length() == 9) {
            botonBuscar.setDisable(false);
        } else {
            botonBuscar.setDisable(true);
        }
    }

    @FXML
    public void buscarAlumno() {
        Alumno alumno;
        tablaReservaciones.setPlaceholder(new Label("El alumno no ha hecho reservaciones"));
        try {
            alumno = AlumnoDAO.verificarMatricula(matriculaTF.getText());
            if (alumno != null) {
                if (alumno.getApMaterno() != null) {
                    nombreLabel.setText("Alumno: " + alumno.getNombre() + " " + alumno.getApPaterno() + " " +
                            alumno.getApMaterno());
                } else {
                    nombreLabel.setText("Alumno: " + alumno.getNombre() + " " + alumno.getApPaterno());
                }
                llenarTablaReservaciones();
            } else {
                nombreLabel.setText("");
                tablaReservaciones.setPlaceholder(new Label(""));
                tablaReservaciones.setItems(null);
                Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                        "La matrícula ingresada no existe, intente de nuevo",
                        "Matrícula no existente", ButtonType.OK);
                dialogo.show();
            }
        } catch (Exception e) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    private void llenarTablaReservaciones() {
        List<ActividadProxima> reservaciones = null;
        try {
            reservaciones = ReservacionDAO.recuperarReservaciones(matriculaTF.getText());
            ObservableList<ActividadProxima> listaObservable =
                    FXCollections.observableArrayList(reservaciones);
            fechaActividadCol.setCellValueFactory(new PropertyValueFactory("fechaActividad"));
            actividadCol.setCellValueFactory(new PropertyValueFactory("nombreActividad"));
            fechaReservacionCol.setCellValueFactory(new PropertyValueFactory("fechaReservacion"));
            cursoCol.setCellValueFactory(new PropertyValueFactory("nombreCurso"));
            estadoCol.setCellValueFactory(new PropertyValueFactory("estado"));
            tablaReservaciones.setItems(listaObservable);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }



}



