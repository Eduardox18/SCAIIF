package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class SeleccionDeCursoController {

    @FXML
    private JFXComboBox<?> comboPeriodo;

    @FXML
    private TableView<?> tablaCursos;

    @FXML
    private JFXButton botonContinuar;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private JFXHamburger menuIcon;
}
