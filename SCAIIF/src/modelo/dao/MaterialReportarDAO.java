package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.MaterialReportar;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class MaterialReportarDAO {

    /**
     * Recupera todos los materiales registrados
     * @return Lista de materiales
     * @throws Exception
     */
    public static List<MaterialReportar> recuperarMateriales() throws Exception {
        List<MaterialReportar> materiales;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            materiales = conn.selectList("MaterialReportar.getMateriales");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return materiales;
    }
    
}
