package vista.controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class DrawerPrincipalController implements Initializable {

    @FXML
    private JFXButton altaAlumnoButton;

    @FXML
    private JFXButton bajaAlumnoButton;

    @FXML
    private JFXButton registrarInduccionButton;

    @FXML
    private JFXButton registrarObservacionButton;

    @FXML
    private JFXButton registrarCalificacionButton;
    
    @FXML
    private JFXButton consultarAlumnoButton;

    @FXML
    private JFXButton registrarAvisoButton;

    @FXML
    private JFXButton consultarActividadAsesorButton;

    @FXML
    private JFXButton reservarActividadButton;

    @FXML
    private JFXButton cancelarActividadButton;

    @FXML
    private JFXButton consultarPorImpartirButton;

    @FXML
    private JFXButton consultarListaAsistenciaButton;

    @FXML
    private JFXButton registrarAsistenciaButton;

    @FXML
    private JFXButton crearCalendarioActButton;

    @FXML
    private JFXButton consultarCalendarioActButton;

    @FXML
    private JFXButton proximasActividadesButton;

    @FXML
    private JFXButton registrarCalendarioCursoButton;

    @FXML
    private JFXButton consultarCalendarioCursoButton;

    @FXML
    private JFXButton salirButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginController login = new LoginController();
        int cargo = login.returnCargo();
        switch (cargo) {
            case 1:
                altaAlumnoButton.setDisable(true);
                registrarInduccionButton.setDisable(true);
                registrarObservacionButton.setDisable(true);
                registrarCalificacionButton.setDisable(true);
                consultarPorImpartirButton.setDisable(true);
                reservarActividadButton.setDisable(true);
                consultarListaAsistenciaButton.setDisable(true);
                registrarAsistenciaButton.setDisable(true);
                break;
            case 2:
                altaAlumnoButton.setDisable(true);
                bajaAlumnoButton.setDisable(true);
                registrarInduccionButton.setDisable(true);
                registrarAvisoButton.setDisable(true);
                consultarActividadAsesorButton.setDisable(true);
                reservarActividadButton.setDisable(true);
                cancelarActividadButton.setDisable(true);
                crearCalendarioActButton.setDisable(true);
                registrarCalendarioCursoButton.setDisable(true);
                break;
            case 3:
                bajaAlumnoButton.setDisable(true);
                registrarObservacionButton.setDisable(true);
                registrarCalificacionButton.setDisable(true);
                registrarAvisoButton.setDisable(true);
                consultarActividadAsesorButton.setDisable(true);
                cancelarActividadButton.setDisable(true);
                consultarPorImpartirButton.setDisable(true);
                consultarListaAsistenciaButton.setDisable(true);
                registrarAsistenciaButton.setDisable(true);
                crearCalendarioActButton.setDisable(true);
                registrarCalendarioCursoButton.setDisable(true);
                break;
            default:
                break;
        }
    }

    /**
     * Cierra la aplicación
     *
     * @param event
     */
    @FXML
    void salirSistema(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Abre la ventana de comentario para alumno
     */
    @FXML
    public void lanzarComentarioAlumno() {
        try {
            URL comentarioAlumno = getClass().getResource("/vista/Observacion.fxml");
            AnchorPane paneComentario = FXMLLoader.load(comentarioAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneComentario);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de lista de asistencia
     */
    @FXML
    public void lanzarListaAsistencia() {
        try {
            URL listasAsistencia = getClass().getResource("/vista/ListasDeAsistencia.fxml");
            AnchorPane paneLista = FXMLLoader.load(listasAsistencia);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneLista);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de registro de inducción
     */
    @FXML
    public void lanzarRegistroInduccion() {
        try {
            URL registroInduccion = getClass().getResource("/vista/RegistrarInduccion.fxml");
            AnchorPane paneInduccion = FXMLLoader.load(registroInduccion);
            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneInduccion);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde, ", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de registro de asistencia a reservación/actividad.
     */
    @FXML
    public void lanzarRegistroAsistencia() {
        try {
            URL listasAsistencia = getClass().getResource("/vista/RegistrodeAsistencia.fxml");
            AnchorPane paneLista = FXMLLoader.load(listasAsistencia);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneLista);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para consultar el historial de los asesores (Actividades que han impartido y
     * actividades que impartirán)
     */
    @FXML
    public void abrirHistorialAsesores() {
        try {
            URL historialAsesores = getClass().getResource("/vista/HistorialAsesores.fxml");
            AnchorPane paneHistorial = FXMLLoader.load(historialAsesores);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneHistorial);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de alta alumno
     */
    @FXML
    public void lanzarAltaAlumno() {
        try {
            URL registrarAlumno = getClass().getResource("/vista/RegistrarAlumno.fxml");
            AnchorPane paneHistorial = FXMLLoader.load(registrarAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneHistorial);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para registrar la calificación de un alumno en base al curso inscrito.
     */
    @FXML
    public void lanzarRegistroCalificaciones() {
        try {
            URL registrarCalificacion = getClass().getResource("/vista/RegistrarCalificaciones.fxml");
            AnchorPane paneCalificaciones = FXMLLoader.load(registrarCalificacion);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneCalificaciones);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de cancelar actividad
     */
    @FXML
    public void lanzarCancelarActividad() {
        try {
            URL cancelarActividad = getClass().getResource("/vista/CancelarActividad.fxml");
            AnchorPane paneCancelacion = FXMLLoader.load(cancelarActividad);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneCancelacion);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de aviso para asesores
     */
    @FXML
    public void lanzarAvisoAsesor() {
        try {
            URL avisoAsesor = getClass().getResource("/vista/AvisoAsesor.fxml");
            AnchorPane paneAviso = FXMLLoader.load(avisoAsesor);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneAviso);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana de reservar actividad
     */
    @FXML
    public void lanzarReservarActividad() {
        try {
            URL reservarAct = getClass().getResource("/vista/ReservarActividad.fxml");
            AnchorPane paneReservar = FXMLLoader.load(reservarAct);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneReservar);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para crear el calendario de un curso
     */
    @FXML
    public void lanzarCrearCalendarioCurso() {
        try {
            URL crearCalendario = getClass().getResource("/vista/SeleccionDeCurso.fxml");
            AnchorPane paneCalendario = FXMLLoader.load(crearCalendario);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneCalendario);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para crear el calendario de un curso
     */
    @FXML
    public void lanzarBajaAlumno() {
        try {
            URL bajaAlumno = getClass().getResource("/vista/BajaAlumno.fxml");
            AnchorPane paneBajas = FXMLLoader.load(bajaAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneBajas);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para consultar las actividades próximas de un alumno
     */
    @FXML
    public void lanzarActividadesProximas() {
        try {
            URL actProximas = getClass().getResource("/vista/ActividadesProximas.fxml");
            AnchorPane paneProximas = FXMLLoader.load(actProximas);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneProximas);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
 
    /**
     * Abre la ventana para consultar la información del alumno
     */
    @FXML
    public void lanzarInformacionAlumno() {
        try {
            URL infoAlumno = getClass().getResource("/vista/InformacionAlumno.fxml");
            AnchorPane paneInfoAlumno = FXMLLoader.load(infoAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneInfoAlumno);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para consultar el calendario del curso
     */
    @FXML
    public void lanzarConsultarCalendarioCurso() {
        try {
            URL calendarioCurso = getClass().getResource("/vista/ConsultaCalendarioCurso.fxml");
            AnchorPane paneCalendario = FXMLLoader.load(calendarioCurso);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneCalendario);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    /**
     * Abre la ventana para consultar el calendario de actividades
     */
    @FXML
    public void lanzarCalendarioActividades() {
        try {
            URL calActividades = getClass().getResource("/vista/CalendarioActividades.fxml");
            AnchorPane paneCalendario = FXMLLoader.load(calActividades);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneCalendario);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}
