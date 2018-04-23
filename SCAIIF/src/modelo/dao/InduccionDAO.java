package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Induccion;

/**
 *
 * @author lalo
 */
public class InduccionDAO {

    /**
     * Registra una nueva inducción en la base de datos
     *
     * @param induccion
     * @return
     * @throws Exception
     */
    public static boolean registrarInduccion(Induccion induccion) throws Exception {
        SqlSession conn = null;
        boolean exito = false;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Induccion.registrarInduccion", induccion);
            conn.commit();
            exito = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return exito;
    }

    /**
     * Comprueba si un alumno ya tiene una inducción registrada en un curso.
     *
     * @param induccion
     * @return
     * @throws Exception
     */
    public static boolean comprobarInduccion(Induccion induccion) throws Exception {
        Induccion induccionComprobar;
        SqlSession conn = null;
        boolean existe = false;

        try {
            conn = MyBatisUtils.getSession();
            induccionComprobar = conn.selectOne("Induccion.getInduccion", induccion);
            if (induccionComprobar != null) {
                existe = true;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return existe;
    }

    /**
     * Consulta la información de inducción del alumno ingresado por el asesor.
     *
     * @param matricula identificador del alumno.
     * @return toda la información del alumno.
     * @throws Exception
     */
    public static List<Induccion> recuperarAlumnos(String matricula) throws Exception {
        List<Induccion> infoAlumno = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            infoAlumno = conn.selectList("Induccion.getInfo", matricula);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return infoAlumno;
    }
}
