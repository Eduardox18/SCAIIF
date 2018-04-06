package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Modulo;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author esmeralda
 */
public class ModuloDAO {

    /**
     * Recupera todos los modulos registrados en la base de datos.
     *
     * @param matricula matricula del alumno.
     * @return lista de modulos.
     * @throws Exception
     */
    public static List<Modulo> recuperarModulos(String matricula) throws Exception {
        List<Modulo> modulos = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            modulos = conn.selectList("Modulo.getModulo", matricula);

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return modulos;
    }

}
