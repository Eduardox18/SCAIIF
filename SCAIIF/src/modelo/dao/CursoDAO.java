package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Curso;

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

    /**
     * Recupera los cursos de un periodo
     * @param idPeriodo Identificador del periodo
     * @return lista de cursos
     * @throws Exception
     */
    public static List<Curso> recuperarCursosDePeriodo(Integer idPeriodo) throws Exception {
        List<Curso> cursos;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            cursos = conn.selectList("Curso.getCursosbyPeriodo", idPeriodo);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return cursos;
    }
    
    public static List<Curso> recuperarCursosAlumno (String matricula) throws Exception {
        List<Curso> cursosAlumno;
        
        try(SqlSession conn = MyBatisUtils.getSession()){
            cursosAlumno = conn.selectList("Curso.recuperarCursosAlumno", matricula);
        }
        return cursosAlumno;
    }

    public static List<Curso> getCursosNoInscritos (Integer idPeriodo, String matricula) throws Exception{
        List<Curso> cursos;
        HashMap<String, Object> params = new HashMap<>();
        params.put("idPeriodo", idPeriodo);
        params.put("matricula", matricula);
        
        try(SqlSession conn = MyBatisUtils.getSession()){
            cursos = conn.selectList("Curso.getCursosNoInscritos", params);
        }
        return cursos;
    }
}
