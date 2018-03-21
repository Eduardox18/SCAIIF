package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Modulo;

/**
 *
 * @author esmeralda
 */
public class ModuloDAO {

    /**
     * Recupera todos los modulos registrados en la base de datos.
     * @return lista de modulos.
     * @throws Exception 
     */
    public static List<Modulo> recuperarModulos() throws Exception {
        List<Modulo> modulos = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            modulos = conn.selectList("Modulo.getModulo");

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return modulos;
    }

}
