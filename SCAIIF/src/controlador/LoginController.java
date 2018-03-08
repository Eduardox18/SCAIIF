package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Cifrado;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
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
        Cifrado cifrar = new Cifrado();
        List<Usuario> ingresado = new ArrayList<Usuario>();
        SqlSession conn = null;
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("noPersonal", campoUsuario.getText());
            conn = MyBatisUtils.getSession();
            ingresado = conn.selectList("Usuario.getUsuario", parametros);
        } catch (Exception ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        if (ingresado.isEmpty()) {
            System.out.println("El usuario ingresado no existe");
        } else if (ingresado.get(0).getPassword().equals(
                cifrar.cifrarCadena(campoContrasenia.getText()))) {
            Stage stage = new Stage();

            try {
                Parent menu = FXMLLoader.load(getClass().getResource("/vista/Principal.fxml"));

                Scene scene = new Scene(menu);

                stage.setScene(scene);
                stage.show();
                Stage actualStage = (Stage) botonIngresar.getScene().getWindow();
                actualStage.close();
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } else {
            System.out.println("La contraseña es incorrecta!");
        }
    }

    @FXML
    void salirSistema() {
        System.exit(0);
    }
}
