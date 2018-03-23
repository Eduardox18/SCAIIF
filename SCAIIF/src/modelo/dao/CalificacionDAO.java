package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Calificacion;

/**
 *
 * @author esmeralda
 */
public class CalificacionDAO {

    /**
     * Registra la calificación de un estudiante.
     * @param calificacion calificacion obtenida.
     * @return verdadero si se registró, falso sino.
     * @throws Exception 
     */
    public static boolean registrarCalificacion(Calificacion calificacion) throws Exception {
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Calificación.actualizarCalificacion", calificacion);
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
