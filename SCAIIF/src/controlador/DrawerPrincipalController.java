package controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
                reservarActividadButton.setDisable(true);
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

    @FXML
    void salirSistema(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void lanzarComentarioAlumno() {
        try {
            URL comentarioAlumno = getClass().getResource("/vista/Comentario.fxml");
            AnchorPane paneComentario = FXMLLoader.load(comentarioAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneComentario);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    @FXML
    public void lanzarListaAsistencia() {
        try {
            URL listasAsistencia = getClass().getResource("/vista/ListasDeAsistencia.fxml");
            AnchorPane paneLista = FXMLLoader.load(listasAsistencia);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneLista);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * Método que abre la ventana para consultar el historial de los asesores (Actividades que han
     * impartido y actividades que impartirán)
     */
    @FXML
    public void abrirHistorialAsesores() {
        try {
            URL historialAsesores = getClass().getResource("/vista/HistorialAsesores.fxml");
            AnchorPane paneHistorial = FXMLLoader.load(historialAsesores);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneHistorial);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
