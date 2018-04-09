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
     * Recupera los módulos que no tienen calificacion de un alumno
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

    /**
     * Recupera todos los módulos registrados
     * @return Lista de módulos
     * @throws Exception
     */
    public static List<Modulo> recuperarTodosModulos() throws Exception {
        List<Modulo> modulos;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            modulos = conn.selectList("Modulo.getModulos");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return modulos;
    }

}
