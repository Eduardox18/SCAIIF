package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Observacion;

/**
 *
 * @author lalo
 */
public class ObservacionDAO {

    /**
     * Método para guardar una nueva observación en la base de datos
     * @param observacion Objeto de tipo observación donde se encuentran todos los valores
     * @return True en caso de que todo salga correcto, False en caso de que haya errores
     * @throws Exception En caso de que exista una excepción el error se propaga a la capa superior
     */
    public static boolean guardarObservacion(Observacion observacion) throws Exception {
        if (observacion == null) {
            return false;
        }
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Observacion.agregarObservacion", observacion);
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
