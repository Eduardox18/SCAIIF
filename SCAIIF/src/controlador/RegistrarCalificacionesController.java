package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Curso;
import servicios.pojos.Modulo;
import servicios.pojos.Seccion;

/**
 * FXML Controller class
 *
 * @author Hernández González Esmeralda Yamileth
 */
public class RegistrarCalificacionesController implements Initializable {

    @FXML
    private JFXTextField TFMatricula;
    @FXML
    private JFXButton BTGuardar;
    @FXML
    private JFXButton BTCancelar;
    @FXML
    private JFXComboBox<String> CBCurso;
    @FXML
    private JFXComboBox<Integer> CBModulo;
    @FXML
    private JFXComboBox<Integer> CBSeccion;
    @FXML
    private JFXTextField TFCalificacion;
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/vista/DrawerPrincipal.fxml"));
            menuDrawer.setSidePane(box);
            menuDrawer.setDisable(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
        llenarComboBox();

    }

    /**
     *
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del mnú
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Recupera todos los cursos registrados en la base de datos.
     */
    private void llenarComboBox() {
        List<Curso> cursos = new ArrayList();
        List<Seccion> secciones = new ArrayList();
        List<Modulo> modulos = new ArrayList();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            cursos = conn.selectList("Curso.getCursos");
            secciones = conn.selectList("Seccion.getSeccion");
            modulos = conn.selectList("Modulo.getModulo");
        } catch (IOException ex) {
            System.out.println("Error al recuperar información");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        List<String> nombreCursos = new ArrayList<>();
        for (Curso curso : cursos) {
            nombreCursos.add(curso.getNombreCurso());
        }
        List<Integer> noModulo = new ArrayList<>();
        for (Modulo modulo : modulos) {
            noModulo.add(modulo.getNoModulo());
        }
        List<Integer> noSeccion = new ArrayList<>();
        for (Seccion seccion : secciones) {
            noSeccion.add(seccion.getNoSeccion());
        }

        ObservableList<String> cursosObservable = FXCollections.observableArrayList(nombreCursos);
        CBCurso.setItems(cursosObservable);
        ObservableList<Integer> modulosObservable = FXCollections.observableArrayList(noModulo);
        CBModulo.setItems(modulosObservable);
        ObservableList<Integer> seccionesObservable = FXCollections.observableArrayList(noSeccion);
        CBSeccion.setItems(seccionesObservable);
        
    }

    @FXML
    private void registrarCalificacion() {

    }

}
