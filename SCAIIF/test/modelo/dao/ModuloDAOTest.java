/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.pojos.Modulo;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 *
 * @author yamii
 */
public class ModuloDAOTest {

    /**
     * Test of recuperarModulos method, of class ModuloDAO.
     */
    @Test
    public void testRecuperarModulos() throws Exception {
        System.out.println("Test del método recuperarModulos()");
        Integer expNoModulo = 6;
        String matricula = "S15011602";
        List<Modulo> result = ModuloDAO.recuperarModulos(matricula);

        assertEquals(expNoModulo, result.get(0).getNoModulo());
        System.out.println("Éxito de igualdad de noModulo.");
    }

    /**
     * Test of recuperarTodosModulos method, of class ModuloDAO.
     */
    @Test
    public void testRecuperarTodosModulos() throws Exception {
        System.out.println("Test del método recuperarTodosModulos()");
        List<Modulo> result = ModuloDAO.recuperarTodosModulos();
        assertThat(result.get(0), instanceOf(Modulo.class));
        System.out.println("Éxito de correspondencia de clase");
    }
}
