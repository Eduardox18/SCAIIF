/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import modelo.dao.CursoDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import servicios.pojos.Curso;

/**
 *
 * @author yamii
 */
public class CursoDAOTest {

    public CursoDAOTest() {
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
     * Test of recuperarHistorial method, of class CursoDAO.
     */
    @Test
    public void testRecuperarCursos() throws Exception {
        System.out.println("Test del método recuperarCursos()");
        String matricula = "S15011601";
        String expNombreCurso = "Inglés I";
        List<Curso> result = CursoDAO.recuperarCursos(matricula);

        assertEquals(expNombreCurso, result.get(0).getNombreCurso());
        System.out.println("Éxito de igualdad de nombres.");
    }
}
