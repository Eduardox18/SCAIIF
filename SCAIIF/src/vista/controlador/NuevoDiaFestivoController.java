package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import modelo.dao.DiaFestivoDAO;
import modelo.pojos.DiasFestivos;
import vista.Dialogo;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class NuevoDiaFestivoController implements Initializable {

    @FXML
    private JFXDatePicker nuevoFestivoDP;

    @FXML
    private JFXButton guardarButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    /**
     * Agrega un nuevo día festivo a la base de datos
     */
    @FXML
    public void agregarDiaFestivo() {
        DiasFestivos dia = new DiasFestivos();
        dia.setDiaFestivo(Date.valueOf(nuevoFestivoDP.getValue()));
        dia.setIdCalendario(ResumenCalendarioCursoController.idCalendario);


        try {
            if (!DiaFestivoDAO.comprobarDiaExistente(dia)) {
                DiaFestivoDAO.agregarDiaFestivo(dia);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        Stage stage = (Stage) guardarButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Habilita el botón guardar
     */
    @FXML
    public void habilitarGuardar() {
        if (nuevoFestivoDP.getValue() != null) {
            guardarButton.setDisable(false);
        } else {
            guardarButton.setDisable(true);
        }
    }

}

