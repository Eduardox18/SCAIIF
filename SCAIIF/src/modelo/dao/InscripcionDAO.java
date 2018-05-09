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
    
    /**
     * Da de baja un alumno del curso al que está inscrito.
     *
     * @param inscripcion objeto que provee la matricula y nrc del alumno y curso a dar de baja
     * @throws java.lang.Exception
     */
    public static boolean bajaInscripcion(Inscripcion inscripcion) throws Exception {
        if (inscripcion == null) {
            return false;
        }

        boolean resultado = false;
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Inscripcion.eliminarCurso", inscripcion);
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
