package vista.controlador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.ActividadDAO;
import modelo.dao.CursoDAO;
import modelo.dao.PeriodoDAO;
import modelo.pojos.ActividadAsesor;
import modelo.pojos.Curso;
import modelo.pojos.Periodo;
import vista.Dialogo;

public class CalendarioActividadesController implements Initializable {
    
     @FXML
    private TableView<ActividadAsesor> tablaActividades;

    @FXML
    private TableColumn columnaFecha;

    @FXML
    private TableColumn columnaNombre;

    @FXML
    private JFXComboBox<Periodo> comboPeriodo;

    @FXML
    private JFXComboBox<Curso> comboCurso;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private JFXHamburger menuIcon;

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
        
        tablaActividades.setPlaceholder(new Label("No hay actividades disponibles"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        
        llenarComboPeriodo();
        llenarComboCurso();
        llenarTabla();
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
    
    private void llenarComboPeriodo(){
        ObservableList<Periodo> listaPeriodos;
        try {
            listaPeriodos = FXCollections.observableArrayList(PeriodoDAO.recuperarPeriodos());
            comboPeriodo.setItems(listaPeriodos);
            comboPeriodo.getSelectionModel().selectFirst();
        } catch(Exception ex){
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    @FXML
    private void llenarComboCurso(){
        ObservableList<Curso> listaCursos;
        try {
            listaCursos = FXCollections.observableArrayList(CursoDAO.recuperarCursosDePeriodo(
                    comboPeriodo.getSelectionModel().getSelectedItem().getIdPeriodo()));
            comboCurso.setItems(listaCursos);
            comboCurso.getSelectionModel().selectFirst();
        } catch(Exception ex){
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
    
    @FXML
    private void llenarTabla(){
        if(comboCurso.getSelectionModel().getSelectedItem() != null) {
            ObservableList<ActividadAsesor> listaActividades;
            try{
                listaActividades = FXCollections.observableArrayList(
                        ActividadDAO.recuperarActividadesCalendario(
                                comboCurso.getSelectionModel().getSelectedItem().getNrc()));
                tablaActividades.setItems(listaActividades);
            } catch (Exception ex) {
                ex.printStackTrace();
                Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR, 
                        "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                dialogo.show();
             }
        }
    }
    
}
