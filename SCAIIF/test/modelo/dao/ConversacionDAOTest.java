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
import modelo.pojos.Conversacion;

/**
 *
 * @author yamii
 */
public class ConversacionDAOTest {

    public ConversacionDAOTest() {
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
     * Test of recuperarConversaciones method, of class ConversacionDAO.
     */
    @Test
    public void testRecuperarConversaciones() throws Exception {
        System.out.println("Test del método recuperarConversaciones()");
        Integer expNoConv = 1;
        List<Conversacion> result = ConversacionDAO.recuperarConversaciones();

        assertEquals(expNoConv, result.get(0).getNoConversacion());
        System.out.println("Éxito de igualdad de noConversacion.");

    }
}
