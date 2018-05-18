package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.CalificacionModulo;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author esmeralda
 */
public class CalificacionModuloDAO {

    /**
     * Registra la calificación de un estudiante.
     * @param calificacion calificacion obtenida.
     * @return verdadero si se registró, falso sino.
     * @throws Exception 
     */
    public static boolean registrarCalificacion(CalificacionModulo calificacion) throws Exception {
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("CalificacionModulo.actualizarCalificacion", calificacion);
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
