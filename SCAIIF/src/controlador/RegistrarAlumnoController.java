/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Alumno;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class RegistrarAlumnoController implements Initializable {
    
    @FXML
    JFXTextField tfMatricula = new JFXTextField();
    
    @FXML
    JFXTextField tfNombre = new JFXTextField();
    
    @FXML
    JFXTextField tfApellidoPaterno = new JFXTextField();
    
    @FXML
    JFXTextField tfApellidoMaterno = new JFXTextField();
    
    @FXML
    JFXTextField tfCorreoElectronico = new JFXTextField();
    
    @FXML
    JFXCheckBox checkLenguaIndigena = new JFXCheckBox();
    
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();
    
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();

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
    public void accionBoton () {
        if (agregarAlumno()) {
            System.out.println("Todo salió bien");
        } else {
            System.out.println("Algo salió mal");
        }
    }
    
    private boolean agregarAlumno () {
        boolean resultado = true;
        SqlSession conn = null;
        Alumno alumno = new Alumno();
        alumno.setMatricula(tfMatricula.getText());
        alumno.setNombre(tfNombre.getText());
        alumno.setApPaterno(tfApellidoPaterno.getText());
        alumno.setApMaterno(tfApellidoMaterno.getText());
        alumno.setCorreo(tfCorreoElectronico.getText());
        alumno.setLenguaIndigena(determinarLengua());
        
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Alumno.agregarAlumno", alumno);
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(RegistrarAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
            resultado = false;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
    
    private boolean determinarLengua () {
        boolean resultado = false;
        if (checkLenguaIndigena.isSelected()) {
            resultado = true;
        }
        return resultado;
    }
    
}
