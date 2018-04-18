package modelo.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.pojos.Curso;

/**
 *
 * @author yamii
 */
public class CursoDAOTest {

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
