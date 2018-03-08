package controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
}
