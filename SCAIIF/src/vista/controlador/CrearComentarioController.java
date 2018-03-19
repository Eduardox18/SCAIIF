package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Observacion;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class CrearComentarioController implements Initializable {
    
    private String matricula;
    
    @FXML
    JFXTextField tfAsunto = new JFXTextField();
    
    @FXML
    JFXTextArea  taComentario = new JFXTextArea();
    
    @FXML
    JFXButton btnAceptar = new JFXButton();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Funciona como un método setter para pasar parámetros de otra ventana a esta.
     * @param matricula La matrícula del alumno que se seleccionó en la ventana Comentario.
     */
    public void infoVentana(String matricula) {
        this.matricula = matricula;
    }
    
    @FXML
    /**
     * Se activa con el botón Aceptar, muestra diferentes diálogos dependiendo del resultado de la 
     * operación
     */
    public void funcionBoton () {
        Dialogo dialogo = null;
        if(guardarObservacion()){
            dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                        "EL comentario se ha registrado correctamente", "Éxito", ButtonType.OK);
                dialogo.show();
            cerrarVentana();
        } else {
            dialogo = new Dialogo(Alert.AlertType.ERROR, 
                        "", "Error", ButtonType.OK);
                dialogo.show();
        }
    }
    
    @FXML
    /**
     * Comprueba que los campos no se encuentren vacios. Activa y desactiva el botón Aceptar
     * dependiendo de si los campos están llenos o no
     */
    private void comprobarCampos() {
        String comentario = taComentario.getText();
        String asunto = tfAsunto.getText();
        if(asunto != null && asunto.length() > 0 && comentario != null && comentario.length() > 0) {
            btnAceptar.setDisable(false);
        } else {
            btnAceptar.setDisable(true);
        }
    }
    
    /**
     * Registra la observación en la base de datos
     * @return En caso de que la operación sea correcta devuelve true y false si ocurre un error en
     * la misma
     */
    private boolean guardarObservacion () {
        boolean resultado = true;
        Integer numeroPersonal = LoginController.returnNoPersonalLog();
        SqlSession conn = null;
        Observacion observacion = new Observacion();
        observacion.setAsunto(tfAsunto.getText());
        observacion.setComentario(taComentario.getText());
        observacion.setMatricula(matricula);
        observacion.setNoPersonal(numeroPersonal);
        
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Observacion.agregarObservacion", observacion);
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(CrearComentarioController.class.getName()).log(Level.SEVERE, null, ex);
            resultado = false;
        } finally {
            if(conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
    
    @FXML
    /***
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y 
     * retorna a la ventana Comentario
     */
    public void botonCerrarVentana () {
        Dialogo dialogo = new Dialogo(Alert.AlertType.CONFIRMATION, 
                "¿Está seguro de que desea descartar el comentario?", "Confirmación", 
                ButtonType.OK, ButtonType.CANCEL);
        
        dialogo.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cerrarVentana();
            }
        });
    }
    
    /***
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y 
     * retorna a la ventana Comentario
     */
    private void cerrarVentana () {
        try {
            URL comentarioAlumno = getClass().getResource("/vista/Comentario.fxml");
            AnchorPane paneComentario = FXMLLoader.load(comentarioAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneComentario);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
