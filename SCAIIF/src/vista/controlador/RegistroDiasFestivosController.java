package vista.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroDiasFestivosController implements Initializable{

    @FXML
    private TableView<?> festivosTable;

    @FXML
    private JFXButton agregarButton;

    @FXML
    private JFXButton eliminarButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}