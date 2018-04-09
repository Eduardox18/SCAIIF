package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class BajaAlumnoController implements Initializable {

    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();

    @FXML
    JFXButton botonBuscar;
    @FXML
    JFXButton botonBaja;
    @FXML
    JFXButton botonCancelar;
    @FXML
    Label lblCorreo;
    @FXML
    Label lblNombre;
    @FXML
    JFXTextField campoMatricula;

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

        campoMatricula.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                if (campoMatricula.getText().length() >= 9) {
                    campoMatricula.setText(campoMatricula.getText().substring(0, 9));
                }
            }

        });
        botonCancelar.setDisable(true);
        botonBaja.setDisable(true);
        botonBuscar.setDisable(true);
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
     * Vuelve la vigencia del alumno a falso, no elimina el registro, solo lo da de baja
     * (inhabilita).
     */
    @FXML
    public void darBajaAlumno() {
        Dialogo dialogo = null;
        String matricula = campoMatricula.getText();
        Alumno alumno = new Alumno();
        alumno.setMatricula(campoMatricula.getText());
        alumno.setVigente(0);

        try {
            AlumnoDAO.bajaAlumno(alumno);
            dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                "El alumno se ha dado de baja correctamente", "Éxito", ButtonType.OK);
            dialogo.show();
            botonCancelar.setDisable(true);
            botonBaja.setDisable(true);
            botonBuscar.setDisable(false);
            limpiarCampos();
        } catch (Exception ioEx) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Error al dar de baja al alumno.", "Error", ButtonType.OK);
            dialogo.show();
        }

    }

    /**
     * Muestra el nombre completo del alumno y su correo electrónico para corroborar que sea el
     * usuario que desea dar de baja.
     */
    @FXML
    private void recuperarInformacionAlumno() {
        Alumno alumno;
        try {
            alumno = AlumnoDAO.recuperarInfoAlumno(campoMatricula.getText());
            lblNombre.setText(alumno.getNombre() + " " + alumno.getApPaterno() + " " + alumno.getApMaterno());
            lblCorreo.setText(alumno.getCorreo());
            botonBaja.setDisable(false);
            botonCancelar.setDisable(false);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "La matrícula que ingresa no es válida o no existe", "Error",
                ButtonType.OK);
            dialogo.show();
            botonBaja.setDisable(true);
            botonCancelar.setDisable(true);
            limpiarCampos();
        }
    }

    /**
     * Limpia los campos.
     */
    public void limpiarCampos() {
        campoMatricula.setText("");
        lblNombre.setText("");
        lblCorreo.setText("");
    }

    /**
     * Comprueba el tamaño y contenido de la matrícula.
     */
    @FXML
    private void buscarDatosAlumno() {
        String matricula = campoMatricula.getText();
        if (matricula != null && matricula.length() == 9) {
            botonBuscar.setDisable(false);
        } else {
            botonBuscar.setDisable(true);
        }
    }    
    
    /**
     *
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y
     * limpia los campos de búsqueda.
     */
    @FXML
    public void cancelar() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.CONFIRMATION,
            "¿Está seguro de que desea cancelar la baja del alumno?", "Confirmación",
            ButtonType.OK, ButtonType.CANCEL);

        dialogo.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                limpiarCampos();
            }
        });
    }
}
