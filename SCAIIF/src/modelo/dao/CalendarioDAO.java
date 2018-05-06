package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Calendario;
import modelo.pojos.ConsultaCalendario;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CalendarioDAO {

    /**
     * Recupera un calendario de un curso por su NRC
     *
     * @param nrc NRC del curso
     * @return Calendario del curso
     * @throws Exception
     */
    public static Calendario recuperarCalendario(int nrc) throws Exception {
        Calendario calendario = new Calendario();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            calendario = conn.selectOne("Calendario.getCalendarioByNRC", nrc);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return calendario;
    }

    /**
     * Actualiza las fechas de un calendario de curso
     * @param calendario Objeto calendario con la información a actualizar
     * @return éxito de la operación (true o false)
     * @throws Exception
     */
    public static boolean actualizarCalendario(Calendario calendario) throws Exception {
        SqlSession conn = null;
        boolean resultado = false;
        try {
            conn = MyBatisUtils.getSession();
            conn.update("Calendario.actualizarCalendario", calendario);
            conn.commit();
            resultado = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }

    /**
     * Recupera la información de un calendario de curso
     * @param nrc Identificador del curso
     * @return
     * @throws Exception
     */
    public static List<ConsultaCalendario> consultarCalendarioCurso(Integer nrc) throws Exception {
        List<ConsultaCalendario> informacionCalendario;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            informacionCalendario = conn.selectList("Calendario.consultarCalendarioCurso", nrc);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return informacionCalendario;
    }
}
