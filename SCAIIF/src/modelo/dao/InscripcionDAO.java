package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.CalificacionModulo;
import modelo.pojos.Inscripcion;
import org.apache.ibatis.session.SqlSession;

public class InscripcionDAO {

    public static Inscripcion recuperarCalFinal(CalificacionModulo insc) throws Exception {
        Inscripcion calificacion = null;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            calificacion = conn.selectOne("Inscripcion.recuperarCalificacionFinal", insc);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return calificacion;
    }
}
