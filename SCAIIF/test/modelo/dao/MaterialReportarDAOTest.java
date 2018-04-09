/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.pojos.MaterialReportar;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class MaterialReportarDAOTest {

    /**
     * Test of recuperarMateriales method, of class MaterialReportarDAO.
     */
    @Test
    public void testRecuperarMateriales() throws Exception {
        System.out.println("Test del método recuperarMateriales()");
        List<MaterialReportar> result = MaterialReportarDAO.recuperarMateriales();
        assertThat(result.get(0), instanceOf(MaterialReportar.class));
        System.out.println("Éxito de correspondencia de clase");
    }
    
}
