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
import servicios.pojos.Modulo;

/**
 *
 * @author yamii
 */
public class ModuloDAOTest {

    public ModuloDAOTest() {
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
     * Test of recuperarModulos method, of class ModuloDAO.
     */
    @Test
    public void testRecuperarModulos() throws Exception {
        System.out.println("Test del método recuperarModulos()");
        Integer expNoModulo = 1;
        List<Modulo> result = ModuloDAO.recuperarModulos();

        assertEquals(expNoModulo, result.get(0).getNoModulo());
        System.out.println("Éxito de igualdad de noModulo.");
    }
}
