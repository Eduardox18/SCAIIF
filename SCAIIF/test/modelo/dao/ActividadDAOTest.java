/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.util.List;
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
     
    @Test
    public void testRecuperarHistorial() throws Exception {
        System.out.println("recuperarHistorial");
        int noPersonal = 0;
        List<Actividad> expResult = null;
        List<Actividad> result = ActividadDAO.recuperarHistorial(noPersonal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

* */
    /**
     * Test of recuperarActividadesAsesor method, of class ActividadDAO.
     */
    @Test
    public void testRecuperarActividadesAsesor() throws Exception {
        System.out.println("recuperarActividadesAsesor");
        int noPersonal = 0;
        Date fecha = null;
        List<Actividad> expResult = null;
        List<Actividad> result = ActividadDAO.recuperarActividadesAsesor(noPersonal, fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
