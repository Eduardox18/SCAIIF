package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Alumno;
import servicios.pojos.Observacion;

/**
 *
 * @author lalo
 */
public class ObservacionDAO {

    public static List<Alumno> recuperarAlumnos(String matricula) throws Exception {
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

    public static boolean guardarObservacion(Observacion observacion) throws Exception {
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Observacion.agregarObservacion", observacion);
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
