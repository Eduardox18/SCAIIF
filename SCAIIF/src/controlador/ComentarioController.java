package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Alumno;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ComentarioController implements Initializable {
    
    @FXML
    JFXButton botonRegistrar = new JFXButton();
    
    @FXML
    JFXTextField campoMatricula = new JFXTextField();
    
    @FXML
    TableView tablaALumnos = new TableView();
    
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();
    
    @FXML
    TableColumn colNombre = new TableColumn();
    
    @FXML
    TableColumn colMatricula = new TableColumn();
    
    @FXML
    TableColumn colEmail = new TableColumn();
    
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
        
        campoMatricula.textProperty().addListener((observable, oldValue, newValue) -> {
            llenarTabla(newValue);
        });
        
        botonRegistrar.setDisable(true);
        tablaALumnos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            botonRegistrar.setDisable(false);
}       );
    } 
    
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    private void llenarTabla(String nombreAlumno) {
        List<Alumno> alumnos = new ArrayList<>();
        SqlSession conn = null;
        String matricula = campoMatricula.getText();
        try {
            conn = MyBatisUtils.getSession();
            alumnos = conn.selectList("Alumno.getAlumnos", matricula);
        } catch(IOException ioEx) {
            System.out.println("marrano");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        ObservableList<Alumno> alumnosObservable = FXCollections.observableArrayList();
        alumnosObservable = FXCollections.observableArrayList(alumnos);
        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tablaALumnos.setItems(alumnosObservable);
    }
    
    @FXML
    private void lanzarEditorComentario () {
        try{
            
        } catch(IOException ioEx){
            ioEx.printStackTrace();
        }
    }
    
}
