/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.pojos.Mes;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class MesDAOTest {

    /**
     * Test of consultarMeses method, of class MesDAO.
     */
    @Test
    public void testConsultarMeses() throws Exception {
        System.out.println("Test del método consultarMeses()");
        List<Mes> result = MesDAO.consultarMeses(2);
        assertThat(result.get(0), instanceOf(Mes.class));
        System.out.println("Éxito de correspondencia de clase");
    }

    /**
     * Test of recuperarMesesPendientes method, of class MesDAO.
     */
    @Test
    public void testRecuperarMesesPendientes() throws Exception {
        System.out.println("Test del método recuperarMesesPendientes()");
        List<Mes> result = MesDAO.recuperarMesesPendientes(2);
        assertThat(result.get(0), instanceOf(Mes.class));
        System.out.println("Éxito de correspondencia de clase");
    }
    
}
