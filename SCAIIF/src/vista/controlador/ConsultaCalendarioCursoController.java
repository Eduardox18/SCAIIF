package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.CursoDAO;
import modelo.dao.PeriodoDAO;
import modelo.pojos.ConsultaCalendario;
import modelo.pojos.Curso;
import modelo.pojos.Periodo;
import vista.Dialogo;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaCalendarioCursoController implements Initializable {

    @FXML
    private JFXComboBox<Periodo> comboPeriodo;

    @FXML
    private JFXComboBox<Curso> comboCurso;

    @FXML
    private TableView<ConsultaCalendario> tablaResumen;

    @FXML
    private TableColumn mesCol;

    @FXML
    private TableColumn diasCol;

    @FXML
    private TableColumn moduloCol;

    @FXML
    private TableColumn seccionCol;

    @FXML
    private TableColumn limiteCol;

    @FXML
    private TableColumn materialCol;

    @FXML
    private Label labelFestivos;

    @FXML
    private JFXButton botonImprimir;

    @FXML
    private Label vacacionesLabel;

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        llenarComboPeriodo();
        tablaResumen.setPlaceholder(new Label(""));
    }

    /**
     * *
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del menú
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Llena el comboBox de los Periodos
     */
    public void llenarComboPeriodo() {
        List<Periodo> periodos;

        try {
            periodos = PeriodoDAO.recuperarPeriodos();
            ObservableList<Periodo> periodosObservable = FXCollections.observableArrayList(periodos);
            comboPeriodo.setItems(periodosObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Llena el comboBox de los Cursos
     */
    @FXML
    public void llenarComboCursos() {
        tablaResumen.setItems(null);
        comboCurso.setDisable(false);
        List<Curso> cursos;
        try {
            cursos = CursoDAO.recuperarCursosDePeriodo(comboPeriodo.getSelectionModel().getSelectedItem().
                    getIdPeriodo());
            ObservableList<Curso> cursosObservable = FXCollections.observableArrayList(cursos);
            comboCurso.setItems(cursosObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    @FXML
    public void llenarTablaCurso() {

    }
}
