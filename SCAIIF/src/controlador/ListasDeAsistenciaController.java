/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Actividad;
import servicios.pojos.ListaAsistencia;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class ListasDeAsistenciaController implements Initializable {

    @FXML
    private JFXComboBox<Actividad> comboActividades;
        
    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;
    
    @FXML
    private TableColumn colNombre;
    
    @FXML
    private TableColumn colMatricula;
    
    @FXML
    private TableColumn colAsistencia;
    
    @FXML
    private TableView tablaLista;
    
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
    
    @FXML
    public void mostrarVentanaImprimir() {
        Dialogo dialogo = null;
        dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                    "Se está imprimiendo la lista...", "Imprimiendo", 
                    ButtonType.OK);
                dialogo.show();
    }
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    private void llenarComboBox() {
        Dialogo dialogo = null;
        List<Actividad> actividadesProximas = new ArrayList<>();
        SqlSession conn = null;
        LoginController logIn = new LoginController();
        int noPersonal = logIn.returnNoPersonalLog();
        try {
            conn = MyBatisUtils.getSession();
            actividadesProximas = conn.selectList("Actividad.recuperarActividadesAsesor", 
                    noPersonal);
        } catch (IOException ioEx) {
            //Diálogo
            dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "No hay conexión con la base de datos", "Error", 
                    ButtonType.OK);
                dialogo.show();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }  
        
        ObservableList<Actividad> actividadesObservable = FXCollections.observableArrayList();
        actividadesObservable = FXCollections.observableArrayList(actividadesProximas);
        comboActividades.setItems(actividadesObservable);
    }
    
    @FXML
    public void llenarTabla() {
        Dialogo dialogo;
        List<ListaAsistencia> listaAlumnosReservacion = new ArrayList<>();
        SqlSession conn = null;
        int noActividad = comboActividades.getSelectionModel().getSelectedItem().getNoActividad();
        try {
            conn = MyBatisUtils.getSession();
            listaAlumnosReservacion = conn.selectList("Reservacion.alumnosDeActividad", 
                    noActividad);
        } catch (IOException ioEx) {
            dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "No hay conexión con la base de datos", "Error", 
                    ButtonType.OK);
                dialogo.show();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        ObservableList<ListaAsistencia> listaObservable = FXCollections.observableArrayList();
        listaObservable = FXCollections.observableArrayList(listaAlumnosReservacion);
        colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tablaLista.setItems(listaObservable);
    }
    
}
