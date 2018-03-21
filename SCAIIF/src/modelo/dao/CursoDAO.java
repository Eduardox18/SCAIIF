package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Curso;

/**
 *
 * @author esmeralda
 */
public class CursoDAO {

    /**
     * Recupera todos los cursos a los que este inscrito el alumno según la matrícula ingresada.
     *
     * @param matricula identificador del estudiante.
     * @return lista de cursos.
     * @throws Exception
     */
    public static List<Curso> recuperarCursos(String matricula) throws Exception {
        List<Curso> cursos = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            cursos = conn.selectList("Curso.getCursos", matricula);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return cursos;
    }

}
