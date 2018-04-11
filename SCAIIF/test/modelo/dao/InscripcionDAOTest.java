/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.pojos.CalificacionModulo;
import modelo.pojos.Inscripcion;
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
public class InscripcionDAOTest {
    
    public InscripcionDAOTest() {
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
     * Pruebas del método para recuperar la calificación de un alumno
     * según la matrícula y el curso.
     * @throws Exception 
     */
    @Test
    public void testRecuperarCalFinal() throws Exception {
        System.out.println("Test del método recuperarCalFinal()");
        String matricula = "S15011601";
        Integer nrc = 28208;
        CalificacionModulo calificacion = new CalificacionModulo();
        calificacion.setMatricula(matricula);
        calificacion.setNrc(nrc);
        
        Inscripcion result = InscripcionDAO.recuperarCalFinal(calificacion);
        
        Double calExp = 9.666666666666666;
        Double calObtenida = result.getCalificacionFinal();

        System.out.println("Éxito de igualdad de calificaciones finales");
        assertEquals(calExp, calObtenida);
    }
}
