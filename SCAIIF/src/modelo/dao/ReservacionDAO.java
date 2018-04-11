package modelo.dao;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.ListaAsistencia;
import modelo.pojos.Reservacion;

/**
 *
 * @author lalo
 */
public class ReservacionDAO {

    /**
     * Método que permite recuperar los alumnos que reservaron para una actividad específica
     * @param noActividad Número de actividad a consultar
     * @return Regresa la lista de alumnos que resevaron
     * @throws Exception 
     */
    public static List<ListaAsistencia> recuperarAlumnosDeActividad(int noActividad)
        throws Exception {
        List<ListaAsistencia> listaAlumnosReservacion = new ArrayList<>();
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            listaAlumnosReservacion = conn.selectList("Reservacion.alumnosDeActividad",
                noActividad);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return listaAlumnosReservacion;
    }

    /**
     * Recupera las fechas de las reservaciones registradas en la base de datos.
     *
     * @return lista de fechas.
     * @throws IOException
     */
    public static List<Reservacion> recuperarFechas() throws IOException {
        List<Reservacion> fechas = new ArrayList();
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            fechas = conn.selectList("Reservacion.getFechas");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return fechas;
    }

    /**
     * Registra la asistencia de los alumnos seleccionados por el Asesor a la reservacion
     * o actividad impartidas.
     * @param reservacion objeto con la asistencia del alumno a una reservacion en especifico.
     * @return true si se registró, false sino. 
     * @throws IOException 
     */
    public static boolean registrarAsistencia(Reservacion reservacion) throws IOException {
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.update("Reservacion.actualizarAsistencia", reservacion);
            conn.commit();
            resultado = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
    
    public static boolean registrarReservación(String matricula, Integer noActividad) throws Exception {
        boolean resultado = false;
        Date fCreacion = Date.valueOf(LocalDate.now());
        HashMap<String, Object> params = new HashMap<>();
        params.put("matricula", matricula);
        params.put("noActividad", noActividad);
        params.put("fecha", fCreacion);
        
        try(SqlSession conn = MyBatisUtils.getSession()) {
            conn.insert("Reservacion.registrarReservacion", params);
            conn.commit();
            resultado = true;
        }
        return resultado;
    }
}
