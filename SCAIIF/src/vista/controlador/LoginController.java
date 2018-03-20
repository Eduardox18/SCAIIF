package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Cifrado;
import modelo.dao.UsuarioDAO;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Usuario;

/**
 *
 * @author Ángel Eduardo Domínguez Delgado
 */
public class LoginController extends Application {

    private static int cargoLog = 0;
    private static int noPersonalLog = 0;
    private static BorderPane root = new BorderPane();
    private static BorderPane panePrincipal = new BorderPane();

    @FXML
    private JFXPasswordField campoContrasenia;

    @FXML
    private JFXTextField campoUsuario;

    @FXML
    private JFXButton botonSalir;

    @FXML
    private JFXButton botonIngresar;

    public static BorderPane getPrincipal() {
        return panePrincipal;
    }

    public static int returnCargo() {
        return cargoLog;
    }

    public static int returnNoPersonalLog() {
        return noPersonalLog;
    }

    @Override
    public void start(Stage primaryStage) {

        AnchorPane paneLogin = null;

        URL login = getClass().getResource("/vista/Login.fxml");
        try {
            paneLogin = FXMLLoader.load(login);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        root.setCenter(paneLogin);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("SCAIIF");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void activarBotonIngresar() {
        if (campoUsuario.getText().length() != 0 & campoContrasenia.getText().length() != 0) {
            botonIngresar.setDisable(false);
        } else {
            botonIngresar.setDisable(true);
        }
    }

    @FXML
    public void ingresarSistema() {
        Cifrado cifrar = new Cifrado();
        Usuario ingresado = new Usuario();
        try {
            ingresado = UsuarioDAO.recuperarUsuario(Integer.parseInt(campoUsuario.getText()));
        } catch (Exception ex) {
            System.out.println("Número de personal no válido");
        }

        try {
            if (ingresado == null) {
                System.out.println("El usuario ingresado no existe");
            } else if (ingresado.getPassword().equals(
                    cifrar.cifrarCadena(campoContrasenia.getText()))) {
                cargoLog = ingresado.getIdCargo();
                noPersonalLog = ingresado.getNoPersonal();
                Stage stagePrincipal = new Stage();
                URL panePrincipalURL = getClass().getResource(("/vista/Principal.fxml"));
                AnchorPane paneInicial = FXMLLoader.load(panePrincipalURL);

                Stage stage = (Stage) botonIngresar.getScene().getWindow();
                stage.close();

                panePrincipal.setCenter(paneInicial);
                Scene sceneDos = new Scene(panePrincipal, 700, 500);
                stagePrincipal.setScene(sceneDos);
                stagePrincipal.show();
            } else {
                System.out.println("Los datos ingresados son incorrectos, intente de nuevo");
            }
        } catch (IOException ioEx) {
            System.out.println("Servidor no disponible, intente más tarde");
        }

    }

    @FXML
    void salirSistema() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
