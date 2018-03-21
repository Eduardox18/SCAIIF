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
import servicios.pojos.Actividad;

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
        System.out.println("recuperarHistorial");
        int noPersonal = 18109;
        String expNombre = "Conversacion Inglés I 1";
        String expFecha = "2018-03-02";
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
        int noPersonal = 18109;
        String expNombreActividad = "Conversación Francés I 1";
        int expNoActividad = 2;
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
}
