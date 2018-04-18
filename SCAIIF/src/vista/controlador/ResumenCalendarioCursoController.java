package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
import java.sql.Date;
import java.util.*;

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

    static int idCalendario;

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

    /**
     * Completa la información actual guardada del curso
     */
    private void completarInformacion() {
        Curso cursoSeleccionado = SeleccionDeCursoController.cursoSeleccionado;
        labelCurso.setText("Resumen del curso " + cursoSeleccionado.getNombreCurso());

        Calendario calendarioCurso;
        List<Mes> meses = new ArrayList<>();

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
            idCalendario = calendarioCurso.getIdCalendario();

            meses = MesDAO.consultarMeses(calendarioCurso.getIdCalendario());
            List<String> nombreMeses = new ArrayList<>();

            if (!meses.isEmpty()) {
                for (Mes mes: meses) {
                    nombreMeses.add(mes.getMes());
                }
                if (nombreMeses.size() == 1) {
                    labelMeses.setText("Mes registrado: " + meses.get(0).getMes());
                } else {
                    labelMeses.setText("Meses registrados: ");
                    for (String nombreMes: nombreMeses) {
                        labelMeses.setText(labelMeses.getText() + nombreMes + ", ");
                    }
                    labelMeses.setText(labelMeses.getText().substring(0, labelMeses.getText().length() - 2));
                }
            } else {
                labelMeses.setText("No hay meses registrados");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Habilita el botón de guardar
     */
    @FXML
    public void habilitarGuardar() {
        if (limiteDP.getValue() != null && inicioVacacionesDP.getValue() != null &&
                finVacacionesDP.getValue() != null) {
            guardarButton.setDisable(false);
        } else {
            guardarButton.setDisable(true);
        }
    }

    /**
     * Guarda el resumen de un curso
     */
    @FXML
    public void guardarResumen() {
        boolean exito = false;
        Calendario calendario = new Calendario();
        calendario.setFechaLimiteExamen(Date.valueOf(limiteDP.getValue()));
        calendario.setInicioVacaciones(Date.valueOf(inicioVacacionesDP.getValue()));
        calendario.setFinVacaciones(Date.valueOf(finVacacionesDP.getValue()));
        calendario.setIdCalendario(idCalendario);

        if (comprobarFechas()) {
            try {
                exito = CalendarioDAO.actualizarCalendario(calendario);
            } catch (Exception e) {
                e.printStackTrace();
                Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                dialogo.show();
            }

            if (exito) {
                Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                        "Información del curso guardada con éxito", "Guardado", ButtonType.OK);
                Optional<ButtonType> result = dialogo.showAndWait();
                if (result.get() == ButtonType.OK) {
                    completarInformacion();
                }
            } else {
                Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                        "Ocurrió un problema al guardar la información del calendario, vuelta a intentarlo",
                        "Problema al guardar Calendario", ButtonType.OK);
                dialogo.show();
            }
        } else {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                    "Una o más fechas no están dentro del periodo", "Fechas inválidas",
                    ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana DiasFestivos
     */
    @FXML
    public void irDiasFestivos() {
        try {
            Stage stageSecundario = new Stage();
            BorderPane paneSecundario = new BorderPane();
            URL paneFestivoURL = getClass().getResource("/vista/RegistroDiasFestivos.fxml");
            AnchorPane paneFestivo = FXMLLoader.load(paneFestivoURL);

            paneSecundario.setCenter(paneFestivo);
            Scene sceneFestivo = new Scene(paneSecundario, 500, 300);
            stageSecundario.setScene(sceneFestivo);
            stageSecundario.show();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Abre la ventana para registrar la información de un mes
     */
    @FXML
    public void registrarNuevoMes() {
        try {
            Stage stageSecundario = new Stage();
            BorderPane paneSecundario = new BorderPane();
            URL paneMesURL = getClass().getResource("/vista/RegistrarInformacionDeMes.fxml");
            AnchorPane paneMes = FXMLLoader.load(paneMesURL);

            paneSecundario.setCenter(paneMes);
            Scene sceneMes = new Scene(paneSecundario, 400, 400);
            stageSecundario.setScene(sceneMes);
            stageSecundario.showAndWait();
            completarInformacion();
        } catch (IOException ioEx) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Verifica si las fechas son válidas (que estén dentro del periodo)
     * @return
     */
    private boolean comprobarFechas() {
        Date inicio = SeleccionDeCursoController.periodoSeleccionado.getFechaInicio();
        Date fin = SeleccionDeCursoController.periodoSeleccionado.getFechaFin();
        Date limite = Date.valueOf(limiteDP.getValue());
        Date inicioVacaciones = Date.valueOf(inicioVacacionesDP.getValue());
        Date finVacaciones = Date.valueOf(finVacacionesDP.getValue());

        boolean flag = false;

        if ((limite.after(inicio) && limite.before(fin)) &&
                (inicioVacaciones.after(inicio) && inicioVacaciones.before(fin)) &&
                (finVacaciones.after(inicio) && finVacaciones.before(fin))) {
            flag = true;
        }
        return flag;
    }
}

