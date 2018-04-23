package modelo.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import modelo.pojos.Induccion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esmeralda
 */
public class InduccionDAOTest {
    
    public InduccionDAOTest() {
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
     * Test of registrarInduccion method, of class InduccionDAO.
     */
    @Test
    public void testRegistrarInduccion() throws Exception {
        System.out.println("Test del método registrarInduccion()");
        Induccion induccion = new Induccion();
        induccion.setMatricula("S15011607");
        induccion.setNrc(28208);
        induccion.setCursoInduccion(Date.valueOf("2018-03-20"));
        induccion.setPrimeraAsesoria(Date.valueOf("2018-03-23"));
        induccion.setNoPersonal(18109);
        
        boolean expResult = true;
        boolean result = InduccionDAO.registrarInduccion(induccion);
        assertEquals(expResult, result);
        System.out.println("Éxito de registro de inducción");
    }

    /**
     * Test of comprobarInduccion method, of class InduccionDAO.
     */
    @Test
    public void testComprobarInduccion() throws Exception {
        System.out.println("Test del método comprobarInduccion()");
        Induccion induccion = new Induccion();
        induccion.setMatricula("s15011601");
        induccion.setNrc(28208);
        boolean expResult = true;
        boolean result = InduccionDAO.comprobarInduccion(induccion);
        assertEquals(expResult, result);
        System.out.println("Éxito de existencia de curso de inducción");
    }
    
    /**
     * Test of recuperarAlumnos method, of class InduccionDAO.
     * @throws Exception 
     */
    @Test
    public void testRecuperarInfoAlumno() throws Exception {
        System.out.println("Test del método recuperarInfoAlumno()");
        String matricula = "S15011601";
        Integer nrc = 28208;
        String expFechaAsesoria = "2018-03-19";
        String expFechaInduccion = "2018-03-19";
        List<Induccion> result = InduccionDAO.recuperarAlumnos(matricula);
        
        assertEquals(nrc, result.get(0).getNrc());
        System.out.println("Éxito de igualdad de nrc de curso.");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaAs = formatter.format(result.get(0).getPrimeraAsesoria());
        assertEquals(expFechaAsesoria, fechaAs);
        System.out.println("Éxito de igualdad de fechas de primera asesoría.");
        String fechaInd = formatter.format(result.get(0).getCursoInduccion());
        assertEquals(expFechaInduccion, fechaInd);
        System.out.println("Éxito de igualdad de fechas de curso de inducción");
    }
    
}
