package vista.controlador;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.dao.CursoDAO;
import modelo.dao.UsuarioDAO;
import modelo.pojos.Alumno;
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

    @FXML
    public void habilitarBuscar() {
        if (campoMatricula.getText().length() == 9) {
            botonBuscar.setDisable(false);
        } else {
            botonBuscar.setDisable(true);
        }
    }

    @FXML
    public void buscarAlumno() {
        Alumno alumno = new Alumno();
        try {
            alumno = AlumnoDAO.verificarMatricula(campoMatricula.getText());
            if (alumno != null) {
                labelNombre.setText(alumno.getNombre() + " " + alumno.getApPaterno() + " " + alumno.getApMaterno());
                llenarComboCursos();
                llenarComboAsesores();
            } else {
                Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Matrícula no registrada", "Matrícula no válida", ButtonType.OK);
                dialogo.show();
            }
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    public void llenarComboCursos() {
        List<Curso> cursosAlumno;

        try {
            cursosAlumno = CursoDAO.recuperarCursos(campoMatricula.getText());
            ObservableList<Curso> cursosObservable = FXCollections.observableArrayList(cursosAlumno);
            comboCursos.setItems(cursosObservable);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    public void llenarComboAsesores() {
        List<Usuario> asesores;

        try {
            asesores = UsuarioDAO.recuperarAsesores();
            ObservableList<Usuario> asesoresObservable = FXCollections.observableArrayList(asesores);
            comboAsesores.setItems(asesoresObservable);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    @FXML
    public void habilitarRegistrar() {
        if (!comboAsesores.getSelectionModel().isEmpty() && !comboCursos.getSelectionModel().isEmpty()
                && induccionDP.getValue() != null && asesoriaDP.getValue() != null) {
            botonRegistrar.setDisable(false);
        } else {
            botonRegistrar.setDisable(true);
        }
    }
}
