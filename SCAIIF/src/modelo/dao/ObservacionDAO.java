package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Alumno;

/**
 *
 * @author lalo
 */
public class ObservacionDAO {

    public List<Alumno> recuperarAlumnos(String matricula) throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            alumnos = conn.selectList("Alumno.getAlumnos", matricula);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return alumnos;
    }
}
