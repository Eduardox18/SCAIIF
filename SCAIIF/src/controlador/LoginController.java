package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Actividad;
import servicios.pojos.Usuario;

/**
 *
 * @author Ángel Eduardo Domínguez Delgado
 */
public class LoginController implements Initializable {
    
    @FXML
    private JFXPasswordField campoContrasenia;

    @FXML
    private JFXTextField campoUsuario;

    @FXML
    private JFXButton botonSalir;

    @FXML
    private JFXButton botonIngresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    void activarBotonIngresar() {
        if (campoUsuario.getText().length() != 0 & campoContrasenia.getText().length() != 0) {
            botonIngresar.setDisable(false);
        } else {
            botonIngresar.setDisable(true);
        }
    }

    @FXML
    void ingresarSistema() {
        
    }

    @FXML
    void salirSistema() {
        System.exit(0);
    }
}
