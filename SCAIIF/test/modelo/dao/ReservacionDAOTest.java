/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
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
        System.out.println("recuperarAlumnosDeActividad");
        int noActividad = 0;
        List<ListaAsistencia> expResult = null;
        List<ListaAsistencia> result = ReservacionDAO.recuperarAlumnosDeActividad(noActividad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
