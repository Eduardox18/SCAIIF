package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Periodo;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class PeriodoDAO {

    /**
     * Recupera todos los periodos registrados
     * @return
     */
    public static List<Periodo> recuperarPeriodos() throws IOException {
        SqlSession conn = null;
        List<Periodo> periodos;
        try {
            conn = MyBatisUtils.getSession();
            periodos = conn.selectList("Periodo.getPeriodos");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return periodos;
    }
}
