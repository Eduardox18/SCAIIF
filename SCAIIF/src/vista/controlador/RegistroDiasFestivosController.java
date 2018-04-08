package vista.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import modelo.dao.DiaFestivoDAO;
import modelo.pojos.DiasFestivos;
import vista.Dialogo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroDiasFestivosController implements Initializable{

    @FXML
    private TableView<DiasFestivos> festivosTable;

    @FXML
    private JFXButton agregarButton;

    @FXML
    private JFXButton eliminarButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTablaDias();
    }

    /**
     * Completa la tabla con los días festivos
     */
    private void llenarTablaDias() {
        List<DiasFestivos> dias;

        try {
            dias = DiaFestivoDAO.recuperarDiasFestivos(ResumenCalendarioCursoController.idCalendario);
            ObservableList<DiasFestivos> diasObservable = FXCollections.observableArrayList(dias);
            festivosTable.setItems(diasObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }
}