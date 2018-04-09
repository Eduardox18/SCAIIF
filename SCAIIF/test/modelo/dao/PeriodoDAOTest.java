/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.pojos.Periodo;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class PeriodoDAOTest {
    
    /**
     * Test of recuperarPeriodos method, of class PeriodoDAO.
     */
    @Test
    public void testRecuperarPeriodos() throws Exception {
        System.out.println("Test del método recuperarPeriodos()");
        List<Periodo> result = PeriodoDAO.recuperarPeriodos();
        assertThat(result.get(0), instanceOf(Periodo.class));
        System.out.println("Éxito de correspondencia de clase.");
    }
}
