package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Mes;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MesDAO {

    /**
     * Regresa los meses ya hechos
     * @param idCalendario
     * @return
     * @throws Exception
     */
    public static List<Mes> consultarMeses (int idCalendario) throws Exception {
        List<Mes> resumenes;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            resumenes = conn.selectList("Mes.mesesCalendarioHechos", idCalendario);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resumenes;
    }
}
