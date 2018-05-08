package vista.controlador;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import modelo.dao.ActividadDAO;
import modelo.dao.UsuarioDAO;
import modelo.pojos.ActividadAsesor;
import modelo.pojos.Usuario;
import vista.Dialogo;

/**
 * FXML Controller class
 *
 * @author yamii
 */
public class ActividadesPorImpartirController implements Initializable {

    @FXML
    private JFXHamburger menuIcon;
    @FXML
    private JFXDrawer menuDrawer;
    @FXML
    private TableView tablaActividades;
    @FXML
    private TableColumn colNombreAct;
    @FXML
    private TableColumn colFecha;
    @FXML
    private Label etiquetaNombreAsesor;

    /**
     * Initializes the controller class.
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
        }
        menuIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            menuDrawer.open();
            menuDrawer.setDisable(false);
            menuIcon.setVisible(false);
        });
        recuperarNombreAsesor();
        llenarTabla();

        tablaActividades.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2
                    && tablaActividades.getSelectionModel().getSelectedItem() != null) {
                    try {
                        String nombreAct = tablaActividades.getSelectionModel().getSelectedItem().toString();
                        ActividadAsesor actividad = ActividadDAO.descripcionActividad(nombreAct);

                        Dialog detalle = new Dialog();
                        detalle.setHeaderText("Descripción Actividad");
                        detalle.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

                        GridPane grid = new GridPane();
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(20, 150, 10, 10));

                        grid.add(new Label("Nombre:"), 0, 0);
                        grid.add(new Label(actividad.getNombre()), 1, 0);
                        grid.add(new Label("Fecha:"), 0, 1);
                        grid.add(new Label(actividad.getFecha().toString()), 1, 1);
                        grid.add(new Label("Hora inicio:"), 0, 2);
                        grid.add(new Label(actividad.getHoraInicio().toString()), 1, 2);
                        grid.add(new Label("Hora fin:"), 0, 3);
                        grid.add(new Label(actividad.getHoraFin().toString()), 1, 3);
                        grid.add(new Label("Cupo:"), 0, 4);
                        grid.add(new Label(actividad.getCupo().toString()), 1, 4);

                        detalle.getDialogPane().setContent(grid);
                        detalle.initStyle(StageStyle.UNDECORATED);
                        detalle.show();

                    } catch (Exception ex) {
                        Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                            "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
                        dialogo.show();
                        ex.printStackTrace();
                    }
                }
            }

        });
    }

    /**
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
     * Recupera las actividades que esta por impartir el Asesor de la base de datos y las muestra en
     * la tabla.
     *
     */
    private void llenarTabla() {
        ObservableList<ActividadAsesor> actividadesObservable;
        Integer noPersonal = LoginController.returnNoPersonalLog();
        Date fechaDate = Date.valueOf(LocalDate.now());
        try {
            actividadesObservable = FXCollections.observableArrayList(ActividadDAO.
                recuperarActividadesPorImpartir(noPersonal, fechaDate));
            colNombreAct.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tablaActividades.setItems(actividadesObservable);
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "Servidor no disponible, intente más tarde", "Error", ButtonType.OK);
            dialogo.show();
        }
    }

    /**
     * Recupera el nombre del Asesor del que se está consultado la información.
     */
    private void recuperarNombreAsesor() {
        Usuario nombreAsesor;
        try {
            nombreAsesor = UsuarioDAO.recuperarAsesor(LoginController.returnNoPersonalLog());
            etiquetaNombreAsesor.setText(nombreAsesor.getNombre() + " " + nombreAsesor.getApPaterno() + " "
                + nombreAsesor.getApMaterno());
        } catch (Exception ex) {
            Dialogo dialogo = new Dialogo(Alert.AlertType.ERROR,
                "El nombre de Asesor no se pudo recuperar.", "Error",
                ButtonType.OK);
            dialogo.show();
        }
    }
}
