package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * FXML Controller class
 *
 * @author Hernández González Esmeralda Yamileth
 */
public class RegistrarCalificacionesController implements Initializable {

    @FXML
    private JFXTextField TFMatricula;
    @FXML
    private JFXButton BTVolver;
    @FXML
    private JFXButton BTGuardar;
    @FXML
    private JFXButton BTCancelar;
    @FXML
    private JFXComboBox CBCurso;
    @FXML
    private JFXComboBox CBModulo;
    @FXML
    private JFXComboBox CBAutoevaluacion;
    @FXML
    private JFXTextField TFCalificacion;
    
    private SqlSessionFactory sqlSessionFactory = null;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void volverPrincipal () {
        Stage stage = new Stage();
        try {
            Parent paginaPrincipal = FXMLLoader.load(getClass().getResource("/vista/Principal.fxml"));
            Scene scene = new Scene(paginaPrincipal);
            stage.setScene(scene);
            stage.show();
            Stage actualStage = (Stage) BTVolver.getScene().getWindow();
            actualStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertarCalificacion (double calificacion) {
        
        SqlSession session = sqlSessionFactory.openSession();
        /*try {
            int matricula = session.insert("CalificacionMapper.insertarCalificacion", calificacion);
         
        }*/
    }
    
}
