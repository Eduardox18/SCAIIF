package vista.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.dao.DiaFestivoDAO;
import modelo.pojos.DiasFestivos;
import vista.Dialogo;

import java.io.IOException;
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

    @FXML
    private TableColumn colDia;

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
            colDia.setCellValueFactory(new PropertyValueFactory<>("diaFestivo"));
            festivosTable.setItems(diasObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para agregar un nuevo día festivo
     */
    @FXML
    public void agregarFestivo() {
        try {
            Stage stageTercero = new Stage();
            BorderPane paneTercero = new BorderPane();
            URL paneFestivoURL = getClass().getResource("/vista/NuevoDiaFestivo.fxml");
            AnchorPane paneFestivo = FXMLLoader.load(paneFestivoURL);

            paneTercero.setCenter(paneFestivo);
            Scene sceneFestivo = new Scene(paneTercero, 300, 100);
            stageTercero.setScene(sceneFestivo);
            stageTercero.showAndWait();
            llenarTablaDias();
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Elimina un día festivo de la base de datos y actualiza la tabla
     */
    @FXML
    public void eliminarFestivo() {
        boolean exito = false;

        try {
            exito = DiaFestivoDAO.borrarDiaFestivo(
                    festivosTable.getSelectionModel().getSelectedItem().getIdDiasFestivos());
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }

        if (exito) {
            llenarTablaDias();
        }
    }

    /**
     * Habilita el botón eliminar
     */
    @FXML
    public void habilitarEliminar() {
        if (festivosTable.getSelectionModel().getSelectedItem() != null) {
            eliminarButton.setDisable(false);
        } else {
            eliminarButton.setDisable(true);
        }
    }
}