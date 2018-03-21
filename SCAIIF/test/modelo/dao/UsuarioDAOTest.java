/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import servicios.pojos.Usuario;

/**
 *
 * @author lalo
 */
public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of recuperarAsesor method, of class UsuarioDAO.
     */
    @Test
    public void testRecuperarAsesor() throws Exception {
        System.out.println("Test del método recuperarNombreAsesor()");
        int noPersonal = 18109;
        int noPersonalFallo = 12345;
        String expResult = "Ángel Eduardo Domínguez Delgado";
        String exResultFallo = "El noPersonal que ingresó no corresponde a un Usuario.";
        
        Usuario result = UsuarioDAO.recuperarAsesor(noPersonal);
        assertEquals(expResult, result.getNombre() + result.getApPaterno() + result.getApMaterno());
        System.out.println("Éxito de igualdad de nombres.");
        
        Usuario resultFallo = UsuarioDAO.recuperarAsesor(noPersonalFallo);
        assertEquals(exResultFallo, resultFallo);
        System.out.println("Éxito de igualdad de mensajes de fallo.");
        
    }

    /**
     * Test of recuperarUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testRecuperarUsuario() throws Exception {
        System.out.println("Test del método recuperarUsuario()");
        int expNoPersonal = 18109;
        int expIdCargo = 2;
        Usuario result = UsuarioDAO.recuperarUsuario(expNoPersonal);
        
        assertEquals(expNoPersonal, result.getNoPersonal());
        System.out.println("Éxito de igualdad de número de personal");
        assertEquals(expIdCargo, result.getIdCargo());
        System.out.println("Éxito de igualdad de ID de cargo");
        assertThat(result, instanceOf(Usuario.class));
        System.out.println("Éxito de igualdad de clase retornada");
    }
}
