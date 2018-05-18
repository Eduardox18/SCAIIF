package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.DiaFestivo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DiaFestivoDAO {

    /**
     * Recupera los días festivos de un calendario de curso con base al NRC del curso
     * @param idPeriodo NRC del curso consultado
     * @return
     * @throws Exception
     */
    public static List<DiaFestivo> consultarDiasFestivosPeriodo (Integer idPeriodo) throws Exception {
        List<DiaFestivo> diasFestivos;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            diasFestivos = conn.selectList("DiaFestivo.getDiasFestivosbyPeriodo", idPeriodo);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return diasFestivos;
    }
}
