/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.pojos.ListaAsistencia;
import modelo.pojos.Reservacion;

/**
 *
 * @author lalo
 */
public class ReservacionDAOTest {

    private Reservacion reservacion;

    public ReservacionDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        reservacion = new Reservacion();
        reservacion.setMatricula("");
        reservacion.setFecha(Date.valueOf("2018-03-02"));
        reservacion.setAsistencia(true);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of recuperarAlumnosDeActividad method, of class ReservacionDAO.
     */
    @Test
    public void testRecuperarAlumnosDeActividad() throws Exception {
        System.out.println("Test del método recuperarAlumnosDeActividad()");
        Integer noActividad = 2;
        String expNombre = "Domínguez González Ricardo";
        List<ListaAsistencia> result = ReservacionDAO.recuperarAlumnosDeActividad(noActividad);

        assertEquals(expNombre, result.get(0).getNombre());
        System.out.println("Éxito de igualdad de nombre del alumno");
        assertEquals(noActividad, result.get(0).getNoActividad());
        System.out.println("Éxito de igualdad de número de actividad");
        assertThat(result.get(0), instanceOf(ListaAsistencia.class));
        System.out.println("Éxito de igualdad de clase retornada");
        assertEquals(result, result);
    }

    /**
     * Test of recuperarFechas method, of class ReservacionDAO.
     */
    @Test
    public void testRecuperarFechas() throws Exception {
        System.out.println("Test del método recuperarFechas()");
        Date expFecha = Date.valueOf("2018-03-02");
        List<Reservacion> result = ReservacionDAO.recuperarFechas();

        assertEquals(expFecha, result.get(0).getFecha());
        System.out.println("Éxito de igualdad de fechas.");
    }

    /**
     * Test of registrarAsistencia method, of class ReservacionDAO.
     */
    @Test
    public void testRegistrarAsistencia() throws Exception {
        System.out.println("Test del método registrarAsistencia()");
        Reservacion asistencia = null;
        boolean expResult = true;
        boolean resultado = ReservacionDAO.registrarAsistencia(asistencia);
        assertEquals(expResult, resultado);
    }

    /**
     * Test of registrarReservación method, of class ReservacionDAO.
     */
    @Test
    public void testRegistrarReservación() throws Exception {
        System.out.println("Prueba del método registrarReservaci\u00f3n");
        String matricula = "S15011613";
        Integer noActividad = 2;
        boolean expResult = true;
        boolean result = ReservacionDAO.registrarReservacion(matricula, noActividad);
        assertEquals(expResult, result);
    }

}
