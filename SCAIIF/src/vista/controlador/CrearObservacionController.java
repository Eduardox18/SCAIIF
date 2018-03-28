package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import modelo.dao.ObservacionDAO;
import modelo.pojos.Observacion;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class CrearObservacionController implements Initializable {

    private String matricula;

    @FXML
    private JFXTextField tfAsunto;

    @FXML
    private JFXTextArea taComentario;

    @FXML
    private JFXButton btnAceptar;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tfAsunto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(tfAsunto.getText().length() >= 45){
                    tfAsunto.setText(tfAsunto.getText().substring(0, 45));
                }
            }
            
        });
        
        taComentario.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                    String newValue) {
                if(taComentario.getText().length() >= 150){
                    taComentario.setText(taComentario.getText().substring(0, 150));
                }
            }
            
        });
    }

    /**
     * Funciona como un método setter para pasar parámetros de otra ventana a esta.
     *
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
    public void funcionBoton() {
        Integer numeroPersonal = LoginController.returnNoPersonalLog();
        Observacion observacion = new Observacion();
        observacion.setAsunto(tfAsunto.getText());
        observacion.setComentario(taComentario.getText());
        observacion.setMatricula(matricula);
        observacion.setNoPersonal(numeroPersonal);
        Dialogo dialogo = null;
        try {
            ObservacionDAO.guardarObservacion(observacion);
            dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                    "La observación se ha registrado correctamente", "Éxito", ButtonType.OK);
            dialogo.show();
            cerrarVentana();
        } catch (Exception ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
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
        if (asunto != null && asunto.length() > 0 && comentario != null && comentario.length() > 0){
            btnAceptar.setDisable(false);
        } else {
            btnAceptar.setDisable(true);
        }
    }

    @FXML
    /**
     * *
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y
     * retorna a la ventana Comentario
     */
    public void botonCerrarVentana() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.CONFIRMATION,
                "¿Está seguro de que desea descartar la observación?", "Confirmación",
                ButtonType.OK, ButtonType.CANCEL);

        dialogo.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cerrarVentana();
            }
        });
    }

    /**
     * *
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y
     * retorna a la ventana Comentario
     */
    private void cerrarVentana() {
        try {
            URL comentarioAlumno = getClass().getResource("/vista/Observacion.fxml");
            AnchorPane paneComentario = FXMLLoader.load(comentarioAlumno);

            BorderPane border = LoginController.getPrincipal();
            border.setCenter(paneComentario);
        } catch (IOException ioEx) {
            //Diáogo error
        }
    }
}
