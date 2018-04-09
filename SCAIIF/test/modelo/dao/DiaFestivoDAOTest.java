/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import modelo.pojos.DiasFestivos;
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
        Integer idCalendario = 2;
        List<DiasFestivos> result = DiaFestivoDAO.recuperarDiasFestivos(idCalendario);
        assertThat(result.get(0), instanceOf(DiasFestivos.class));
        System.out.println("Éxito de correspondencia de clase");
    }

    /**
     * Test of agregarDiaFestivo method, of class DiaFestivoDAO.
     */
    @Test
    public void testAgregarDiaFestivo() throws Exception {
        System.out.println("Test del método agregarDiaFestivo()");
        DiasFestivos diaFestivo = new DiasFestivos();
        diaFestivo.setDiaFestivo(Date.valueOf("2018-02-15"));
        diaFestivo.setIdCalendario(2);
        boolean expResult = true;
        boolean result = DiaFestivoDAO.agregarDiaFestivo(diaFestivo);
        assertEquals(expResult, result);
        System.out.println("Éxito de agregación de día festivo");
    }

    /**
     * Test of borrarDiaFestivo method, of class DiaFestivoDAO.
     */
    @Test
    public void testBorrarDiaFestivo() throws Exception {
        System.out.println("Test del método borrarDiaFestivo()");
        int idDiasFestivos = 4;
        boolean expResult = true;
        boolean result = DiaFestivoDAO.borrarDiaFestivo(idDiasFestivos);
        assertEquals(expResult, result);
        System.out.println("Éxito de eliminación de día festivo");
    }

    /**
     * Test of comprobarDiaExistente method, of class DiaFestivoDAO.
     */
    @Test
    public void testComprobarDiaExistente() throws Exception {
        System.out.println("Test del método comprobarDiaExistente()");
        DiasFestivos dia = new DiasFestivos();
        dia.setIdCalendario(2);
        dia.setDiaFestivo(Date.valueOf("2018-12-31"));
        boolean expResult = false;
        boolean result = DiaFestivoDAO.comprobarDiaExistente(dia);
        assertEquals(expResult, result);
        System.out.println("Éxito de comprobación de día festivo");
    }
    
}
