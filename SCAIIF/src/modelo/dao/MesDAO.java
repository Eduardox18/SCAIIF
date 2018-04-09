package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Mes;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MesDAO {

    /**
     * Regresa los meses ya hechos en un calendario
     * @param idCalendario
     * @return
     * @throws Exception
     */
    public static List<Mes> consultarMeses (int idCalendario) throws Exception {
        List<Mes> meses;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            meses = conn.selectList("Mes.mesesCalendarioHechos", idCalendario);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return meses;
    }

    /**
     * Recupera los meses registrados que no tengan información guardada
     * @return Lista de meses
     * @throws Exception
     */
    public static List<Mes> recuperarMesesPendientes(int idCalendario) throws Exception {
        List<Mes> meses;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            meses = conn.selectList("Mes.mesesPendientes", idCalendario);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return meses;
    }
}
