package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Seccion;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 * @author lalo
 */
public class SeccionDAO {

    /**
     * Recupera las secciones registradas
     * @return Lista de secciones
     * @throws Exception
     */
    public static List<Seccion> recuperarSecciones() throws Exception {
        List<Seccion> secciones;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            secciones = conn.selectList("Seccion.getSecciones");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return secciones;
    }
}
