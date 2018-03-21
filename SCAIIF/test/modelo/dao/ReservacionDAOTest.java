/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import servicios.pojos.ListaAsistencia;

/**
 *
 * @author lalo
 */
public class ReservacionDAOTest {
    
    public ReservacionDAOTest() {
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
     * Test of recuperarAlumnosDeActividad method, of class ReservacionDAO.
     */
    @Test
    public void testRecuperarAlumnosDeActividad() throws Exception {
        System.out.println("Test del método recuperarAlumnosDeActividad()");
        int noActividad = 2;
        String expNombre = "Domínguez González Ricardo";
        List<ListaAsistencia> result = ReservacionDAO.recuperarAlumnosDeActividad(noActividad);
        
        assertEquals(expNombre, result.get(0).getNombre());
        System.out.println("Éxito de igualdad de nombre del alumno");
        assertEquals(noActividad, result.get(0).getNoActividad());
        System.out.println("Éxito de igualdad de número de actividad");
        assertThat(result.get(0), instanceOf(ListaAsistencia.class));
        System.out.println("Éxito de igualdad de clase retornada");
        assertEquals(result, result);
    }
    
}
