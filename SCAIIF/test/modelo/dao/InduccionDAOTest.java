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
import modelo.pojos.Induccion;

/**
 *
 * @author yamii
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
     * Test of consultarActividades method, of class InduccionDAO.
     */
    @Test
    public void testConsultarActividades() throws Exception {
        System.out.println("Test del método consultarActividades()");
        int noPersonal = 18109;
        String expMatricula = "S15011601";
        Date expFecha = Date.valueOf("2018-03-19");

        List<Induccion> resultListaInduccion = InduccionDAO.recuperarHistorialAsesores(noPersonal);

        assertEquals(expMatricula, resultListaInduccion.get(0).getMatricula());
        System.out.println("Éxito de igualdad de matricula");
        assertEquals(expFecha, resultListaInduccion.get(0).getFecha());
        System.out.println("Éxito de igualdad de fechas");
    }
}
