package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.CalendarioDAO;
import modelo.dao.MesDAO;
import modelo.dao.ResumenMesDAO;
import modelo.pojos.Calendario;
import modelo.pojos.Curso;
import modelo.pojos.Mes;
import modelo.pojos.ResumenMes;
import vista.Dialogo;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ResumenCalendarioCursoController implements Initializable {

    @FXML
    private Label labelCurso;

    @FXML
    private JFXDatePicker limiteDP;

    @FXML
    private JFXDatePicker inicioVacacionesDP;

    @FXML
    private JFXDatePicker finVacacionesDP;

    @FXML
    private JFXButton festivosButton;

    @FXML
    private JFXButton guardarButton;

    @FXML
    private Label labelMeses;

    @FXML
    private JFXButton nuevoButton;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private JFXHamburger menuIcon;

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

        completarInformacion();
    }

    /**
     * Muestra el ícono del menú en la ventana
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    public void completarInformacion() {
        Curso cursoSeleccionado = SeleccionDeCursoController.cursoSeleccionado;
        labelCurso.setText("Resumen del curso " + cursoSeleccionado.getNombreCurso());

        Calendario calendarioCurso;
        List<Mes> meses;

        try {
            calendarioCurso = CalendarioDAO.recuperarCalendario(cursoSeleccionado.getNrc());
            if (calendarioCurso.getInicioVacaciones() != null) {
                inicioVacacionesDP.setValue(calendarioCurso.getInicioVacaciones().toLocalDate());
            }
            if (calendarioCurso.getFinVacaciones() != null) {
                finVacacionesDP.setValue(calendarioCurso.getFinVacaciones().toLocalDate());
            }
            if (calendarioCurso.getFechaLimiteExamen() != null) {
                limiteDP.setValue(calendarioCurso.getFechaLimiteExamen().toLocalDate());
            }
            meses = MesDAO.consultarMeses(calendarioCurso.getIdCalendario());

            labelMeses.setText("Meses registrados: ");
        } catch (Exception e) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

}

