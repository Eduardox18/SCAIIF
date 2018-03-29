/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import modelo.pojos.Aviso;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andres
 */
public class AvisoDAOTest {
    
    Aviso aviso = new Aviso();
    Aviso avisoFalla = null;
    
    public AvisoDAOTest() {
    }
    
    @Before
    public void setUp() {
        aviso.setAsunto("Asunto de prueba");
        aviso.setMensaje("mensaje de prueba");
        aviso.setFechaCreacion(Date.valueOf(LocalDate.now()));
        aviso.setFechaLimite(Date.valueOf(LocalDate.now().plusDays(2)));
    }

    /**
     * Test of guardarAviso method, of class AvisoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGuardarAviso() throws Exception {
        System.out.println("Prueba del método guardarAviso");
        
        
        boolean result = AvisoDAO.guardarAviso(aviso);
        boolean resultFalse = AvisoDAO.guardarAviso(avisoFalla);
        assertTrue(result);
        assertFalse(resultFalse);
    }
    
}
