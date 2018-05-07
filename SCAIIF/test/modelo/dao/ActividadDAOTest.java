/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.text.SimpleDateFormat;
import java.util.List;
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
        String expNombre = "Conversación Inglés I 1";
        String expFecha = "2018-03-29";
        List<Actividad> result = ActividadDAO.recuperarHistorial(noPersonal);
        
        assertEquals(expNombre, result.get(0).getNombre());
        System.out.println("Éxito de igualdad de nombres.");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formatter.format(result.get(0).getFecha());
        assertEquals(expFecha, fechaTexto);
        System.out.println("Éxito de igualdad de fechas.");
        
    }

    /**
     * Test of recuperarActividadesAsesor method, of class ActividadDAO.
     * Prueba solo utilizable si se cumple con que existen actividades
     * en la fecha actual o posterior.
     
    @Test
    public void testRecuperarActividadesAsesor() throws Exception {
        System.out.println("Test del método recuperarActividadesAsesor()");
        Integer noPersonal = 18109;
        String expNombreActividad = "Conversación Inglés I 1";
        Integer expNoActividad = 1;
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
        Integer estado = 3;
        boolean result = ActividadDAO.actualizarEstado(noActividad, estado);
        ActividadDAO.actualizarEstado(noActividad, 1);
        assertTrue(result);
    }
    
    /**
     * Comprueba que se recuperó el nombre de la actividad
     */
    @Test
    public void testRecuperarNombreActividad() throws Exception {
        System.out.println(" prueba del método recuperarNombreActividad()");
        String fecha = "2018-03-02";
        String expResult = "Conversación Francés I 1";
        List<Actividad> result = ActividadDAO.recuperarNombreActividad(fecha);
        String resultado = result.get(0).getNombre();
        assertEquals(expResult, resultado);
        System.out.println("Éxito de igualdad de nombres.");  
    }

    /**
     * Test of recuperarActividadesDisponibles method, of class ActividadDAO.
     */
    @Test
    public void testRecuperarActividadesDisponibles() throws Exception {
        System.out.println("Prueba del recuperarActividadesDisponibles");
        String matricula = "S15011601";
        Integer nrc = 28192;
        List<ActividadAsesor> result = ActividadDAO.recuperarActividadesDisponibles(matricula, nrc);
        assertFalse(result.isEmpty());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of recuperarActividadesCalendario method, of class ActividadDAO.
     */
    @Test
    public void testRecuperarActividadesCalendario() throws Exception {
        System.out.println("Prueba del método recuperarActividadesCalendario");
        Integer nrc = 28192;
        List<ActividadAsesor> result = ActividadDAO.recuperarActividadesCalendario(nrc);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of detalleActividad method, of class ActividadDAO.
     */
    @Test
    public void testDetalleActividad() throws Exception {
        System.out.println("Prueba del método detalleActividad");
        Integer noActividad = 1;
        ActividadAsesor result = ActividadDAO.detalleActividad(noActividad);
        assertNotNull(result);
    }
    
    /**
     * Test of descripcionActividad method, of class ActividadDAO.
     */
    @Test
    public void testDescripcionActividad() throws Exception {
        System.out.println("Prueba del método descripcionActividad");
        String nombre = "Prueba Actividad";
        ActividadAsesor result = ActividadDAO.descripcionActividad(nombre);
        assertNotNull(result);
    }
    
    /**
     * Test of recuperarActividadesPorImpartir method, of class ActividadDAO.
     * Prueba solo utilizable si se cumple con que existen actividades
     * en la fecha actual o posterior.
     
    @Test
    public void testRecuperarActividadesPorImpartir() throws Exception {
        System.out.println("Test del método recuperarActividadesPorImpartir()");
        Integer noPersonal = 18109;
        String expNombreActividad = "Conversación Inglés I 1";
        Date fecha = Date.valueOf(LocalDate.now());
        
        List<Actividad> resultListaActividad = ActividadDAO.recuperarActividadesPorImpartir(
                 noPersonal, fecha);
        
        assertEquals(expNombreActividad, resultListaActividad.get(0).getNombre());
        System.out.println("Éxito de igualdad de nombre de la actividad");
        assertThat(resultListaActividad.get(0), instanceOf(Actividad.class));
        System.out.println("Éxito de igualdad de clase retornada");
    }
}
