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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.CalendarioDAO;
import modelo.dao.CursoDAO;
import modelo.dao.DiaFestivoDAO;
import modelo.dao.PeriodoDAO;
import modelo.pojos.*;
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
    private TableColumn conversacionCol;

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
            ex.printStackTrace();
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
        botonImprimir.setDisable(true);
        limpiarCampos();
        comboCurso.setDisable(false);
        List<Curso> cursos;
        try {
            cursos = CursoDAO.recuperarCursosDePeriodo(comboPeriodo.getSelectionModel().getSelectedItem().
                    getIdPeriodo());
            ObservableList<Curso> cursosObservable = FXCollections.observableArrayList(cursos);
            comboCurso.setItems(cursosObservable);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    @FXML
    public void llenarTablaCurso() {
        if (comboCurso.getSelectionModel().getSelectedItem() != null) {
            botonImprimir.setDisable(false);
            List<ConsultaCalendario> infoCalendarioCurso;
            List<DiasFestivos> diasFestivos = null;
            Calendario calendario = null;

            try {
                botonImprimir.setDisable(false);
                infoCalendarioCurso = CalendarioDAO.consultarCalendarioCurso(comboCurso.getSelectionModel().
                        getSelectedItem().getNrc());


                ObservableList<ConsultaCalendario> calendarioObservable =
                        FXCollections.observableArrayList(infoCalendarioCurso);
                mesCol.setCellValueFactory(new PropertyValueFactory<>("mes"));
                diasCol.setCellValueFactory(new PropertyValueFactory<>("diaInicio"));
                moduloCol.setCellValueFactory(new PropertyValueFactory<>("noModulo"));
                seccionCol.setCellValueFactory(new PropertyValueFactory<>("noSeccion"));
                limiteCol.setCellValueFactory(new PropertyValueFactory<>("fechaLimiteExamen"));
                materialCol.setCellValueFactory(new PropertyValueFactory<>("nombreMaterial"));
                conversacionCol.setCellValueFactory(new PropertyValueFactory<>("noConversacion"));
                tablaResumen.setItems(calendarioObservable);

                if (calendarioObservable.isEmpty()) {
                    tablaResumen.setPlaceholder(new Label("No hay información del curso seleccionado."));
                    labelFestivos.setText("");
                    vacacionesLabel.setText("");
                } else {
                    botonImprimir.setDisable(false);
                }

                diasFestivos = DiaFestivoDAO.consultarDiasFestivosCurso(comboCurso.getSelectionModel().
                        getSelectedItem().getNrc());

                calendario = CalendarioDAO.recuperarCalendario(comboCurso.getSelectionModel().
                        getSelectedItem().getNrc());
            } catch (Exception ex) {
                ex.printStackTrace();
                Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                dialogo.show();
            }

            try {
                String diasFestivosCadena = "Días festivos: ";
                for (int i = 0; i < diasFestivos.size(); i++) {
                    diasFestivosCadena += diasFestivos.get(i).getFormatoDiaFestivo() + ", ";
                }
                labelFestivos.setText(diasFestivosCadena.substring(0, diasFestivosCadena.length() - 2));

                vacacionesLabel.setText("Vacaciones: " + calendario.getVacaciones());
            } catch (NullPointerException ex) {
                //DoNothing
            }

        }
    }

    /**
     * Muestra el diálogo para imprimir una lista de asistencia
     */
    @FXML
    public void mostrarVentanaImprimir() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "Se está imprimiendo la lista...", "Imprimiendo",
                ButtonType.OK);
        dialogo.show();
    }

    private void limpiarCampos() {
        tablaResumen.setItems(null);
        labelFestivos.setText("No se ha seleccionado curso");
        vacacionesLabel.setText("No se ha seleccionado curso");
    }
}
