package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.dao.*;
import modelo.pojos.*;
import vista.Dialogo;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrarInformacionDeMesController implements Initializable {

    @FXML
    private JFXComboBox<Mes> comboMes;

    @FXML
    private JFXComboBox<Modulo> comboModulo;

    @FXML
    private JFXComboBox<Seccion> comboSeccion;

    @FXML
    private JFXComboBox<Conversacion> comboConversacion;

    @FXML
    private JFXComboBox<MaterialReportar> comboMaterial;

    @FXML
    private Spinner<Integer> spinnerInicio;

    @FXML
    private Spinner<Integer> spinnerFin;

    @FXML
    private JFXButton guardarButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactoryInicio =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1);
        SpinnerValueFactory<Integer> valueFactoryFin =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1);
        spinnerInicio.setValueFactory(valueFactoryInicio);
        spinnerFin.setValueFactory(valueFactoryFin);
        llenarComboMes();
        llenarComboConversacion();
        llenarComboModulo();
        llenarComboSeccion();
        llenarComboMaterial();
    }

    /**
     * Completa el comboBox de mes
     */
    private void llenarComboMes() {
        List<Mes> meses;

        try {
            meses = MesDAO.recuperarMesesPendientes(ResumenCalendarioCursoController.idCalendario);
            ObservableList<Mes> mesesObservable = FXCollections.observableArrayList(meses);
            comboMes.setItems(mesesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Completa el combobox de módulos
     */
    private void llenarComboModulo() {
        List<Modulo> modulos;

        try {
            modulos = ModuloDAO.recuperarTodosModulos();
            ObservableList<Modulo> modulosObservable = FXCollections.observableArrayList(modulos);
            comboModulo.setItems(modulosObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Completa el combobox de secciones
     */
    private void llenarComboSeccion() {
        List<Seccion> secciones;

        try {
            secciones = SeccionDAO.recuperarSecciones();
            ObservableList<Seccion> seccionesObservable = FXCollections.observableArrayList(secciones);
            comboSeccion.setItems(seccionesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Completa el combobox de conversaciones
     */
    private void llenarComboConversacion() {
        List<Conversacion> conversaciones;

        try {
            conversaciones = ConversacionDAO.recuperarConversaciones();
            ObservableList<Conversacion> conversacionesObservable = FXCollections.observableArrayList(conversaciones);
            comboConversacion.setItems(conversacionesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Completa el combobox de materiales
     */
    private void llenarComboMaterial() {
        List<MaterialReportar> materiales;

        try {
            materiales = MaterialReportarDAO.recuperarMateriales();
            ObservableList<MaterialReportar> materialesObservable = FXCollections.observableArrayList(materiales);
            comboMaterial.setItems(materialesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Activa el botón guardar cuando sean válidos los campos
     */
    @FXML
    public void activarBotonGuardar() {
        if (comboMes.getSelectionModel().getSelectedItem() != null &&
                comboMaterial.getSelectionModel().getSelectedItem() != null &&
                comboConversacion.getSelectionModel().getSelectedItem() != null &&
                comboSeccion.getSelectionModel().getSelectedItem() != null &&
                comboModulo.getSelectionModel().getSelectedItem() != null &&
                spinnerInicio.getValue() != null &&
                spinnerFin.getValue() != null) {
            guardarButton.setDisable(false);
        } else {
            guardarButton.setDisable(true);
        }
    }

    /**
     * Guarda la información de un nuevo mes de un calendario de curso
     */
    @FXML
    public void guardarInformacion() {
        boolean exito = false;
        ResumenMes resumen = new ResumenMes();
        resumen.setDiaInicio(spinnerInicio.getValue());
        resumen.setDiaFin(spinnerFin.getValue());
        resumen.setIdCalendario(ResumenCalendarioCursoController.idCalendario);
        resumen.setIdConversacion(comboConversacion.getSelectionModel().getSelectedItem().getIdConversacion());
        resumen.setIdMes(comboMes.getSelectionModel().getSelectedItem().getIdMes());
        resumen.setIdModulo(comboModulo.getSelectionModel().getSelectedItem().getIdModulo());
        resumen.setIdSeccion(comboSeccion.getSelectionModel().getSelectedItem().getIdSeccion());
        resumen.setNoMaterial(comboMaterial.getSelectionModel().getSelectedItem().getNoMaterial());

        try {
            exito = ResumenMesDAO.registrarResumenMes(resumen);
        } catch (Exception ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }

        if (exito) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                    "Información del mes guardada con éxito", "Guardar", ButtonType.OK);
            Optional<ButtonType> result = dialogo.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) guardarButton.getScene().getWindow();
                stage.close();
            }
        } else {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING,
                    "Ocurrió un problema al guardar la información del mes, vuelva a intentarlo",
                    "Problema al guardar Resumen del mes", ButtonType.OK);
            Optional<ButtonType> result = dialogo.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) guardarButton.getScene().getWindow();
                stage.close();
            }
        }
    }

}
