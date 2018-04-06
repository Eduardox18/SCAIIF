package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.CalificacionModuloDAO;
import modelo.dao.ConversacionDAO;
import modelo.dao.CursoDAO;
import modelo.dao.ModuloDAO;
import modelo.pojos.CalificacionModulo;
import modelo.pojos.Conversacion;
import modelo.pojos.Curso;
import modelo.pojos.Modulo;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author Hernández González Esmeralda Yamileth
 */
public class RegistrarCalificacionesController implements Initializable {

    @FXML
    private JFXTextField TFMatricula;
    @FXML
    private JFXButton BTGuardar;
    @FXML
    private JFXButton BTCancelar;
    @FXML
    private JFXComboBox<String> CBCurso;
    @FXML
    private JFXComboBox<Integer> CBModulo;
    @FXML
    private JFXComboBox<Integer> CBConv;
    @FXML
    private JFXTextField TFCalificacion;
    @FXML
    private JFXButton BTBuscar;
    @FXML
    private Label lbl_calificacion;
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();

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
        BTBuscar.setDisable(true);
        BTGuardar.setDisable(true);
        BTCancelar.setDisable(true);
    }

    /**
     *
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del mnú
     */
    @FXML
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }

    /**
     * Comprueba el tamaño y contenido de la matrícula.
     */
    @FXML
    private void buscarDatosAlumno() {
        String matricula = TFMatricula.getText();
        if (matricula != null && matricula.length() == 9) {
            BTBuscar.setDisable(false);
            llenarComboBox();
        } else {
            BTBuscar.setDisable(true);
        }
    }

    /**
     * Habilita los botones de guardado y cancelar siempre y cuando el Asesor ya haya ingresado la
     * califcación del alumno.
     */
    @FXML
    private void habilitarBotones() {
        String matricula = TFMatricula.getText();
        String calificacion = TFCalificacion.getText();
        Integer conv = CBConv.getSelectionModel().getSelectedItem();
        Integer modulo = CBModulo.getSelectionModel().getSelectedItem();
        String cursos = CBCurso.getSelectionModel().getSelectedItem();
        Dialogo dialogo = null;

        if (matricula != null && matricula.length() > 0
            && cursos != null && cursos.length() > 0
            && conv != null && modulo != null
            && calificacion != null && calificacion.length() > 0) {
            BTGuardar.setDisable(false);
            BTCancelar.setDisable(false);
        } else {
            BTGuardar.setDisable(true);
            BTCancelar.setDisable(true);
        }
    }

    /**
     * Recupera todos los cursos registrados a los que está inscrito el alumno, las conversaciones y
     * modulos existentes.
     */
    private void llenarComboBox() {
        String matricula = TFMatricula.getText();
        List<Curso> cursos = null;
        List<Conversacion> convs = null;
        List<Modulo> modulos = null;
        Dialogo dialogo = null;
        try {
            cursos = CursoDAO.recuperarCursos(matricula);
            List<String> nombreCursos = new ArrayList<>();
            for (Curso curso : cursos) {
                nombreCursos.add(curso.getNombreCurso());
            }
            ObservableList<String> cursosObservable = FXCollections.observableArrayList(nombreCursos);
            CBCurso.setItems(cursosObservable);

            convs = ConversacionDAO.recuperarConversaciones();
            List<Integer> noConv = new ArrayList<>();
            for (Conversacion conversacion : convs) {
                noConv.add(conversacion.getNoConversacion());
            }
            ObservableList<Integer> conversacionesObservable = FXCollections.observableArrayList(noConv);
            CBConv.setItems(conversacionesObservable);

            modulos = ModuloDAO.recuperarModulos(matricula);
            List<Integer> noModulo = new ArrayList<>();
            for (Modulo modulo : modulos) {
                noModulo.add(modulo.getIdModulo());
            }
            ObservableList<Integer> modulosObservable = FXCollections.observableArrayList(noModulo);
            CBModulo.setItems(modulosObservable);
        } catch (Exception ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Comprueba si se registro o no la calificación del alumno, mandando una ventana emergente para
     * cualquiera de los casos.
     */
    @FXML
    private void comprobarRegistro() {
        Dialogo dialogo = null;
        String matricula = TFMatricula.getText();

        try {
            double alumnoCalificacion = Double.parseDouble(TFCalificacion.getText());
            Integer idModulo = CBModulo.getSelectionModel().getSelectedItem();

            if (idModulo == 3) {
                if (alumnoCalificacion > 0 && alumnoCalificacion <= 10) {
                    CalificacionModulo calificacion = new CalificacionModulo();
                    calificacion.setMatricula(matricula);
                    calificacion.setIdModulo(idModulo);
                    calificacion.setCalificacion(alumnoCalificacion);
                    try {
                        CalificacionModuloDAO.registrarCalificacion(calificacion);
                        dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                            "Calificación registrada correctamente.", "Éxito", ButtonType.OK);
                        dialogo.show();
                        lbl_calificacion.setText(String.valueOf(alumnoCalificacion));
                        BTGuardar.setDisable(true);
                        BTCancelar.setDisable(true);
                    } catch (Exception ex) {
                        dialogo = new Dialogo(Alert.AlertType.ERROR,
                            "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                        dialogo.show();
                    }

                } else {
                    dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Ingresa una calificación mayor a 0 y menor a 10.", "Error", ButtonType.OK);
                    dialogo.show();
                    TFCalificacion.setText("");
                    BTGuardar.setDisable(true);
                    BTCancelar.setDisable(true);
                }
            } else {
                if (alumnoCalificacion > 0 && alumnoCalificacion <= 10) {
                    CalificacionModulo calificacion = new CalificacionModulo();
                    calificacion.setMatricula(matricula);
                    calificacion.setIdModulo(idModulo);
                    calificacion.setCalificacion(alumnoCalificacion);
                    try {
                        CalificacionModuloDAO.registrarCalificacion(calificacion);
                        dialogo = new Dialogo(Alert.AlertType.INFORMATION,
                            "Calificación registrada correctamente.", "Éxito", ButtonType.OK);
                        dialogo.show();
                        BTGuardar.setDisable(true);
                        BTCancelar.setDisable(true);
                        limpiarCampos();

                    } catch (Exception ex) {
                        dialogo = new Dialogo(Alert.AlertType.ERROR,
                            "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                        dialogo.show();
                    }

                } else {
                    dialogo = new Dialogo(Alert.AlertType.ERROR,
                        "Ingresa una calificación mayor a 0 y menor a 10.", "Error", ButtonType.OK);
                    dialogo.show();
                    TFCalificacion.setText("");
                    BTGuardar.setDisable(true);
                    BTCancelar.setDisable(true);
                }

            }
        } catch (NumberFormatException ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Ingresa solo números.", "Error", ButtonType.OK);
            dialogo.show();
            TFCalificacion.setText("");
        }
    }

    /**
     * Limpia los campos.
     */
    public void limpiarCampos() {
        TFMatricula.setText("");
        CBCurso.setValue("");
        CBModulo.setValue(0);
        CBConv.setValue(0);
        TFCalificacion.setText("");
    }

    /**
     *
     * Cierra la ventana actual cuando se cancela la operación o una vez que se haya concluido y
     * limpia los campos de búsqueda.
     */
    @FXML
    public void botonCerrarVentana() {
        Dialogo dialogo = new Dialogo(Alert.AlertType.CONFIRMATION,
            "¿Está seguro de que desea cancelar el registro de calificación?", "Confirmación",
            ButtonType.OK, ButtonType.CANCEL);

        dialogo.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                limpiarCampos();
            }
        });
    }
}
