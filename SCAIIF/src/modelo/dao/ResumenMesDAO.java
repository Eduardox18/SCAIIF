package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.ResumenMes;
import org.apache.ibatis.session.SqlSession;


public class ResumenMesDAO {

    /**
     * Registra un nuevo resumen de un mes de un calendario
     * @param resumen Objeto resumen
     * @return Éxito o fracaso de la operación
     * @throws Exception
     */
    public static boolean registrarResumenMes(ResumenMes resumen) throws Exception {
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("ResumenMes.registrarResumen", resumen);
            conn.commit();
            resultado = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
}
