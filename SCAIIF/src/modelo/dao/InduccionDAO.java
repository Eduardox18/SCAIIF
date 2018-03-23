package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Induccion;

/**
 *
 * @author lalo
 */
public class InduccionDAO {

    public static List<Induccion> recuperarHistorialAsesores(int noPersonal) throws Exception {
        SqlSession conn = null;
        List<Induccion> historialAsesores = new ArrayList<>();
        try {
            conn = MyBatisUtils.getSession();
            historialAsesores = conn.selectList("Induccion.getHistorial", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return historialAsesores;
    }
}
