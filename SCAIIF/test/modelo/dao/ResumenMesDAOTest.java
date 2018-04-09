/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.pojos.ResumenMes;
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
public class ResumenMesDAOTest {

    /**
     * Test of registrarResumenMes method, of class ResumenMesDAO.
     */
    @Test
    public void testRegistrarResumenMes() throws Exception {
        System.out.println("Test del método registrarResumenMes()");
        ResumenMes resumen = new ResumenMes();
        resumen.setIdCalendario(1);
        resumen.setDiaInicio(1);
        resumen.setDiaFin(15);
        resumen.setIdConversacion(1);
        resumen.setIdMes(1);
        resumen.setIdModulo(1);
        resumen.setIdSeccion(1);
        resumen.setNoMaterial(1);
        boolean expResult = true;
        boolean result = ResumenMesDAO.registrarResumenMes(resumen);
        assertEquals(expResult, result);
        System.out.println("Éxito de registro de nuevo resumen de mes");
    }
    
}
