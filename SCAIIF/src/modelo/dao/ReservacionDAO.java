package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.ListaAsistencia;

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
}
