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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import static org.eclipse.persistence.jpa.jpql.JPAVersion.value;
import servicios.pojos.Actividad;
import servicios.pojos.Reservacion;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class ListasDeAsistenciaController implements Initializable {

    @FXML
    private JFXComboBox<String> comboActividades;
        
    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private JFXDrawer menuDrawer;
    
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
        llenarTabla();
    }    
    
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    private void llenarComboBox() {
        List<Actividad> actividadesProximas = new ArrayList<>();
        SqlSession conn = null;
        LoginController logIn = new LoginController();
        int noPersonal = logIn.returnNoPersonalLog();
        try {
            conn = MyBatisUtils.getSession();
            actividadesProximas = conn.selectList("Actividad.recuperarActividadesAsesor", 
                    noPersonal);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        List<String> nombreActividades = new ArrayList<>();
        for (Actividad actividad : actividadesProximas) {
            nombreActividades.add(actividad.getNombre());
        }
        
        ObservableList<String> actividadesObservable = FXCollections.observableArrayList();
        actividadesObservable = FXCollections.observableArrayList(nombreActividades);
        comboActividades.setItems(actividadesObservable);
    }
    
    @FXML
    public void llenarTabla() {
        List<Reservacion> listaAlumnosReservacion = new ArrayList<>();
        SqlSession conn = null;
        int noActividad = 1;
        try {
            conn = MyBatisUtils.getSession();
            listaAlumnosReservacion = conn.selectList("Reservacion.alumnosDeActividad", 
                    noActividad);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        for (Reservacion reser: listaAlumnosReservacion) {
            System.out.println(reser);
        }
    }
    
}
