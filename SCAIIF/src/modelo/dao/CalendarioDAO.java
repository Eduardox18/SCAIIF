package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Calendario;
import org.apache.ibatis.session.SqlSession;

public class CalendarioDAO {

    /**
     * Recupera un calendario de un curso por su NRC
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
}
