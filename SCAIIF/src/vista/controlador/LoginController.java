package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Cifrado;
import modelo.dao.UsuarioDAO;
import servicios.pojos.Usuario;
import vista.Dialogo;

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
        Dialogo dialogo = null;
        Cifrado cifrar = new Cifrado();
        Usuario ingresado = new Usuario();
        try {
            ingresado = UsuarioDAO.recuperarUsuario(Integer.parseInt(campoUsuario.getText()));
        } catch (Exception ex) {
            dialogo = new Dialogo(Alert.AlertType.INFORMATION, "Número de personal no válido", 
                        "Número de personal", ButtonType.OK);
            dialogo.show();
        }

        try {
            if (ingresado == null) {
                dialogo = new Dialogo(Alert.AlertType.INFORMATION, "El usuario ingresado no existe", 
                        "Usuario no existe", ButtonType.OK);
                dialogo.show();
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
                dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                        "Los datos ingresados son incorrectos, intente de nuevo",
                        "Datos incorrectos", ButtonType.OK);
                dialogo.show();
            }
        } catch (IOException ioEx) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
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
