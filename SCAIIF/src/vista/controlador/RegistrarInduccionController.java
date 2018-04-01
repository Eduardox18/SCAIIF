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

import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.dao.CursoDAO;
import modelo.dao.InduccionDAO;
import modelo.dao.UsuarioDAO;
import modelo.pojos.Alumno;
import modelo.pojos.Curso;
import modelo.pojos.Induccion;
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

    private String matriculaActual;

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
     * Muestra el ícono del menú en la ventana
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Habilita el botón de buscar matrícula si es una cadena de tamaño válido
     */
    @FXML
    public void habilitarBuscar() {
        if (campoMatricula.getText().length() == 9) {
            botonBuscar.setDisable(false);
        } else {
            botonBuscar.setDisable(true);
        }
    }

    /**
     * Busca al alumno y guarda su información. También imprime su nombre en pantalla
     */
    @FXML
    public void buscarAlumno() {
        Alumno alumno = new Alumno();
        try {
            alumno = AlumnoDAO.verificarMatricula(campoMatricula.getText());
            if (alumno != null) {
                labelNombre.setText(alumno.getNombre() + " " + alumno.getApPaterno() + " " + alumno.getApMaterno());
                matriculaActual = campoMatricula.getText();
                llenarComboCursos();
                llenarComboAsesores();
            } else {
                Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                        "Matrícula no registrada", "Matrícula no válida", ButtonType.OK);
                dialogo.show();
            }
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Llena el comboBox de cursos con los cursos del alumno
     */
    public void llenarComboCursos() {
        List<Curso> cursosAlumno;

        try {
            cursosAlumno = CursoDAO.recuperarCursos(matriculaActual);
            ObservableList<Curso> cursosObservable = FXCollections.observableArrayList(cursosAlumno);
            comboCursos.setItems(cursosObservable);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Llena el combo de asesores con los asesores del sistema
     */
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

    /**
     * Habilita el botón de registrar si los componentes tienen selección.
     */
    @FXML
    public void habilitarRegistrar() {
        if (!comboAsesores.getSelectionModel().isEmpty() && !comboCursos.getSelectionModel().isEmpty()
                && induccionDP.getValue() != null && asesoriaDP.getValue() != null) {
            botonRegistrar.setDisable(false);
        } else {
            botonRegistrar.setDisable(true);
        }
    }

    /**
     * Registra la inducción de un alumno
     */
    @FXML
    public void registrarInduccion() {
        boolean existe;
        boolean exito;

        Induccion induccion = new Induccion(matriculaActual,
                comboCursos.getSelectionModel().getSelectedItem().getNrc(),
                Date.valueOf(induccionDP.getValue()),
                Date.valueOf(asesoriaDP.getValue()),
                comboAsesores.getSelectionModel().getSelectedItem().getNoPersonal());

        try {
            existe = InduccionDAO.comprobarInduccion(induccion);
            if (!existe) {
                exito = InduccionDAO.registrarInduccion(induccion);
                if (exito) {
                    Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                            "Información guardada con éxito", "Éxito",
                            ButtonType.OK);
                    dialogo.show();
                }
            } else {
                Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                        "Ya existe un registro de ese alumno en ese curso", "Registro existente",
                        ButtonType.OK);
                dialogo.show();
            }
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}
