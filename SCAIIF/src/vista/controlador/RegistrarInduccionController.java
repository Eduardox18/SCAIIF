package vista.controlador;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.pojos.Curso;
import modelo.pojos.Usuario;
import vista.Dialogo;


public class RegistrarInduccionController implements Initializable{

    @FXML
    private JFXTextField campoMatricula;

    @FXML
    private JFXComboBox<Curso> comboCursos;

    @FXML
    private JFXComboBox<Usuario> comboAsesores;

    @FXML
    private JFXDatePicker asesoriaDP;

    @FXML
    private JFXDatePicker induccionDP;

    @FXML
    private JFXButton botonRegistrar;

    @FXML
    private Label labelNombre;

    @FXML
    private JFXButton botonBuscar;

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;

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
    }

    /**
     * Método que muestra el ícono del menú en la ventana
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }



}
