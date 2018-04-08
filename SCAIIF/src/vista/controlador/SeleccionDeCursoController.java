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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.dao.CursoDAO;
import modelo.dao.PeriodoDAO;
import modelo.pojos.Curso;
import modelo.pojos.Periodo;
import vista.Dialogo;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SeleccionDeCursoController implements Initializable{

    @FXML
    private JFXComboBox<Periodo> comboPeriodo;

    @FXML
    private TableView<Curso> tablaCursos;

    @FXML
    private JFXButton botonContinuar;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private TableColumn colNrc;

    @FXML
    private TableColumn colCurso;

    static Curso cursoSeleccionado;

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

        llenarComboPeriodo();
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

    @FXML
    public void llenarTabla() {
        List<Curso> cursos;

        try {
            cursos = CursoDAO.recuperarCursosDePeriodo(
                    comboPeriodo.getSelectionModel().getSelectedItem().getIdPeriodo());
            ObservableList<Curso> cursosObservable = FXCollections.observableArrayList(cursos);
            colNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            colCurso.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
            tablaCursos.setItems(cursosObservable);
        } catch (Exception e) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    @FXML
    public void activarContinuar() {
        if (tablaCursos.getSelectionModel().getSelectedItem() != null) {
            botonContinuar.setDisable(false);
        }
    }

    @FXML
    public void continuarResumen() {
        cursoSeleccionado = tablaCursos.getSelectionModel().getSelectedItem();
        try {
            URL resumenCalendario = getClass().getResource("/vista/ResumenCalendarioCurso.fxml");
            AnchorPane paneResumen = FXMLLoader.load(resumenCalendario);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneResumen);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}
