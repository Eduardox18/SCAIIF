/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.pojos.Actividad;
import modelo.pojos.ActividadAsesor;

/**
 *
 * @author lalo
 */
public class ActividadDAOTest {
    
    public ActividadDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of recuperarHistorial method, of class ActividadDAO.
    */
     
    @Test
    public void testRecuperarHistorial() throws Exception {
        System.out.println("Test del método recuperarHistorial()");
        int noPersonal = 18109;
        String expNombre = "Conversacion Inglés I 1";
        Date expFecha = Date.valueOf("2018-03-02");
        List<Actividad> result = ActividadDAO.recuperarHistorial(noPersonal);
        
        assertEquals(expNombre, result.get(0).getNombre());
        System.out.println("Éxito de igualdad de nombres.");
        assertEquals(expFecha, result.get(0).getFecha());
        System.out.println("Éxito de igualdad de fechas.");
        
    }

    /**
     * Test of recuperarActividadesAsesor method, of class ActividadDAO.
     */
    @Test
    public void testRecuperarActividadesAsesor() throws Exception {
        System.out.println("Test del método recuperarActividadesAsesor()");
        Integer noPersonal = 18109;
        String expNombreActividad = "Conversación Francés I 1";
        Integer expNoActividad = 2;
        Date fecha = Date.valueOf(LocalDate.now());
        
        List<Actividad> resultListaActividad = ActividadDAO.recuperarActividadesAsesor(
                 noPersonal, fecha);
        
        assertEquals(expNoActividad, resultListaActividad.get(0).getNoActividad());
        System.out.println("Éxito de igualdad del número de la actividad");
        assertEquals(expNombreActividad, resultListaActividad.get(0).getNombre());
        System.out.println("Éxito de igualdad de nombre de la actividad");
        assertThat(resultListaActividad.get(0), instanceOf(Actividad.class));
        System.out.println("Éxito de igualdad de clase retornada");
    }
    
    /**
     * Comprueba que la lista no se encuentre vacia (hay actividades pendientes) y que en ninguna 
     * circunstancia la lista que se devuelve sea null
     * @throws java.lang.Exception
     */
    @Test
    public void testRecuperarActividadesPendientes() throws Exception {
        System.out.println(" prueba del método recuperarActividadesPendientes");
        List<ActividadAsesor> result = ActividadDAO.recuperarActividadesPendientes();
        assertNotNull("La lista se encuentra vacia. ERROR", result);
        assertFalse(result.isEmpty());
    }

    /**
     * Comprueba que la actividad sea cancelada correctamente
     */
    @Test
    public void testActualizarEstado() throws Exception {
        System.out.println("prueba del método cancelarActividad");
        Integer noActividad = 2;
        Integer estado = 2;
        boolean result = ActividadDAO.actualizarEstado(noActividad, estado);
        assertTrue(result);
        ActividadDAO.actualizarEstado(noActividad, 0);
    }
}
