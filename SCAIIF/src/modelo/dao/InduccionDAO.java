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

    /**
     * Recupera el historial de un asesor
     * @param noPersonal
     * @return
     * @throws Exception
     */
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

    /**
     * Registra una nueva inducción en la base de datos
     * @param induccion
     * @return
     * @throws Exception
     */
    public static boolean registrarInduccion(Induccion induccion) throws Exception {
        SqlSession conn = null;
        boolean exito = false;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Induccion.registrarInduccion", induccion);
            conn.commit();
            exito = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return exito;
    }

    /**
     * Comprueba si un alumno ya tiene una inducción registrada en un curso.
     * @param induccion
     * @return
     * @throws Exception
     */
    public static boolean comprobarInduccion(Induccion induccion) throws Exception {
        Induccion induccionComprobar;
        SqlSession conn = null;
        boolean existe = false;

        try {
            conn = MyBatisUtils.getSession();
            induccionComprobar = conn.selectOne("Induccion.getInduccion", induccion);
            if (induccionComprobar != null) {
                existe = true;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return existe;
    }
}
