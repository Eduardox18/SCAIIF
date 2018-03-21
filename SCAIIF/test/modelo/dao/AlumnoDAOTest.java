/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import servicios.pojos.Alumno;

/**
 *
 * @author andres
 */
public class AlumnoDAOTest {
    
    private Alumno alumnoUno;
    private Alumno alumnoDos;
    
    public AlumnoDAOTest() {
    }
    
    
    @Before
    public void setUp() {
        alumnoUno = new Alumno();
        alumnoUno.setMatricula("1243dafsdfa");
        alumnoUno.setNombre("Pedro");
        alumnoUno.setApPaterno("Pica");
        alumnoUno.setApMaterno("Piedra");
        alumnoUno.setCorreo("picapapas@gmail.com");
        
        alumnoDos = new Alumno();
        alumnoDos.setMatricula("S15011613");
        alumnoDos.setNombre("Pablo");
        alumnoDos.setApPaterno("Marmol");
        alumnoDos.setCorreo("pablo@gmail.com");
    }

    /**
     * Test of comprobarMatricula method, of class AlumnoDAO.
     */
    @Test
    public void testComprobarMatricula() {
        System.out.println("Prueba del método comprobarMatricula");
        boolean expResultFallo = false;
        boolean resultFallo = AlumnoDAO.comprobarMatricula(alumnoDos.getMatricula());
        boolean expResultExito = true;
        boolean resultExito = AlumnoDAO.comprobarMatricula(alumnoUno.getMatricula());
        assertEquals(expResultFallo, resultFallo);
        assertEquals(expResultExito, resultExito);
    }

    /**
     * Test of agregarAlumno method, of class AlumnoDAO.
     */
    @Test
    public void testAgregarAlumno() throws Exception {
        System.out.println("Prueba del método AgregarAlumno");
        Alumno prueba = null;
        boolean expResult = false;
        boolean result = AlumnoDAO.agregarAlumno(prueba);
        assertEquals(expResult, result);
    }

    /**
     * Test of recuperarAlumnos method, of class AlumnoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testRecuperarAlumnos() throws Exception {
        System.out.println("Prueba del método recuperarAlumnos");
        List<Alumno> result = AlumnoDAO.recuperarAlumnos(alumnoUno.getMatricula());
        List<Alumno> resultValido = AlumnoDAO.recuperarAlumnos(alumnoDos.getMatricula());
        assertTrue(result.isEmpty());
        assertFalse(resultValido.isEmpty());
    }
    
}
