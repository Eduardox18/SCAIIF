package modelo.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.ListaAsistencia;
import servicios.pojos.Reservacion;

/**
 *
 * @author lalo
 */
public class ReservacionDAO {

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
}
