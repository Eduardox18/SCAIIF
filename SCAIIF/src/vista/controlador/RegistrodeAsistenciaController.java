package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import modelo.dao.AlumnoDAO;
import modelo.dao.ReservacionDAO;
import servicios.pojos.Actividad;
import servicios.pojos.Alumno;
import servicios.pojos.Reservacion;
import vista.Dialogo;

/**
 *
 * @author Hernández González Esmeralda Yamileth
 */
public class RegistrodeAsistenciaController implements Initializable {

    @FXML
    private JFXButton botonCancelar;
    @FXML
    private JFXButton botonAsistencia;
    @FXML
    private TableView tablaAlumnos;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colMatricula;
    @FXML
    private TableColumn colApPaterno;
    @FXML
    private TableColumn colApMaterno;
    @FXML
    private JFXComboBox<String> comboFecha;
    @FXML
    private JFXComboBox<Integer> comboActividad;
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();

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

        botonCancelar.setDisable(true);
        botonAsistencia.setDisable(true);
        llenarComboBox();

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
     * Recupera de la base de datos las fechas y noActividad de las reservaciones registradas.
     */
    private void llenarComboBox() {
        List<Reservacion> fechas = null;
        List<Actividad> noActividad = null;
        Dialogo dialogo = null;
        try {
            fechas = ReservacionDAO.recuperarFechas();
            List<String> fechaReservacion = new ArrayList<>();
            for (Reservacion reservacion : fechas) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fechaTexto = formatter.format(reservacion.getFecha());
                fechaReservacion.add(fechaTexto);
            }
            ObservableList<String> fechasObservable = FXCollections.observableArrayList(fechaReservacion);
            comboFecha.setItems(fechasObservable);

            noActividad = ActividadDAO.recuperarNoActividad();
            List<Integer> noActividades = new ArrayList<>();
            for (Actividad actividad : noActividad) {
                noActividades.add(actividad.getNoActividad());
            }
            ObservableList<Integer> noActividadObservable = FXCollections.observableArrayList(noActividades);
            comboActividad.setItems(noActividadObservable);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al recuperar información.", "Error", ButtonType.OK);
        }
    }

    /**
     * Recupera los alumnos que reservación la actividad seleccionada a la hora seleccionada.
     */
    @FXML
    public void recuperarLista() {
        Dialogo dialogo = null;
        int noActividad = comboActividad.getValue();
        Date fechaDate = null;
        String fecha = comboFecha.getValue();
        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fechaDate = fechaFormat.parse(fecha);
        } catch (ParseException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al recuperar actividades", "Error", ButtonType.OK);
        }

        List<Alumno> reservaciones = new ArrayList<>();
        Reservacion reservacion = new Reservacion();
        reservacion.setFecha(fechaDate);
        reservacion.setNoActividad(noActividad);
        reservacion.setAsistencia(false);

        try {
            reservaciones = AlumnoDAO.recuperarLista(reservacion);
            ObservableList<Alumno> alumnosReservados = FXCollections.observableArrayList(reservaciones);
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
            colApMaterno.setCellValueFactory(new PropertyValueFactory<>("apMaterno"));
            tablaAlumnos.setItems(alumnosReservados);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al recuperar actividades", "Error", ButtonType.OK);
        }
    }

    /**
     * Comprueba que se encuentre seleccionado un alumno antes de registrar su asistencia.
     */
    @FXML
    private void seleccionarRegistro() {
        if (tablaAlumnos.getSelectionModel().getSelectedCells() != null) {
            botonCancelar.setDisable(false);
            botonAsistencia.setDisable(false);
        } else {
            botonCancelar.setDisable(false);
            botonAsistencia.setDisable(false);
        }
    }

    /**
     * Comprueba que el registro haya sido exitoso.
     */
    @FXML
    private void comprobarRegistroAsistencia() {
        Dialogo dialogo = null;
        String matricula = tablaAlumnos.getSelectionModel().getSelectedItem().toString();
        int noActividad = comboActividad.getValue();
        Date fechaDate = null;
        String fecha = comboFecha.getValue();
        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fechaDate = fechaFormat.parse(fecha);
        } catch (ParseException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al recuperar actividades", "Error", ButtonType.OK);
        }
        Reservacion reservacion = new Reservacion();
        reservacion.setMatricula(matricula);
        reservacion.setNoActividad(noActividad);
        reservacion.setFecha(fechaDate);
        reservacion.setAsistencia(true);
        try {
            ReservacionDAO.registrarAsistencia(reservacion);
            dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "Asistencia registradas correctamente.", "Éxito", ButtonType.OK);
            dialogo.show();
            botonCancelar.setDisable(true);
            botonAsistencia.setDisable(true);
            recuperarLista();
            limpiarCampos();

        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al registrar asistencias.", "Error", ButtonType.OK);
        }
    }

    /**
     * Limpia los campos.
     */
    public void limpiarCampos() {
        comboFecha.setValue("");
        comboActividad.setValue(0);
    }

    /**
     *
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y
     * limpia los campos de búsqueda.
     */
    @FXML
    public void botonCerrarVentana() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.CONFIRMATION,
            "¿Seguro que desea cancelar el registro de asistencia?", "Confirmación",
            ButtonType.OK, ButtonType.CANCEL);

        dialogo.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                limpiarCampos();
                botonCancelar.setDisable(true);
                botonAsistencia.setDisable(true);
            }
        });
    }

}
