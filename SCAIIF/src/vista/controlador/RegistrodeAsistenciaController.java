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
import modelo.pojos.Actividad;
import modelo.pojos.Alumno;
import modelo.pojos.Reservacion;
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
    private JFXComboBox<String> comboActividad;
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
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
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
        Dialogo dialogo = null;
        try {
            fechas = ReservacionDAO.recuperarFechas();
            List<String> fechaReservacion = new ArrayList<>();
            for (Reservacion reservacion : fechas) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String fechaTexto = formatter.format(reservacion.getFecha());
                fechaReservacion.add(fechaTexto);
            }
            ObservableList<String> fechasObservable = FXCollections.observableArrayList(fechaReservacion);
            comboFecha.setItems(fechasObservable);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al recuperar fechas.", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Recupera el nombre de las actividades de acuerdo a la fecha seleccionada.
     */
    @FXML
    public void recuperarActividades() {
        List<Actividad> nombreActividad = null;
        Dialogo dialogo = null;
        try {
            nombreActividad = ActividadDAO.recuperarNombreActividad(comboFecha.getSelectionModel().getSelectedItem());
            List<String> nombreActividades = new ArrayList<>();
            for (Actividad actividad : nombreActividad) {
                nombreActividades.add(actividad.getNombre());
            }
            ObservableList<String> noActividadObservable = FXCollections.observableArrayList(nombreActividades);
            comboActividad.setItems(noActividadObservable);
        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al recuperar nombre de actividades.", "Error", ButtonType.OK);
            dialogo.show();
        }

    }

    /**
     * Recupera los alumnos que reservación la actividad seleccionada a la hora seleccionada.
     */
    @FXML
    public void recuperarLista() {
        Dialogo dialogo = null;
        Date fechaDate = null;
        List<Integer> noActividades = null;
        String fecha = comboFecha.getValue();
        SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy/MM/dd");
        List<Actividad> noActividad = null;
        int numActividad = 0;

        try {
            fechaDate = fechaFormat.parse(fecha);
        } catch (ParseException ex) { 
        }

        try {
            noActividad = ActividadDAO.recuperarNombreActividad(comboFecha.getSelectionModel().getSelectedItem());
            noActividades = new ArrayList<>();
            for (Actividad actividad : noActividad) {
                noActividades.add(actividad.getNoActividad());
            }
            numActividad = noActividades.get(comboActividad.getSelectionModel().getSelectedIndex());
            
            System.out.println("noActv: " + noActividades.get(comboActividad.getSelectionModel().getSelectedIndex()));
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
        }

        List<Alumno> reservaciones = new ArrayList<>();
        Reservacion reservacion = new Reservacion();
        reservacion.setFecha(fechaDate);
        reservacion.setNoActividad(numActividad);
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
            dialogo.show();
        }
    }

    /**
     * Comprueba que se encuentre seleccionado un alumno antes de registrar su asistencia.
     */
    @FXML
    private void seleccionarRegistro() {
        if (tablaAlumnos.getSelectionModel().getSelectedItem() != null) {
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
        Date fechaDate = null;
        List<Actividad> noActividad = null;
        List<Integer> noActividades = null;
        int numActividad = 0;

        String fecha = comboFecha.getValue();
        SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            noActividad = ActividadDAO.recuperarNombreActividad(comboFecha.getSelectionModel().getSelectedItem());
            noActividades = new ArrayList<>();
            for (Actividad actividad : noActividad) {
                noActividades.add(actividad.getNoActividad());
            }
            numActividad = noActividades.get(comboActividad.getSelectionModel().getSelectedIndex());
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
        }

        try {
            fechaDate = fechaFormat.parse(fecha);
        } catch (ParseException ex) {
        }
        
        Reservacion reservacion = new Reservacion();
        reservacion.setMatricula(matricula);
        reservacion.setFecha(fechaDate);
        reservacion.setNoActividad(numActividad);
        reservacion.setAsistencia(true);
        try {
            ReservacionDAO.registrarAsistencia(reservacion);
            dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "Asistencia registrada correctamente.", "Éxito", ButtonType.OK);
            dialogo.show();
            botonCancelar.setDisable(true);
            botonAsistencia.setDisable(true);
            recuperarLista();
            limpiarCampos();

        } catch (IOException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al registrar asistencias.", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Limpia los campos.
     */
    public void limpiarCampos() {
        comboFecha.setValue("");
        comboActividad.setValue("");
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
