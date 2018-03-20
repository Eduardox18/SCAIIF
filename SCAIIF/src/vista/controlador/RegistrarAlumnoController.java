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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Alumno;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class RegistrarAlumnoController implements Initializable {
    
    @FXML
    JFXTextField tfMatricula = new JFXTextField();
    
    @FXML
    JFXTextField tfNombre = new JFXTextField();
    
    @FXML
    JFXTextField tfApellidoPaterno = new JFXTextField();
    
    @FXML
    JFXTextField tfApellidoMaterno = new JFXTextField();
    
    @FXML
    JFXTextField tfCorreoElectronico = new JFXTextField();
    
    @FXML
    JFXCheckBox checkLenguaIndigena = new JFXCheckBox();
    
    @FXML
    JFXDrawer menuDrawer = new JFXDrawer();
    
    @FXML
    JFXHamburger menuIcon = new JFXHamburger();
    
    @FXML
    JFXButton btnRegistrar = new JFXButton();

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
        if (comprobarMatricula()) {
            if (agregarAlumno()) {
                dialogo = new Dialogo(Alert.AlertType.INFORMATION, 
                        "EL usuario se ha registrado correctamente", "Éxito", ButtonType.OK);
                dialogo.show();
                btnRegistrar.setDisable(true);
                limpiarCampos();
            } else {
                dialogo = new Dialogo(Alert.AlertType.ERROR, 
                        "Ha ocurrido un error al guardar el usuario", "Error", ButtonType.OK);
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
     * Recupera la información de los campos de texto para crear un objeto Alumno y posteriormente
     * registrarlo en la base de datos
     * @return Devuelve la variable resultado que puede ser verdadera en caso de que todo funcione 
     * correctamente o falso en caso de que ocurra algún error y no se registre al alumno.
     */
    private boolean agregarAlumno () {
        boolean resultado = true;
        SqlSession conn = null;
        Alumno alumno = new Alumno();
        alumno.setMatricula(tfMatricula.getText());
        alumno.setNombre(tfNombre.getText());
        alumno.setApPaterno(tfApellidoPaterno.getText());
        alumno.setApMaterno(determinarApMaterno());
        alumno.setCorreo(tfCorreoElectronico.getText());
        alumno.setLenguaIndigena(determinarLengua());
        
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Alumno.agregarAlumno", alumno);
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(RegistrarAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
            resultado = false;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
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
     * Concecta con la base de datos para comprobar que la matrícula que se intenta utilizar no esté
     * ocupara previamente
     * @return Devuelve true en caso de que la matrícula esté disponible y false si ya ha sido 
     * utilizada previamente.
     */
    private boolean comprobarMatricula () {
        boolean resultado = false;
        SqlSession conn = null;
        List<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            conn = MyBatisUtils.getSession();
            listaAlumnos = conn.selectList("Alumno.getAlumnos", tfMatricula.getText());
        } catch (IOException ex) {
            Logger.getLogger(RegistrarAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        if(listaAlumnos.isEmpty()){
            resultado = true;
        }
        
        return resultado;
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
}
