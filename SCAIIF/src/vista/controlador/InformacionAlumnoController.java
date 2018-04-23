package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.dao.CursoDAO;
import modelo.dao.InduccionDAO;
import modelo.pojos.Alumno;
import modelo.pojos.Curso;
import modelo.pojos.Induccion;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author esmeralda yamileth Hernández González
 */
public class InformacionAlumnoController implements Initializable {

    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();
    @FXML
    JFXTextField txt_matricula;
    @FXML
    JFXTextField txt_nombre;
    @FXML
    JFXTextField txt_apellidos;
    @FXML
    JFXTextField txt_correo;
    @FXML
    TableView table_Induccion;
    @FXML
    TableColumn colNrc;
    @FXML
    TableColumn colAsesoria;
    @FXML
    TableColumn colInduccion;
    @FXML
    JFXCheckBox checkLengua;
    @FXML
    JFXListView list_cursos;
    @FXML
    JFXButton buscarBtn;

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
        
        txt_matricula.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                if (txt_matricula.getText().length() >= 9) {
                    txt_matricula.setText(txt_matricula.getText().substring(0, 9));
                }
            }
        });
        
        buscarBtn.setDisable(true);
       
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
     * Comprueba el tamaño y contenido de la matrícula.
     */
    @FXML
    private void comprobarMatAlumno() {
        String matricula = txt_matricula.getText();
        if (matricula != null && matricula.length() == 9) {
            buscarBtn.setDisable(false);
        } else {
            buscarBtn.setDisable(true);
        }
    }
    
    /**
     * Muestra el nombre completo del alumno y su correo electrónico para corroborar que sea el
     * usuario que desea dar de baja.
     */
    @FXML
    private void recuperarInformacionAlumno() {
        Alumno alumno;
        try {
            alumno = AlumnoDAO.verificarMatricula(txt_matricula.getText());
            txt_nombre.setText(alumno.getNombre());
            txt_apellidos.setText(alumno.getApPaterno() + " " + alumno.getApMaterno());
            txt_correo.setText(alumno.getCorreo());
            if (alumno.isLenguaIndigena() == true) {
                checkLengua.setSelected(true);
            } else {
                checkLengua.setSelected(false);
            }
            llenarTabla();
            llenarListaCursos();
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "La matrícula que ingresa no es válida o no existe", "Error",
                ButtonType.OK);
            dialogo.show();
            limpiarCampos();
        }
    }
    
    /**
     * Llena la tabla con las fechas de induccion y primera asesoría de cada curso
     * al que esta inscrito el alumno ingresado.
     */
    public void llenarTabla() {
        List<Induccion> listaInfoAlumno = new ArrayList<>();
        String matricula = txt_matricula.getText();

        try {
            listaInfoAlumno = InduccionDAO.recuperarAlumnos(matricula);
            ObservableList<Induccion> listaObservable
                    = FXCollections.observableArrayList(listaInfoAlumno);
            colNrc.setCellValueFactory(new PropertyValueFactory("nrc"));
            colAsesoria.setCellValueFactory(new PropertyValueFactory("primeraAsesoria"));
            colInduccion.setCellValueFactory(new PropertyValueFactory("cursoInduccion"));
            table_Induccion.setItems(listaObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    /**
     * Limpia los campos.
     */
    public void limpiarCampos() {
        txt_matricula.setText("");
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_correo.setText("");
    }

    /**
     * Recupera el nrc y nombre de los cursos a los que está inscrito
     * el alumno.
     */
    private void llenarListaCursos() {
        List<Curso> cursos = new ArrayList<>();
        String matricula = txt_matricula.getText();
        
        try {
            cursos = CursoDAO.recuperarCursos(matricula);
            List<String> cursosAlumno = new ArrayList<>();
            for (Curso cursosAl : cursos) {
                String infoCursos = "Nrc: " + cursosAl.getNrc() + "\n" 
                    + "Nombre: " + cursosAl.getNombreCurso();
                cursosAlumno.add(infoCursos);
            }
            ObservableList<String> cursosObservable = FXCollections.observableArrayList(cursosAlumno);
            list_cursos.setItems(cursosObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        
    }

}
