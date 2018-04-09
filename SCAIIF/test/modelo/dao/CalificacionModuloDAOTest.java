/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.pojos.CalificacionModulo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yamii
 */
public class CalificacionModuloDAOTest {

    public CalificacionModuloDAOTest() {
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
     * Test of registrarCalificacionModulo method, of class CalificacionModuloDAO.
     */
    @Test
    public void testRegistrarCalificacionModulo() throws Exception {
        System.out.println("Test del método registrarCalificacionModulo()");
        CalificacionModulo calificacion = null;
        boolean expResult = true;
        boolean resultado = CalificacionModuloDAO.registrarCalificacion(calificacion);
        System.out.println("Éxito de igualdades de resultado");
        assertEquals(expResult, resultado);
    }
}
