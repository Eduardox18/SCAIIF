/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andres
 */
public class CorreoElectronicoTest {
    
    public CorreoElectronicoTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of enviarCorreo method, of class CorreoElectronico.
     * @throws java.lang.Exception
     */
    @Test
    public void testEnviarCorreo() throws Exception {
        System.out.println("Prueba de enviarCorreo");
        List<String> destinatarios = new ArrayList<>();
        destinatarios.add("andresdglez@gmail.com");
        String asunto = "Prueba del módulo";
        String motivo = "Este correo es una prueba del módulo para enviar correo electrónico";
        boolean resultIndividual = CorreoElectronico.enviarCorreo(destinatarios, asunto, motivo);
        assertTrue("ERROR, no se ha enviado el mensje correctamente", resultIndividual);
        destinatarios.add("andresdglez@outlook.com");
        destinatarios.add("zs15011613@estudiantes.uv.mx");
        boolean resultVarios = CorreoElectronico.enviarCorreo(destinatarios, asunto, motivo);
        assertTrue("ERROR, no se ha enviado el mensje correctamente", resultVarios);
    }
}
