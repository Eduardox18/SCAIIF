/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import servicios.pojos.Usuario;

/**
 *
 * @author lalo
 */
public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
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
     * Test of recuperarAsesor method, of class UsuarioDAO.
     */
    @Test
    public void testRecuperarAsesor() throws Exception {
        System.out.println("recuperarAsesor");
        int noPersonal = 0;
        Usuario expResult = null;
        Usuario result = UsuarioDAO.recuperarAsesor(noPersonal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recuperarUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testRecuperarUsuario() throws Exception {
        System.out.println("recuperarUsuario");
        int noPersonal = 0;
        Usuario expResult = null;
        Usuario result = UsuarioDAO.recuperarUsuario(noPersonal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
