/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import modelo.pojos.Calendario;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class CalendarioDAOTest {
    
    /**
     * Test of recuperarCalendario method, of class CalendarioDAO.
     */
    @Test
    public void testRecuperarCalendario() throws Exception {
        System.out.println("Test del método recuperarCalendario()");
        int nrc = 28208;
        Calendario result = CalendarioDAO.recuperarCalendario(nrc);
        assertThat(result, instanceOf(Calendario.class));
        System.out.println("Éxito de correspondencia de clase");
    }

    /**
     * Test of actualizarCalendario method, of class CalendarioDAO.
     */
    @Test
    public void testActualizarCalendario() throws Exception {
        System.out.println("Test del método actualizarCalendario()");
        Calendario calendario = new Calendario();
        calendario.setFechaLimiteExamen(Date.valueOf("2018-04-24"));
        boolean expResult = true;
        boolean result = CalendarioDAO.actualizarCalendario(calendario);
        assertEquals(expResult, result);
        System.out.println("Éxito de actualización de calendario");
    }
    
}
