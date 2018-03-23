/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.pojos.Observacion;

/**
 *
 * @author andres
 */
public class ObservacionDAOTest {
    private Observacion observacion;
    
    public ObservacionDAOTest() {
    }
    
    @Before
    public void setUp() {
        Date fecha = new Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        observacion = new Observacion();
        observacion.setAsunto("Asunto de prueba" + fechaSQL.toString());
        observacion.setComentario("Comentario de pruaba" + fechaSQL.toString());
        observacion.setMatricula("S15011613");
        observacion.setNoPersonal(38192);
    }

    /**
     * Test of guardarObservacion method, of class ObservacionDAO.
     */
    @Test
    public void testGuardarObservacion() throws Exception {
        System.out.println("Prueba del método guardarObservacion");
        Observacion observacionNula = null;
        boolean resultNull = ObservacionDAO.guardarObservacion(observacionNula);
        boolean resulTrue = ObservacionDAO.guardarObservacion(observacion);
        assertFalse(resultNull);
        assertTrue(resulTrue);
    }
    
}
