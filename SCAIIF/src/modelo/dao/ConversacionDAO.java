package modelo.dao;

import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Conversacion;

/**
 *
 * @author esmeralda
 */
public class ConversacionDAO {

    /**
     * Recupera todas las conversaciones registradas en la base de datos.
     *
     * @return lista de conversaciones.
     * @throws Exception
     */
    public static List<Conversacion> recuperarConversaciones() throws Exception {
        List<Conversacion> conversaciones;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conversaciones = conn.selectList("Conversacion.getConversaciones");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return conversaciones;
    }
}
