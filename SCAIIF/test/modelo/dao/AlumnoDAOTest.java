/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.pojos.Alumno;
import modelo.pojos.Reservacion;

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
        boolean expResultExito = true;
        try {
            boolean resultFallo = AlumnoDAO.comprobarMatricula(alumnoDos.getMatricula());
            boolean resultExito = AlumnoDAO.comprobarMatricula(alumnoUno.getMatricula());
            assertEquals(expResultFallo, resultFallo);
            assertEquals(expResultExito, resultExito);
        } catch (Exception ex) {
            fail("Ocurrió un error en la base de datos");
        }
        
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
    
    /**
     * Test of recuperarLista method, of class AlumnoDAO.
     */
    @Test
    public void testRecuperarLista() throws Exception {
        System.out.println("Test del método recuperarLista()");
        Reservacion reservacion = new Reservacion();
        reservacion.setFecha(Date.valueOf("2018-03-02"));
        reservacion.setNoActividad(1);
        
        String expMatricula = "S15011601";
        String expNombre = "Ricardo";
        String expApellidoP = "Domínguez";
        String expApellidoM = "González";
        
        List<Alumno> resultLista = AlumnoDAO.recuperarLista(reservacion);
        
        assertEquals(expMatricula, resultLista.get(0).getMatricula());
        System.out.println("Éxito de igualdad de matriculas.");
        assertEquals(expNombre, resultLista.get(0).getNombre());
        System.out.println("Éxito de igualdad de Nombres.");
        assertEquals(expApellidoP, resultLista.get(0).getApPaterno());
        System.out.println("Éxito de igualdad de apellidos paternos.");
        assertEquals(expApellidoM, resultLista.get(0).getApMaterno());
        System.out.println("Éxito de igualdad de apellidos maternos.");
        
        
    }
    
}
