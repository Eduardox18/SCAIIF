/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import modelo.pojos.Induccion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class InduccionDAOTest {
    
    public InduccionDAOTest() {
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
     * Test of registrarInduccion method, of class InduccionDAO.
     */
    @Test
    public void testRegistrarInduccion() throws Exception {
        System.out.println("Test del método registrarInduccion()");
        Induccion induccion = new Induccion();
        induccion.setMatricula("S15011607");
        induccion.setNrc(28208);
        induccion.setCursoInduccion(Date.valueOf("2018-03-20"));
        induccion.setPrimeraAsesoria(Date.valueOf("2018-03-23"));
        induccion.setNoPersonal(18109);
        
        boolean expResult = true;
        boolean result = InduccionDAO.registrarInduccion(induccion);
        assertEquals(expResult, result);
        System.out.println("Éxito de registro de inducción");
    }

    /**
     * Test of comprobarInduccion method, of class InduccionDAO.
     */
    @Test
    public void testComprobarInduccion() throws Exception {
        System.out.println("Test del método comprobarInduccion()");
        Induccion induccion = new Induccion();
        induccion.setMatricula("s15011601");
        induccion.setNrc(28208);
        boolean expResult = true;
        boolean result = InduccionDAO.comprobarInduccion(induccion);
        assertEquals(expResult, result);
        System.out.println("Éxito de existencia de curso de inducción");
    }
    
}
