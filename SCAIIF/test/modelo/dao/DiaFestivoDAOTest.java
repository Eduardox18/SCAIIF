/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.pojos.DiaFestivo;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lalo
 */
public class DiaFestivoDAOTest {

    /**
     * Test of recuperarDiasFestivos method, of class DiaFestivoDAO.
     */
    @Test
    public void testRecuperarDiasFestivos() throws Exception {
        System.out.println("Test del método recuperarDiasFestivos()");
        Integer idPeriodo = 1;
        List<DiaFestivo> result = DiaFestivoDAO.consultarDiasFestivosPeriodo(idPeriodo);
        assertThat(result.get(0), instanceOf(DiaFestivo.class));
        System.out.println("Éxito de correspondencia de clase");
    }
}
