/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import servicios.pojos.Calificacion;

/**
 *
 * @author yamii
 */
public class CalificacionDAOTest {

    private Calificacion calificacion;

    public CalificacionDAOTest() {
    }

    @Before
    public void setUp() {
        calificacion = new Calificacion();
        calificacion.setMatricula("S15011601");
        calificacion.setCalificacion(9.2);
    }

    /**
     * Test of recuperarHistorial method, of class CalificacionDAO.
     */
    @Test
    public void testRegistrarCalificacion() throws Exception {
        System.out.println("Test del método registrarCalificacion()");
        Calificacion calificacionNula = null;
        boolean resultNull = CalificacionDAO.registrarCalificacion(calificacionNula);
        boolean resultTrue = CalificacionDAO.registrarCalificacion(calificacion);
        assertFalse(resultNull);
        assertTrue(resultTrue);
    }
}
