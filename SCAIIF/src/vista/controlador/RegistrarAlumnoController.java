package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.dao.CursoDAO;
import modelo.dao.InscripcionDAO;
import modelo.dao.PeriodoDAO;
import modelo.pojos.Alumno;
import modelo.pojos.Curso;
import modelo.pojos.Periodo;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class RegistrarAlumnoController implements Initializable {
    
    @FXML
    private JFXTextField tfMatricula;
    
    @FXML
    private JFXTextField tfNombre;
    
    @FXML
    private JFXTextField tfApellidoPaterno;
    
    @FXML
    private JFXTextField tfApellidoMaterno;
    
    @FXML
    private JFXTextField tfCorreoElectronico;
    
    @FXML
    private JFXCheckBox checkLenguaIndigena;
    
    @FXML
    private JFXDrawer menuDrawer;
    
    @FXML
    private JFXHamburger menuIcon;
    
    @FXML
    private JFXButton btnRegistrar;
    
    @FXML
    private JFXComboBox<Periodo> comboPeriodo;
    
    @FXML
    private JFXComboBox<Curso> comboCurso;
    
    @FXML
    private JFXTextField tfBusqueda;
    
    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private Label labelNombre;
    
    @FXML
    private Label labelCorreo;
    
    @FXML
    private JFXButton btnInscribir;
    
    @FXML
    private JFXButton btnLimpiar;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
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
            ex.printStackTrace();
        }
        
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
        
        tfNombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(newValue.matches("[0-9]")){
                    tfNombre.setText(oldValue);
                }
            }
        });
        
        tfApellidoPaterno.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                    String newValue) {
                if(tfApellidoPaterno.getText().length() >= 45){
                    tfApellidoPaterno.setText(tfApellidoPaterno.getText().substring(0, 45));
                }
            }
        });
        
        tfApellidoMaterno.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(tfApellidoMaterno.getText().length() >= 45){
                    tfApellidoMaterno.setText(tfApellidoMaterno.getText().substring(0, 45));
                }
            }
        });
        
        tfCorreoElectronico.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(tfCorreoElectronico.getText().length() >= 45){
                    tfCorreoElectronico.setText(tfCorreoElectronico.getText().substring(0, 45));
                }
            }
        });
        
        tfMatricula.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(tfMatricula.getText().length() >= 9){
                    tfMatricula.setText(tfMatricula.getText().substring(0, 9));
                }
            }
            
        });
        
        tfBusqueda.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(tfBusqueda.getText().length() >= 9){
                    tfBusqueda.setText(tfBusqueda.getText().substring(0, 9));
                    btnBuscar.setDisable(false);
                } else {
                    btnBuscar.setDisable(true);
                    btnInscribir.setDisable(true);
                }
            }
        });
        
        labelCorreo.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                if(!labelCorreo.getText().isEmpty() && !labelNombre.getText().isEmpty()){
                    btnInscribir.setDisable(false);
                    btnLimpiar.setDisable(false);
                    llenarComboCurso();
                } else {
                    btnInscribir.setDisable(true);
                    btnLimpiar.setDisable(true);
                }
            }
        });
        
        llenarComboPeriodo();
        llenarComboCurso();
    }

    @FXML
    /**
     * Metodo que se encarga de mostrar el ícono del menú cada vez que se sale del mnú
     */
    public void mostrarIcono() {
        if (!menuDrawer.isShown()) {
            menuIcon.setVisible(true);
            menuDrawer.setDisable(true);
        }
    }
    
    @FXML
    /**
     * Método de acción para el botón, se realiza el registro del alumno si no se encuentra
     * ninguna matrícula igual registrada
     */
    public void accionBoton () {
        Dialogo dialogo = null;
        
        boolean matriculaValida;
        try {
            matriculaValida = AlumnoDAO.comprobarMatricula(tfMatricula.getText());
        } catch (Exception ex) {
            dialogo = new Dialogo(Alert.AlertType.ERROR, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
            ex.printStackTrace();
            return;
        }
        if (matriculaValida) {
            if(validarCampos(tfNombre.getText()) && validarCampos(tfApellidoMaterno.getText()) 
                    && validarCampos(tfApellidoPaterno.getText())) {
                if(validarCorreo(tfCorreoElectronico.getText())){
                    Alumno alumno = new Alumno();
                    alumno.setMatricula(tfMatricula.getText());
                    alumno.setNombre(tfNombre.getText());
                    alumno.setApPaterno(tfApellidoPaterno.getText());
                    alumno.setApMaterno(determinarApMaterno());
                    alumno.setCorreo(tfCorreoElectronico.getText());
                    alumno.setLenguaIndigena(determinarLengua());
                    alumno.setVigente(1);
                    try {
                        AlumnoDAO.agregarAlumno(alumno);
                        dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                                "El alumno se ha registrado correctamente", "Éxito", ButtonType.OK);
                        dialogo.show();
                        btnRegistrar.setDisable(true);
                        limpiarRegistrar();
                    } catch (Exception ex) {
                        dialogo = new Dialogo(Alert.AlertType.ERROR, 
                                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                        dialogo.show();
                        ex.printStackTrace();
                    }
                
                } else {
                    dialogo = new Dialogo(Alert.AlertType.WARNING, 
                        "La dirección de correo no es válida", "Dirección de correo inválida", 
                        ButtonType.OK);
                    dialogo.show();
                }
            } else {
                dialogo = new Dialogo(Alert.AlertType.WARNING, 
                    "Por favor verifique que no haya caracteres inválidos", "Texto inválido", 
                    ButtonType.OK);
                dialogo.show();
            }
        } else {
            dialogo = new Dialogo(Alert.AlertType.WARNING, 
                    "La matrícula que trata de ingresar ya existe", "Matrícula repetida", 
                    ButtonType.OK);
            dialogo.show();
        }
    }
    
    /**
     * Recupera el valor del checkBox Lengua indígena, y devuelve un valor según su estado.
     * @return En caso de que esté marcado devuelve true, de lo contrario, devuelve false.
     */
    private boolean determinarLengua () {
        boolean resultado = false;
        if (checkLenguaIndigena.isSelected()) {
            resultado = true;
        }
        return resultado;
    }
    
    /**
     * Recupera el texto del campo Apellido materno, y devuelve un valor según su contenido.
     * @return El contenido del campo en caso de que lo tenga o null si este está vacio.
     */
    private String determinarApMaterno () {
        String apMaterno = tfApellidoMaterno.getText();
        if (apMaterno.length() == 0 && apMaterno == null){
            apMaterno = null;
        }
        return apMaterno;
    }
    
    @FXML
    /**
     * Comprueba que los campos obligatorios (matricula, nombre, apellido paterno y correo) no se 
     * encuentre vacios para posteriormente activar el botón. Cuando uno de estos campos se 
     * encuentra vacio, desactiva el botón.
     */
    private void activarBoton () {
        String matricula = tfMatricula.getText();
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String correo = tfCorreoElectronico.getText();
        
        if (matricula != null && matricula.length() > 0 && nombre != null && nombre.length() > 0
                && apellidoPaterno != null && apellidoPaterno.length() > 0 && correo != null 
                && correo.length() > 0) {
            btnRegistrar.setDisable(false);
        } else {
            btnRegistrar.setDisable(true);
        }
    }
    
    /**
     * Limpia todos los campos y desmarca el checkbox 
     */
    public void limpiarRegistrar() {
        tfMatricula.setText("");
        tfNombre.setText("");
        tfApellidoMaterno.setText("");
        tfApellidoPaterno.setText("");
        tfCorreoElectronico.setText("");
        checkLenguaIndigena.setSelected(false);
    }
    
    /**
     * Valida que no se tengan caracteres inválidos en el texto
     * @param texto El texto que se desea evaluar
     * @return True en caso de que el texto no contenga carcteres inválidos y false si los contiene
     */
    public boolean validarCampos(String texto){
        boolean resultado = false;
        if(texto.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+") || texto.equals("")){
            resultado = true;
        }
        return resultado;
    }
    
    /**
     * Valida que sea una dirección de correo válida
     * @param correo La dirección de correo a evaluar
     * @return True si se tarta de una dirección válida, de lo contrario retorna false
     */
    public boolean validarCorreo(String correo) {
        boolean resultado = false;
        if(correo.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")){
            resultado = true;
        }
        return resultado;
    }
    
    private void llenarComboPeriodo() {
        ObservableList<Periodo> periodosObservables = null;
        try {
            periodosObservables = FXCollections.observableArrayList(PeriodoDAO.recuperarPeriodos());
        } catch (IOException ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING, 
                    "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        comboPeriodo.setItems(periodosObservables);
        comboPeriodo.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void llenarComboCurso() {
        ObservableList<Curso> cursosObservables = null;
        String matricula = (tfBusqueda.getText().isEmpty()) ? "" : tfBusqueda.getText();
        
        try {
            cursosObservables = FXCollections.observableArrayList(CursoDAO.getCursosNoInscritos(
                    comboPeriodo.getSelectionModel().getSelectedItem().getIdPeriodo(), matricula));
            
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING, 
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        comboCurso.setItems(cursosObservables);
        comboCurso.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void buscarAlumno(){
        Alumno alumno = null ;
        try {
            alumno = AlumnoDAO.recuperarInfoAlumno(tfBusqueda.getText());
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING, 
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        
        if(alumno != null){
            labelNombre.setText(alumno.getNombreCompleto());
            labelCorreo.setText(alumno.getCorreo());
        } else {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING, 
                "La matrícula que ingresó no está registrada, puede registrar un nuevo alumno en la"
                + " pestaña \"Registrar alumno\"", "Matrícula no registrada", 
                ButtonType.OK);
            dialogo.show();
            tfBusqueda.setText("");
            labelCorreo.setText("");
            labelNombre.setText("");
        }
    }
    
    @FXML
    private void inscribirAlumno() {
        String matricula = tfBusqueda.getText();
        Integer nrc = comboCurso.getSelectionModel().getSelectedItem().getNrc();
        Dialogo exito = new Dialogo(Alert.AlertType.INFORMATION, 
                "La inscripción se realizó correctamente", "Inscripción exitosa", ButtonType.OK);

        
        try {
            InscripcionDAO.inscribirAlumno(matricula, nrc);
            exito.show();
            limpiarInscribir();
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.WARNING, 
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
        
        llenarComboCurso();
    }
    
    @FXML
    private void limpiarInscribir() {
        comboPeriodo.getSelectionModel().selectFirst();
        comboCurso.getSelectionModel().selectFirst();
        tfBusqueda.setText("");
        labelNombre.setText("");
        labelCorreo.setText("");
    }
    
}
