package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import modelo.dao.ActividadDAO;
import modelo.dao.CursoDAO;
import modelo.dao.ReservacionDAO;
import modelo.pojos.ActividadAsesor;
import modelo.pojos.Alumno;
import modelo.pojos.Curso;
import vista.Dialogo;

public class ReservarParaAlumnoController implements Initializable {
    
    @FXML
    private Label labelMatricula;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelMail;
    
    @FXML
    private TableView<ActividadAsesor> tablaActividades;
    
    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colinicio;

    @FXML
    private TableColumn colFin;

    @FXML
    private TableColumn colAsesor;

    @FXML
    private JFXButton botonCancelar;

    @FXML
    private JFXButton botonReservar;
    
    @FXML
    private JFXComboBox<Curso> comboCurso;
    
    private Alumno infoAlumno;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonReservar.setDisable(true);
        
        tablaActividades.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                botonReservar.setDisable(false);
            }
        });
        
        comboCurso.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            botonReservar.setDisable(true);
        });
        
        tablaActividades.setPlaceholder(new Label("No hay actividades disponibles"));
    }   
    
    /**
     * Carga los valores iniciales de la ventana
     * @param infoAlumno Información del alumno que desea reservar la actividad
     */
    public void infoVentana(Alumno infoAlumno) {
        this.infoAlumno = infoAlumno;
        labelMatricula.setText(infoAlumno.getMatricula());
        labelNombre.setText(infoAlumno.getApPaterno() + " " + infoAlumno.getApMaterno() + " " + 
                infoAlumno.getNombre());
        labelMail.setText(infoAlumno.getCorreo());
        llenarCombo();
        llenarTabla();
    }
    
    @FXML
    /**
     * Regresa a la ventana donde se selecciona un alumno
     */
    private void accionRegresar() {
        try {
            URL reservarAct = getClass().getResource("/vista/ReservarActividad.fxml");
            AnchorPane paneReservar = FXMLLoader.load(reservarAct);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneReservar);
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    @FXML
    /**
     * Puebla la tabla con las actividades disponibles para el alumno
     */
    private void llenarTabla(){
        ObservableList<ActividadAsesor> actividadesObservable;
        try {
            actividadesObservable = FXCollections.observableArrayList(
                    ActividadDAO.recuperarActividadesDisponibles(infoAlumno.getMatricula(), 
                    comboCurso.getSelectionModel().getSelectedItem().getNrc()));
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
            colFin.setCellValueFactory(new PropertyValueFactory("horaFin"));
            colinicio.setCellValueFactory(new PropertyValueFactory("horaInicio"));
            colAsesor.setCellValueFactory(new PropertyValueFactory("nombreAsesor"));
            tablaActividades.setItems(actividadesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        
    }
    
    /**
     * Puebla el combo box con los cursos a los que el alumno esté inscrito
     */
    private void llenarCombo(){
        ObservableList<Curso> cursoObservable;
        try {
            cursoObservable = FXCollections.observableArrayList(
                    CursoDAO.recuperarCursosAlumno(infoAlumno.getMatricula()));
            comboCurso.setItems(cursoObservable);
            comboCurso.getSelectionModel().selectFirst();
        } catch (Exception ex){
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    @FXML
    /**
     * Realiza la reservación y la almacena en la base de datos
     * 
     */
    private void realizarReservacion() {
        Integer noActividad = tablaActividades.getSelectionModel().getSelectedItem().getNoActividad();
        Dialogo dialogo;
        try {
            ReservacionDAO.registrarReservacion(infoAlumno.getMatricula(), noActividad);
            llenarTabla();
        } catch (Exception ex) {
            ex.printStackTrace();
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
}
