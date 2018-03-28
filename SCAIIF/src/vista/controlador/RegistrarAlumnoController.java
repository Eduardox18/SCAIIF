package vista.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.dao.AlumnoDAO;
import modelo.pojos.Alumno;
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
                    try {
                        AlumnoDAO.agregarAlumno(alumno);
                        dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                                "El alumno se ha registrado correctamente", "Éxito", ButtonType.OK);
                        dialogo.show();
                        btnRegistrar.setDisable(true);
                        limpiarCampos();
                    } catch (Exception ex) {
                        dialogo = new Dialogo(Alert.AlertType.ERROR, 
                                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                        dialogo.show();
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
    public void limpiarCampos() {
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
    
}
