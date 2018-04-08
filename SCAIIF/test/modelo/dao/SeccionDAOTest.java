/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.pojos.Seccion;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class SeccionDAOTest {

    /**
     * Test of recuperarSecciones method, of class SeccionDAO.
     */
    @Test
    public void testRecuperarSecciones() throws Exception {
        System.out.println("Test del método recuperarSecciones()");
        List<Seccion> result = SeccionDAO.recuperarSecciones();
        assertThat(result.get(0), instanceOf(Seccion.class));
        System.out.println("Éxito de correspondencia de clase");
    }
    
}
