package controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
        // TODO
    }    
    
    @FXML
    void salirSistema(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void lanzarComentarioAlumno () {
            try {
                URL comentarioAlumno = getClass().getResource("/vista/Comentario.fxml");
                AnchorPane paneComentario = FXMLLoader.load(comentarioAlumno);
                
                BorderPane border = LoginController.getPrincipal();
                border.setCenter(paneComentario);
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
    }
}
