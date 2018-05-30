package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.dao.CursoDAO;
import modelo.dao.InduccionDAO;
import modelo.dao.InscripcionDAO;
import modelo.pojos.Alumno;
import modelo.pojos.Curso;
import modelo.pojos.Induccion;
import modelo.pojos.Inscripcion;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class BajaAlumnoController implements Initializable {

    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();

    @FXML
    JFXButton botonBuscar;
    @FXML
    JFXButton botonBaja;
    @FXML
    JFXButton botonBajaCurso;
    @FXML
    JFXComboBox<String> comboCursos;
    @FXML
    Label lblCorreo;
    @FXML
    Label lblNombre;
    @FXML
    JFXTextField campoMatricula;

    /**
     * Initializes the controller class.
     */
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

        campoMatricula.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                if (campoMatricula.getText().length() >= 9) {
                    campoMatricula.setText(campoMatricula.getText().substring(0, 9));
                }
            }

        });
        botonBajaCurso.setDisable(true);
        botonBaja.setDisable(true);
        botonBuscar.setDisable(true);
    }

    /**
     * Método que hace visible e invisible el menú drawer.
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Vuelve la vigencia del alumno a falso, no elimina el registro, solo lo da de baja
     * (inhabilita).
     */
    @FXML
    public void darBajaAlumno() {
        Dialogo dialogo = null;
        String matricula = campoMatricula.getText();
        Alumno alumno = new Alumno();
        alumno.setMatricula(campoMatricula.getText());
        alumno.setVigente(0);

        try {
            AlumnoDAO.bajaAlumno(alumno);
            dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "El alumno se ha dado de baja correctamente", "Éxito", ButtonType.OK);
            dialogo.show();
            botonBajaCurso.setDisable(true);
            botonBaja.setDisable(true);
            botonBuscar.setDisable(false);
            limpiarCampos();
        } catch (Exception ioEx) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al dar de baja al alumno.", "Error", ButtonType.OK);
            dialogo.show();
        }

    }

    /**
     * Vuelve la vigencia del alumno a falso, no elimina el registro, solo lo da de baja
     * (inhabilita).
     */
    @FXML
    public void darBajaCurso() {
        String matricula = campoMatricula.getText();
        Induccion induccion = new Induccion();
        Inscripcion inscripcion = new Inscripcion();
        List<Curso> nrc = null;
        List<Integer> nrcCursos = null;
        int nrcCurso = 0;

        Dialogo dialogo = new Dialogo(Alert.AlertType.CONFIRMATION,
            "¿Seguro que desea dar de baja al alumno del curso?", "Éxito", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = dialogo.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                nrc = CursoDAO.recuperarCursos(matricula);
                nrcCursos = new ArrayList<>();

                for (Curso cursosNRC : nrc) {
                    nrcCursos.add(cursosNRC.getNrc());
                }
                nrcCurso = nrcCursos.get(comboCursos.getSelectionModel().getSelectedIndex());
                induccion.setMatricula(matricula);
                induccion.setNrc(nrcCurso);
                inscripcion.setMatricula(matricula);
                inscripcion.setNrc(nrcCurso);

                InduccionDAO.bajaInduccion(induccion);
                InscripcionDAO.bajaInscripcion(inscripcion);
                dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                    "Alumno dado de baja del curso exitosamente", "Éxito", ButtonType.OK);
                dialogo.show();
                
                botonBaja.setDisable(true);
                botonBuscar.setDisable(true);
                limpiarCampos();
                botonBajaCurso.setDisable(true);
                
            } catch (Exception ex) {
                dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Error al dar de baja del curso", "Error", ButtonType.OK);
                dialogo.show();
            }
        } else if (result.get() == ButtonType.NO) {
            botonBuscar.setDisable(true);
        }
    }

    /**
     * Muestra el nombre completo del alumno y su correo electrónico para corroborar que sea el
     * usuario que desea dar de baja.
     */
    @FXML
    private void recuperarInformacionAlumno() {
        Alumno alumno;
        List<Curso> cursosAl = null;

        try {
            alumno = AlumnoDAO.recuperarInfoAlumno(campoMatricula.getText());
            cursosAl = CursoDAO.recuperarCursos(campoMatricula.getText());
            List<String> cursosAlumno = new ArrayList<>();

            for (Curso cursoAlumno : cursosAl) {
                String infoCurso = cursoAlumno.getNombreCurso();
                cursosAlumno.add(infoCurso);
            }

            ObservableList<String> cursosObservable = FXCollections.observableArrayList(cursosAlumno);

            comboCursos.setItems(cursosObservable);
            lblNombre.setText(alumno.getNombre() + " " + alumno.getApPaterno() + " " + alumno.getApMaterno());
            lblCorreo.setText(alumno.getCorreo());
            botonBaja.setDisable(false);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "La matrícula que ingresa no es válida o no existe", "Error",
                ButtonType.OK);
            dialogo.show();

            botonBaja.setDisable(true);
            //botonBajaCurso.setDisable(true);
            limpiarCampos();
        }
    }

    /**
     * Comprueba que se seleccione un curso para habilitar el botón que da de baja el curso del
     * alumno.
     */
    @FXML
    private void seleccionarCurso() {
        if (!comboCursos.getSelectionModel().getSelectedItem().equals("")) {
            botonBajaCurso.setDisable(false);
        } else {
            botonBajaCurso.setDisable(true);
        }
    }

    /**
     * Limpia los campos.
     */
    public void limpiarCampos() {
        campoMatricula.setText("");
        lblNombre.setText("");
        lblCorreo.setText("");
        comboCursos.setValue("");
    }

    /**
     * Comprueba el tamaño y contenido de la matrícula.
     */
    @FXML
    private void buscarDatosAlumno() {
        String matricula = campoMatricula.getText();
        if (matricula != null && matricula.length() == 9) {
            botonBuscar.setDisable(false);
        } else {
            botonBuscar.setDisable(true);
        }
    }
}
