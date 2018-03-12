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
import servicios.pojos.Usuario;

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
    TableView tablaInduccion = new TableView();
    @FXML
    TableColumn colMatricula = new TableColumn();
    @FXML
    TableColumn colFecha = new TableColumn();
    @FXML
    TableView tablaActividades = new TableView();
    @FXML
    TableColumn colMatActividad = new TableColumn();
    @FXML
    TableColumn colFechaAct = new TableColumn();
    
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
     * Llamada al método consultar Actividades asignada al botón buscar, así como
     * la recuperación del nombre del asesor que coincide con el noPersonal
     * ingresado por el Coordinador. 
     */
    @FXML
    private void consultarHistorialAsesores () {
        recuperarNombreAsesor();
        consultarActividades();
    }
    
    private void recuperarNombreAsesor () {
        int noPersonal = Integer.parseInt(campoNoPersonal.getText());
        Usuario nombreAsesor = null;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            nombreAsesor = conn.selectOne("Usuario.getNombreUsuario", noPersonal);
        } catch (IOException ex) {
            System.out.println("Error al Nombre");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        campoNombre.setText(nombreAsesor.getNombre() + " " + nombreAsesor.getApPaterno() +" " + nombreAsesor.getApMaterno());
    }
    
    
    /**
     * Método que realiza la búsqueda en la base de datos de las matrículas
     * y fechas de las asesorías introductorias impartidas por el asesor ingresado
     * por el coordinador, así como de las actividaes impartidas o que impartirá.
     */
    private void consultarActividades () {
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
        
        ObservableList<Induccion> actividadesIntro = FXCollections.observableArrayList();
        actividadesIntro = FXCollections.observableArrayList(historialAsesores);
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tablaInduccion.setItems(actividadesIntro);
        
        ObservableList<Reservacion> actividades = FXCollections.observableArrayList();
        actividades = FXCollections.observableArrayList(historialReservaciones);
        colMatActividad.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colFechaAct.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tablaActividades.setItems(actividades);        
    }  
}
