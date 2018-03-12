package controlador;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Induccion;
import servicios.pojos.Reservacion;

/**
 * FXML Controller class
 *
 * @author Hernández González Esmeralda Yamileth
 */
public class HistorialAsesoresController implements Initializable {
    
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();
    @FXML
    JFXTextField campoNombre = new JFXTextField();
    @FXML
    JFXTextField campoNoPersonal = new JFXTextField();
    @FXML
    TableView tablaActividades = new TableView();
    @FXML
    TableColumn colAsesoriasIntroductorias = new TableColumn();
    @FXML
    TableColumn colActividades = new TableColumn();

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
            ex.printStackTrace();
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
        
        
        
    }
    
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    @FXML
    private void consultarHistorialAsesores () {
        consultarAsesoriasIntroductorias();
        
    }
    
    private void consultarAsesoriasIntroductorias () {
        List<Induccion> historialAsesores = new ArrayList<>();
        List<Reservacion> historialReservaciones = new ArrayList<>();
        int noPersonal = Integer.parseInt(campoNoPersonal.getText());
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            historialAsesores = conn.selectList("Induccion.getHistorial", noPersonal);
            historialReservaciones = conn.selectList("Reservacion.getActividades", noPersonal);
        } catch (IOException ex) {
            System.out.println("Error al recuperar Actividades");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        ObservableList<Induccion> asesoriasIntro = FXCollections.observableArrayList();
        asesoriasIntro = FXCollections.observableArrayList(historialAsesores);
        colAsesoriasIntroductorias.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tablaActividades.setItems(asesoriasIntro);
        ObservableList<Reservacion> actividades = FXCollections.observableArrayList();
        actividades = FXCollections.observableArrayList(historialReservaciones);
        colActividades.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tablaActividades.setItems(actividades);
        
        
        
    }
    
    
        
    
}
