package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;

public class RegistrarInformacionDeMesController {

    @FXML
    private JFXComboBox<?> comboMes;

    @FXML
    private JFXComboBox<?> comboModulo;

    @FXML
    private JFXComboBox<?> comboSeccion;

    @FXML
    private JFXComboBox<?> comboConversacion;

    @FXML
    private JFXComboBox<?> comboMaterial;

    @FXML
    private Spinner<?> spinnerInicio;

    @FXML
    private Spinner<?> spinnerFin;

    @FXML
    private JFXButton guardarButton;

}
