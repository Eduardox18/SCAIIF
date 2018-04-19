package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.dao.AlumnoDAO;
import modelo.pojos.Alumno;
import vista.Dialogo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EdicionAlumnoController implements Initializable {

    /**
     * Prueba del módulo
     */
    Alumno alumnoTest = new Alumno("S15011624", "Ámbar", "Espinosa", null,
            "ambar@gmail.com", true, 1);
    /**
     *
     */

    @FXML
    private Label labelMatricula;

    @FXML
    private JFXTextField nombreTF;

    @FXML
    private JFXTextField apPaternoTF;

    @FXML
    private JFXTextField apMaternoTF;

    @FXML
    private JFXTextField emailTF;

    @FXML
    private JFXButton botonGuardar;

    @FXML
    private JFXCheckBox lenguaCB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelMatricula.setText("Matrícula: " + alumnoTest.getMatricula());
        nombreTF.setText(alumnoTest.getNombre());
        apPaternoTF.setText(alumnoTest.getApPaterno());
        if (alumnoTest.getApMaterno() != null) {
            apMaternoTF.setText(alumnoTest.getApMaterno());
        }
        emailTF.setText(alumnoTest.getCorreo());
        if (alumnoTest.isLenguaIndigena()) {
            lenguaCB.setSelected(true);
        }
    }

    @FXML
    public void actualizarAlumno() {
        Alumno alumno = new Alumno();
        alumno.setMatricula(alumnoTest.getMatricula());
        alumno.setNombre(nombreTF.getText());
        alumno.setApPaterno(apPaternoTF.getText());
        if (!apMaternoTF.getText().isEmpty()) {
            alumno.setApMaterno(apMaternoTF.getText());
        }
        alumno.setCorreo(emailTF.getText());
        alumno.setLenguaIndigena(lenguaCB.isSelected());

        try {
            boolean exito = AlumnoDAO.actualizarAlumno(alumno);

            if (exito) {
                Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                        "Información actualizada con éxito", "Éxito", ButtonType.OK);
                Optional<ButtonType> result = dialogo.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Stage stage = (Stage) botonGuardar.getScene().getWindow();
                    stage.close();
                }
            } else {
                Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                        "No se pudo completar la operación, intente más tarde",
                        "Problema edición", ButtonType.OK);
                Optional<ButtonType> result = dialogo.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Stage stage = (Stage) botonGuardar.getScene().getWindow();
                    stage.close();
                }
            }
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            Optional<ButtonType> result = dialogo.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) botonGuardar.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    public void habilitarGuardar() {
        if (!nombreTF.getText().isEmpty() && !apPaternoTF.getText().isEmpty() && !emailTF.getText().isEmpty()) {
            botonGuardar.setDisable(false);
        } else {
            botonGuardar.setDisable(true);
        }
    }
}
