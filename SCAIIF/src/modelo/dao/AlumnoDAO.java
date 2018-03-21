package modelo.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Alumno;
import servicios.pojos.Reservacion;

/**
 *
 * @author lalo
 */
public class AlumnoDAO {

    /**
     * Concecta con la base de datos para comprobar que la matrícula que se intenta utilizar no esté
     * ocupara previamente
     *
     * @param matricula Es la matrícula que se desea comprobar
     * @return Devuelve true en caso de que la matrícula esté disponible y false si ya ha sido
     * utilizada previamente.
     * @throws java.lang.Exception
     */
    public static boolean comprobarMatricula(String matricula) throws Exception{
        boolean resultado = false;
        SqlSession conn = null;
        List<Alumno> listaAlumnos = new ArrayList<>();

        try {
            conn = MyBatisUtils.getSession();
            listaAlumnos = conn.selectList("Alumno.getAlumnos", matricula);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        if (listaAlumnos.isEmpty()) {
            resultado = true;
        }

        return resultado;
    }

    /**
     * Recupera la información de los campos de texto para crear un objeto Alumno y posteriormente
     * registrarlo en la base de datos
     *
     * @param alumno Objeto alumno con los datos necesarios
     * @return Devuelve la variable resultado que puede ser verdadera en caso de que todo funcione
     * correctamente o falso en caso de que ocurra algún error y no se registre al alumno.
     * @throws java.lang.Exception
     */
    public static boolean agregarAlumno (Alumno alumno) throws Exception{
        if (alumno == null){
            return false;
        }
        
        boolean resultado = false;
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Alumno.agregarAlumno", alumno);
            conn.commit();
            resultado = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }

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

    /**
     * Recupera la lista de alumnos que reservaron en una fecha especifica y un
     * noActividad especifico.
     * @param reservacion fecha y noActividad que reservaron.
     * @return lista de nombre de alumno.
     * @throws IOException 
     */
    public static List<Alumno> recuperarLista(Reservacion reservacion) throws IOException {
        List<Alumno> reservAlumnos = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            reservAlumnos = conn.selectList("Alumno.getReservacionAlumnos", reservacion);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return reservAlumnos;
    }
}
